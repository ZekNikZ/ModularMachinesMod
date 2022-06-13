package dev.mattrm.mc.modularmachines.common.block.controller;

import dev.mattrm.mc.modularmachines.client.gui.ModGuiTranslation;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.block.base.BaseMachineControllerBlock;
import dev.mattrm.mc.modularmachines.common.blockentity.MachineControllerBlockEntity;
import dev.mattrm.mc.modularmachines.common.container.ControllerMenu;
import dev.mattrm.mc.modularmachines.common.tag.ModTags;
import dev.mattrm.mc.modularmachines.common.util.MachinePartPosition;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MachineControllerBlock extends BaseMachineControllerBlock implements EntityBlock {
    public MachineControllerBlock(Properties properties, boolean connected) {
        super(properties, connected);
    }

    @Override
    public void connectToMachine(Level level, BlockPos pos, BlockPos controllerPos, MachinePartPosition machinePartPosition) {
        this.connectionHelper(level, pos, machinePartPosition, ModBlocks.MACHINE_CONTROLLER_CONNECTED.get());
    }

    @Override
    public void disconnectFromMachine(Level level, BlockPos pos, BlockPos controllerPos) {
        this.disconnectionHelper(level, pos, ModBlocks.MACHINE_CONTROLLER.get());
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(blockPos);
            if (be instanceof MachineControllerBlockEntity) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return ModGuiTranslation.CONTROLLER_GUI.component();
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        return new ControllerMenu(windowId, blockPos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
//        if (level.isClientSide()) {
//            ControllerScreen.safeOpen();
//        }
        return InteractionResult.SUCCESS;

//        if (level.isClientSide()) return InteractionResult.SUCCESS;
//
//        // Get block entity
//        MachineControllerBlockEntity be = (MachineControllerBlockEntity) level.getBlockEntity(blockPos);
//        if (be == null) {
//            return InteractionResult.PASS;
//        }
//
//        // Setup/teardown the machine
//        boolean success;
//        if (!this.connected) {
//            player.sendMessage(new TextComponent("Creating machine..."), Util.NIL_UUID);
//            success = be.createMachine();
//        } else {
//            player.sendMessage(new TextComponent("Tearing down machine..."), Util.NIL_UUID);
//            success = be.teardownMachine();
//        }
//
//        if (success) {
//            player.sendMessage(new TextComponent("Success!"), Util.NIL_UUID);
//        } else {
//            player.sendMessage(new TextComponent("Error: " + be.getErrorMessage()), Util.NIL_UUID);
//        }
//
//        return InteractionResult.CONSUME;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState blockState, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!blockState.is(newState.getBlock())) {
            // If we are transitioning "connected" states, don't do anything
            if (newState.getBlock() instanceof MachineControllerBlock) {
                return;
            }

            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MachineControllerBlockEntity) {
                ((MachineControllerBlockEntity) be).teardownMachine();
            }
            super.onRemove(blockState, level, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState blockState) {
        return new MachineControllerBlockEntity(pos, blockState);
    }

    @Override
    public String dataLanguageKey(String locale) {
        return "Machine Controller" + (this.connected ? " (Connected)" : "");
    }

    @Override
    public @NotNull List<TagKey<Block>> dataTags() {
        List<TagKey<Block>> tags = new ArrayList<>();
        tags.add(ModTags.Blocks.MACHINE_CONTROLLERS);
        if (!this.connected) {
            tags.add(ModTags.Blocks.METAL_MACHINE_PARTS);
        }
        return tags;
    }
}
