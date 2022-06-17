package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinType;

public abstract class ResourceInputPin<T extends ResourceOutputPin<?>> extends InputPin {
    public ResourceInputPin(PinType<?> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }
}
