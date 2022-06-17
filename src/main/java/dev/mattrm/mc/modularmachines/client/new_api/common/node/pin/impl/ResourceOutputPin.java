package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinType;

public abstract class ResourceOutputPin<T extends ResourceInputPin<?>> extends OutputPin {
    public ResourceOutputPin(PinType<?> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }

    public abstract boolean attemptTransfer(T inputPin);
}
