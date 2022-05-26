package dev.mattrm.mc.modularmachines.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.GuiUtils;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class StretchableTexture extends GuiComponent {
    private final int top;
    private final int bottom;
    private final int left;
    private final int right;

    private final ResourceLocation texture;
    private final int textureX;
    private final int textureY;
    private final int textureWidth;
    private final int textureHeight;

    public StretchableTexture(ResourceLocation texture, int textureX, int textureY, int textureWidth, int textureHeight, int border) {
        this(texture, textureX, textureY, textureWidth, textureHeight, border, border, border, border);
    }

    public StretchableTexture(ResourceLocation texture, int textureX, int textureY, int textureWidth, int textureHeight, int topBottom, int leftRight) {
        this(texture, textureX, textureY, textureWidth, textureHeight, topBottom, topBottom, leftRight, leftRight);
    }

    public StretchableTexture(ResourceLocation texture, int textureX, int textureY, int textureWidth, int textureHeight, int top, int bottom, int left, int right) {
        this.texture = texture;
        this.textureX = textureX;
        this.textureY = textureY;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public void render(@NotNull PoseStack poseStack, int x, int y, int width, int height) {
        int minWidth = this.left + this.right;
        int minHeight = this.top + this.bottom;
        GuiUtils.drawContinuousTexturedBox(poseStack, this.texture, x, y, this.textureX, this.textureY, Math.max(width, minWidth), Math.max(height, minHeight), this.textureWidth, this.textureHeight, this.top, this.bottom, this.left, this.right, this.getBlitOffset());
    }
}
