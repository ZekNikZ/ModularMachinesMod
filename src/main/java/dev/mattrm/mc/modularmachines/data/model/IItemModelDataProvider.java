package dev.mattrm.mc.modularmachines.data.model;

import net.minecraftforge.client.model.generators.ItemModelProvider;

public interface IItemModelDataProvider {
    default void dataItemModel(ItemModelProvider provider) {
    }
}
