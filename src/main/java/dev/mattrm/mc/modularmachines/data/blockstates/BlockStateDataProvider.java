package dev.mattrm.mc.modularmachines.data.blockstates;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.util.MachinePartPosition;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.Map;

public class BlockStateDataProvider extends BlockStateProvider {
    public BlockStateDataProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    public final Map<MachinePartPosition, ModelFile> MACHINE_PART_MODELS = new HashMap<>();

    @Override
    protected void registerStatesAndModels() {
        // Machine Parts
        MachinePartPosition.VALUES.forEach(pos -> MACHINE_PART_MODELS.put(pos,
            // TODO: finish this
            this.models().getBuilder("machine/" + pos.getSerializedName())
        ));

        // Blocks
        ModBlocks.getRegisteredBlocks().forEach(block -> {
            if (block instanceof IBlockStateDataProvider stateProvider) {
                stateProvider.dataBlockState(this);
            }
        });
    }
}
