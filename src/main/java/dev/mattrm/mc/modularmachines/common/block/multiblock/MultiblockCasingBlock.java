package dev.mattrm.mc.modularmachines.common.block.multiblock;

import dev.mattrm.mc.modularmachines.api.block.IMachineWall;
import dev.mattrm.mc.modularmachines.common.block.util.CustomBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public abstract class MultiblockCasingBlock extends MultiblockPartBlock implements IMachineWall {
    public MultiblockCasingBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(
            this.stateDefinition.any()
                .setValue(CustomBlockStateProperties.CONNECTED, false)
                .setValue(BlockStateProperties.UP, false)
                .setValue(BlockStateProperties.DOWN, false)
                .setValue(BlockStateProperties.NORTH, false)
                .setValue(BlockStateProperties.SOUTH, false)
                .setValue(BlockStateProperties.EAST, false)
                .setValue(BlockStateProperties.WEST, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.UP);
        builder.add(BlockStateProperties.DOWN);
        builder.add(BlockStateProperties.NORTH);
        builder.add(BlockStateProperties.SOUTH);
        builder.add(BlockStateProperties.EAST);
        builder.add(BlockStateProperties.WEST);
    }
}
