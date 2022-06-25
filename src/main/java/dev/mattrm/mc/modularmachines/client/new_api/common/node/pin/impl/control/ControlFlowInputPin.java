package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.control;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.Node;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.ModPins;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.InputPin;
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
