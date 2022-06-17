package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin;

public abstract class Pin {
    public enum Type {
        /**
         * An INPUT pin, meaning that it accepts input from output pins.
         */
        INPUT,

        /**
         * An OUTPUT pin, meaning that it pushes output to input pins.
         */
        OUTPUT
    }

    private final PinType<?> type;
    private final String id;
    private final String translationKey;
    private final int maxConnections;

    public Pin(PinType<?> type, String id, String translationKey, int maxConnections) {
        this.type = type;
        this.id = id;
        this.translationKey = translationKey;
        this.maxConnections = maxConnections;
    }

    public PinType<?> type() {
        return this.type;
    }

    public String id() {
        return this.id;
    }

    public String translationKey() {
        return this.translationKey;
    }

    public int maxConnections() {
        return this.maxConnections;
    }
}