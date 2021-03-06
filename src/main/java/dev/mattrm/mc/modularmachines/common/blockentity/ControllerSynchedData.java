package dev.mattrm.mc.modularmachines.common.blockentity;

import dev.mattrm.mc.modularmachines.common.network.sync.BlockEntitySyncAction;
import dev.mattrm.mc.modularmachines.common.network.sync.SynchedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;
import java.util.function.Function;

public class ControllerSynchedData extends SynchedData {
    public int x;
    public int y;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", this.x);
        tag.putInt("y", this.y);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.x = tag.getInt("x");
        this.y = tag.getInt("y");
    }

    public static class TestAction extends BlockEntitySyncAction<ControllerSynchedData> {
        int x;
        int y;

        public TestAction(SynchedDataBlockEntity blockEntity, int x, int y) {
            super(List.of(ControllerSynchedData.class), blockEntity, "data");
            this.x = x;
            this.y = y;
        }

        public TestAction(FriendlyByteBuf buffer) {
            super(List.of(ControllerSynchedData.class), buffer);
            this.x = buffer.readInt();
            this.y = buffer.readInt();
        }

        @Override
        public void encode(FriendlyByteBuf buffer) {
            super.encode(buffer);
            buffer.writeInt(this.x);
            buffer.writeInt(this.y);
        }

        @Override
        public void apply(SynchedData _synchedData) {
            ControllerSynchedData synchedData = ((ControllerSynchedData) _synchedData);
            synchedData.x = this.x;
            synchedData.y = this.y;
            super.apply(synchedData);
        }

        public static Function<MachineControllerBlockEntity, TestAction> create(int x, int y) {
            return (dbe) -> new TestAction(dbe, x, y);
        }
    }
}
