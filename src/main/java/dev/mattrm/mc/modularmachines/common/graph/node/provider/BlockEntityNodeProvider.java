package dev.mattrm.mc.modularmachines.common.graph.node.provider;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockEntityNodeProvider extends NodeProvider {
    @Override
    @Nullable
    public INodeProvider.NodeConstructor<?> create(@NotNull Level level, @NotNull String name, @NotNull CompoundTag extraData) {
        BlockPos pos = NbtUtils.readBlockPos(extraData.getCompound("pos"));
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof INodeProvider provider) {
            return provider.getNodeBuilders().get(name);
        }

        return null;
    }
}
