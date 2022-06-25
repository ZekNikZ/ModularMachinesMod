package dev.mattrm.mc.modularmachines.data.lang;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.util.ITranslationProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public enum ModMiscLangEntries implements ITranslationProvider {
    CONTROL_FLOW_INPUT_NAME("gui.pin.control_flow_input", 0, "Control"),
    CONTROL_FLOW_OUTPUT_NAME("gui.pin.control_flow_output", 0, "Control"),
    ;

    private final String key;
    private final int argCount;
    private final String defaultValue;

    public static List<ModMiscLangEntries> VALUES = Arrays.asList(values());

    ModMiscLangEntries(@NotNull String key, int argCount, String defaultValue) {
        this.key = Constants.MOD_ID + "." + key;
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