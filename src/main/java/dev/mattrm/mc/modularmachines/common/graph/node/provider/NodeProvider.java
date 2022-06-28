package dev.mattrm.mc.modularmachines.common.graph.node.provider;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NodeProvider extends ForgeRegistryEntry<NodeProvider> {
    @Nullable
    public abstract INodeProvider.NodeConstructor<?> create(@NotNull Level level, @NotNull String name, @NotNull CompoundTag extraData);
}
