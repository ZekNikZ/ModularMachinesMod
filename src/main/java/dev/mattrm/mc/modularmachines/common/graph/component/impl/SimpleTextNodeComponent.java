package dev.mattrm.mc.modularmachines.common.graph.component.impl;

import dev.mattrm.mc.modularmachines.common.graph.component.ModNodeComponents;
import dev.mattrm.mc.modularmachines.common.graph.component.NodeComponent;
import net.minecraft.nbt.CompoundTag;

public class SimpleTextNodeComponent extends NodeComponent {
    private String text;
    private int color = 0;

    public SimpleTextNodeComponent(String text) {
        super(ModNodeComponents.SIMPLE_TEXT.get());
        this.text = text;
    }

    public SimpleTextNodeComponent(CompoundTag nbt) {
        super(ModNodeComponents.SIMPLE_TEXT.get());
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
