package dev.mattrm.mc.modularmachines.common.block.base;

import dev.mattrm.mc.modularmachines.api.block.IMachinePart;
import dev.mattrm.mc.modularmachines.common.block.util.CustomBlockStateProperties;
import dev.mattrm.mc.modularmachines.common.util.MachinePosition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public abstract class BaseMachinePartBlock extends Block implements IMachinePart {
    protected final boolean connected;

    public BaseMachinePartBlock(Properties properties, boolean connected, MachinePosition defaultPosition) {
        super(properties);
        this.connected = connected;

        this.registerDefaultState(
            this.stateDefinition.any()
                .setValue(CustomBlockStateProperties.CONNECTED, connected)
                .setValue(CustomBlockStateProperties.MACHINE_POSITION, defaultPosition)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CustomBlockStateProperties.CONNECTED);
        builder.add(CustomBlockStateProperties.MACHINE_POSITION);
    }

    @Override
    public boolean isConnected(Level level, BlockPos pos) {
        return this.connected;
    }

    @Nullable
    @Override
    public MachinePosition getPosition(Level level, BlockPos pos) {
        return this.connected ? level.getBlockState(pos).getValue(CustomBlockStateProperties.MACHINE_POSITION) : null;
    }

    protected void connectionHelper(Level level, BlockPos pos, MachinePosition machinePosition, Block block) {
        if (this.connected) return;
        if (level.isClientSide()) return;

        BlockState state = block.defaultBlockState().setValue(CustomBlockStateProperties.MACHINE_POSITION, machinePosition);
        level.setBlockAndUpdate(pos, state);

        System.out.println("connection " + pos.toShortString());
    }

    protected void disconnectionHelper(Level level, BlockPos pos, Block block) {
        if (!this.connected) return;

        BlockState state = block.defaultBlockState();
        level.setBlockAndUpdate(pos, state);
    }
}