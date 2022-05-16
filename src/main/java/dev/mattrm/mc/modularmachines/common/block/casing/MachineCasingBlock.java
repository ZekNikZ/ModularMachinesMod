package dev.mattrm.mc.modularmachines.common.block.casing;

import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.block.base.BaseMachineCasingBlock;
import dev.mattrm.mc.modularmachines.common.util.MachinePosition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class MachineCasingBlock extends BaseMachineCasingBlock {
    public MachineCasingBlock(Properties properties, boolean connected) {
        super(properties, connected);
    }

    @Override
    public void connectToMachine(Level level, BlockPos pos, BlockPos controllerPos, MachinePosition machinePosition) {
        this.connectionHelper(level, pos, machinePosition, ModBlocks.MACHINE_CASING_CONNECTED.get());
    }

    @Override
    public void disconnectFromMachine(Level level, BlockPos pos, BlockPos controllerPos) {
        this.disconnectionHelper(level, pos, ModBlocks.MACHINE_CASING.get());
    }
}
