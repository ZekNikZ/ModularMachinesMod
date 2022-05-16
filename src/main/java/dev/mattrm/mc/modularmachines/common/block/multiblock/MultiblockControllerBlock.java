package dev.mattrm.mc.modularmachines.common.block.multiblock;

import dev.mattrm.mc.modularmachines.api.block.IMachineController;
import net.minecraft.world.level.block.EntityBlock;

public abstract class MultiblockControllerBlock extends MultiblockCasingBlock implements EntityBlock, IMachineController {
    public MultiblockControllerBlock(Properties properties) {
        super(properties);
    }
}
