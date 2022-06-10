package dev.mattrm.mc.modularmachines.common.network.sync;

import dev.mattrm.mc.modularmachines.common.network.PacketHandler;
import dev.mattrm.mc.modularmachines.common.network.packet.IMMPacket;
import net.minecraftforge.network.PacketDistributor;

public abstract class SyncAction<T extends SynchedData> implements IMMPacket {
    public enum SyncDirection {
        SERVER_TO_CLIENT,
        CLIENT_TO_SERVER
    }

    private final SyncDirection direction;

    public SyncAction(SyncDirection direction) {
        this.direction = direction;
    }

    public abstract T getData();

    public abstract void apply(T synchedData);

    protected PacketDistributor.PacketTarget getPacketDistributor() {
        if (this.direction == SyncDirection.SERVER_TO_CLIENT) {
            return PacketDistributor.ALL.noArg();
        }

        return null;
    }

    public final void initiateSync() {
        if (this.direction == SyncDirection.SERVER_TO_CLIENT) {
            PacketHandler.INSTANCE.send(this.getPacketDistributor(), this);
        } else {
            PacketHandler.INSTANCE.sendToServer(this);
        }
    }
}
