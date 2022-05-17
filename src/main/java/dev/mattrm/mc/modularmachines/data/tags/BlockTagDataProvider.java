package dev.mattrm.mc.modularmachines.data.tags;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.tag.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockTagDataProvider extends BlockTagsProvider {
    public BlockTagDataProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // Get and apply all tag-assigned tags
        ModTags.Blocks.getRegisteredTags().forEach((tag, children) -> {
            this.tag(tag); // ensures empty tags are created still
            children.forEach(child -> this.tag(tag).addTag(child));
        });

        // Get all block-assigned tags
        Map<TagKey<Block>, List<Block>> tags = new HashMap<>();
        ModBlocks.getRegisteredBlocks().forEach(block -> {
            if (block instanceof IBlockTagDataProvider dataProvider) {
                List<TagKey<Block>> tagKeys = dataProvider.dataTags();
                if (tagKeys != null) {
                    tagKeys.forEach(tag -> {
                        tags.putIfAbsent(tag, new ArrayList<>());
                        tags.get(tag).add(block);
                    });
                }
            }
        });

        // Apply tags
        tags.forEach((tag, blocks) -> blocks.forEach(block -> this.tag(tag).add(block)));

        // Additional tags
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .addTag(ModTags.Blocks.METAL_MACHINE_PARTS);
        this.tag(BlockTags.NEEDS_IRON_TOOL)
            .addTag(ModTags.Blocks.METAL_MACHINE_PARTS);
    }

    @Override
    public @NotNull String getName() {
        return Constants.MOD_NAME + " Tags";
    }
}
