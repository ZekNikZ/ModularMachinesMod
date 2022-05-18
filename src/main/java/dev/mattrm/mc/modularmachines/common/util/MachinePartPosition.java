package dev.mattrm.mc.modularmachines.common.util;

import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public enum MachinePartPosition implements StringRepresentable {
    INSIDE("inside", Vec3i.ZERO, 4, 0, 0),

    TOP("top", Direction.UP.getNormal(), 0, 0, 0),
    BOTTOM("bottom", Direction.DOWN.getNormal(), 0, 180, 0),
    NORTH("north", Direction.NORTH.getNormal(), 0, 90, 0),
    SOUTH("south", Direction.SOUTH.getNormal(), 0, 270, 0),
    EAST("east", Direction.EAST.getNormal(), 0, 90, 90),
    WEST("west", Direction.WEST.getNormal(), 0, 270, 90),

    NORTH_TOP("north_top", Direction.NORTH.getNormal().offset(Direction.UP.getNormal()), 1, 0, 0),
    NORTH_BOTTOM("north_bottom", Direction.NORTH.getNormal().offset(Direction.DOWN.getNormal()), 1, 90, 0),
    SOUTH_TOP("south_top", Direction.SOUTH.getNormal().offset(Direction.UP.getNormal()), 1, 270, 0),
    SOUTH_BOTTOM("south_bottom", Direction.SOUTH.getNormal().offset(Direction.DOWN.getNormal()), 1, 180, 0),
    EAST_TOP("east_top", Direction.EAST.getNormal().offset(Direction.UP.getNormal()), 1, 0, 90),
    EAST_BOTTOM("east_bottom", Direction.EAST.getNormal().offset(Direction.DOWN.getNormal()), 1, 90, 90),
    WEST_TOP("west_top", Direction.WEST.getNormal().offset(Direction.UP.getNormal()), 1, 270, 90),
    WEST_BOTTOM("west_bottom", Direction.WEST.getNormal().offset(Direction.DOWN.getNormal()), 1, 180, 90),
    NORTH_EAST("north_east", Direction.NORTH.getNormal().offset(Direction.EAST.getNormal()), 2, 0, 0),
    NORTH_WEST("north_west", Direction.NORTH.getNormal().offset(Direction.WEST.getNormal()), 2, 0, 270),
    SOUTH_EAST("south_east", Direction.SOUTH.getNormal().offset(Direction.EAST.getNormal()), 2, 0, 90),
    SOUTH_WEST("south_west", Direction.SOUTH.getNormal().offset(Direction.WEST.getNormal()), 2, 0, 180),

    NORTH_EAST_TOP("north_east_top", Direction.NORTH.getNormal().offset(Direction.EAST.getNormal()).offset(Direction.UP.getNormal()), 3, 0, 0),
    NORTH_WEST_TOP("north_west_top", Direction.NORTH.getNormal().offset(Direction.WEST.getNormal()).offset(Direction.UP.getNormal()), 3, 0, 270),
    SOUTH_EAST_TOP("south_east_top", Direction.SOUTH.getNormal().offset(Direction.EAST.getNormal()).offset(Direction.UP.getNormal()), 3, 0, 90),
    SOUTH_WEST_TOP("south_west_top", Direction.SOUTH.getNormal().offset(Direction.WEST.getNormal()).offset(Direction.UP.getNormal()), 3, 0, 180),
    NORTH_EAST_BOTTOM("north_east_bottom", Direction.NORTH.getNormal().offset(Direction.EAST.getNormal()).offset(Direction.DOWN.getNormal()), 3, 90, 0),
    NORTH_WEST_BOTTOM("north_west_bottom", Direction.NORTH.getNormal().offset(Direction.WEST.getNormal()).offset(Direction.DOWN.getNormal()), 3, 90, 270),
    SOUTH_EAST_BOTTOM("south_east_bottom", Direction.SOUTH.getNormal().offset(Direction.EAST.getNormal()).offset(Direction.DOWN.getNormal()), 3, 90, 90),
    SOUTH_WEST_BOTTOM("south_west_bottom", Direction.SOUTH.getNormal().offset(Direction.WEST.getNormal()).offset(Direction.DOWN.getNormal()), 3, 90, 180),

    NONE("none", Vec3i.ZERO, 4, 0, 0);

    final String serializedName;
    final Vec3i normal;
    final int arity;
    final int modelRotX;
    final int modelRotY;

    MachinePartPosition(String serializedName, @NotNull Vec3i normal, int arity, int modelRotX, int modelRotY) {
        this.serializedName = serializedName;
        this.normal = normal;
        this.arity = arity;
        this.modelRotX = modelRotX;
        this.modelRotY = modelRotY;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.serializedName;
    }

    public @NotNull Vec3i getNormal() {
        return this.normal;
    }

    public int getArity() {
        return this.arity;
    }

    public int getModelRotX() {
        return this.modelRotX;
    }

    public int getModelRotY() {
        return this.modelRotY;
    }

    public static List<MachinePartPosition> VALUES = Arrays.asList(values());

    public static MachinePartPosition from(Vec3i normal) {
        return VALUES.stream().filter(pos -> pos.getNormal().equals(normal)).findFirst().orElse(null);
    }
}
