package dev.mattrm.mc.modularmachines.common.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMMPacket {
    void handle(NetworkEvent.Context context);

    void encode(FriendlyByteBuf buffer);

    static <P extends IMMPacket> void handle(P msg, Supplier<NetworkEvent.Context> ctx) {
        if (msg != null) {
            NetworkEvent.Context context = ctx.get();
            context.enqueueWork(() -> msg.handle(context));
            context.setPacketHandled(true);
        }
    }
}
