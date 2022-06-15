package dev.mattrm.mc.modularmachines.client.new_api.common.node.components.impl;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.components.NodeComponent;
import net.minecraft.nbt.CompoundTag;

public class SimpleTextNodeComponent extends NodeComponent {
    private String text;
    private int color = 0;

    public SimpleTextNodeComponent(Object text) {
        this.text = (String) text;
    }

    public SimpleTextNodeComponent(CompoundTag nbt) {
        this.deserializeNBT(nbt);
    }

    public String getText() {
        return this.text;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("text", this.text);
        return nbt;
    }

    @Override
    public final void deserializeNBT(CompoundTag nbt) {
        this.text = nbt.getString("text");
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
