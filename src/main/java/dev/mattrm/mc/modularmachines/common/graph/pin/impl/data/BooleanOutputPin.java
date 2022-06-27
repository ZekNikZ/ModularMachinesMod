package dev.mattrm.mc.modularmachines.common.graph.pin.impl.data;

import dev.mattrm.mc.modularmachines.common.graph.pin.PinType;

import java.util.function.Supplier;

public class BooleanOutputPin extends DataOutputPin<Boolean> {
    public BooleanOutputPin(PinType<?> type, String id, String translationKey, int maxConnections, Class<Boolean> dataType, Supplier<Boolean> valueSupplier) {
        super(type, id, translationKey, maxConnections, dataType, valueSupplier);
    }
}
