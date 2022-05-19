package dev.mattrm.mc.modularmachines.api.machine;

import java.util.List;

public abstract class Node {
    private final int id;

    public Node(int id) {

        this.id = id;
    }

    abstract INodeProvider.ControlFlowInput getControlFlowState();

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
