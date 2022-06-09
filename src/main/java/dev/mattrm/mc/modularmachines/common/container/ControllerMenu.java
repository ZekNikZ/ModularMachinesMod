package dev.mattrm.mc.modularmachines.common.container;

import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ControllerMenu extends DataContainerMenu<ControllerMenu.ControllerContainerData> {
    static class ControllerContainerData {
        int test = 0;
    }

    private final BlockEntity blockEntity;
    private final Player playerEntity;
    private final IItemHandler playerInventory;

    public ControllerMenu(int id, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.CONTROLLER.get(), id, pos, playerInventory, player, (_pos, _player) -> new ControllerContainerData());
        this.blockEntity = player.getCommandSenderWorld().getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(this.blockEntity.getLevel(), this.blockEntity.getBlockPos()), this.playerEntity, ModBlocks.MACHINE_CONTROLLER.get());
    }
}
