package dev.mattrm.mc.modularmachines.common.old_blockentity;

import dev.mattrm.mc.modularmachines.common.old_multiblock.IMultiblockStructure;
import dev.mattrm.mc.modularmachines.common.old_multiblock.IMultiblockProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class MultiblockControllerBlockEntity extends BlockEntity implements IMultiblockProvider {
    private IMultiblockStructure multiblockStructure;

    public MultiblockControllerBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, IMultiblockStructure defaultStructure) {
        super(blockEntityType, blockPos, blockState);
        this.multiblockStructure = defaultStructure;
    }

    @Override
    public IMultiblockStructure getStructure() {
        return this.multiblockStructure;
    }

    @Override
    public void setStructure(IMultiblockStructure structure) {
        this.multiblockStructure = structure;
    }

    @Override
    public boolean checkValidity() {
        return this.multiblockStructure.checkValidity(this.getBlockPos(), this.getBlockState());
    }
}
