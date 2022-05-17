package dev.mattrm.mc.modularmachines.data.blockstates;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateDataProvider extends BlockStateProvider {
    public BlockStateDataProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Blocks
        ModBlocks.getRegisteredBlocks().forEach(block -> {
            if (block instanceof IBlockStateDataProvider stateProvider) {
                stateProvider.dataBlockState(this);
            }
        });
    }
}
