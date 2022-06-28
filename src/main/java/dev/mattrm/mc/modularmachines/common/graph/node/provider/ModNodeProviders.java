package dev.mattrm.mc.modularmachines.common.graph.node.provider;

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

public class ModNodeProviders {
    public static final ResourceLocation NODE_PROVIDER_REGISTRY = new ResourceLocation(Constants.MOD_ID, "node_providers");
    public static final DeferredRegister<NodeProvider> NODE_PROVIDERS = DeferredRegister.create(NODE_PROVIDER_REGISTRY, Constants.MOD_ID);
    public static final Supplier<IForgeRegistry<NodeProvider>> REGISTRY = NODE_PROVIDERS.makeRegistry(CastUtils.generify(NodeProvider.class), RegistryBuilder::new);

    public static final RegistryObject<BlockEntityNodeProvider> BLOCK_ENTITY = NODE_PROVIDERS.register("blockentity", BlockEntityNodeProvider::new);
    public static final RegistryObject<LibraryNodeProvider> LIBRARY = NODE_PROVIDERS.register("library", LibraryNodeProvider::new);

    public static void register(IEventBus eventBus) {
        NODE_PROVIDERS.register(eventBus);
    }
}
