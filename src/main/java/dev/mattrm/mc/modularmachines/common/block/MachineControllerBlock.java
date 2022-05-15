package dev.mattrm.mc.modularmachines.common.block;

import dev.mattrm.mc.modularmachines.common.block.multiblock.MultiblockControllerBlock;
import dev.mattrm.mc.modularmachines.common.blockentity.MachineControllerBlockEntity;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MachineControllerBlock extends MultiblockControllerBlock {
    public MachineControllerBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;

        player.sendMessage(new TextComponent("Right-clicked!"), Util.NIL_UUID);

        // Get block entity
        MachineControllerBlockEntity be = (MachineControllerBlockEntity) level.getBlockEntity(blockPos);
        if (be == null) {
            return InteractionResult.PASS;
        }

        // Setup/teardown the machine
        boolean success;
        if (!be.isConnected()) {
            success = be.createMachine();
        } else {
            success = be.teardownMachine();
        }
        player.sendMessage(new TextComponent("Status: " + success), Util.NIL_UUID);

        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState blockState) {
        return new MachineControllerBlockEntity(pos, blockState);
    }
}
