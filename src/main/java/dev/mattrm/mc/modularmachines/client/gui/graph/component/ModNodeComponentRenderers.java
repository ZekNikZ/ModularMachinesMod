package dev.mattrm.mc.modularmachines.client.gui.graph.component;

import dev.mattrm.mc.modularmachines.client.gui.graph.component.impl.PinComponentRenderer;
import dev.mattrm.mc.modularmachines.common.graph.component.NodeComponent;
import dev.mattrm.mc.modularmachines.common.graph.component.NodeComponentType;
import dev.mattrm.mc.modularmachines.client.gui.graph.component.impl.SimpleTextNodeComponentRenderer;
import dev.mattrm.mc.modularmachines.common.graph.component.ModNodeComponents;

import java.util.HashMap;
import java.util.Map;

public class ModNodeComponentRenderers {
    @FunctionalInterface
    public interface RendererConstructor<T extends NodeComponent, R extends NodeComponentRenderer<T>> {
        R create(T component);
    }

    private static final Map<NodeComponentType<?>, RendererConstructor<?, ?>> RENDERERS = new HashMap<>();

    public static <T extends NodeComponent, R extends NodeComponentRenderer<T>> void register(NodeComponentType<T> type, RendererConstructor<T, R> constructor) {
        RENDERERS.put(type, constructor);
    }

    public static void registerDefaults() {
        register(ModNodeComponents.SIMPLE_TEXT.get(), SimpleTextNodeComponentRenderer::new);
        register(ModNodeComponents.PINS.get(), PinComponentRenderer::new);
    }
}
