package dev.mattrm.mc.modularmachines.common.graph.pin;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;

public class PinType<T extends Pin> extends ForgeRegistryEntry<PinType<T>> {
    private final Class<T> discriminator;
    private final Pin.Type ioType;
    private final ResourceLocation icon;
    private final Color color;
    private final Class<?>[] allowedDataTypes;

    private PinType(Class<T> discriminator, Pin.Type ioType, ResourceLocation icon, Color color, Class<?>[] allowedDataTypes) {
        this.discriminator = discriminator;
        this.ioType = ioType;
        this.icon = icon;
        this.color = color;
        this.allowedDataTypes = allowedDataTypes;
    }

    public Class<T> getDiscriminator() {
        return this.discriminator;
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
        if (this.ioType() == pin.type().ioType()) {
            return  false;
        }

        HashSet<Class<?>> classes = new HashSet<>(Arrays.asList(this.allowedDataTypes));
        return Arrays.stream(this.allowedDataTypes).noneMatch(classes::add);
    }

    public static final class Builder<T extends Pin> {
        private final Class<T> discriminator;
        private final Pin.Type ioType;
        private final ResourceLocation icon;
        private final Color color;
        private final Class<?>[] allowedDataTypes;

        private Builder(Class<T> discriminator, Pin.Type ioType, ResourceLocation icon, Color color, Class<?>[] allowedDataTypes) {
            this.discriminator = discriminator;
            this.ioType = ioType;
            this.icon = icon;
            this.color = color;
            this.allowedDataTypes = allowedDataTypes;
        }

        public static <T extends Pin> Builder<T> of(Class<T> discriminator, Pin.Type ioType, ResourceLocation icon, Color color, Class<?>... allowedDataTypes) {
            return new Builder<>(discriminator, ioType, icon, color, allowedDataTypes);
        }

        public PinType<T> build() {
            return new PinType<>(this.discriminator, this.ioType, this.icon, this.color, this.allowedDataTypes);
        }
    }
}
