package dev.mattrm.mc.modularmachines.api.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.client.gui.IControllerRenderContext;

public abstract class NodeComponent extends AbstractFocusableEventListener {
    public abstract int getWidth();

    public abstract int getHeight();

    public boolean isHidden() {
        return false;
    }

    public abstract void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth, IControllerRenderContext ctx);

    @Override
    public boolean isMouseOver(double relMouseX, double relMouseY) {
        return 0 <= relMouseX && relMouseX <= this.getWidth() && 0 <= relMouseY && relMouseY <= this.getHeight();
    }
}
