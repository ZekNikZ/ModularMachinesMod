package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinBuilder;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.PinType;

public class ItemOutputPin extends ResourceOutputPin<ItemInputPin> {
    public ItemOutputPin(PinType<?> type, String id, String translationKey, int maxConnections) {
        super(type, id, translationKey, maxConnections);
    }

    @Override
    public boolean attemptTransfer(ItemInputPin inputPin) {
        return false;
    }

    public static PinBuilder<ItemOutputPin> create(PinType<?> pinType, String id, String translationKey, int maxConnections) {
        return new PinBuilder<>(pinType, id, translationKey, maxConnections) {
            @Override
            public ItemOutputPin build() {
                return new ItemOutputPin(this.type, this.id, this.translationKey, this.maxConnections);
            }
        };
    }
}
