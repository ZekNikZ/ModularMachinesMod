package dev.mattrm.mc.modularmachines.data;

import dev.mattrm.mc.modularmachines.data.lang.LanguageDataProvider;
import dev.mattrm.mc.modularmachines.data.loot.LootTableDataProvider;
import dev.mattrm.mc.modularmachines.data.tags.BlockTagDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModDataProvider {
    public static void genClientData(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        generator.addProvider(new LanguageDataProvider(generator, "en_us"));
    }

    public static void genServerData(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        generator.addProvider(new BlockTagDataProvider(generator, existingFileHelper));
        generator.addProvider(new LootTableDataProvider(generator));
    }

    public static void genDevData(DataGenerator generator, ExistingFileHelper existingFileHelper) {

    }

    public static void genReportsData(DataGenerator generator, ExistingFileHelper existingFileHelper) {

    }
}
