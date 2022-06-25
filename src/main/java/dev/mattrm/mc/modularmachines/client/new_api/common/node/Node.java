package dev.mattrm.mc.modularmachines.client.new_api.common.node;

import dev.mattrm.mc.modularmachines.client.new_api.INodeManager;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.impl.PinComponent;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.NodeComponent;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.OutputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.control.ControlFlowInputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.control.ControlFlowOutputPin;

import java.util.*;

public abstract class Node {
    private final INodeManager manager;

    private final UUID id;
    private final ControlFlowInput controlFlowInputState;
    private final ControlFlowOutput controlFlowOutputState;
    private final List<InputPin> inputPins = new ArrayList<>();
    private final Map<String, InputPin> inputPinMap = new HashMap<>();
    private final List<OutputPin> outputPins = new ArrayList<>();
    private final Map<String, OutputPin> outputPinMap = new HashMap<>();
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
}
