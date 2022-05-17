package dev.mattrm.mc.modularmachines.data.lang;

import org.jetbrains.annotations.NotNull;

public interface ILanguageDataProvider {
    @NotNull String dataLanguageKey(String locale);
}
