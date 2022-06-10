package dev.mattrm.mc.modularmachines.common.network.sync;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public abstract class ServerboundSyncAction<T extends SynchedData> extends SyncAction<T> {
    public ServerboundSyncAction() {
        super(SyncDirection.CLIENT_TO_SERVER);
    }

    protected void init(ServerPlayer sender) {}

    @Override
    public void handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            this.init(context.getSender());
            SynchedData.applyPropagatedAction(this.getData(), this);
        });
        context.setPacketHandled(true);
    }
}
