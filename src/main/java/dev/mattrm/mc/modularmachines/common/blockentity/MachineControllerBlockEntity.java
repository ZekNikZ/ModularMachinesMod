package dev.mattrm.mc.modularmachines.common.blockentity;

import dev.mattrm.mc.modularmachines.api.block.IMachineCore;
import dev.mattrm.mc.modularmachines.api.block.IMachinePart;
import dev.mattrm.mc.modularmachines.api.block.IMachineWall;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
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
        this.setChanged();
    }

    protected void setCorner1(@NotNull BlockPos corner1) {
        Objects.requireNonNull(corner1);

        if (this.corner1.equals(corner1)) return;

        this.corner1 = corner1;
        this.setChanged();
    }

    protected void setCorner2(@NotNull BlockPos corner2) {
        Objects.requireNonNull(corner2);

        if (this.corner2.equals(corner2)) return;

        this.corner2 = corner2;
        this.setChanged();
    }

    protected void setCorners(@NotNull BlockPos corner1, @NotNull BlockPos corner2) {
        this.setCorner1(corner1);
        this.setCorner2(corner2);
    }

    /**
     * Tears down a multiblock machine.
     *
     * @return whether teardown was successful
     */
    public boolean teardownMachine() {
        // Ensure we are already connected
        if (!this.connected) return false;

        // Mark as disconnected
        this.setConnected(false);

        // Update the block state of the multiblock
        this.updateMultiblock();

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
        if (this.connected) return false;

        // Find the corners of the multiblock
        this.findMultiblock();

        // Mark machine as connected
        this.setConnected(true);

        // Update the block state of the multiblock
        this.updateMultiblock();

        return true;
    }

    private void findMultiblock() {
        // TODO: properly implement

        Level level = this.getLevel();
        if (level == null) return;

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

        // Store new corners
        BlockPos min = new BlockPos(minX, minY, minZ);
        BlockPos max = new BlockPos(maxX, maxY, maxZ);
        this.setCorners(min, max);
    }

    private static boolean isValidWallBlock(Level level, BlockPos pos) {
        // TODO: properly implement
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        return blockState.is(ModTags.Blocks.MACHINE_WALLS) && block instanceof IMachineWall && !((IMachineWall) block).isConnected(level, pos);
    }

    private static boolean isValidCoreBlock(Level level, BlockPos pos) {
        // TODO: properly implement
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        return blockState.is(ModTags.Blocks.MACHINE_WALLS) && block instanceof IMachineCore && !((IMachineCore) block).isConnected(level, pos);
    }

    private void updateMultiblock() {
        Level level = this.getLevel();
        if (level == null) return;

        for (int x = this.corner1.getX(); x <= this.corner2.getX(); x++) {
            for (int y = this.corner1.getY(); y <= this.corner2.getY(); y++) {
                for (int z = this.corner1.getZ(); z <= this.corner2.getZ(); z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState blockState = level.getBlockState(pos);
                    if (blockState.getBlock() instanceof IMachinePart) {
                        ((IMachinePart) blockState.getBlock()).setConnected(level, pos, this.connected);
                    }
                }
            }
        }
    }
}
