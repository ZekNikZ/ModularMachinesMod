package dev.mattrm.mc.modularmachines.common.graph.node.impl;

import dev.mattrm.mc.modularmachines.common.graph.node.provider.library.NodeLibrary;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class StandardNodeLibrary extends NodeLibrary {
    @Override
    public @NotNull Map<String, NodeConstructor<?>> getNodeBuilders() {
        return Map.of();
    }
}
