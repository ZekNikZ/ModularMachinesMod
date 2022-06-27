package dev.mattrm.mc.modularmachines.common.graph.component;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class NodeComponentType<T extends NodeComponent> extends ForgeRegistryEntry<NodeComponentType<?>> {
    private final NodeComponentType.NodeComponentDeserializer<? extends T> deserializer;

    public NodeComponentType(NodeComponentDeserializer<? extends T> deserializer) {
        this.deserializer = deserializer;
    }

    @Nullable
    public T create(CompoundTag data) {
        return this.deserializer.create(data);
    }

    @FunctionalInterface
    public interface NodeComponentDeserializer<T extends NodeComponent> {
        T create(CompoundTag data);
    }

    public static final class Builder<T extends NodeComponent> {
        private final NodeComponentType.NodeComponentDeserializer<? extends T> deserializer;

        private Builder(NodeComponentDeserializer<? extends T> deserializer) {
            this.deserializer = deserializer;
        }

        public static <T extends NodeComponent> NodeComponentType.Builder<T> of(NodeComponentType.NodeComponentDeserializer<? extends T> deserializer) {
            return new NodeComponentType.Builder<>(deserializer);
        }

        public NodeComponentType<T> build() {
            return new NodeComponentType<>(this.deserializer);
        }
    }
}
