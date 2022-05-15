package dev.mattrm.mc.modularmachines.common.block;

import dev.mattrm.mc.modularmachines.common.block.multiblock.MultiblockCasingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class MachineCasingBlock extends MultiblockCasingBlock {
    public MachineCasingBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL));
    }
}
