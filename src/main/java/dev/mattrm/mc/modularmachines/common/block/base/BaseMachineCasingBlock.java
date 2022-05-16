package dev.mattrm.mc.modularmachines.common.block.base;

import dev.mattrm.mc.modularmachines.api.block.IMachineWall;
import dev.mattrm.mc.modularmachines.common.util.MachinePosition;

public abstract class BaseMachineCasingBlock extends BaseMachinePartBlock implements IMachineWall {
    public BaseMachineCasingBlock(Properties properties, boolean connected) {
        super(properties, connected, MachinePosition.NONE);
    }
}
