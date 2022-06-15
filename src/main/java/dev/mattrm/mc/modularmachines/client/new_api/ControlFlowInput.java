package dev.mattrm.mc.modularmachines.client.new_api;

public enum ControlFlowInput {
    /**
     * No input pin and no automatic control flow.
     * Example: external machine nodes
     */
    DISABLED(false, false),
    /**
     * No input pin and automatic control flow (activates upon input push).
     * Example: logic gates
     */
    AUTOMATIC(false, true),
    /**
     * Input pin and no automatic control flow.
     * Example: conditional nodes
     */
    ENABLED(true, false),
    /**
     * Input pin and automatic control flow.
     * Example: most processing nodes
     */
    ENABLED_AUTOMATIC(true, true);

    private final boolean inputPin;
    private final boolean automatic;

    ControlFlowInput(boolean inputPin, boolean automatic) {
        this.inputPin = inputPin;
        this.automatic = automatic;
    }

    public boolean hasInputPin() {
        return this.inputPin;
    }

    public boolean isAutomatic() {
        return this.automatic;
    }
}
