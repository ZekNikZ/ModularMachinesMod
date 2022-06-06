package dev.mattrm.mc.modularmachines.api.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;

public abstract class NodeComponent extends AbstractFocusableEventListener {
    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth);

    @Override
    public boolean isMouseOver(double relMouseX, double relMouseY) {
        return 0 <= relMouseX && relMouseX <= this.getWidth() && 0 <= relMouseY && relMouseY <= this.getHeight();
    }
}