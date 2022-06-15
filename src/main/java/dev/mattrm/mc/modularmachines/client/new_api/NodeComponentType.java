package dev.mattrm.mc.modularmachines.client.new_api;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class NodeComponentType<T extends NodeComponent> extends ForgeRegistryEntry<NodeComponentType<?>> {
    private final NodeComponentType.NodeComponentSupplier<? extends T> factory;
    private final NodeComponentType.NodeComponentDeserializer<? extends T> deserializer;

    public NodeComponentType(NodeComponentSupplier<? extends T> factory, NodeComponentDeserializer<? extends T> deserializer) {
        this.factory = factory;
        this.deserializer = deserializer;
    }

    @Nullable
    public T create(Object data) {
        return this.factory.create(data);
    }

    @Nullable
    public T create(CompoundTag data) {
        return this.deserializer.create(data);
    }

    @FunctionalInterface
    public interface NodeComponentSupplier<T extends NodeComponent> {
        T create(Object data);
    }

    @FunctionalInterface
    public interface NodeComponentDeserializer<T extends NodeComponent> {
        T create(CompoundTag data);
    }

    public static final class Builder<T extends NodeComponent> {
        private final NodeComponentType.NodeComponentSupplier<? extends T> factory;
        private final NodeComponentType.NodeComponentDeserializer<? extends T> deserializer;
        private Builder(NodeComponentSupplier<? extends T> factory, NodeComponentDeserializer<? extends T> deserializer) {
            this.factory = factory;
            this.deserializer = deserializer;
        }

        public static <T extends NodeComponent> NodeComponentType.Builder<T> of(NodeComponentType.NodeComponentSupplier<? extends T> factory, NodeComponentType.NodeComponentDeserializer<? extends T> deserializer) {
            return new NodeComponentType.Builder<>(factory, deserializer);
        }

        public NodeComponentType<T> build() {
            return new NodeComponentType<>(this.factory, this.deserializer);
        }
    }
}
