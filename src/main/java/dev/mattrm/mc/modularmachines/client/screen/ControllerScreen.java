package dev.mattrm.mc.modularmachines.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.common.container.ControllerContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ControllerScreen extends AbstractContainerScreen<ControllerContainer> {
    public ControllerScreen(ControllerContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(PoseStack matrixStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float pPartialTick, int pMouseX, int pMouseY) {

    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        super.renderLabels(matrixStack, mouseX, mouseY);
        drawString(matrixStack, Minecraft.getInstance().font, "Test. Hello, world!", 10, 10, 0xffffff);
    }

    @Override
    protected void init() {
        super.init();
        // this.addRenderableWidget();
    }
}
