package dev.mattrm.mc.modularmachines.common.block;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.casing.MachineCasingBlock;
import dev.mattrm.mc.modularmachines.common.block.controller.MachineControllerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.stream.Collectors;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    private static final BlockBehaviour.Properties DISCONNECTED_PROPERTIES = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL);
    private static final BlockBehaviour.Properties CONNECTED_PROPERTIES = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).strength(-1.0F, 3600000.0F).noDrops().sound(SoundType.METAL);

    public static final RegistryObject<Block> MACHINE_CASING = BLOCKS.register("machine_casing", () -> new MachineCasingBlock(DISCONNECTED_PROPERTIES, false));
    public static final RegistryObject<Block> MACHINE_CASING_CONNECTED = BLOCKS.register("machine_casing_connected", () -> new MachineCasingBlock(CONNECTED_PROPERTIES, true));
    public static final RegistryObject<Block> MACHINE_CONTROLLER = BLOCKS.register("machine_controller", () -> new MachineControllerBlock(DISCONNECTED_PROPERTIES, false));
    public static final RegistryObject<Block> MACHINE_CONTROLLER_CONNECTED = BLOCKS.register("machine_controller_connected", () -> new MachineControllerBlock(CONNECTED_PROPERTIES, true));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static List<Block> getRegisteredBlocks() {
        return BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }
}
