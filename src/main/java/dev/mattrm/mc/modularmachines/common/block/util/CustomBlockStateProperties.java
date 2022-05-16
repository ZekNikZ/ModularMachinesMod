package dev.mattrm.mc.modularmachines.common.block.util;

import dev.mattrm.mc.modularmachines.common.util.MachinePosition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class CustomBlockStateProperties {
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
    public static final EnumProperty<MachinePosition> MACHINE_POSITION = EnumProperty.create("position", MachinePosition.class);
}
