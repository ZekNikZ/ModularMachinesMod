package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.function.Function;

public class PinType<T extends Pin> extends ForgeRegistryEntry<PinType<T>> {
    private final PinBuilderConstructor<? extends T> factory;
    private final Pin.Type ioType;
    private final ResourceLocation icon;
    private final Color color;
    private final Function<Pin, Boolean> compatFunc;

    private PinType(PinBuilderConstructor<? extends T> factory, Pin.Type ioType, ResourceLocation icon, Color color, Function<Pin, Boolean> compatFunc) {
        this.factory = factory;
        this.ioType = ioType;
        this.icon = icon;
        this.color = color;
        this.compatFunc = compatFunc;
    }

    public Pin.Type ioType() {
        return this.ioType;
    }

    public ResourceLocation icon() {
        return this.icon;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isCompatible(Pin pin) {
        return this.ioType() != pin.type().ioType() && this.compatFunc.apply(pin);
    }

    @FunctionalInterface
    public interface PinBuilderConstructor<T extends Pin> {
        PinBuilder<T> create(PinType<?> type, String id, String translationKey, int maxConnections);
    }

    @Nullable
    public PinBuilder<? extends T> create(String id, String translationKey, int maxConnections) {
        return this.factory.create(this, id, translationKey, maxConnections);
    }

    public static final class Builder<T extends Pin> {
        private final PinBuilderConstructor<? extends T> factory;
        private final Pin.Type ioType;
        private final ResourceLocation icon;
        private final Color color;
        private final Function<Pin, Boolean> compatFunc;

        private Builder(PinBuilderConstructor<? extends T> factory, Pin.Type ioType, ResourceLocation icon, Color color, Function<Pin, Boolean> compatFunc) {
            this.factory = factory;
            this.ioType = ioType;
            this.icon = icon;
            this.color = color;
            this.compatFunc = compatFunc;
        }

        public static <T extends Pin> Builder<T> of(PinBuilderConstructor<? extends T> factory, Pin.Type ioType, ResourceLocation icon, Color color, Function<Pin, Boolean> compatFunc) {
            return new Builder<>(factory, ioType, icon, color, compatFunc);
        }

        public PinType<T> build() {
            return new PinType<>(this.factory, this.ioType, this.icon, this.color, this.compatFunc);
        }
    }
}
