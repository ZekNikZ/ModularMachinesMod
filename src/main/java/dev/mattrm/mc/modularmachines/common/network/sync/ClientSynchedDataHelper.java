package dev.mattrm.mc.modularmachines.common.network.sync;

public class ClientSynchedDataHelper {
    static <T extends SynchedData> void applyPropagatedAction(T data, SyncAction<T> action) {
        action.apply(data);
    }
}
