package dev.mattrm.mc.modularmachines.api.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;

public class SimpleTextNodeComponent extends NodeComponent {
    private final String text;
    private final boolean shadow;
    private int color;

    public SimpleTextNodeComponent(String text) {
        this(text, false, 0);
    }

    public SimpleTextNodeComponent(String text, boolean shadow, int color) {
        this.text = text;
        this.shadow = shadow;
        this.color = color;
    }

    @Override
    public int getWidth() {
        return Minecraft.getInstance().font.width(this.text);
    }

    @Override
    public int getHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth) {
        if (this.shadow) {
            Minecraft.getInstance().font.drawShadow(poseStack, this.text, 0, 0, this.color);
        } else {
            Minecraft.getInstance().font.draw(poseStack, this.text, 0, 0, this.color);
        }
    }

    @Override
    public void mouseHoverStart() {
        this.color = 0xFF0000;
    }

    @Override
    public void mouseHoverEnd() {
        this.color = 0;
    }
}
