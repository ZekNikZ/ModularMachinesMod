package dev.mattrm.mc.modularmachines.common.graph.pin;

public abstract class PinBuilder<T extends Pin> {
    protected final PinType<?> type;
    protected final String id;
    protected final String translationKey;
    protected final int maxConnections;

    public PinBuilder(PinType<?> type, String id, String translationKey, int maxConnections) {
        this.type = type;
        this.id = id;
        this.translationKey = translationKey;
        this.maxConnections = maxConnections;
    }

    public abstract T build();
}
