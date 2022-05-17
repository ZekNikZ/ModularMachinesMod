package dev.mattrm.mc.modularmachines.data.itemmodel;

import net.minecraftforge.client.model.generators.ItemModelProvider;

public interface IItemModelDataProvider {
    default void dataItemModel(ItemModelProvider provider) {
    }
}
