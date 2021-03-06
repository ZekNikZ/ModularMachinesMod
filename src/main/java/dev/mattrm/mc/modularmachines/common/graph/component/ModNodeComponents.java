package dev.mattrm.mc.modularmachines.common.graph.component;

import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.graph.component.impl.PinComponent;
import dev.mattrm.mc.modularmachines.common.graph.component.impl.SimpleTextNodeComponent;
import dev.mattrm.mc.modularmachines.common.util.CastUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModNodeComponents {
    public static final ResourceLocation NODE_COMPONENT_REGISTRY = new ResourceLocation(Constants.MOD_ID, "node_components");
    public static final DeferredRegister<NodeComponentType<?>> NODE_COMPONENTS = DeferredRegister.create(NODE_COMPONENT_REGISTRY, Constants.MOD_ID);
    public static final Supplier<IForgeRegistry<NodeComponentType<?>>> REGISTRY = NODE_COMPONENTS.makeRegistry(CastUtils.generify(NodeComponentType.class), RegistryBuilder::new);

    public static final RegistryObject<NodeComponentType<SimpleTextNodeComponent>> SIMPLE_TEXT = NODE_COMPONENTS.register("simple_text", () -> NodeComponentType.Builder.of(SimpleTextNodeComponent::new).build());
    public static final RegistryObject<NodeComponentType<PinComponent>> PINS = NODE_COMPONENTS.register("pins", () -> NodeComponentType.Builder.of(PinComponent::new).build());

    public static void register(IEventBus eventBus) {
        NODE_COMPONENTS.register(eventBus);
    }
}
