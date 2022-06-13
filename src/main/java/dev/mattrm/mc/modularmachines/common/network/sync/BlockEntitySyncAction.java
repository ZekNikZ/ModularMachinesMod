package dev.mattrm.mc.modularmachines.common.network.sync;

import dev.mattrm.mc.modularmachines.client.ClientHelpers;
import dev.mattrm.mc.modularmachines.common.blockentity.SynchedDataBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

import java.util.function.Supplier;

public abstract class BlockEntitySyncAction<T extends SynchedData> extends SyncAction<T> {
    private final BlockPos blockPos;
    private SynchedDataBlockEntity blockEntity;
    private final String key;

    public BlockEntitySyncAction(SynchedDataBlockEntity blockEntity, String key) {
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
            this.blockEntity = (SynchedDataBlockEntity) ClientHelpers.getBlockEntityAtPos(this.blockPos);
        };
    }

    @Override
    protected void initServerbound(ServerPlayer sender) {
        this.blockEntity = (SynchedDataBlockEntity) sender.getLevel().getBlockEntity(this.blockPos);
    }

    @MustBeInvokedByOverriders
    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.blockEntity.getBlockPos());
        buffer.writeUtf(this.key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getData() {
        SynchedData synchedData = this.blockEntity.getSynchedData(this.key);
        if (synchedData == null) {
            throw new IllegalStateException("Key is invalid for the provided BlockEntity");
        }

        return (T) synchedData;
    }

    @MustBeInvokedByOverriders
    @Override
    public void apply(T synchedData) {
        this.blockEntity.setChanged();
    }
}
