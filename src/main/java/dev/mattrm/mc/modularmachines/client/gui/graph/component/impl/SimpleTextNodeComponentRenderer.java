package dev.mattrm.mc.modularmachines.client.gui.graph.component.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.client.gui.IControllerRenderContext;
import dev.mattrm.mc.modularmachines.client.gui.graph.component.NodeComponentRenderer;
import dev.mattrm.mc.modularmachines.common.graph.component.ModNodeComponents;
import dev.mattrm.mc.modularmachines.common.graph.component.impl.SimpleTextNodeComponent;
import net.minecraft.client.Minecraft;

public class SimpleTextNodeComponentRenderer extends NodeComponentRenderer<SimpleTextNodeComponent> {
    public SimpleTextNodeComponentRenderer(SimpleTextNodeComponent component) {
        super(ModNodeComponents.SIMPLE_TEXT.get(), component);
    }

    @Override
    public int getWidth() {
        return Minecraft.getInstance().font.width(this.component.getText());
    }

    @Override
    public int getHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth, IControllerRenderContext ctx) {
        ctx.drawString(poseStack, this.component.getText(), 0, 0, this.component.getColor());
    }

    @Override
    public void mouseHoverStart() {
        this.component.setColor(0xFF0000);
    }

    @Override
    public void mouseHoverEnd() {
        this.component.setColor(0);
    }
}
