package dev.mattrm.mc.modularmachines.common.block;

import dev.mattrm.mc.modularmachines.data.blockstates.BlockStateDataProvider;
import dev.mattrm.mc.modularmachines.data.blockstates.IBlockStateDataProvider;
import dev.mattrm.mc.modularmachines.data.itemmodel.IItemModelDataProvider;
import dev.mattrm.mc.modularmachines.data.lang.ILanguageDataProvider;
import dev.mattrm.mc.modularmachines.data.loot.BaseLootTableProvider;
import dev.mattrm.mc.modularmachines.data.loot.ILootTableDataProvider;
import dev.mattrm.mc.modularmachines.data.tags.IBlockTagDataProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import org.jetbrains.annotations.Nullable;

public abstract class DataBlock extends Block implements ILanguageDataProvider, IBlockTagDataProvider, ILootTableDataProvider, IBlockStateDataProvider, IItemModelDataProvider {
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

    @Override
    public void dataBlockState(BlockStateDataProvider provider) {
        provider.simpleBlock(this);
    }

    @Override
    public void dataItemModel(ItemModelProvider provider) {
        provider.withExistingParent(this.getRegistryName().getPath(), provider.modLoc("block/" + this.getRegistryName().getPath()));
    }
}
