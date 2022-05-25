package dev.mattrm.mc.modularmachines.client.gui;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.util.ITranslationProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public enum ModGuiTranslation implements ITranslationProvider {
    CONTROLLER_GUI("controller", 0, "Machine Controller");

    private static final String PREFIX = "gui.";
    private final String key;
    private final int argCount;
    private final String defaultValue;

    public static List<ModGuiTranslation> VALUES = Arrays.asList(values());

    ModGuiTranslation(@NotNull String key, int argCount, String defaultValue) {
        this.key = PREFIX + Constants.MOD_ID + "." + key;
        this.argCount = argCount;
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean areValidArguments(Object... args) {
        return args.length == this.argCount;
    }

    @NotNull
    @Override
    public String getTranslationKey() {
        return this.key;
    }

    @NotNull
    @Override
    public String getDefault() {
        return this.defaultValue;
    }
}
