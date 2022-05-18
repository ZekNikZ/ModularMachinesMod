package dev.mattrm.mc.modularmachines.data.blockstates;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.MultiLayerModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockStateDataProvider extends BlockStateProvider {
    public BlockStateDataProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    public final List<ModelFile> MACHINE_PART_MODEL_TEMPLATES = new ArrayList<>();

    @Override
    protected void registerStatesAndModels() {
        // Machine Parts
        MACHINE_PART_MODEL_TEMPLATES.addAll(Arrays.asList(
            // 0
            this.models().getBuilder("block/machine/template_0")
                .element()
                .allFaces((dir, x) -> x.texture("#all")
                    .cullface(dir)
                    .uvs(4, 0, 8, 16)
                    .end())
                .face(Direction.UP)
                    .uvs(0, 0, 4, 16)
                    .end()
                .end(),
            // 1
            this.models().getBuilder("block/machine/template_1")
                .element()
                .allFaces((dir, x) -> x.texture("#all")
                    .cullface(dir)
                    .uvs(4, 0, 8, 16)
                    .end())
                .face(Direction.UP)
                .uvs(8, 0, 12, 16)
                .end()
                .face(Direction.NORTH)
                .uvs(8, 0, 12, 16)
                .end()
                .end(),
            // 2
            this.models().getBuilder("block/machine/template_2")
                .element()
                .allFaces((dir, x) -> x.texture("#all")
                    .cullface(dir)
                    .uvs(4, 0, 8, 16)
                    .end())
                .face(Direction.NORTH)
                .uvs(8, 0, 12, 16)
                .rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90)
                .end()
                .face(Direction.EAST)
                .uvs(8, 0, 12, 16)
                .rotation(ModelBuilder.FaceRotation.CLOCKWISE_90)
                .end()
                .end(),
            // 3
            this.models().getBuilder("block/machine/template_3")
                .element()
                .allFaces((dir, x) -> x.texture("#all")
                    .cullface(dir)
                    .uvs(4, 0, 8, 16)
                    .end())
                .face(Direction.UP)
                .uvs(12, 0, 16, 16)
                .rotation(ModelBuilder.FaceRotation.CLOCKWISE_90)
                .end()
                .face(Direction.NORTH)
                .uvs(12, 0, 16, 16)
                .end()
                .face(Direction.EAST)
                .uvs(12, 0, 16, 16)
                .rotation(ModelBuilder.FaceRotation.CLOCKWISE_90)
                .end()
                .end(),
            // 4
            this.models().getBuilder("block/machine/template_4")
                .element()
                .allFaces((dir, x) -> x.texture("#all")
                    .cullface(dir)
                    .uvs(4, 0, 8, 16)
                    .end())
                .end()));

        // Blocks
        ModBlocks.getRegisteredBlocks().forEach(block -> {
            if (block instanceof IBlockStateDataProvider stateProvider) {
                stateProvider.dataBlockState(this);
            }
        });
    }
}
