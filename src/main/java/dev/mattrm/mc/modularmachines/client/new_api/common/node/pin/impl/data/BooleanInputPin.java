package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.data;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.ModPins;

public class BooleanInputPin extends DataInputPin<Boolean> {
    public BooleanInputPin(String id, String translationKey, int maxConnections, Class<Boolean> dataType) {
        super(ModPins.BOOLEAN_INPUT.get(), id, translationKey, maxConnections, dataType);
    }

    public BooleanInputPin(String id, String translationKey, int maxConnections, Class<Boolean> dataType, Boolean initialValue) {
        super(ModPins.BOOLEAN_INPUT.get(), id, translationKey, maxConnections, dataType, initialValue);
    }

    @Override
    public boolean setValue(Object value) {
        if (value instanceof Boolean val) {
            this.internalSetValue(val);
            return true;
        }

        if (value instanceof Integer val) {
            this.internalSetValue(val != 0);
            return true;
        }

        return false;
    }
}
