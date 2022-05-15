package dev.mattrm.mc.modularmachines.common.block;

import dev.mattrm.mc.modularmachines.Constants;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    public static final RegistryObject<Block> MACHINE_CASING = BLOCKS.register("machine_casing", MachineCasingBlock::new);
    public static final RegistryObject<Block> MACHINE_CONTROLLER = BLOCKS.register("machine_controller", MachineControllerBlock::new);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
