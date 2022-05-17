package dev.mattrm.mc.modularmachines.common.tag;

import dev.mattrm.mc.modularmachines.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.*;
import java.util.function.Supplier;

public class ModTags {
    public static class Blocks {
        private static final Map<TagKey<Block>, List<TagKey<Block>>> BLOCK_TAGS = new HashMap<>();

        public static final TagKey<Block> MACHINE_CORES = createTag("machine/cores");
        public static final TagKey<Block> MACHINE_PORTS = createTag("machine/ports");
        public static final TagKey<Block> MACHINE_CONTROLLERS = createTag("machine/controllers");
        public static final TagKey<Block> MACHINE_WALLS = createTag("machine/walls", MACHINE_PORTS, MACHINE_CONTROLLERS);

        public static final TagKey<Block> METAL_MACHINE_PARTS = createTag("metal_machine_parts");

        @SafeVarargs
        private static TagKey<Block> createTag(String name, TagKey<Block>... children) {
            TagKey<Block> tag = BlockTags.create(new ResourceLocation(Constants.MOD_ID, name));
            BLOCK_TAGS.put(tag, Arrays.asList(children));
            return tag;
        }

        @SafeVarargs
        private static TagKey<Block> createForgeTag(String name, TagKey<Block>... children) {
            TagKey<Block> tag = BlockTags.create(new ResourceLocation("forge", name));
            BLOCK_TAGS.put(tag, Arrays.asList(children));
            return tag;
        }

        public static Map<TagKey<Block>, List<TagKey<Block>>> getRegisteredTags() {
            return BLOCK_TAGS;
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(new ResourceLocation(Constants.MOD_ID, name));
        }

        private static TagKey<Item> createForgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Fluids {
        private static TagKey<Fluid> createTag(String name) {
            return FluidTags.create(new ResourceLocation(Constants.MOD_ID, name));
        }

        private static TagKey<Fluid> createForgeTag(String name) {
            return FluidTags.create(new ResourceLocation("forge", name));
        }
    }
}
