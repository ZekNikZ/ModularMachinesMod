package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.ItemInputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.ItemOutputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.ResourceInputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.ResourceOutputPin;
import net.minecraft.resources.ResourceLocation;
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

    public static final RegistryObject<PinType<ItemInputPin>> ITEM_INPUT = registerInputPin(
        "item_input",
        ItemInputPin::create,
        Icons.ITEM,
        Colors.ITEM,
        "item_output"
    );
    public static final RegistryObject<PinType<ItemOutputPin>> ITEM_OUTPUT = registerOutputPin(
        "item_output",
        ItemOutputPin::create,
        Icons.ITEM,
        Colors.ITEM,
        "item_input"
    );

    private static <I extends ResourceInputPin<O>, O extends ResourceOutputPin<I>> RegistryObject<PinType<I>> registerInputPin(String key, PinType.PinBuilderConstructor<I> factory, ResourceLocation icon, Color color, String linkedPin) {
        return PINS.register(key, () -> PinType.Builder.of(
            factory,
            Pin.Type.INPUT,
            icon,
            color,
            (pin) -> pin.type() == PINS.getEntries().stream().filter(p -> p.get().getRegistryName().getPath().equals(linkedPin)).findFirst().orElse(null).get()
        ).build());
    }

    private static <I extends ResourceInputPin<O>, O extends ResourceOutputPin<I>> RegistryObject<PinType<O>> registerOutputPin(String key, PinType.PinBuilderConstructor<O> factory, ResourceLocation icon, Color color, String linkedPin) {
        return PINS.register(key, () -> PinType.Builder.of(
            factory,
            Pin.Type.INPUT,
            icon,
            color,
            (pin) -> pin.type() == linkedPin.get()
        ).build());
    }

    public static void register(IEventBus eventBus) {
        PINS.register(eventBus);
    }
}
