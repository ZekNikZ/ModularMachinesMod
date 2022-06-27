package dev.mattrm.mc.modularmachines.common.graph.pin.impl.control;

import dev.mattrm.mc.modularmachines.common.graph.pin.ModPins;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.OutputPin;
import dev.mattrm.mc.modularmachines.data.lang.ModMiscLangEntries;

public class ControlFlowOutputPin extends OutputPin {
    public ControlFlowOutputPin() {
        super(ModPins.CONTROL_OUTPUT.get(), "control_flow_output", ModMiscLangEntries.CONTROL_FLOW_OUTPUT_NAME.getTranslationKey(), -1);
    }

    @Override
    public boolean attemptTransfer(InputPin inputPin) {
        ((ControlFlowInputPin) inputPin).activate();
        return true;
    }
}
