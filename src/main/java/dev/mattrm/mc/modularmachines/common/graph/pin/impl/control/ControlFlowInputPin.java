package dev.mattrm.mc.modularmachines.common.graph.pin.impl.control;

import dev.mattrm.mc.modularmachines.common.graph.node.Node;
import dev.mattrm.mc.modularmachines.common.graph.pin.ModPins;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.data.lang.ModMiscLangEntries;

public class ControlFlowInputPin extends InputPin {
    private final Node node;

    public ControlFlowInputPin(Node node) {
        super(ModPins.CONTROL_INPUT.get(), "control_flow_input", ModMiscLangEntries.CONTROL_FLOW_INPUT_NAME.getTranslationKey(), -1);
        this.node = node;
    }

    public void activate() {
        this.node.getManager().addToActivationQueue(this.node);
    }
}
