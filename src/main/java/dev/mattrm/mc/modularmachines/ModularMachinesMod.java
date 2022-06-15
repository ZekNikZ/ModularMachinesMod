package dev.mattrm.mc.modularmachines;

import com.mojang.logging.LogUtils;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.components.ModNodeComponents;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.blockentity.ModBlockEntities;
import dev.mattrm.mc.modularmachines.common.container.ModContainers;
import dev.mattrm.mc.modularmachines.common.item.ModItems;
import dev.mattrm.mc.modularmachines.common.setup.Config;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constants.MOD_ID)
public class ModularMachinesMod {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModularMachinesMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Registries
        ModBlocks.register(eventBus);
        ModItems.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModContainers.register(eventBus);

        // Custom Registries
        ModNodeComponents.register(eventBus);

        // Config
        Config.register();
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    @SubscribeEvent
    public void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    @SubscribeEvent
    public void processIMC(final InterModProcessEvent event) {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
            map(m -> m.messageSupplier().get()).
            collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            // TODO: register mod-specific blocks here (AE_BLOCKS, AE_ITEMS, RS_, ...)
        }
    }
}
