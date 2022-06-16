package dev.mattrm.mc.modularmachines.client.new_api.client.node.compmonent;

import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.NodeComponent;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.NodeComponentType;
import dev.mattrm.mc.modularmachines.client.new_api.client.node.compmonent.impl.SimpleTextNodeComponentRenderer;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.ModNodeComponents;

import java.util.Map;

public class ModNodeComponentRenderers {
    @FunctionalInterface
    public interface RendererConstructor<T extends NodeComponent, R extends NodeComponentRenderer<T>> {
        R create(T component);
    }

    private static Map<NodeComponentType<?>, RendererConstructor<?, ?>> RENDERERS;

    public static <T extends NodeComponent, R extends NodeComponentRenderer<T>> void register(NodeComponentType<T> type, RendererConstructor<T, R> constructor) {
        RENDERERS.put(type, constructor);
    }

    public static void registerDefaults() {
        register(ModNodeComponents.SIMPLE_TEXT.get(), SimpleTextNodeComponentRenderer::new);
    }
}
