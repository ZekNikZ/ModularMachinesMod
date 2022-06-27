package dev.mattrm.mc.modularmachines.common.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMetadataNBTSerializable extends INBTSerializable<CompoundTag> {
    @Override
    default void deserializeNBT(CompoundTag nbt) {
        this.load(nbt);
    }

    void load(CompoundTag nbt);

    @Override
    default CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        this.saveAdditional(nbt);
        return nbt;
    }

    void saveAdditional(CompoundTag nbt);
}
