package dev.mattrm.mc.modularmachines.common.old_multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.Set;

public class LayeredCuboidMultiblockStructure implements IMultiblockStructure {
    private final int minX, maxX, minY, maxY, minZ, maxZ;
    private final Set<ResourceLocation> outsideBlocks;
    private final Set<TagKey<Block>> outsideTags;
    private final Set<ResourceLocation> insideBlocks;
    private final Set<TagKey<Block>> insideTags;

    public LayeredCuboidMultiblockStructure(int minX, int maxX, int minY, int maxY, int minZ, int maxZ, Set<ResourceLocation> outsideBlocks, Set<TagKey<Block>> outsideTags, Set<ResourceLocation> insideBlocks, Set<TagKey<Block>> insideTags) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.outsideBlocks = outsideBlocks;
        this.outsideTags = outsideTags;
        this.insideBlocks = insideBlocks;
        this.insideTags = insideTags;
    }

    @Override
    public boolean checkValidity(BlockPos blockPos, BlockState blockState) {
        return false;
    }

    private static class Builder {
        private int minX, maxX, minY, maxY, minZ, maxZ;
        private Set<ResourceLocation> outsideBlocks;
        private Set<TagKey<Block>> outsideTags;
        private Set<ResourceLocation> insideBlocks;
        private Set<TagKey<Block>> insideTags;

        private Builder() {
            this.minX = this.maxX = 3;
            this.minY = this.maxY = 3;
            this.minZ = this.maxZ = 3;
        }

        public void setSizeX(int sizeX) {
            this.minX = this.maxX = sizeX;
        }

        public void setSizeX(int minX, int maxX) {
            this.minX = minX;
            this.maxX = maxX;
        }

        public void setSizeY(int sizeY) {
            this.minY = this.maxY = sizeY;
        }

        public void setSizeY(int minY, int maxY) {
            this.minY = minY;
            this.maxY = maxY;
        }

        public void setSizeZ(int sizeZ) {
            this.minZ = this.maxZ = sizeZ;
        }

        public void setSizeZ(int minZ, int maxZ) {
            this.minZ = minZ;
            this.maxZ = maxZ;
        }

        public void addOutsideOption(ResourceLocation... blocks) {
            this.outsideBlocks.addAll(Arrays.asList(blocks));
        }

        public void addInsideOption(ResourceLocation... blocks) {
            this.insideBlocks.addAll(Arrays.asList(blocks));
        }

        @SafeVarargs
        public final void addOutsideOption(TagKey<Block>... blocks) {
            this.outsideTags.addAll(Arrays.asList(blocks));
        }

        @SafeVarargs
        public final void addInsideOption(TagKey<Block>... blocks) {
            this.insideTags.addAll(Arrays.asList(blocks));
        }

        public LayeredCuboidMultiblockStructure build() {
            return new LayeredCuboidMultiblockStructure(this.minX, this.maxX, this.minY, this.maxY, this.minZ, this.maxZ, this.outsideBlocks, this.outsideTags, this.insideBlocks, this.insideTags);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
