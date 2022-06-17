package dev.mattrm.mc.modularmachines.client.new_api.common.node;

public enum ControlFlowOutput {
    /**
     * No output pin.
     * Example: mode nodes
     */
    DISABLED(false),
    /**
     * Output pin.
     * Example: output nodes
     */
    ENABLED(true);

    private final boolean outputPin;

    ControlFlowOutput(boolean outputPin) {
        this.outputPin = outputPin;
    }

    public boolean hasOutputPin() {
        return this.outputPin;
    }
}
