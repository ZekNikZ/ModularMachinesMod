package dev.mattrm.mc.modularmachines.data.model;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelDataProvider extends ItemModelProvider {
    public ItemModelDataProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Items
        ModItems.getRegisteredItems().forEach(item -> {
            if (item instanceof IItemModelDataProvider modelProvider) {
                modelProvider.dataItemModel(this);
            }
        });
    }
}
