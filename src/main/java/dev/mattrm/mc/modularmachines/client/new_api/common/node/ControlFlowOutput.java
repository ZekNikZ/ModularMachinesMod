package dev.mattrm.mc.modularmachines.client.new_api.common.node;

public enum ControlFlowOutput {
    /**
     * No input pin and no automatic control flow.
     * Example: external machine nodes
     */
    DISABLED(false, false),
    /**
     * Input pin and no automatic control flow.
     * Example: conditional nodes
     */
    ENABLED(true, false);

    private final boolean outputPin;

    ControlFlowOutput(boolean inputPin, boolean automatic) {
        this.outputPin = inputPin;
    }

    public boolean hasOutputPin() {
        return this.outputPin;
    }
}
