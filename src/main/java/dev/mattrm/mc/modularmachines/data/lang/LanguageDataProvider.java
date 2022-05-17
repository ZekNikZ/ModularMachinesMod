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
                this.add(block, keyProvider.dataLanguageKey(this.locale));
            }
        });

        // Creative Tabs
        ModCreativeTabs.getRegisteredTabs().forEach(_tab -> {
            if (_tab instanceof ModCreativeTabs.MCT tab) {
                this.add("itemGroup." + tab.getLabel(), tab.dataLanguageKey(this.locale));
            }
        });
    }
}
