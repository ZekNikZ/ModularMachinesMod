package dev.mattrm.mc.modularmachines.common.blockentity;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Constants.MOD_ID);

    public static final RegistryObject<BlockEntityType<MachineControllerBlockEntity>> MACHINE_CONTROLLER = BLOCK_ENTITIES.register("machine_controller", () -> BlockEntityType.Builder.of(MachineControllerBlockEntity::new, ModBlocks.MACHINE_CONTROLLER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
