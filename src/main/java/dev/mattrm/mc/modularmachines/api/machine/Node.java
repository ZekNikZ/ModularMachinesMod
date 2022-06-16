package dev.mattrm.mc.modularmachines.api.machine;

import dev.mattrm.mc.modularmachines.api.machine.pin.InputPin;
import dev.mattrm.mc.modularmachines.api.machine.pin.OutputPin;
import dev.mattrm.mc.modularmachines.api.machine.pin.impl.ControlFlowInputPin;
import dev.mattrm.mc.modularmachines.api.machine.pin.impl.ControlFlowOutputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.NodeComponent;
import dev.mattrm.mc.modularmachines.client.gui.PinComponent;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.ControlFlowInput;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.ControlFlowOutput;

import java.util.*;

public abstract class Node {
    private final INodeManager manager;

    private final UUID id;
    private final ControlFlowInput controlFlowInputState;
    private final ControlFlowOutput controlFlowOutputState;
    private final List<InputPin<?>> inputPins = new ArrayList<>();
    private final Map<String, InputPin<?>> inputPinMap = new HashMap<>();
    private final List<OutputPin<?, ?>> outputPins = new ArrayList<>();
    private final Map<String, OutputPin<?, ?>> outputPinMap = new HashMap<>();
    private final List<NodeComponent> components = new ArrayList<>();

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

    protected void addInputPin(InputPin<?> pin) {
        this.inputPins.add(pin);
        this.inputPinMap.put(pin.name(), pin);
    }

    protected void addOutputPin(OutputPin<?, ?> pin) {
        this.outputPins.add(pin);
        this.outputPinMap.put(pin.name(), pin);
    }

    public final ControlFlowInput getControlFlowInputState() {
        return this.controlFlowInputState;
    }

    public final ControlFlowOutput getControlFlowOutputState() {
        return this.controlFlowOutputState;
    }

    public final List<InputPin<?>> getInputPins() {
        return this.inputPins;
    }

    public final List<OutputPin<?, ?>> getOutputPins() {
        return this.outputPins;
    }

    public final InputPin<?> getInputPins(String name) {
        return this.inputPinMap.get(name);
    }

    public final OutputPin<?, ?> getOutputPins(String name) {
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
    public boolean activationAllowed() {
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
}
