package dev.mattrm.mc.modularmachines.common.old_multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public interface IMultiblockStructure {
    /**
     * Check the validity of this particular structure. The structure may not need this.
     *
     * @param blockPos   the block position to check
     * @param blockState the block state of the controller
     * @return whether this structure is true or not
     */
    boolean checkValidity(BlockPos blockPos, BlockState blockState);
}