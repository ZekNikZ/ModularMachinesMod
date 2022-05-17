package dev.mattrm.mc.modularmachines.data.loot;

import net.minecraft.world.level.storage.loot.LootTable;

public interface ILootTableDataProvider {
    default LootTable.Builder dataLootTable(String name) {
        return null;
    }
}
