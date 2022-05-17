package dev.mattrm.mc.modularmachines.data.lang;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import dev.mattrm.mc.modularmachines.common.tab.ModCreativeTabs;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LanguageDataProvider extends LanguageProvider {
    private final String locale;

    public LanguageDataProvider(DataGenerator generator, String locale) {
        super(generator, Constants.MOD_ID, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        // Blocks
        ModBlocks.getRegisteredBlocks().forEach(block -> {
            if (block instanceof ILanguageDataProvider keyProvider) {
                String key = keyProvider.dataLanguageKey(this.locale);
                if (key != null) {
                    this.add(block, key);
                }
            }
        });

        // Creative Tabs
        ModCreativeTabs.getRegisteredTabs().forEach(_tab -> {
            if (_tab instanceof ModCreativeTabs.MCT tab) {
                String key = tab.dataLanguageKey(this.locale);
                if (key != null) {
                    this.add("itemGroup." + tab.getLabel(), key);
                }
            }
        });
    }
}
