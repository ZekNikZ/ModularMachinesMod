package dev.mattrm.mc.modularmachines.common.container;

import dev.mattrm.mc.modularmachines.Constants;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Constants.MOD_ID);

    public static final RegistryObject<MenuType<ControllerMenu>> CONTROLLER = CONTAINERS.register("controller", () -> IForgeMenuType.create(
        (windowId, inv, data) -> new ControllerMenu(windowId, data.readBlockPos(), inv, inv.player)
    ));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }

}
