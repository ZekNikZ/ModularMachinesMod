package dev.mattrm.mc.modularmachines.common.util;

import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public enum MachinePosition implements StringRepresentable {
    INSIDE("inside", Vec3i.ZERO),

    TOP("top", Direction.UP.getNormal()),
    BOTTOM("bottom", Direction.DOWN.getNormal()),
    NORTH("north", Direction.NORTH.getNormal()),
    SOUTH("south", Direction.SOUTH.getNormal()),
    EAST("east", Direction.EAST.getNormal()),
    WEST("west", Direction.WEST.getNormal()),

    NORTH_TOP("north_top", Direction.NORTH.getNormal().offset(Direction.UP.getNormal())),
    NORTH_BOTTOM("north_bottom", Direction.NORTH.getNormal().offset(Direction.DOWN.getNormal())),
    SOUTH_TOP("south_top", Direction.SOUTH.getNormal().offset(Direction.UP.getNormal())),
    SOUTH_BOTTOM("south_bottom", Direction.SOUTH.getNormal().offset(Direction.DOWN.getNormal())),
    EAST_TOP("east_top", Direction.EAST.getNormal().offset(Direction.UP.getNormal())),
    EAST_BOTTOM("east_bottom", Direction.EAST.getNormal().offset(Direction.DOWN.getNormal())),
    WEST_TOP("west_top", Direction.WEST.getNormal().offset(Direction.UP.getNormal())),
    WEST_BOTTOM("west_bottom", Direction.WEST.getNormal().offset(Direction.DOWN.getNormal())),
    NORTH_EAST("north_east", Direction.NORTH.getNormal().offset(Direction.EAST.getNormal())),
    NORTH_WEST("north_west", Direction.NORTH.getNormal().offset(Direction.WEST.getNormal())),
    SOUTH_EAST("south_east", Direction.SOUTH.getNormal().offset(Direction.EAST.getNormal())),
    SOUTH_WEST("south_west", Direction.SOUTH.getNormal().offset(Direction.WEST.getNormal())),

    NORTH_EAST_TOP("north_east_top", Direction.NORTH.getNormal().offset(Direction.EAST.getNormal()).offset(Direction.UP.getNormal())),
    NORTH_WEST_TOP("north_west_top", Direction.NORTH.getNormal().offset(Direction.WEST.getNormal()).offset(Direction.UP.getNormal())),
    SOUTH_EAST_TOP("south_east_top", Direction.SOUTH.getNormal().offset(Direction.EAST.getNormal()).offset(Direction.UP.getNormal())),
    SOUTH_WEST_TOP("south_west_top", Direction.SOUTH.getNormal().offset(Direction.WEST.getNormal()).offset(Direction.UP.getNormal())),
    NORTH_EAST_BOTTOM("north_east_bottom", Direction.NORTH.getNormal().offset(Direction.EAST.getNormal()).offset(Direction.DOWN.getNormal())),
    NORTH_WEST_BOTTOM("north_west_bottom", Direction.NORTH.getNormal().offset(Direction.WEST.getNormal()).offset(Direction.DOWN.getNormal())),
    SOUTH_EAST_BOTTOM("south_east_bottom", Direction.SOUTH.getNormal().offset(Direction.EAST.getNormal()).offset(Direction.DOWN.getNormal())),
    SOUTH_WEST_BOTTOM("south_west_bottom", Direction.SOUTH.getNormal().offset(Direction.WEST.getNormal()).offset(Direction.DOWN.getNormal())),

    NONE("none", Vec3i.ZERO);

    final String serializedName;
    final Vec3i normal;

    MachinePosition(String serializedName, @NotNull Vec3i normal) {
        this.serializedName = serializedName;
        this.normal = normal;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.serializedName;
    }

    public @NotNull Vec3i getNormal() {
        return this.normal;
    }

    public static List<MachinePosition> VALUES = Arrays.asList(values());

    public static MachinePosition from(Vec3i normal) {
        return VALUES.stream().filter(pos -> pos.getNormal().equals(normal)).findFirst().orElse(null);
    }
}
