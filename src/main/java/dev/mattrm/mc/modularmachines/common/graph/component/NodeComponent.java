package dev.mattrm.mc.modularmachines.common.graph.component;

import net.minecraft.nbt.CompoundTag;

public abstract class NodeComponent {
    private final NodeComponentType<?> type;

    protected NodeComponent(NodeComponentType<?> type) {
        this.type = type;
    }

    public NodeComponent(NodeComponentType<?> type, CompoundTag nbt) {
        this.type = type;
        this.deserializeNBT(nbt);
    }

    public NodeComponentType<?> type() {
        return this.type;
    }

    public boolean isHidden() {
        return false;
    }

    public abstract void serializeNBT(CompoundTag nbt);
    public abstract void deserializeNBT(CompoundTag nbt);
}
