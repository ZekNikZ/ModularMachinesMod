package dev.mattrm.mc.modularmachines.common.graph.pin.impl.data;

import dev.mattrm.mc.modularmachines.common.graph.pin.ModPins;

public class IntegerInputPin extends DataInputPin<Integer> {
    public IntegerInputPin(String id, String translationKey, int maxConnections, Class<Integer> dataType) {
        super(ModPins.INTEGER_INPUT.get(), id, translationKey, maxConnections, dataType);
    }

    public IntegerInputPin(String id, String translationKey, int maxConnections, Class<Integer> dataType, Integer initialValue) {
        super(ModPins.INTEGER_INPUT.get(), id, translationKey, maxConnections, dataType, initialValue);
    }

    @Override
    public boolean setValue(Object value) {
        if (value instanceof Integer val) {
            this.internalSetValue(val);
            return true;
        }

        if (value instanceof Boolean val) {
            this.internalSetValue(val ? 1 : 0);
            return true;
        }

        return false;
    }
}
