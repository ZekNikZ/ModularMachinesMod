package dev.mattrm.mc.modularmachines.api.machine;

import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.function.Consumer;

public abstract class Pin {
    public enum PinType {
        INPUT,
        OUTPUT
    }

    public abstract PinType type();

    public abstract ResourceLocation icon();

    public abstract Color color();

    public abstract int maxConnections();
}

abstract class InputPin<T> extends Pin {
    // TODO: determine if needed?
    // tries to accept a given output value and returns whether or not the input pin can
    //   successfully do so
    // If truthy, the output buffer should be cleared
    // If falsy, the specific implementation is responsible for determining whether or not
    //   to transfer partial output
    // this method is provided as a convenience wrapper, if a pin needs to make a fully
    // custom solution, then simply return true
    public abstract boolean tryApplyOutputValue(T value);

    // TODO: return GUI widget, null if disabled
    // TODO: figure out how to best do this. probably will be to create an instance of the editor widget here and return it
    // TODO: this approach would let me delete `setFallbackValue` since the linked widget would be conditionally rendered
    public abstract EditorWidget<T> fallbackWidget();
    public abstract T fallbackValue();
    public abstract void setFallbackValue(T value);


    @Override
    public final PinType type() {
        return PinType.INPUT;
    }
}

abstract class OutputPin<T, I extends InputPin<T>> extends Pin {
    public abstract boolean tryProvideOutput(I connectedPin);

    @Override
    public final PinType type() {
        return PinType.OUTPUT;
    }
}

abstract class DataInputPin<T> extends InputPin<T> {
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

abstract class DataOutputPin<T, I extends DataInputPin<T>> extends OutputPin<T, I> {
    private T value;

    public DataOutputPin(T value) {
        this.value = value;
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

class Connection<T, I extends InputPin<T>> {
    public final I inputPin;
    public final OutputPin<T, I> outputPin;

    public Connection(I inputPin, OutputPin<T, I> outputPin) {
        this.inputPin = inputPin;
        this.outputPin = outputPin;
    }

    public boolean transferData() {
        return this.outputPin.tryProvideOutput(this.inputPin);
    }
}