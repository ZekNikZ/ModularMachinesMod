package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.resource;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinType;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.InputPin;

public abstract class ResourceInputPin extends InputPin {
    public ResourceInputPin(PinType<? extends ResourceInputPin> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }
}
