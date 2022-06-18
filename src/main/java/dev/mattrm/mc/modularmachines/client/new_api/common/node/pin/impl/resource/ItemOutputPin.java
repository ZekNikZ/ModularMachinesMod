package dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.resource;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.ModPins;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.InputPin;

public class ItemOutputPin extends ResourceOutputPin {
    public ItemOutputPin(String id, String translationKey, int maxConnections) {
        super(ModPins.ITEM_OUTPUT.get(), id, translationKey, maxConnections);
    }

    @Override
    public boolean attemptTransfer(InputPin inputPin) {
        ItemInputPin itemInputPin = ((ItemInputPin) inputPin);

        return false;
    }
}
