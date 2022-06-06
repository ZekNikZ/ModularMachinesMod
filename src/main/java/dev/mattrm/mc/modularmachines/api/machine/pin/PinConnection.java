package dev.mattrm.mc.modularmachines.api.machine.pin;

public class PinConnection<T, I extends InputPin<T>> {
    public final I inputPin;
    public final OutputPin<T, I> outputPin;

    public PinConnection(I inputPin, OutputPin<T, I> outputPin) {
        this.inputPin = inputPin;
        this.outputPin = outputPin;
    }

    public boolean transferData() {
        return this.outputPin.tryProvideOutput(this.inputPin);
    }
}