package dev.mattrm.mc.modularmachines.client.gui;

import dev.mattrm.mc.modularmachines.common.blockentity.ISynchedDataManager;
import dev.mattrm.mc.modularmachines.common.network.sync.SyncAction;
import dev.mattrm.mc.modularmachines.common.network.sync.SynchedData;

import java.util.function.Function;

public interface ISynchedDataScreen<M extends ISynchedDataManager<?>> {
    M getManager();

    default void applyAction(String key, SyncAction<? extends SynchedData> action) {
        SynchedData data = this.getManager().getSynchedData(key);
        SynchedData.applyAction(data, action);
    }

    default void applyAction(String key, Function<M, ? extends SyncAction<? extends SynchedData>> actionFunc) {
        this.applyAction(key, actionFunc.apply(this.getManager()));
    }

    default SynchedData getSynchedData(String key) {
        return this.getManager().getSynchedData(key);
    }
}
