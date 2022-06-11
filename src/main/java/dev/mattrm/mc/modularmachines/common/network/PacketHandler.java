package dev.mattrm.mc.modularmachines.common.network;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.blockentity.ControllerSynchedData;
import dev.mattrm.mc.modularmachines.common.network.packet.IMMPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.Function;

public class PacketHandler {
    private PacketHandler() {
    }

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(Constants.MOD_ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    private static int index = 0;

    protected static <P extends IMMPacket> void registerBidirectional(Class<P> type, Function<FriendlyByteBuf, P> decoder) {
        registerServerToClient(type, decoder);
        registerClientToServer(type, decoder);
    }

    protected static <P extends IMMPacket> void registerClientToServer(Class<P> type, Function<FriendlyByteBuf, P> decoder) {
        registerMessage(type, decoder, NetworkDirection.PLAY_TO_SERVER);
    }

    protected static <P extends IMMPacket> void registerServerToClient(Class<P> type, Function<FriendlyByteBuf, P> decoder) {
        registerMessage(type, decoder, NetworkDirection.PLAY_TO_CLIENT);
    }

    private static <P extends IMMPacket> void registerMessage(Class<P> type, Function<FriendlyByteBuf, P> decoder, NetworkDirection networkDirection) {
        INSTANCE.registerMessage(index++, type, IMMPacket::encode, decoder, IMMPacket::handle, Optional.of(networkDirection));
    }

    public static void init() {
        registerClientToServer(ControllerSynchedData.TestAction.class, ControllerSynchedData.TestAction::new);
    }
}
