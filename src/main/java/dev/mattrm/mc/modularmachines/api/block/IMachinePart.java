package dev.mattrm.mc.modularmachines.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IMachinePart {
    void setConnected(Level level, BlockPos pos, boolean isConnected);
    boolean isConnected(Level level, BlockPos pos);
}
