package dev.mattrm.mc.modularmachines.common.block.multiblock;

import dev.mattrm.mc.modularmachines.api.block.IMachinePart;
import dev.mattrm.mc.modularmachines.common.block.util.CustomBlockStateProperties;
import dev.mattrm.mc.modularmachines.common.util.Cuboid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public abstract class MultiblockPartBlock extends Block implements IMachinePart {
    public MultiblockPartBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(
            this.stateDefinition.any()
                .setValue(CustomBlockStateProperties.CONNECTED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CustomBlockStateProperties.CONNECTED);
    }

    @Override
    public void setConnected(Level level, BlockPos pos, boolean isConnected, @Nullable Cuboid bounds) {
        level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(CustomBlockStateProperties.CONNECTED, isConnected));
    }

    @Override
    public boolean isConnected(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos).getValue(CustomBlockStateProperties.CONNECTED);
    }
}
