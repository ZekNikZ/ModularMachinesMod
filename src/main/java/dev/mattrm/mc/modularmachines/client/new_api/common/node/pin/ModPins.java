package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin;

import cpw.mods.modlauncher.LaunchPluginHandler;
import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.data.BooleanInputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.data.BooleanOutputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.data.IntegerInputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.data.IntegerOutputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.resource.ItemInputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.resource.ItemOutputPin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModPins {
    private static class Colors {
        public static final Color ITEM = Color.RED;
        public static final Color FLUID = Color.BLUE;
        public static final Color ENERGY = Color.YELLOW;
        public static final Color ITEM_FILTER = Color.ORANGE;
        public static final Color FLUID_FILTER = Color.ORANGE;
        public static final Color DATA = Color.CYAN;
    }

    private static class Icons {
        public static final ResourceLocation ITEM = r("item");
        public static final ResourceLocation FLUID = r("fluid");
        public static final ResourceLocation ENERGY = r("energy");
        public static final ResourceLocation ITEM_FILTER = r("item_filter");
        public static final ResourceLocation FLUID_FILTER = r("fluid_filter");
        public static final ResourceLocation BOOLEAN = r("boolean");
        public static final ResourceLocation INTEGER = r("integer");

        private static ResourceLocation r(String icon) {
            return new ResourceLocation(Constants.MOD_ID, "textures/gui/pins/" + icon);
        }
    }

    public static final ResourceLocation PIN_REGISTRY = new ResourceLocation(Constants.MOD_ID, "pins");
    public static final DeferredRegister<PinType<?>> PINS = DeferredRegister.create(PIN_REGISTRY, Constants.MOD_ID);

    public static final RegistryObject<PinType<ItemInputPin>> ITEM_INPUT = PINS.register(
        "item_input",
        () -> PinType.Builder.of(
            ItemInputPin.class,
            Pin.Type.INPUT,
            Icons.ITEM,
            Colors.ITEM,
            ItemStack.class
        ).build()
    );
    public static final RegistryObject<PinType<ItemOutputPin>> ITEM_OUTPUT = PINS.register(
        "item_output",
        () -> PinType.Builder.of(
            ItemOutputPin.class,
            Pin.Type.OUTPUT,
            Icons.ITEM,
            Colors.ITEM,
            ItemStack.class
        ).build()
    );

    public static final RegistryObject<PinType<IntegerInputPin>> INTEGER_INPUT = PINS.register(
        "int_input",
        () -> PinType.Builder.of(
            IntegerInputPin.class,
            Pin.Type.INPUT,
            Icons.INTEGER,
            Colors.DATA,
            Integer.class,
            Boolean.class
        ).build()
    );
    public static final RegistryObject<PinType<IntegerOutputPin>> INTEGER_OUTPUT = PINS.register(
        "int_output",
        () -> PinType.Builder.of(
            IntegerOutputPin.class,
            Pin.Type.OUTPUT,
            Icons.INTEGER,
            Colors.DATA,
            Integer.class,
            Boolean.class
        ).build()
    );
    public static final RegistryObject<PinType<BooleanInputPin>> BOOLEAN_INPUT = PINS.register(
        "int_input",
        () -> PinType.Builder.of(
            BooleanInputPin.class,
            Pin.Type.INPUT,
            Icons.INTEGER,
            Colors.DATA,
            Integer.class,
            Boolean.class
        ).build()
    );
    public static final RegistryObject<PinType<BooleanOutputPin>> BOOLEAN_OUTPUT = PINS.register(
        "int_output",
        () -> PinType.Builder.of(
            BooleanOutputPin.class,
            Pin.Type.OUTPUT,
            Icons.INTEGER,
            Colors.DATA,
            Integer.class,
            Boolean.class
        ).build()
    );

    public static void register(IEventBus eventBus) {
        PINS.register(eventBus);
    }
}
