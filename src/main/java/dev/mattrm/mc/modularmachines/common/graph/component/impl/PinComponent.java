package dev.mattrm.mc.modularmachines.common.graph.component.impl;

import dev.mattrm.mc.modularmachines.common.graph.node.Node;
import dev.mattrm.mc.modularmachines.common.graph.component.ModNodeComponents;
import dev.mattrm.mc.modularmachines.common.graph.component.NodeComponent;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.OutputPin;
import net.minecraft.nbt.CompoundTag;

import java.util.List;

public class PinComponent extends NodeComponent {
    private final Node node;

    public PinComponent(Node node) {
        super(ModNodeComponents.PINS.get());
        this.node = node;
    }

    public PinComponent(CompoundTag nbt) {
        super(ModNodeComponents.PINS.get(), nbt);
        this.node = null;
    }

    public List<InputPin> getInputPins() {
        // TODO: grab the cached input pins
        return null;
    }

    public List<OutputPin> getOutputPins() {
        // TODO: grab the cached output pins
        return null;
    }

    @Override
    public void serializeNBT(CompoundTag nbt) {
        // TODO: serialize the pins
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        // TODO: deserialize the pins
    }
}
