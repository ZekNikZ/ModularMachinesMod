package dev.mattrm.mc.modularmachines.client.new_api.common.node;

public enum ControlFlowInput {
    /**
     * No input pin and no automatic control flow.
     * Example: external machine nodes
     */
    DISABLED(false, false, true),
    /**
     * No input pin and automatic control flow (activates upon input push).
     * Example: logic gates
     */
    AUTOMATIC(false, true, true),
    /**
     * No input pin and automatic control flow (except for data pins).
     * Example: logic gates
     */
    AUTOMATIC_EXCEPT_DATA(false, true, false),
    /**
     * Input pin and no automatic control flow.
     * Example: conditional nodes
     */
    ENABLED(true, false, false),
    /**
     * Input pin and automatic control flow.
     * Example: most processing nodes
     */
    ENABLED_AUTOMATIC(true, true, true),
    /**
     * Input pin and automatic control flow (except for data pins).
     * Example: most processing nodes
     */
    ENABLED_AUTOMATIC_EXCEPT_DATA(true, true, false);

    private final boolean inputPin;
    private final boolean automatic;
    private final boolean automaticExceptData;

    ControlFlowInput(boolean inputPin, boolean automatic, boolean automaticExceptData) {
        this.inputPin = inputPin;
        this.automatic = automatic;
        this.automaticExceptData = automaticExceptData;
    }

    public boolean hasInputPin() {
        return this.inputPin;
    }

    public boolean isAutomatic() {
        return this.automatic;
    }

    public boolean isAutomaticExceptData() {
        return this.automaticExceptData;
    }
}
