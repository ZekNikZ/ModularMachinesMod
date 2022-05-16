package dev.mattrm.mc.modularmachines.common.util;

import net.minecraft.core.BlockPos;

public class BlockPosUtils {
    public static boolean within(BlockPos target, BlockPos corner1, BlockPos corner2) {
        return target.getX() >= Math.min(corner1.getX(), corner2.getX())
                && target.getX() <= Math.max(corner1.getX(), corner2.getX())
                && target.getY() >= Math.min(corner1.getY(), corner2.getY())
                && target.getY() <= Math.max(corner1.getY(), corner2.getY())
                && target.getZ() >= Math.min(corner1.getZ(), corner2.getZ())
                && target.getZ() <= Math.max(corner1.getZ(), corner2.getZ());
    }
}
