package dev.mattrm.mc.modularmachines.common.block.base;

import dev.mattrm.mc.modularmachines.api.block.IMachineCore;
import dev.mattrm.mc.modularmachines.common.util.MachinePartPosition;

public abstract class BaseMachineCoreBlock extends BaseMachinePartBlock implements IMachineCore {
    public BaseMachineCoreBlock(Properties properties, boolean connected) {
        super(properties, connected, MachinePartPosition.INSIDE);
    }
}
