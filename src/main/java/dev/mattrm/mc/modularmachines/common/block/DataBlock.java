package dev.mattrm.mc.modularmachines.common.block;

import dev.mattrm.mc.modularmachines.data.lang.ILanguageDataProvider;
import dev.mattrm.mc.modularmachines.data.loot.BaseLootTableProvider;
import dev.mattrm.mc.modularmachines.data.loot.ILootTableDataProvider;
import dev.mattrm.mc.modularmachines.data.tags.IBlockTagDataProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;

public abstract class DataBlock extends Block implements ILanguageDataProvider, IBlockTagDataProvider, ILootTableDataProvider {
    public DataBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public abstract String dataLanguageKey(String locale);

    @Override
    public LootTable.Builder dataLootTable(String name) {
        return BaseLootTableProvider.createSimpleTable(name, this);
    }
}
