package dev.mattrm.mc.modularmachines.old;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Widget;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface RelativeWidget extends Widget {
    List<RelativeWidget> getChildren();

    int getX();

    int getY();

    @Override
    default void render(@NotNull PoseStack poseStack, int relMouseX, int relMouseY, float partialTick) {
        this.render(poseStack, relMouseX, relMouseY, partialTick, relMouseX, relMouseY);
    }

    default void render(@NotNull PoseStack poseStack, int relMouseX, int relMouseY, float partialTick, int absMouseX, int absMouseY) {
        poseStack.pushPose();
        poseStack.translate(this.getX(), this.getY(), 0);
        this.renderRelative(poseStack, relMouseX - this.getX(), relMouseY - this.getY(), partialTick, absMouseX, absMouseY);
        poseStack.popPose();
    }

    default void renderRelative(@NotNull PoseStack poseStack, int relMouseX, int relMouseY, float partialTick, int absMouseX, int absMouseY) {
        this.getChildren().forEach(c -> c.render(poseStack, relMouseX, relMouseY, partialTick, absMouseX, absMouseY));
    }
}
