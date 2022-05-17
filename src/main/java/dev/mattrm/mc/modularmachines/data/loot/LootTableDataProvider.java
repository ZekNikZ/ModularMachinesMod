package dev.mattrm.mc.modularmachines.data.loot;

import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.LootTable;

public class LootTableDataProvider extends BaseLootTableProvider {
    public LootTableDataProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        // Blocks
        ModBlocks.getRegisteredBlocks().forEach(block -> {
            if (block instanceof ILootTableDataProvider lootProvider) {
                LootTable.Builder builder = lootProvider.dataLootTable(block.getRegistryName().toString());
                if (builder != null && !lootProvider.dataNoLootTable()) {
                    this.lootTables.put(block, builder);
                }
            }
        });
    }
}
