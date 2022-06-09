package dev.mattrm.mc.modularmachines.common.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class DataContainerMenu<T> extends AbstractContainerMenu {
    protected final int id;
    protected final BlockPos pos;
    protected final Inventory playerInv;
    protected final Player player;
    protected final BlockEntity blockEntity;
    protected final IItemHandler playerInventory;

    protected final T data;

    protected DataContainerMenu(@Nullable MenuType<?> pMenuType, int id, BlockPos pos, Inventory playerInv, Player player, BiFunction<BlockPos, Player, T> initialDataSupplier) {
        super(pMenuType, id);
        this.id = id;
        this.pos = pos;
        this.playerInv = playerInv;
        this.player = player;

        this.blockEntity = player.getCommandSenderWorld().getBlockEntity(pos);
        this.playerInventory = new InvWrapper(playerInv);
        this.data = initialDataSupplier.apply(this.pos, this.player);
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
    }
}
