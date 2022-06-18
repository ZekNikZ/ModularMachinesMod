package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.resource;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinType;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.OutputPin;

public abstract class ResourceOutputPin extends OutputPin {
    public ResourceOutputPin(PinType<? extends ResourceOutputPin> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }

    public abstract boolean attemptTransfer(InputPin inputPin);
}
