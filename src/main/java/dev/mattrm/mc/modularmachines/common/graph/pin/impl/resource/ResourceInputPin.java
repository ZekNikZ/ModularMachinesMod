package dev.mattrm.mc.modularmachines.common.graph.pin.impl.resource;

import dev.mattrm.mc.modularmachines.common.graph.pin.PinType;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.InputPin;

public abstract class ResourceInputPin extends InputPin {
    public ResourceInputPin(PinType<? extends ResourceInputPin> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }
}
