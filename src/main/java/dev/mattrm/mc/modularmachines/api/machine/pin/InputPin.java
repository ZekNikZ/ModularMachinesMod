package dev.mattrm.mc.modularmachines.api.machine.pin;

public abstract class InputPin<T> extends Pin<T> {
    /**
     * Tries to accept a given output value and returns whether the input pin can successfully do so. If truthy, the
     * output buffer should be fully cleared. If falsy, the specific implementation is responsible for determining
     * whether to transfer partial output.
     * <p>
     * This method is provided as a convenience wrapper, if a pin needs to make a fully custom solution, then simply
     * return true.
     *
     * @param value the value to try to apply
     * @return whether the transfer was successful
     */
    public abstract boolean tryApplyOutputValue(T value);

    // TODO: return GUI widget, null if disabled
    // TODO: figure out how to best do this. probably will be to create an instance of the editor widget here and return it
    // TODO: this approach would let me delete `setFallbackValue` since the linked widget would be conditionally rendered
//    public abstract EditorWidget<T> fallbackWidget();
    public abstract T fallbackValue();

    public abstract void setFallbackValue(T value);

    @Override
    public final PinType ioType() {
        return PinType.INPUT;
    }
}
