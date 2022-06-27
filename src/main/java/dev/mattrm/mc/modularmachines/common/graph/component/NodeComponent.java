package dev.mattrm.mc.modularmachines.common.graph.component;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class NodeComponent implements INBTSerializable<CompoundTag> {
    private final NodeComponentType<?> type;

    protected NodeComponent(NodeComponentType<?> type) {
        this.type = type;
    }

    public NodeComponentType<?> type() {
        return this.type;
    }

    public boolean isHidden() {
        return false;
    }
}
