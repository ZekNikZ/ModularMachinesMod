package dev.mattrm.mc.modularmachines.api.machine.pin;

public abstract class OutputPin<T, I extends InputPin<T>> extends Pin<T> {
    /**
     * Try pushing the current output to the specified input pin. Call {@link InputPin#tryApplyOutputValue(T)} within
     * this method.
     *
     * @param connectedPin the connected input pin
     * @return whether the push was successful
     */
    public abstract boolean tryProvideOutput(I connectedPin);

    @Override
    public final PinType ioType() {
        return PinType.OUTPUT;
    }
}
