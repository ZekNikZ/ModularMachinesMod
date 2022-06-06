package dev.mattrm.mc.modularmachines.api.machine.pin;

import java.util.function.Consumer;

public abstract class DataInputPin<T> extends InputPin<T> {
    private final Consumer<T> setter;

    public DataInputPin(Consumer<T> setter) {
        this.setter = setter;
    }

    @Override
    public boolean tryApplyOutputValue(T value) {
        this.setter.accept(value);
        return true;
    }
}
