package dev.mattrm.mc.modularmachines.common.graph.pin.impl;

import dev.mattrm.mc.modularmachines.common.graph.pin.Pin;
import dev.mattrm.mc.modularmachines.common.graph.pin.PinType;

public abstract class OutputPin extends Pin {
    public OutputPin(PinType<?> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }

    public abstract boolean attemptTransfer(InputPin inputPin);
}
