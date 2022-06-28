package dev.mattrm.mc.modularmachines.common.graph.node.provider.library;

import dev.mattrm.mc.modularmachines.common.graph.node.provider.INodeProvider;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class NodeLibrary extends ForgeRegistryEntry<NodeLibrary> implements INodeProvider {
    @Override
    public boolean providesLibrary() {
        return true;
    }
}
