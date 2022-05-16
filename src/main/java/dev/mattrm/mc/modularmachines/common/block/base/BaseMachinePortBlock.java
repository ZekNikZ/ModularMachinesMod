package dev.mattrm.mc.modularmachines.common.block.base;

import dev.mattrm.mc.modularmachines.api.block.IMachinePort;

public abstract class BaseMachinePortBlock extends BaseMachineCasingBlock implements IMachinePort {
    public BaseMachinePortBlock(Properties properties, boolean connected) {
        super(properties, connected);
    }
}
