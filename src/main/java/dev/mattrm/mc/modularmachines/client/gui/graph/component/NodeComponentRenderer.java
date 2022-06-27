package dev.mattrm.mc.modularmachines.client.gui.graph.component;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.client.gui.AbstractFocusableEventListener;
import dev.mattrm.mc.modularmachines.client.gui.IControllerRenderContext;
import dev.mattrm.mc.modularmachines.common.graph.component.NodeComponent;
import dev.mattrm.mc.modularmachines.common.graph.component.NodeComponentType;

public abstract class NodeComponentRenderer<T extends NodeComponent> extends AbstractFocusableEventListener {
    private final NodeComponentType<T> type;
    protected final T component;

    public NodeComponentRenderer(NodeComponentType<T> type, T component) {
        this.type = type;
        this.component = component;
    }

    public NodeComponentType<T> getType() {
        return this.type;
    }

    public boolean isHidden() {
        return this.component.isHidden();
    }

    public abstract void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth, IControllerRenderContext ctx);

    public abstract int getWidth();

    public abstract int getHeight();

    @Override
    public boolean isMouseOver(double relMouseX, double relMouseY) {
        return 0 <= relMouseX && relMouseX <= this.getWidth() && 0 <= relMouseY && relMouseY <= this.getHeight();
    }
}
