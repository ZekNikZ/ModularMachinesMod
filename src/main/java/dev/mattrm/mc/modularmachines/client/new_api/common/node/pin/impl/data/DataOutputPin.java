package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.data;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinType;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.OutputPin;

import java.util.function.Supplier;

public abstract class DataOutputPin<T> extends OutputPin {
    private final Supplier<T> valueSupplier;
    private final Class<T> dataType;

    public DataOutputPin(PinType<?> type, String id, String translationKey, int maxConnections, Class<T> dataType, Supplier<T> valueSupplier) {
        super(type, id, translationKey, maxConnections);
        this.valueSupplier = valueSupplier;
        this.dataType = dataType;
    }

    public Class<T> getDataType() {
        return this.dataType;
    }

    @Override
    public boolean attemptTransfer(InputPin inputPin) {
        DataInputPin<T> dataInputPin = ((DataInputPin<T>) inputPin);
        return dataInputPin.setValue(this.valueSupplier.get());
    }
}
