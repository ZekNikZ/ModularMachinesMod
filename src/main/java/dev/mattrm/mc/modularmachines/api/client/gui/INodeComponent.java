package dev.mattrm.mc.modularmachines.api.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface INodeComponent {
    int getWidth();

    int getHeight();

    void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth);
}
