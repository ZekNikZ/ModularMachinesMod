package dev.mattrm.mc.modularmachines.data.loot;

import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.Nullable;

public interface ILootTableDataProvider {
    @Nullable
    default LootTable.Builder dataLootTable(String name) {
        return null;
    }

    default boolean dataNoLootTable() {
        return false;
    }
}
