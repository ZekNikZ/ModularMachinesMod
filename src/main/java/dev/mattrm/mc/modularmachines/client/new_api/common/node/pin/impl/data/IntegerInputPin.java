package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.data;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.ModPins;

public class IntegerInputPin extends DataInputPin<Integer> {
    public IntegerInputPin(String id, String translationKey, int maxConnections, Class<Integer> dataType) {
        super(ModPins.INTEGER_INPUT.get(), id, translationKey, maxConnections, dataType);
    }

    public IntegerInputPin(String id, String translationKey, int maxConnections, Class<Integer> dataType, Integer initialValue) {
        super(ModPins.INTEGER_INPUT.get(), id, translationKey, maxConnections, dataType, initialValue);
    }
}
