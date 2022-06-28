package dev.mattrm.mc.modularmachines.common.graph.node.provider.library;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.graph.node.impl.StandardNodeLibrary;
import dev.mattrm.mc.modularmachines.common.util.CastUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModNodeLibraries {
    public static final ResourceLocation NODE_LIBRARY_REGISTRY = new ResourceLocation(Constants.MOD_ID, "node_libraries");
    public static final DeferredRegister<NodeLibrary> NODE_LIBRARIES = DeferredRegister.create(NODE_LIBRARY_REGISTRY, Constants.MOD_ID);
    public static final Supplier<IForgeRegistry<NodeLibrary>> REGISTRY = NODE_LIBRARIES.makeRegistry(CastUtils.generify(NodeLibrary.class), RegistryBuilder::new);

    public static final RegistryObject<StandardNodeLibrary> STANDARD_LIBRARY = NODE_LIBRARIES.register("std", StandardNodeLibrary::new);

    public static void register(IEventBus eventBus) {
        NODE_LIBRARIES.register(eventBus);
    }
}
