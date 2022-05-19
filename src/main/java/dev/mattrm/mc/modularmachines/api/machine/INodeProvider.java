package dev.mattrm.mc.modularmachines.api.machine;

import java.util.List;
import java.util.function.Function;

public interface INodeProvider {
    enum ControlFlowInput {
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
            return inputPin;
        }

        public boolean isAutomatic() {
            return automatic;
        }
    }

    /**
     * Returns a list of Node constructors. If this provider needs to keep
     * track of these nodes, return a function to create the node and store
     * a reference to it. However, the recommended way is to instead create
     * a subclass of Node in which you also pass a reference to the provider
     * and have the node be reactive to the state of the provider, not the
     * other way around.
     *
     * @return a list of Node constructors
     */
    List<Function<Integer, Node>> getNodeBuilders();
}
