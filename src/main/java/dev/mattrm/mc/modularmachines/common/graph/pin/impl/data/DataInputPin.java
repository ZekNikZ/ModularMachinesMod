package dev.mattrm.mc.modularmachines.common.graph.pin.impl.data;

import dev.mattrm.mc.modularmachines.common.graph.pin.PinType;
import dev.mattrm.mc.modularmachines.common.graph.pin.impl.InputPin;

public abstract class DataInputPin<T> extends InputPin {
    private T value;
    private final Class<T> dataType;

    public DataInputPin(PinType<?> type, String id, String translationKey, int maxConnections, Class<T> dataType) {
        this(type, id, translationKey, maxConnections, dataType, null);
    }

    public DataInputPin(PinType<?> type, String id, String translationKey, int maxConnections, Class<T> dataType, T initialValue) {
        super(type, id, translationKey, maxConnections);
        this.dataType = dataType;
        this.value = initialValue;
    }

    public Class<T> getDataType() {
        return this.dataType;
    }

    public T getValue() {
        return this.value;
    }

    protected void internalSetValue(T value) {
        this.value = value;
    }

    public boolean setValue(Object value) {
        if (this.dataType.isInstance(value)) {
            this.internalSetValue((T) value);
            return true;
        }

        throw new UnsupportedOperationException("This pin does not support object values");
    }
}
