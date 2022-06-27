package dev.mattrm.mc.modularmachines.common.graph.node;

import dev.mattrm.mc.modularmachines.common.graph.component.ModNodeComponents;
import dev.mattrm.mc.modularmachines.common.graph.component.NodeComponent;
import dev.mattrm.mc.modularmachines.common.graph.component.NodeComponentType;
import dev.mattrm.mc.modularmachines.common.graph.component.impl.PinComponent;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.OutputPin;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.control.ControlFlowInputPin;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.control.ControlFlowOutputPin;
import dev.mattrm.mc.modularmachines.common.util.IMetadataNBTSerializable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Node implements IMetadataNBTSerializable {
    private final INodeManager manager;

    private final UUID id;
    private final ControlFlowInput controlFlowInputState;
    private final ControlFlowOutput controlFlowOutputState;
    private final List<InputPin> inputPins = new ArrayList<>();
    private final Map<String, InputPin> inputPinMap = new HashMap<>();
    private final List<OutputPin> outputPins = new ArrayList<>();
    private final Map<String, OutputPin> outputPinMap = new HashMap<>();
    private List<NodeComponent> components = new ArrayList<>();

    private int x = 0;
    private int y = 0;

    public Node(INodeManager manager, UUID id, ControlFlowInput controlFlowInputState, ControlFlowOutput controlFlowOutputState) {
        this.manager = manager;
        this.id = id;
        this.controlFlowInputState = controlFlowInputState;
        this.controlFlowOutputState = controlFlowOutputState;

        if (this.controlFlowInputState.hasInputPin()) {
            this.addInputPin(new ControlFlowInputPin(this));
        }

        if (this.controlFlowOutputState.hasOutputPin()) {
            this.addOutputPin(new ControlFlowOutputPin());
        }

        this.initComponents();
        this.addComponent(new PinComponent(this));
    }

    abstract protected void initComponents();

    protected void addInputPin(InputPin pin) {
        this.inputPins.add(pin);
        this.inputPinMap.put(pin.id(), pin);
    }

    protected void addOutputPin(OutputPin pin) {
        this.outputPins.add(pin);
        this.outputPinMap.put(pin.id(), pin);
    }

    public final ControlFlowInput getControlFlowInputState() {
        return this.controlFlowInputState;
    }

    public final ControlFlowOutput getControlFlowOutputState() {
        return this.controlFlowOutputState;
    }

    public final List<InputPin> getInputPins() {
        return this.inputPins;
    }

    public final List<OutputPin> getOutputPins() {
        return this.outputPins;
    }

    public final InputPin getInputPins(String name) {
        return this.inputPinMap.get(name);
    }

    public final OutputPin getOutputPins(String name) {
        return this.outputPinMap.get(name);
    }

    protected final void addComponent(NodeComponent component) {
        this.components.add(component);
    }

    public final UUID getId() {
        return this.id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final void activate() {
        if (!this.activationAllowed()) return;
        if (!this.activateNode()) return;
        this.activateConnections();
    }

    /**
     * Check whether this node can be activated before actually activating it. Intended to be overwritten in subclasses.
     *
     * @return whether to go through with the activation
     */
    @Deprecated
    protected boolean activationAllowed() {
        return true;
    }

    /**
     * Perform activation logic.
     *
     * @return whether this node should provide control flow to connected nodes
     */
    public abstract boolean activateNode();

    public void activateConnections() {
        // TODO: this is a stupid way to do this, figure out a better way
        this.manager.activateConnections(this);
    }

    public INodeManager getManager() {
        return this.manager;
    }

    public enum Type {
        COMPUTATION(0), // nodes that compute data values
        CONTROL(1), // nodes that process control flow states
        PROCESSING(2), // nodes that take resource inputs and provide outputs
        ;

        private final int priority;

        Type(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    public abstract Type type();

    @Override
    public final CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();

        ListTag list = new ListTag();
        list.addAll(this.components.stream().map(c -> {
            CompoundTag tag = new CompoundTag();
            tag.putString("type", String.valueOf(c.type().getRegistryName()));
            c.serializeNBT(tag);
            return tag;
        }).collect(Collectors.toList()));
        nbt.put("components", list);
        this.saveAdditional(nbt);

        return nbt;
    }

    public void saveAdditional(CompoundTag nbt) {
    }

    @Override
    public final void deserializeNBT(CompoundTag nbt) {
        this.components = nbt.getList("components", Tag.TAG_COMPOUND).stream().map(c -> {
            CompoundTag tag = ((CompoundTag) c);
            String type = tag.getString("type");
            NodeComponentType<?> componentType = ModNodeComponents.REGISTRY.get().getValue(ResourceLocation.tryParse(type));
            // TODO: error checking ^
            return componentType.create(tag);
        }).collect(Collectors.toList());

        this.load(nbt);
    }

    public void load(CompoundTag nbt) {
    }
}
