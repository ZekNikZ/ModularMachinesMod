package dev.mattrm.mc.modularmachines.common.tab;

import dev.mattrm.mc.modularmachines.common.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTabs {
    public static final CreativeModeTab MODULAR_MACHINES_TAB = new CreativeModeTab("modular_machines") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.MACHINE_CONTROLLER.get());
        }
    };
}
