package dev.mattrm.mc.modularmachines.client.event;

import dev.mattrm.mc.modularmachines.client.gui.graph.component.ModNodeComponentRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
//            MenuScreens.register(ModContainers.CONTROLLER.get(), ControllerScreen::new);

            ModNodeComponentRenderers.registerDefaults();
        });
    }
}
