package dev.mattrm.mc.modularmachines.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.common.graph.Connection;
import dev.mattrm.mc.modularmachines.client.gui.graph.RenderableNode;
import dev.mattrm.mc.modularmachines.common.blockentity.ControllerSynchedData;
import dev.mattrm.mc.modularmachines.common.blockentity.MachineControllerBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerScreen extends SynchedDataScreen<MachineControllerBlockEntity> implements IControllerRenderContext {
    private static final ResourceLocation BG_TEXTURE_LOCATION = new ResourceLocation(Constants.MOD_ID, "textures/gui/blueprint.png");
    private static final ResourceLocation NODE_FONT_LOCATION = new ResourceLocation(Constants.MOD_ID, "smooth");
    private static final int TEXTURE_SIZE = 256;
    private static final Style TEXT_STYLE = Style.EMPTY.withFont(NODE_FONT_LOCATION);
    private double scrollX = 0;
    private double scrollY = 0;
    private double zoom = 1;
    private final List<RenderableNode> nodes;
    private final List<Connection> connections;

    public ControllerScreen(MachineControllerBlockEntity blockEntity) {
        super(blockEntity, TextComponent.EMPTY);

        // TODO: DEBUG
        this.nodes = List.of(
//            new Node(null, UUID.randomUUID(), ControlFlowInput.ENABLED_AUTOMATIC, ControlFlowOutput.DISABLED) {
//                @Override
//                protected void initComponents() {
//                    this.addComponent(new SimpleTextNodeComponent(this.getId().toString()));
//                    this.addComponent(new SimpleTextNodeComponent("Test 1"));
//                    this.addComponent(new SimpleTextNodeComponent("Test 2"));
//                }
//
//                @Override
//                public boolean activateNode() {
//                    return true;
//                }
//            },
//            new Node(null, UUID.randomUUID(), ControlFlowInput.DISABLED, ControlFlowOutput.ENABLED) {
//                @Override
//                protected void initComponents() {
//                    this.addComponent(new SimpleTextNodeComponent(this.getId().toString()));
//                    this.addComponent(new SimpleTextNodeComponent("Test 3"));
//                    this.addComponent(new SimpleTextNodeComponent("Test 4"));
//                }
//
//                @Override
//                public boolean activateNode() {
//                    return true;
//                }
//            },
//            new Node(null, UUID.randomUUID(), ControlFlowInput.ENABLED_AUTOMATIC, ControlFlowOutput.ENABLED) {
//                @Override
//                protected void initComponents() {
//                    this.addComponent(new SimpleTextNodeComponent(this.getId().toString()));
//                    this.addComponent(new SimpleTextNodeComponent("Test 3"));
//                    this.addComponent(new SimpleTextNodeComponent("Test 4"));
//                }
//
//                @Override
//                public boolean activateNode() {
//                    return true;
//                }
//            }
        );
//        this.nodes.get(0).setX(100);
//        this.nodes.get(0).setY(10);
//        this.nodes.get(1).setX(100);
//        this.nodes.get(1).setY(100);
//        this.nodes.get(2).setX(100);
//        this.nodes.get(2).setY(200);
//
        this.connections = new ArrayList<>();
//        this.connections.add(new Connection(
//            this.nodes.get(1).getId(),
//            this.nodes.get(1).getOutputPins().get(0).name(),
//            this.nodes.get(2).getId(),
//            this.nodes.get(2).getInputPins().get(0).name()
//        ));
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);
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
        RenderSystem.setShaderTexture(0, BG_TEXTURE_LOCATION);
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

        // Connections
        this.renderConnections(pPoseStack);

        // Nodes
        this.getNodes().forEach(node -> {
            pPoseStack.pushPose();
            pPoseStack.scale((float) this.zoom, (float) this.zoom, (float) this.zoom);
            pPoseStack.translate(node.getX() + (int) this.scrollX, node.getY() + (int) this.scrollY, 0);
            node.render(pPoseStack, pMouseX, pMouseY, pPartialTick, this);
            pPoseStack.popPose();
        });

        this.font.draw(pPoseStack, text("Zoom: " + this.zoom), 10, 10, 0);
        this.font.draw(pPoseStack, text("X: " + this.scrollX), 10, 20, 0);
        this.font.draw(pPoseStack, text("Y: " + this.scrollY), 10, 30, 0);
        this.font.draw(pPoseStack, text("Rel. Mouse X: " + relMouseX), 10, 40, 0);
        this.font.draw(pPoseStack, text("Rel. Mouse Y: " + relMouseY), 10, 50, 0);
        this.font.draw(pPoseStack, text("Partial Tick: " + pPartialTick), 10, 60, 0);
        this.font.draw(pPoseStack, text("Data X: " + ((ControllerSynchedData) this.getSynchedData("data")).getX()), 10, 70, 0);
        this.font.draw(pPoseStack, text("Data Y: " + ((ControllerSynchedData) this.getSynchedData("data")).getY()), 10, 80, 0);
    }

    protected void renderConnections(PoseStack poseStack) {
        RenderUtils.drawLine(poseStack, 100, 100, 400, 400, Color.RED);
    }

    private Iterable<RenderableNode> getNodes() {
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
        this.applyAction("data", ControllerSynchedData.TestAction.create((int) pMouseX, (int) pMouseY));

        for (RenderableNode node : this.getNodes()) {
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

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        for (RenderableNode node : this.getNodes()) {
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

        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        for (RenderableNode node : this.getNodes()) {
            if (!node.isFocused() && !node.bypassFocus()) {
                continue;
            }

            boolean res = node.keyPressed(pKeyCode, pScanCode, pModifiers);
            if (res) {
                return true;
            }
        }

        // Need t
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        for (RenderableNode node : this.getNodes()) {
            if (!node.isFocused() && !node.bypassFocus()) {
                continue;
            }

            boolean res = node.keyReleased(pKeyCode, pScanCode, pModifiers);
            if (res) {
                return true;
            }
        }

        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        for (RenderableNode node : this.getNodes()) {
            if (!node.isFocused() && !node.bypassFocus()) {
                continue;
            }

            boolean res = node.charTyped(pCodePoint, pModifiers);
            if (res) {
                return true;
            }
        }

        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    protected void init() {
        // this.addRenderableWidget();
    }

    public static void safeOpen(MachineControllerBlockEntity blockEntity) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> open(blockEntity));
    }

    private static void open(MachineControllerBlockEntity blockEntity) {
        Minecraft.getInstance().setScreen(new ControllerScreen(blockEntity));
    }

    public Font font() {
        return this.font;
    }

    @Override
    public Style textStyle() {
        return TEXT_STYLE;
    }
}
