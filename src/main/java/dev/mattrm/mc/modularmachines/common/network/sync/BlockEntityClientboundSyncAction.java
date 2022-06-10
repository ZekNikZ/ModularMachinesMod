package dev.mattrm.mc.modularmachines.common.network.sync;

import dev.mattrm.mc.modularmachines.client.ClientHelpers;
import dev.mattrm.mc.modularmachines.common.blockentity.DataBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

import java.util.function.Supplier;

public abstract class BlockEntityClientboundSyncAction<T extends SynchedData> extends ClientboundSyncAction<T> {
    private final BlockPos blockPos;
    private DataBlockEntity blockEntity;
    private final String key;

    public BlockEntityClientboundSyncAction(DataBlockEntity blockEntity, String key) {
        this.blockEntity = blockEntity;
        this.blockPos = blockEntity.getBlockPos();
        this.key = key;
    }

    public BlockEntityClientboundSyncAction(FriendlyByteBuf buffer) {
        this.blockPos = buffer.readBlockPos();
        this.key = buffer.readUtf();
    }

    @Override
    protected Supplier<Runnable> init() {
        return () -> () -> {
            this.blockEntity = (DataBlockEntity) ClientHelpers.getBlockEntityAtPos(this.blockPos);
        };
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
