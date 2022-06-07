package dev.mattrm.mc.modularmachines.api.machine.pin.impl;

import dev.mattrm.mc.modularmachines.api.machine.Node;
import dev.mattrm.mc.modularmachines.api.machine.pin.InputPin;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ControlFlowInputPin extends InputPin<ControlFlowInputPin.ControlFlow> {
    private final Node node;

    public ControlFlowInputPin(Node node) {

        this.node = node;
    }

    // Dummy class to allow a custom type for control flow pins
    public static class ControlFlow {}

    @Override
    public boolean tryApplyOutputValue(ControlFlow value) {
        this.node.activate();
        return true;
    }

    @Override
    public ControlFlow fallbackValue() {
        return null;
    }

    @Override
    public void setFallbackValue(ControlFlow value) {
    }

    @Override
    public Class<ControlFlow> type() {
        return ControlFlow.class;
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
    public int maxConnections() {
        return -1;
    }

    @Override
    public String name() {
        return "Flow In";
    }
}
