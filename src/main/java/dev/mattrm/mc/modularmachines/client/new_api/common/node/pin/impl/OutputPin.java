package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.Pin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinType;

public class OutputPin extends Pin {
    public OutputPin(PinType<?> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }
}
