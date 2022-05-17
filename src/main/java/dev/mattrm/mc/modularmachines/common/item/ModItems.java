package dev.mattrm.mc.modularmachines.common.item;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.tab.ModCreativeTabs;
import dev.mattrm.mc.modularmachines.data.model.IItemModelDataProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.stream.Collectors;

public class ModItems {
    private static class ModBlockItem extends BlockItem implements IItemModelDataProvider {
        public ModBlockItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void dataItemModel(ItemModelProvider provider) {
            Block block = this.getBlock();
            if (block instanceof IItemModelDataProvider modelProvider) {
                modelProvider.dataItemModel(provider);
            }
        }
    }

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    public static final RegistryObject<Item> MACHINE_CONTROLLER = ITEMS.register("machine_controller", () -> new ModBlockItem(ModBlocks.MACHINE_CONTROLLER.get(), new Item.Properties().tab(ModCreativeTabs.MODULAR_MACHINES_TAB)));
    public static final RegistryObject<Item> MACHINE_CASING = ITEMS.register("machine_casing", () -> new ModBlockItem(ModBlocks.MACHINE_CASING.get(), new Item.Properties().tab(ModCreativeTabs.MODULAR_MACHINES_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static List<Item> getRegisteredItems() {
        return ITEMS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }
}
