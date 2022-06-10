package dev.mattrm.mc.modularmachines.common.container;

import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.blockentity.ControllerSynchedData;
import dev.mattrm.mc.modularmachines.common.blockentity.MachineControllerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class ControllerMenu extends SynchedDataContainerMenu<ControllerSynchedData> {
    public ControllerMenu(int id, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.CONTROLLER.get(), id, pos, playerInventory, player, (blockEntity) -> ((MachineControllerBlockEntity) blockEntity).getControllerData());
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(this.blockEntity.getLevel(), this.blockEntity.getBlockPos()), this.player, ModBlocks.MACHINE_CONTROLLER.get());
    }
}
