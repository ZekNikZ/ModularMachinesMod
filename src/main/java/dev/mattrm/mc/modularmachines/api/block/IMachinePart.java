package dev.mattrm.mc.modularmachines.api.block;

import dev.mattrm.mc.modularmachines.common.util.MachinePosition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public interface IMachinePart {
    /**
     * Connect to a specified machine. If this block is already connected, this is a no-op.
     *
     * @param level           the level of the block to modify
     * @param pos             the position of the block to modify
     * @param machinePosition the position of this part on the machine
     * @param controllerPos   the position of the controller of the machine
     */
    void connectToMachine(Level level, BlockPos pos, BlockPos controllerPos, MachinePosition machinePosition);

    /**
     * Disconnect from the connected machine. If this block is not connected, this is a no-op.
     *
     * @param level           the level of the block to modify
     * @param pos             the position of the block to modify
     * @param machinePosition the position of this part on the machine
     */
    void disconnectFromMachine(Level level, BlockPos pos, BlockPos controllerPos);

    /**
     * Returns whether this machine part is connected to a machine.
     *
     * @param level the level of the block to check
     * @param pos   the position of the block to check
     * @return whether this machine part is connected to a machine
     */
    boolean isConnected(Level level, BlockPos pos);

    /**
     * Returns where this part is in the machine.
     *
     * @param level the level of the block to check
     * @param pos   the position of the block to check
     * @return the position of this block in the machine, or {@code null} if it is not connected
     */
    @Nullable
    MachinePosition getPosition(Level level, BlockPos pos);
}
