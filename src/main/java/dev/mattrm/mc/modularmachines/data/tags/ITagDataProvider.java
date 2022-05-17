package dev.mattrm.mc.modularmachines.data.tags;

import net.minecraft.tags.TagKey;

import java.util.List;

public interface ITagDataProvider<T> {
    List<TagKey<T>> dataTags();
}
