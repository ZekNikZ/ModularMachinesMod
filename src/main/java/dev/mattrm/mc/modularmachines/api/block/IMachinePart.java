package dev.mattrm.mc.modularmachines.api.block;

import dev.mattrm.mc.modularmachines.common.util.Cuboid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

public interface IMachinePart {
    void setConnected(Level level, BlockPos pos, boolean isConnected, @Nullable Cuboid bounds);
    boolean isConnected(LevelAccessor level, BlockPos pos);
}
