package dev.mattrm.mc.modularmachines.api.machine.pin.impl;

import dev.mattrm.mc.modularmachines.api.machine.pin.OutputPin;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ControlFlowOutputPin extends OutputPin<ControlFlowInputPin.ControlFlow, ControlFlowInputPin> {
    @Override
    public boolean tryProvideOutput(ControlFlowInputPin connectedPin) {
        return connectedPin.tryApplyOutputValue(null);
    }

    @Override
    public Class<ControlFlowInputPin.ControlFlow> type() {
        return ControlFlowInputPin.ControlFlow.class;
    }

    @Override
    public ResourceLocation icon() {
        return TextureManager.INTENTIONAL_MISSING_TEXTURE;
    }

    @Override
    public Color color() {
        return Color.WHITE;
    }

    @Override
    public int maxConnectionCount() {
        return -1;
    }

    @Override
    public String name() {
        return "Flow Out";
    }
}
