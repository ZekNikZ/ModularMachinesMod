package dev.mattrm.mc.modularmachines.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.api.client.gui.SimpleTextNodeComponent;
import dev.mattrm.mc.modularmachines.api.machine.INodeProvider;
import dev.mattrm.mc.modularmachines.api.machine.Node;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class ControllerScreen extends Screen {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/blueprint.png");
    private static final int TEXTURE_SIZE = 256;
    private double scrollX = 0;
    private double scrollY = 0;
    private double zoom = 1;

    private final Node node;

    public ControllerScreen() {
        super(ModGuiTranslation.CONTROLLER_GUI.component());
        this.node = new Node(0, Node.ControlFlowInput.DISABLED) {
            @Override
            protected void initComponents() {
                this.addComponent(new SimpleTextNodeComponent("Test 1"));
                this.addComponent(new SimpleTextNodeComponent("Test 2"));
            }
        };
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        this.renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderFg(pPoseStack, pPartialTick, pMouseX, pMouseY);
    }

    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        final int TILE_SIZE = TEXTURE_SIZE;
        final int TILES_ACROSS = ((int) (this.screenWidth() / TILE_SIZE / this.zoom));
        final int TILES_DOWN = ((int) (this.screenHeight() / TILE_SIZE / this.zoom));
        final int SCROLL_X = Mth.floor(this.scrollX) % TILE_SIZE;
        final int SCROLL_Y = Mth.floor(this.scrollY) % TILE_SIZE;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG_TEXTURE);
        pPoseStack.pushPose();
        pPoseStack.scale((float) this.zoom, (float) this.zoom, (float) this.zoom);
        for (int i = -1; i <= TILES_ACROSS + 1; i++) {
            for (int j = -1; j <= TILES_DOWN + 1; j++) {
                this.blit(pPoseStack, SCROLL_X + i * TILE_SIZE, SCROLL_Y + j * TILE_SIZE, 0, 0, TILE_SIZE, TILE_SIZE);
            }
        }
        pPoseStack.popPose();
    }

    protected void renderFg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        final int relMouseX = (int) (pMouseX / this.zoom - this.scrollX);
        final int relMouseY = (int) (pMouseY / this.zoom - this.scrollY);

        pPoseStack.pushPose();
        pPoseStack.scale((float) this.zoom, (float) this.zoom, (float) this.zoom);
        pPoseStack.translate(100 + (int) this.scrollX, 10 + (int) this.scrollY, 0);
        this.node.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        pPoseStack.popPose();

        this.font.draw(pPoseStack, "Zoom: " + this.zoom, 10, 10, 0);
        this.font.draw(pPoseStack, "X: " + this.scrollX, 10, 20, 0);
        this.font.draw(pPoseStack, "Y: " + this.scrollY, 10, 30, 0);
        this.font.draw(pPoseStack, "Rel. Mouse X: " + relMouseX, 10, 40, 0);
        this.font.draw(pPoseStack, "Rel. Mouse Y: " + relMouseY, 10, 50, 0);
        this.font.draw(pPoseStack, "Partial Tick: " + pPartialTick, 10, 60, 0);
    }

    private int screenWidth() {
        return this.getMinecraft().getWindow().getScreenWidth();
    }

    private int screenHeight() {
        return this.getMinecraft().getWindow().getScreenHeight();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        double deltaZoom = delta / 20;
        double newZoom = Mth.clamp(this.zoom + deltaZoom, 0.1, 2);

        // TODO: make this zoom in/out from the center, not the corner
        this.zoom = newZoom;

        return true;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        this.scrollX += pDragX / this.zoom;
        this.scrollY += pDragY / this.zoom;
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    protected void init() {
        // this.addRenderableWidget();
    }
}
