package dev.mattrm.mc.modularmachines.common.util;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.TranslatableComponent;
import org.jetbrains.annotations.NotNull;

public interface ITranslationProvider {
    /*Client side only! */
    default String format(Object... args) {
        assert areValidArguments(args);
        return I18n.get(getTranslationKey(), args);
    }

    default TranslatableComponent component(Object... args) {
        assert areValidArguments(args);
        return new TranslatableComponent(getTranslationKey(), args);
    }

    boolean areValidArguments(Object... args);

    @NotNull String getTranslationKey();

    @NotNull String getDefault();
}