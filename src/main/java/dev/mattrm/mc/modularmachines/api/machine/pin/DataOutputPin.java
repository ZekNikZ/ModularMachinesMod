package dev.mattrm.mc.modularmachines.api.machine.pin;

public abstract class DataOutputPin<T, I extends DataInputPin<T>> extends OutputPin<T, I> {
    private T value;

    public DataOutputPin(T value) {
        this.setValue(value);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean tryProvideOutput(I connectedPin) {
        return connectedPin.tryApplyOutputValue(this.getValue());
    }
}
