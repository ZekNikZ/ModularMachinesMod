package dev.mattrm.mc.modularmachines.common.container;

import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ContainerSynchronizer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ControllerContainer extends DataContainerMenu<ControllerContainer.ControllerContainerData> {
    static class ControllerContainerData {
        int test = 0;
    }

    private BlockEntity blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;

    public ControllerContainer(int id, BlockPos pos, Inventory playerInv, Player player) {
        super(ModContainers.CONTROLLER.get(), id, pos, playerInv, player, (_pos, _player) -> new ControllerContainerData());
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(this.blockEntity.getLevel(), this.blockEntity.getBlockPos()), this.playerEntity, ModBlocks.MACHINE_CONTROLLER.get());
    }


}
