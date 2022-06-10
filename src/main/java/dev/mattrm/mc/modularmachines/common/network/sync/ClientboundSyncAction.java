package dev.mattrm.mc.modularmachines.common.network.sync;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class ClientboundSyncAction<T extends SynchedData> extends SyncAction<T> {
    public ClientboundSyncAction() {
        super(SyncDirection.SERVER_TO_CLIENT);
    }

    protected Supplier<Runnable> init() {
        return () -> () -> {};
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, this.init());
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                ClientSynchedDataHelper.applyPropagatedAction(this.getData(), this);
            });
        });
        context.setPacketHandled(true);
    }
}
