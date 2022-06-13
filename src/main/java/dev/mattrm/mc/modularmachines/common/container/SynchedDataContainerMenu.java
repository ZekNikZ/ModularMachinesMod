package dev.mattrm.mc.modularmachines.common.container;

import dev.mattrm.mc.modularmachines.common.blockentity.SynchedDataBlockEntity;
import dev.mattrm.mc.modularmachines.common.network.sync.SyncAction;
import dev.mattrm.mc.modularmachines.common.network.sync.SynchedData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public abstract class SynchedDataContainerMenu<T extends SynchedData> extends AbstractContainerMenu {
    protected final int id;
    protected final BlockPos pos;
    protected final Inventory playerInv;
    public final Player player;
    public final SynchedDataBlockEntity blockEntity;
    public final IItemHandler playerInventory;

    public final T data;

    protected SynchedDataContainerMenu(@Nullable MenuType<?> pMenuType, int id, BlockPos pos, Inventory playerInv, Player player, Function<BlockEntity, T> initialDataSupplier) {
        super(pMenuType, id);
        this.id = id;
        this.pos = pos;
        this.playerInv = playerInv;
        this.player = player;

        this.blockEntity = (SynchedDataBlockEntity) player.getCommandSenderWorld().getBlockEntity(pos);
        this.playerInventory = new InvWrapper(playerInv);

        this.data = initialDataSupplier.apply(this.blockEntity);
    }

    public void applyAction(SyncAction<T> action) {
        SynchedData.applyAction(this.data, action);
    }

    public void applyAction(Function<SynchedDataBlockEntity, ? extends SyncAction<T>> action) {
        SynchedData.applyAction(this.data, action.apply(this.blockEntity));
    }
}
