package dev.mattrm.mc.modularmachines.old;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.client.gui.StretchableTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TestRelativeWidget extends AbstractRelativeWidget {
    private static final ResourceLocation TEST_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/node.png");
    private static final StretchableTexture STRETCHABLE_TEXTURE = new StretchableTexture(TEST_TEXTURE, 0, 0, 48, 48, 16);

    public TestRelativeWidget(int x, int y) {
        super(x, y);

        this.addWidget(new AbstractRelativeWidget(10, 10) {
            @Override
            public void renderRelative(@NotNull PoseStack poseStack, int relMouseX, int relMouseY, float partialTick, int absMouseX, int absMouseY) {
                Minecraft.getInstance().font.draw(poseStack, "Test", 0, 0, 0);
            }
        });
    }

    @Override
    public void renderRelative(@NotNull PoseStack poseStack, int relMouseX, int relMouseY, float partialTick, int absMouseX, int absMouseY) {
        STRETCHABLE_TEXTURE.render(poseStack, 0, 0, Math.max(0, relMouseX), Math.max(0, relMouseY));
    }
}
