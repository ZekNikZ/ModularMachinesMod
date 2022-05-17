package dev.mattrm.mc.modularmachines.data.tags;

import net.minecraft.tags.TagKey;

import javax.annotation.Nullable;
import java.util.List;

public interface ITagDataProvider<T> {
    @Nullable
    default List<TagKey<T>> dataTags() {
        return null;
    }
}
