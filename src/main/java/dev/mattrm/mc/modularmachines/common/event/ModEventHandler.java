package dev.mattrm.mc.modularmachines.common.event;

import dev.mattrm.mc.modularmachines.data.ModDataProvider;
import dev.mattrm.mc.modularmachines.network.PacketHandler;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventHandler {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(PacketHandler::init);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeClient()) {
            ModDataProvider.genClientData(generator, event.getExistingFileHelper());
        }
        if (event.includeServer()) {
            ModDataProvider.genServerData(generator, event.getExistingFileHelper());
        }
        if (event.includeDev()) {
            ModDataProvider.genDevData(generator, event.getExistingFileHelper());
        }
        if (event.includeReports()) {
            ModDataProvider.genReportsData(generator, event.getExistingFileHelper());
        }
    }
}
