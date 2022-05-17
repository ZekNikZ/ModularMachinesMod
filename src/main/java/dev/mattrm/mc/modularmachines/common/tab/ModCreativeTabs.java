package dev.mattrm.mc.modularmachines.common.tab;

import dev.mattrm.mc.modularmachines.common.item.ModItems;
import dev.mattrm.mc.modularmachines.data.lang.ILanguageDataProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModCreativeTabs {
    public abstract static class MCT extends CreativeModeTab implements ILanguageDataProvider {
        private final String label;

        public MCT(String label) {
            super(label);
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }
    }

    private static final List<CreativeModeTab> CREATIVE_TABS = new ArrayList<>();

    private static CreativeModeTab register(String label, Supplier<Item> item, String languageKey) {
        MCT tab = new MCT(label) {
            @Override
            public @NotNull ItemStack makeIcon() {
                return new ItemStack(item.get());
            }

            @Override
            public String dataLanguageKey(String locale) {
                return languageKey;
            }
        };
        CREATIVE_TABS.add(tab);
        return tab;
    }

    public static final CreativeModeTab MODULAR_MACHINES_TAB = register(
        "modular_machines",
        ModItems.MACHINE_CONTROLLER,
        "Modular Machines"
    );

    public static List<CreativeModeTab> getRegisteredTabs() {
        return CREATIVE_TABS;
    }
}
