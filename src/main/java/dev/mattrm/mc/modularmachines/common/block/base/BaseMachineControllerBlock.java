package dev.mattrm.mc.modularmachines.common.block.base;

import dev.mattrm.mc.modularmachines.api.block.IMachineController;

public abstract class BaseMachineControllerBlock extends BaseMachineCasingBlock implements IMachineController {
    public BaseMachineControllerBlock(Properties properties, boolean connected) {
        super(properties, connected);
    }
}
