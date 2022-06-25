package dev.mattrm.mc.modularmachines.common.graph.pin.impl.resource;

import dev.mattrm.mc.modularmachines.common.graph.pin.PinType;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.OutputPin;

public abstract class ResourceOutputPin extends OutputPin {
    public ResourceOutputPin(PinType<? extends ResourceOutputPin> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }

    public abstract boolean attemptTransfer(InputPin inputPin);
}
