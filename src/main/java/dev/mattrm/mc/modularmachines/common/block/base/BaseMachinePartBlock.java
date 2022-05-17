package dev.mattrm.mc.modularmachines.common.block.base;

import dev.mattrm.mc.modularmachines.api.block.IMachinePart;
import dev.mattrm.mc.modularmachines.common.block.DataBlock;
import dev.mattrm.mc.modularmachines.common.block.util.CustomBlockStateProperties;
import dev.mattrm.mc.modularmachines.common.util.MachinePartPosition;
import dev.mattrm.mc.modularmachines.data.blockstates.BlockStateDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import org.jetbrains.annotations.Nullable;

public abstract class BaseMachinePartBlock extends DataBlock implements IMachinePart {
    protected final boolean connected;

    public BaseMachinePartBlock(Properties properties, boolean connected, MachinePartPosition defaultPosition) {
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
    public MachinePartPosition getPosition(Level level, BlockPos pos) {
        return this.connected ? level.getBlockState(pos).getValue(CustomBlockStateProperties.MACHINE_POSITION) : null;
    }

    protected void connectionHelper(Level level, BlockPos pos, MachinePartPosition machinePartPosition, Block block) {
        if (this.connected) return;
        if (level.isClientSide()) return;

        BlockState state = block.defaultBlockState().setValue(CustomBlockStateProperties.MACHINE_POSITION, machinePartPosition);
        level.setBlockAndUpdate(pos, state);
    }

    protected void disconnectionHelper(Level level, BlockPos pos, Block block) {
        if (!this.connected) return;

        BlockState state = block.defaultBlockState();
        level.setBlockAndUpdate(pos, state);
    }

    @Override
    public boolean dataNoLootTable() {
        return this.connected;
    }

    @Override
    public void dataBlockState(BlockStateDataProvider provider) {
        provider.getVariantBuilder(this)
            .forAllStatesExcept(state ->
                    ConfiguredModel.builder()
                        .modelFile(machineModel(provider, this, state.getValue(CustomBlockStateProperties.MACHINE_POSITION)))
                        .build()
                , CustomBlockStateProperties.CONNECTED);
    }

    private static ModelFile machineModel(BlockStateDataProvider provider, Block block, MachinePartPosition value) {
        return provider.models()
            .getBuilder(block.getRegistryName().getPath() + "_" + value.getSerializedName())
            .parent(provider.MACHINE_PART_MODELS.get(value))
            // TODO: finish this
            .texture("top", provider.modLoc(block.getRegistryName().getPath()));
    }
}
