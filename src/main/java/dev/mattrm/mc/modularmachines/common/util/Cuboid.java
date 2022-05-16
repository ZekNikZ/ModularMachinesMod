package dev.mattrm.mc.modularmachines.common.util;

import net.minecraft.core.BlockPos;

public class Cuboid {
    private int minX;
    private int minY;
    private int minZ;
    private int maxX;
    private int maxY;
    private int maxZ;

    public Cuboid(BlockPos corner1, BlockPos corner2) {
        this.minX = Math.min(corner1.getX(), corner2.getX());
        this.minY = Math.min(corner1.getY(), corner2.getY());
        this.minZ = Math.min(corner1.getZ(), corner2.getZ());
        this.maxX = Math.max(corner1.getX(), corner2.getX());
        this.maxY = Math.max(corner1.getY(), corner2.getY());
        this.maxZ = Math.max(corner1.getZ(), corner2.getZ());
    }

    public boolean within(BlockPos target) {
        return this.minX <= target.getX() && target.getX() <= this.maxX
                && this.minY <= target.getY() && target.getY() <= this.maxY
                && this.minZ <= target.getZ() && target.getZ() <= this.maxZ;
    }

    public static boolean within(BlockPos target, BlockPos corner1, BlockPos corner2) {
        return target.getX() >= Math.min(corner1.getX(), corner2.getX())
                && target.getX() <= Math.max(corner1.getX(), corner2.getX())
                && target.getY() >= Math.min(corner1.getY(), corner2.getY())
                && target.getY() <= Math.max(corner1.getY(), corner2.getY())
                && target.getZ() >= Math.min(corner1.getZ(), corner2.getZ())
                && target.getZ() <= Math.max(corner1.getZ(), corner2.getZ());
    }
}
