package dev.mattrm.mc.modularmachines.setup;

import net.minecraftforge.common.ForgeConfigSpec;

public class MachineConstructionConfig {
    public static ForgeConfigSpec.IntValue MACHINE_MAX_SIZE;

    public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Machine Construction").push("machine_construction");

        MACHINE_MAX_SIZE = SERVER_BUILDER
            .comment("Maximum machine dimensions")
            .defineInRange("maxsize", 11, 5, 20);

        SERVER_BUILDER.pop();
    }
}
