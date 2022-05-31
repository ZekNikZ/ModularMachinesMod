package dev.mattrm.mc.modularmachines.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.api.client.gui.ControllerScreenState;
import dev.mattrm.mc.modularmachines.api.client.gui.SimpleTextNodeComponent;
import dev.mattrm.mc.modularmachines.api.machine.INodeProvider;
import dev.mattrm.mc.modularmachines.api.machine.Node;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ControllerScreen extends Screen {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/blueprint.png");
    private static final int TEXTURE_SIZE = 256;
    private double scrollX = 0;
    private double scrollY = 0;
    private double zoom = 1;
    private final List<Node> nodes;

    public ControllerScreen() {
        super(ModGuiTranslation.CONTROLLER_GUI.component());

        // TODO: DEBUG
        this.nodes = List.of(
            new Node(0, Node.ControlFlowInput.DISABLED) {
                @Override
                protected void initComponents() {
                    this.addComponent(new SimpleTextNodeComponent("Test 1"));
                    this.addComponent(new SimpleTextNodeComponent("Test 2"));
                }
            },
            new Node(1, Node.ControlFlowInput.DISABLED) {
                @Override
                protected void initComponents() {
                    this.addComponent(new SimpleTextNodeComponent("Test 3"));
                    this.addComponent(new SimpleTextNodeComponent("Test 4"));
                }
            }
        );
        this.nodes.get(0).setX(100);
        this.nodes.get(0).setY(10);
        this.nodes.get(1).setX(100);
        this.nodes.get(1).setY(100);
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

        this.getNodes().forEach(node -> {
            pPoseStack.pushPose();
            pPoseStack.scale((float) this.zoom, (float) this.zoom, (float) this.zoom);
            pPoseStack.translate(node.getX() + (int) this.scrollX, node.getY() + (int) this.scrollY, 0);
            node.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
            pPoseStack.popPose();
        });

        this.font.draw(pPoseStack, "Zoom: " + this.zoom, 10, 10, 0);
        this.font.draw(pPoseStack, "X: " + this.scrollX, 10, 20, 0);
        this.font.draw(pPoseStack, "Y: " + this.scrollY, 10, 30, 0);
        this.font.draw(pPoseStack, "Rel. Mouse X: " + relMouseX, 10, 40, 0);
        this.font.draw(pPoseStack, "Rel. Mouse Y: " + relMouseY, 10, 50, 0);
        this.font.draw(pPoseStack, "Partial Tick: " + pPartialTick, 10, 60, 0);
    }

    private Iterable<Node> getNodes() {
        // TODO: fix
        return this.nodes;
    }

    private int screenWidth() {
        return this.getMinecraft().getWindow().getScreenWidth();
    }

    private int screenHeight() {
        return this.getMinecraft().getWindow().getScreenHeight();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (!ControllerScreenState.isZoomingLocked()) {
            double deltaZoom = delta / 20;
            double newZoom = Mth.clamp(this.zoom + deltaZoom, 0.1, 2);

            // Zoom about mouse location by making sure that the relative mouse coordinates
            // stay in the same place after the zoom
            double relMouseX = mouseX / this.zoom - this.scrollX;
            double relMouseY = mouseY / this.zoom - this.scrollY;
            this.zoom = newZoom;
            this.scrollX = mouseX / this.zoom - relMouseX;
            this.scrollY = mouseY / this.zoom - relMouseY;

            return true;
        }

        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        // TODO: account for holding only the left button
        if (!ControllerScreenState.isPanningLocked()) {
            this.scrollX += pDragX / this.zoom;
            this.scrollY += pDragY / this.zoom;

            return true;
        }

        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        final double relMouseX = pMouseX / this.zoom - this.scrollX;
        final double relMouseY = pMouseY / this.zoom - this.scrollY;
        this.getNodes().forEach(node -> {
            final double xOffset = node.getX();
            final double yOffset = node.getY();
            if (node.isMouseOver(relMouseX - xOffset, relMouseY - yOffset)) {
                if (!node.isHovered()) {
                    node.setHovered(true);
                    node.mouseHoverStart();
                }
            } else {
                if (node.isHovered()) {
                    node.setHovered(false);
                    node.mouseHoverEnd();
                }
            }

            node.mouseMoved(relMouseX - xOffset, relMouseY - yOffset);
        });
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        for (Node node : this.getNodes()) {
            int xOffset = node.getX() + (int) this.scrollX;
            int yOffset = node.getY() + (int) this.scrollY;
            double relMouseX = pMouseX - xOffset;
            double relMouseY = pMouseY - yOffset;

            if (!node.isMouseOver(relMouseX, relMouseY)) {
                continue;
            }

            boolean res = node.mouseClicked(relMouseX, relMouseY, pButton);
            if (res) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        for (Node node : this.getNodes()) {
            int xOffset = node.getX() + (int) this.scrollX;
            int yOffset = node.getY() + (int) this.scrollY;
            double relMouseX = pMouseX - xOffset;
            double relMouseY = pMouseY - yOffset;

            if (!node.isMouseOver(relMouseX, relMouseY)) {
                continue;
            }

            boolean res = node.mouseReleased(relMouseX, relMouseY, pButton);
            if (res) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        for (Node node : this.getNodes()) {
            if (!node.isFocused() && !node.bypassFocus()) {
                continue;
            }

            boolean res = node.keyPressed(pKeyCode, pScanCode, pModifiers);
            if (res) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        for (Node node : this.getNodes()) {
            if (!node.isFocused() && !node.bypassFocus()) {
                continue;
            }

            boolean res = node.keyReleased(pKeyCode, pScanCode, pModifiers);
            if (res) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        for (Node node : this.getNodes()) {
            if (!node.isFocused() && !node.bypassFocus()) {
                continue;
            }

            boolean res = node.charTyped(pCodePoint, pModifiers);
            if (res) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void init() {
        // this.addRenderableWidget();
    }
}
