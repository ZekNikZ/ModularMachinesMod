package dev.mattrm.mc.modularmachines.client.new_api;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class NodeComponent implements INBTSerializable<CompoundTag> {
    public boolean isHidden() {
        return false;
    }
}
