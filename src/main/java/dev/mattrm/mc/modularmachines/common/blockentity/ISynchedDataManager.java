package dev.mattrm.mc.modularmachines.common.blockentity;

import dev.mattrm.mc.modularmachines.common.network.sync.SynchedData;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public interface ISynchedDataManager<T> {
    Map<String, SynchedData> getAllSynchedData();

    default void addSynchedData(@NotNull String key, @NotNull SynchedData data) {
        this.getAllSynchedData().put(key, data);
    }

    default void addSynchedData(@NotNull String key, @NotNull Supplier<? extends SynchedData> dataSupplier) {
        this.addSynchedData(key, dataSupplier.get());
    }

    default SynchedData getSynchedData(String key) {
        return this.getAllSynchedData().get(key);
    }
}
