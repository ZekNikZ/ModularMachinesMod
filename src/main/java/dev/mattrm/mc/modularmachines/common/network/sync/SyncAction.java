package dev.mattrm.mc.modularmachines.common.network.sync;

import com.google.common.util.concurrent.Runnables;
import dev.mattrm.mc.modularmachines.common.network.PacketHandler;
import dev.mattrm.mc.modularmachines.common.network.packet.IMMPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public abstract class SyncAction<T extends SynchedData> implements IMMPacket {
    private final List<Class<? extends T>> applicableClasses;

    @SafeVarargs
    protected SyncAction(Class<? extends T>... applicableClasses) {
        this.applicableClasses = Arrays.stream(applicableClasses).toList();
    }

    public <S extends SynchedData> boolean isApplicableTo(S dataClass) {
        return this.applicableClasses.stream().anyMatch(applicableClass -> applicableClass.isInstance(dataClass));
    }

    protected abstract T getData();

    public abstract void apply(T synchedData);

    protected @Nullable PacketDistributor.PacketTarget getClientPacketDistributor() {
        return PacketDistributor.ALL.noArg();
    }

    public final void initiateSync() {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
            PacketHandler.INSTANCE.send(this.getClientPacketDistributor(), this);
        } else {
            PacketHandler.INSTANCE.sendToServer(this);
        }
    }

    protected Supplier<Runnable> initClientbound() {
        return () -> () -> {
        };
    }

    protected void initServerbound(ServerPlayer sender) {

    }

    @Override
    public void handle(NetworkEvent.Context context) {
        if (context.getDirection().getReceptionSide().isServer()) {
            context.enqueueWork(() -> {
                this.initServerbound(context.getSender());
                SynchedData.applyPropagatedAction(this.getData(), this);
            });
        } else {
            context.enqueueWork(() -> {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, this.initClientbound());
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    SynchedData.applyPropagatedAction(this.getData(), this);
                });
            });
        }
        context.setPacketHandled(true);
    }
}
