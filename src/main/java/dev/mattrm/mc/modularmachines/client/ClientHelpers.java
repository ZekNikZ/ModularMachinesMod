package dev.mattrm.mc.modularmachines.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ClientHelpers {
    public static BlockEntity getBlockEntityAtPos(BlockPos pos) {
        return Minecraft.getInstance().level.getBlockEntity(pos);
    }
}
