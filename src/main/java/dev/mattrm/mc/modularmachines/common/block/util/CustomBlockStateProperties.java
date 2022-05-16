package dev.mattrm.mc.modularmachines.common.block.util;

import dev.mattrm.mc.modularmachines.common.util.MachinePartPosition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class CustomBlockStateProperties {
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
    public static final EnumProperty<MachinePartPosition> MACHINE_POSITION = EnumProperty.create("position", MachinePartPosition.class);
}
