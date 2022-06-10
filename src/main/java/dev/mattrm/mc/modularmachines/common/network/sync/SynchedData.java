package dev.mattrm.mc.modularmachines.common.network.sync;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

/**
 * The designed usage of this is for subclasses to define a set of "actions" which modify the data.
 * When an "action" occurs, it should both call the function on the current side and send a packet to call it on the
 * other side.
 * <p>
 * TODO: I likely want a way to do this automatically. One option is to define a per-SynchedData registry of action
 *       name to handler function. All data would be passed using just 2 packets (one C->S and one S->C) and NBT.
 *       Another option is to leave the proper handling onus onto the end users. I don't like that idea, but I can't
 *       find a way to avoid it right now, unfortunately.
 *       Perhaps a new "Action" type, like below, could come in handy. Basically, you call "applyAction(new Action(...))"
 *       and it takes care of the rest. I just need to figure out whether to create a separate packet for each action or
 *       to have a common C->S and S->C packet and somehow figure out what kind of action it was to properly deserialize
 *       it. Either way, the handling function will get the SynchedData and call applyAction
 */
public abstract class SynchedData {
    public abstract CompoundTag serializeNBT();

    public abstract void deserializeNBT(CompoundTag tag);

    public static <T extends SynchedData> void applyAction(T data, SyncAction<T> action) {
        action.apply(data);
        action.initiateSync();
    }

    static <T extends SynchedData> void applyPropagatedAction(T data, SyncAction<T> action) {
        action.apply(data);
    }
}
