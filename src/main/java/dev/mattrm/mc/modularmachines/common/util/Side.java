package dev.mattrm.mc.modularmachines.common.util;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum Side implements StringRepresentable {
    TOP("top"),
    BOTTOM("bottom"),
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west"),

    NORTH_TOP("north_top"),
    NORTH_BOTTOM("north_bottom"),
    SOUTH_TOP("south_top"),
    SOUTH_BOTTOM("south_bottom"),
    EAST_TOP("east_top"),
    EAST_BOTTOM("east_bottom"),
    WEST_TOP("west_top"),
    WEST_BOTTOM("west_bottom"),
    NORTH_EAST("north_east"),
    NORTH_WEST("north_west"),
    SOUTH_EAST("south_east"),
    SOUTH_WEST("south_west"),

    NORTH_EAST_TOP("north_east_top"),
    NORTH_WEST_TOP("north_west_top"),
    SOUTH_EAST_TOP("south_east_top"),
    SOUTH_WEST_TOP("south_west_top"),
    NORTH_EAST_BOTTOM("north_east_bottom"),
    NORTH_WEST_BOTTOM("north_west_bottom"),
    SOUTH_EAST_BOTTOM("south_east_bottom"),
    SOUTH_WEST_BOTTOM("south_west_bottom");

    final String serializedName;

    Side(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.serializedName;
    }
}
