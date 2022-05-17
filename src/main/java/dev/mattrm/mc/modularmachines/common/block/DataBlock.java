package dev.mattrm.mc.modularmachines.common.block;

import dev.mattrm.mc.modularmachines.data.lang.ILanguageDataProvider;
import dev.mattrm.mc.modularmachines.data.loot.BaseLootTableProvider;
import dev.mattrm.mc.modularmachines.data.loot.ILootTableDataProvider;
import dev.mattrm.mc.modularmachines.data.tags.IBlockTagDataProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class DataBlock extends Block implements ILanguageDataProvider, IBlockTagDataProvider, ILootTableDataProvider {
    public DataBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull List<TagKey<Block>> dataTags() {
        return List.of();
    }

    @Override
    public LootTable.Builder dataLootTable(String name) {
        return BaseLootTableProvider.createSimpleTable(name, this);
    }
}
