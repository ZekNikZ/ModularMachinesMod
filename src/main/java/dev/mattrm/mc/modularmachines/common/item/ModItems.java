package dev.mattrm.mc.modularmachines.common.item;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.tab.ModCreativeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    public static final RegistryObject<Item> MACHINE_CONTROLLER = ITEMS.register("machine_controller", () -> new BlockItem(ModBlocks.MACHINE_CONTROLLER.get(), new Item.Properties().tab(ModCreativeTabs.MODULAR_MACHINES_TAB)));
    public static final RegistryObject<Item> MACHINE_CASING = ITEMS.register("machine_casing", () -> new BlockItem(ModBlocks.MACHINE_CASING.get(), new Item.Properties().tab(ModCreativeTabs.MODULAR_MACHINES_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
