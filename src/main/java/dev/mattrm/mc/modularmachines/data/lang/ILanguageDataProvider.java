package dev.mattrm.mc.modularmachines.data.lang;

import javax.annotation.Nullable;

public interface ILanguageDataProvider {
   @Nullable
   default String dataLanguageKey(String locale) {
      return null;
   }
}
