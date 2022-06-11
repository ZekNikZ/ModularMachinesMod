package dev.mattrm.mc.modularmachines.common.network.sync;

import dev.mattrm.mc.modularmachines.client.ClientHelpers;
import dev.mattrm.mc.modularmachines.common.blockentity.DataBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

import java.util.function.Supplier;

public abstract class BlockEntitySyncAction<T extends SynchedData> extends SyncAction<T> {
    private final BlockPos blockPos;
    private DataBlockEntity blockEntity;
    private final String key;

    public BlockEntitySyncAction(DataBlockEntity blockEntity, String key) {
        this.blockEntity = blockEntity;
        this.blockPos = blockEntity.getBlockPos();
        this.key = key;
    }

    public BlockEntitySyncAction(FriendlyByteBuf buffer) {
        this.blockPos = buffer.readBlockPos();
        this.key = buffer.readUtf();
    }

    @Override
    protected Supplier<Runnable> initClientbound() {
        return () -> () -> {
            this.blockEntity = (DataBlockEntity) ClientHelpers.getBlockEntityAtPos(this.blockPos);
        };
    }

    @Override
    protected void initServerbound(ServerPlayer sender) {
        this.blockEntity = (DataBlockEntity) sender.getLevel().getBlockEntity(this.blockPos);
    }

    @MustBeInvokedByOverriders
    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.blockEntity.getBlockPos());
        buffer.writeUtf(this.key);
    }

    @Override
    public T getData() {
        return (T) this.blockEntity.getSynchedData(this.key);
    }

    @MustBeInvokedByOverriders
    @Override
    public void apply(T synchedData) {
        this.blockEntity.setChanged();
    }
}
