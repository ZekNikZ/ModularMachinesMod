package dev.mattrm.mc.modularmachines.common.graph.node.provider;

import dev.mattrm.mc.modularmachines.common.graph.node.provider.library.ModNodeLibraries;
import dev.mattrm.mc.modularmachines.common.graph.node.provider.library.NodeLibrary;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LibraryNodeProvider extends NodeProvider {
    @Override
    @Nullable
    public INodeProvider.NodeConstructor<?> create(@NotNull Level level, @NotNull String name, @NotNull CompoundTag extraData) {
        NodeLibrary library = ModNodeLibraries.REGISTRY.get().getValue(ResourceLocation.tryParse(extraData.getString("library")));
        if (library != null && library.providesLibrary()) {
            return library.getNodeBuilders().get(name);
        }
        return null;
    }
}
