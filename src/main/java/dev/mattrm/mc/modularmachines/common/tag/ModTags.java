package dev.mattrm.mc.modularmachines.common.tag;

import dev.mattrm.mc.modularmachines.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MACHINE_WALLS = createTag("machine_walls");
        public static final TagKey<Block> MACHINE_CONTROLLERS = createTag("machine_controllers");
        public static final TagKey<Block> MACHINE_CORES = createTag("machine_cores");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(new ResourceLocation(Constants.MOD_ID, name));
        }

        private static TagKey<Block> createForgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
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
