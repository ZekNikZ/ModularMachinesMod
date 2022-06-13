package dev.mattrm.mc.modularmachines.common.network.sync;

import net.minecraft.nbt.CompoundTag;

/**
 * The designed usage of this is for subclasses to define a set of "actions" which modify the data.
 * When an "action" occurs, it should both call the function on the current side and send a packet to call it on the
 * other side.
 */
public abstract class SynchedData {
    public abstract CompoundTag serializeNBT();

    public abstract void deserializeNBT(CompoundTag tag);

    public static <T extends SynchedData> void applyAction(T data, SyncAction<T> action) {
        if (!action.isApplicableTo(data)) {
            throw new IllegalArgumentException("Action is not applicable to the data type");
        }
        action.apply(data);
        action.initiateSync();
    }

    static <T extends SynchedData> void applyPropagatedAction(T data, SyncAction<T> action) {
        action.apply(data);
    }
}
