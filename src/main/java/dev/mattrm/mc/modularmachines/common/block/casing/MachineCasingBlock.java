package dev.mattrm.mc.modularmachines.common.block.casing;

import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.block.base.BaseMachineCasingBlock;
import dev.mattrm.mc.modularmachines.common.tag.ModTags;
import dev.mattrm.mc.modularmachines.common.util.MachinePartPosition;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MachineCasingBlock extends BaseMachineCasingBlock {
    public MachineCasingBlock(Properties properties, boolean connected) {
        super(properties, connected);
    }

    @Override
    public void connectToMachine(Level level, BlockPos pos, BlockPos controllerPos, MachinePartPosition machinePartPosition) {
        this.connectionHelper(level, pos, machinePartPosition, ModBlocks.MACHINE_CASING_CONNECTED.get());
    }

    @Override
    public void disconnectFromMachine(Level level, BlockPos pos, BlockPos controllerPos) {
        this.disconnectionHelper(level, pos, ModBlocks.MACHINE_CASING.get());
    }

    @Override
    public String dataLanguageKey(String locale) {
        return "Machine Casing" + (this.connected ? " (Connected)" : "");
    }

    @Override
    public @NotNull List<TagKey<Block>> dataTags() {
        List<TagKey<Block>> tags = new ArrayList<>();
        tags.add(ModTags.Blocks.MACHINE_WALLS);
        if (!this.connected) {
            tags.add(ModTags.Blocks.METAL_MACHINE_PARTS);
        }
        return tags;
    }
}
