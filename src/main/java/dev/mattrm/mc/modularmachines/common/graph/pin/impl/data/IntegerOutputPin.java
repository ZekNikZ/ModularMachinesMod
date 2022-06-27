package dev.mattrm.mc.modularmachines.common.graph.pin.impl.data;

import dev.mattrm.mc.modularmachines.common.graph.pin.PinType;

import java.util.function.Supplier;

public class IntegerOutputPin extends DataOutputPin<Integer> {
    public IntegerOutputPin(PinType<?> type, String id, String translationKey, int maxConnections, Class<Integer> dataType, Supplier<Integer> valueSupplier) {
        super(type, id, translationKey, maxConnections, dataType, valueSupplier);
    }
}
