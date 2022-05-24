package dev.mattrm.mc.modularmachines.common.blockentity;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.api.block.IMachineController;
import dev.mattrm.mc.modularmachines.api.block.IMachineCore;
import dev.mattrm.mc.modularmachines.api.block.IMachinePart;
import dev.mattrm.mc.modularmachines.api.block.IMachineWall;
import dev.mattrm.mc.modularmachines.common.tag.ModTags;
import dev.mattrm.mc.modularmachines.common.util.Cuboid;
import dev.mattrm.mc.modularmachines.common.util.MachinePartPosition;
import dev.mattrm.mc.modularmachines.common.setup.MachineConstructionConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MachineControllerBlockEntity extends BlockEntity {
    private boolean connected = false;
    private BlockPos corner1 = BlockPos.ZERO;
    private BlockPos corner2 = BlockPos.ZERO;
    private Cuboid bounds;
    private String errorMessage = null;

    public MachineControllerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.MACHINE_CONTROLLER.get(), blockPos, blockState);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putBoolean("connected", this.connected);
        nbt.put("corner1", NbtUtils.writeBlockPos(corner1));
        nbt.put("corner2", NbtUtils.writeBlockPos(corner2));
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.connected = nbt.getBoolean("connected");
        this.corner1 = NbtUtils.readBlockPos(nbt.getCompound("corner1"));
        this.corner2 = NbtUtils.readBlockPos(nbt.getCompound("corner2"));
    }

    public boolean isConnected() {
        return this.connected;
    }

    public BlockPos getCorner1() {
        return this.corner1;
    }

    public BlockPos getCorner2() {
        return this.corner2;
    }

    protected void setConnected(boolean connected) {
        if (this.connected == connected) return;

        this.connected = connected;
        this.updateBounds();
        this.setChanged();
    }

    protected void setCorner1(@NotNull BlockPos corner1) {
        Objects.requireNonNull(corner1);

        if (this.corner1.equals(corner1)) return;

        this.corner1 = corner1;
        this.updateBounds();
        this.setChanged();
    }

    protected void setCorner2(@NotNull BlockPos corner2) {
        Objects.requireNonNull(corner2);

        if (this.corner2.equals(corner2)) return;

        this.corner2 = corner2;
        this.updateBounds();
        this.setChanged();
    }

    protected void setCorners(@NotNull BlockPos corner1, @NotNull BlockPos corner2) {
        this.setCorner1(corner1);
        this.setCorner2(corner2);
    }

    protected void updateBounds() {
        if (this.connected) {
            this.bounds = new Cuboid(this.corner1, this.corner2);
        } else {
            this.bounds = null;
        }
    }

    /**
     * Tears down a multiblock machine.
     *
     * @return whether teardown was successful
     */
    public boolean teardownMachine() {
        // Ensure we are already connected
        if (!this.connected) {
            this.errorMessage = "cannot teardown an already disconnected machine";
            return false;
        }

        // Mark as disconnected
        this.setConnected(false);

        // Update the block state of the multiblock
        this.updateMultiblock(false);

        // Clear corners
        this.setCorners(BlockPos.ZERO, BlockPos.ZERO);

        // Empty contents of buffers
        this.clearBuffers();

        return true;
    }

    private void clearBuffers() {
        // TODO: implement
    }

    /**
     * Sets up a multiblock machine
     *
     * @return whether setup was successful
     */
    public boolean createMachine() {
        // Ensure we are not connected
        if (this.connected) {
            this.errorMessage = "cannot setup an already connected machine";
            return false;
        }

        // Find the corners of the multiblock
        boolean status = this.findMultiblock();
        if (!status) {
            this.errorMessage += " (could not find multiblock structure)";
            return false;
        }

        // Mark machine as connected
        this.setConnected(true);

        // Update the block state of the multiblock
        this.updateMultiblock(true);

        return true;
    }

    private boolean findMultiblock() {
        Level level = this.getLevel();
        if (level == null) {
            this.errorMessage = "level is not available";
            return false;
        }

        // BFS setup
        Set<BlockPos> visited = new HashSet<>();
        Deque<BlockPos> queue = new ArrayDeque<>();

        // Bounding box setup
        int minX, minY, minZ;
        int maxX, maxY, maxZ;

        // Initial position
        visited.add(this.getBlockPos());
        queue.add(this.getBlockPos());
        minX = maxX = this.getBlockPos().getX();
        minY = maxY = this.getBlockPos().getY();
        minZ = maxZ = this.getBlockPos().getZ();

        // BFS
        while (!queue.isEmpty()) {
            BlockPos pos = queue.pop();
            minX = Math.min(minX, pos.getX());
            maxX = Math.max(maxX, pos.getX());
            minY = Math.min(minY, pos.getY());
            maxY = Math.max(maxY, pos.getY());
            minZ = Math.min(minZ, pos.getZ());
            maxZ = Math.max(maxZ, pos.getZ());
            for (Direction direction : Direction.values()) {
                BlockPos neighbor = pos.relative(direction);
                if (isValidWallBlock(level, neighbor) && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // Checks to ensure structure isn't too small
        int xSize = Math.min(maxX - minX + 1, MachineConstructionConfig.MACHINE_MAX_SIZE.get());
        int ySize = Math.min(maxY - minY + 1, MachineConstructionConfig.MACHINE_MAX_SIZE.get());
        int zSize = Math.min(maxZ - minZ + 1, MachineConstructionConfig.MACHINE_MAX_SIZE.get());
        if (xSize < Constants.MIN_STRUCTURE_SIZE
            || ySize < Constants.MIN_STRUCTURE_SIZE
            || zSize < Constants.MIN_STRUCTURE_SIZE
        ) {
            this.errorMessage = "potential structures are all too small";
            return false;
        }

        // Check if structure is too big
        // TODO: this can be smarter and more efficient, but for now, this should be OK
        boolean found = false;
        searchLoop:
        for (int dx = xSize - 1; dx >= Constants.MIN_STRUCTURE_SIZE - 1; --dx) {
            for (int dz = zSize - 1; dz >= Constants.MIN_STRUCTURE_SIZE - 1; --dz) {
                for (int dy = ySize - 1; dy >= Constants.MIN_STRUCTURE_SIZE - 1; --dy) {
                    for (int x = minX; x <= maxX - dx; ++x) {
                        for (int z = minZ; z <= maxZ - dz; ++z) {
                            for (int y = minY; y <= maxY - dy; ++y) {
                                BlockPos corner1 = new BlockPos(x, y, z);
                                BlockPos corner2 = new BlockPos(x + dx, y + dy, z + dz);
                                if (!Cuboid.within(this.getBlockPos(), corner1, corner2)) {
                                    continue;
                                }

                                if (checkStructure(level, corner1, corner2)) {
                                    found = true;
                                    this.setCorners(corner1, corner2);
                                    break searchLoop;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!found) {
            this.errorMessage = "could not find structure";
            return false;
        }

        return true;
    }

    private static boolean checkStructure(Level level, BlockPos corner1, BlockPos corner2) {
        Set<ResourceLocation> controllerTypes = new HashSet<>();
        return BlockPos.betweenClosedStream(corner1, corner2).allMatch(pos -> {
            if (isValidControllerBlock(level, pos)) {
                // Controller
                ResourceLocation block = level.getBlockState(pos).getBlock().getRegistryName();
                if (controllerTypes.contains(block)) {
                    return false;
                }
                controllerTypes.add(block);
            }

            if (pos.getX() == corner1.getX() || pos.getX() == corner2.getX() || pos.getY() == corner1.getY() || pos.getY() == corner2.getY() || pos.getZ() == corner1.getZ() || pos.getZ() == corner2.getZ()) {
                // Wall
                return isValidWallBlock(level, pos);
            } else {
                // Core
                return isValidCoreBlock(level, pos);
            }
        });
    }

    private static boolean isValidControllerBlock(Level level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        return blockState.is(ModTags.Blocks.MACHINE_CONTROLLERS) && block instanceof IMachineController && !((IMachineController) block).isConnected(level, pos);
    }

    private static boolean isValidWallBlock(Level level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        return blockState.is(ModTags.Blocks.MACHINE_WALLS) && block instanceof IMachineWall && !((IMachineWall) block).isConnected(level, pos);
    }

    private static boolean isValidCoreBlock(Level level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        return blockState.isAir() || (blockState.is(ModTags.Blocks.MACHINE_CORES) && block instanceof IMachineCore && !((IMachineCore) block).isConnected(level, pos));
    }

    public void updateMultiblock(boolean toBeConnected) {
        Level level = this.getLevel();
        if (level == null) return;

        for (int x = this.corner1.getX(); x <= this.corner2.getX(); x++) {
            for (int y = this.corner1.getY(); y <= this.corner2.getY(); y++) {
                for (int z = this.corner1.getZ(); z <= this.corner2.getZ(); z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState blockState = level.getBlockState(pos);
                    if (blockState.getBlock() instanceof IMachinePart) {
                        if (toBeConnected) {
                            // Determine machine part location
                            int dx = 0;
                            int dy = 0;
                            int dz = 0;
                            if (pos.getX() == this.corner1.getX()) {
                                dx = -1;
                            } else if (pos.getX() == this.corner2.getX()) {
                                dx = 1;
                            }
                            if (pos.getY() == this.corner1.getY()) {
                                dy = -1;
                            } else if (pos.getY() == this.corner2.getY()) {
                                dy = 1;
                            }
                            if (pos.getZ() == this.corner1.getZ()) {
                                dz = -1;
                            } else if (pos.getZ() == this.corner2.getZ()) {
                                dz = 1;
                            }

                            // Connect to machine
                            ((IMachinePart) blockState.getBlock()).connectToMachine(level, pos, this.getBlockPos(), MachinePartPosition.from(new Vec3i(dx, dy, dz)));
                        } else {
                            ((IMachinePart) blockState.getBlock()).disconnectFromMachine(level, pos, this.getBlockPos());
                        }
                    }
                }
            }
        }
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
