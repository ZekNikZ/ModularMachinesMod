package dev.mattrm.mc.modularmachines.client.new_api.common.node.component.impl;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.Node;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.ModNodeComponents;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.NodeComponent;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.OutputPin;
import net.minecraft.nbt.CompoundTag;

import java.util.List;

public class PinComponent extends NodeComponent {
    private final Node node;

    public PinComponent(Node node) {
        super(ModNodeComponents.PINS.get());
        this.node = node;
    }

    public PinComponent(CompoundTag nbt) {
        super(ModNodeComponents.PINS.get());
        this.deserializeNBT(nbt);
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
    public CompoundTag serializeNBT() {
        // TODO: serialize the pins
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        // TODO: deserialize the pins
    }
}
