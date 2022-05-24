package dev.mattrm.mc.modularmachines.api.machine;

import java.util.List;

public abstract class Node {
    private final int id;
    private final INodeProvider.ControlFlowInput controlFlowState;

    public Node(int id, INodeProvider.ControlFlowInput controlFlowState) {

        this.id = id;
        this.controlFlowState = controlFlowState;
    }

    protected void addInputPin(Pin pin) {

    }

    protected void addOutputPin(Pin pin) {

    }

    public final INodeProvider.ControlFlowInput getControlFlowState() {
        return this.controlFlowState;
    }

    public final List<InputPin> getInputPins() {

    }

    public final List<OutputPin> getOutputPins() {

    }

    abstract ControlFlowPin getControlFlowInputPin();

    abstract ControlFlowPin getControlFlowOutputPin();

    abstract List<ResourcePin> getResourceInputPins();

    abstract List<ResourcePin> getResourceOutputPins();

    abstract List<DataPin> getDataInputPins();

    abstract List<DataPin> getDataOutputPins();

    // TODO: properly set this up
    final void render() {

    }

    // TODO: properly set this up
    void renderExtraContent() {

    }

    // TODO: properly set this up
    int extraContentHeight() {
        return 0;
    }

    public final int getId() {
        return this.id;
    }
}
