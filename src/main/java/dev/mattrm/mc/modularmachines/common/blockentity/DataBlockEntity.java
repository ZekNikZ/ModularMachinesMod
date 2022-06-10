package dev.mattrm.mc.modularmachines.common.blockentity;

import dev.mattrm.mc.modularmachines.common.network.sync.SynchedData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DataBlockEntity extends BlockEntity {
    protected final Map<String, SynchedData> synchedData = new HashMap<>();

    public DataBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    protected final void addSynchedData(@NotNull String key, @NotNull Supplier<SynchedData> dataSupplier) {
        this.synchedData.put(key, dataSupplier.get());
    }

    @MustBeInvokedByOverriders
    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        this.synchedData.forEach((key, data) -> nbt.put(key, data.serializeNBT()));
    }

    @MustBeInvokedByOverriders
    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.synchedData.forEach((key, data) -> data.deserializeNBT(nbt.getCompound(key)));
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        this.synchedData.forEach((key, data) -> tag.put(key, data.serializeNBT()));
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        this.synchedData.forEach((key, data) -> data.deserializeNBT(tag.getCompound(key)));
    }

    public SynchedData getSynchedData(String key) {
        return this.synchedData.get(key);
    }
}
