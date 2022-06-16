package dev.mattrm.mc.modularmachines.client.new_api.client.node;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.client.gui.AbstractFocusableEventListener;
import dev.mattrm.mc.modularmachines.client.gui.IControllerRenderContext;
import dev.mattrm.mc.modularmachines.client.gui.StretchableTexture;
import dev.mattrm.mc.modularmachines.client.new_api.client.node.compmonent.NodeComponentRenderer;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.ControlFlowInput;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.ControlFlowOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class RenderableNode extends AbstractFocusableEventListener {
    private static final int NODE_PADDING = 10;
    private static final int COMPONENT_PADDING = 5;
    private static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation(Constants.MOD_ID, "textures/gui/node.png");
    private static final StretchableTexture BACKGROUND_TEXTURE = new StretchableTexture(BACKGROUND_LOCATION, 0, 0, 48, 48, 16);

    private final UUID id;
    private int x;
    private int y;
    private final List<NodeComponentRenderer<?>> components;
    private final ControlFlowInput controlFlowInputState;
    private final ControlFlowOutput controlFlowOutputState;

    public RenderableNode(UUID id, int x, int y, List<NodeComponentRenderer<?>> components, ControlFlowInput controlFlowInputState, ControlFlowOutput controlFlowOutputState) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.components = components;
        this.controlFlowInputState = controlFlowInputState;
        this.controlFlowOutputState = controlFlowOutputState;
    }

    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, IControllerRenderContext ctx) {
        final int componentMaxWidth = this.components.stream().filter(Predicate.not(NodeComponentRenderer::isHidden)).mapToInt(NodeComponentRenderer::getWidth).max().orElse(0);
        final int componentTotalHeight = this.components.stream().filter(Predicate.not(NodeComponentRenderer::isHidden)).mapToInt(NodeComponentRenderer::getHeight).sum();
        final int totalWidth = componentMaxWidth + 2 * NODE_PADDING;
        final int totalHeight = componentTotalHeight + (this.components.size() - 1) * COMPONENT_PADDING + 2 * NODE_PADDING;

        // Render background
        this.renderBackground(poseStack, mouseX, mouseY, partialTick, totalWidth, totalHeight);

        // Render components
        poseStack.pushPose();
        poseStack.translate(NODE_PADDING, NODE_PADDING, 0);
        this.renderForeground(poseStack, mouseX, mouseY, partialTick, componentMaxWidth, ctx);
        poseStack.popPose();
    }


    protected void renderBackground(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int totalWidth, int totalHeight) {
        final float SCALE_FACTOR = 1.5f;

        poseStack.pushPose();
        poseStack.scale(1 / SCALE_FACTOR, 1 / SCALE_FACTOR, 1);

        BACKGROUND_TEXTURE.render(poseStack, 0, 0, (int) (totalWidth * SCALE_FACTOR), (int) (totalHeight * SCALE_FACTOR));

        poseStack.popPose();
    }

    protected void renderForeground(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth, IControllerRenderContext ctx) {
        int y = 0;
        for (NodeComponentRenderer<?> comp : this.components) {
            if (comp.isHidden()) {
                continue;
            }

            poseStack.pushPose();
            poseStack.translate(0, y, 0);
            comp.render(poseStack, mouseX, mouseY, partialTick, fullWidth, ctx);
            poseStack.popPose();

            y += comp.getHeight() + COMPONENT_PADDING;
        }
    }


    @Override
    public boolean bypassFocus() {
        return true;
    }

    @Override
    public boolean isMouseOver(double relMouseX, double relMouseY) {
        final int xOffset = NODE_PADDING;
        int yOffset = NODE_PADDING;
        for (NodeComponentRenderer<?> comp : this.components) {
            if (comp.isHidden()) {
                continue;
            }

            boolean res = comp.isMouseOver(relMouseX - xOffset, relMouseY - yOffset);
            if (res) {
                return true;
            }

            yOffset += comp.getHeight() + COMPONENT_PADDING;
        }

        return false;
    }

    @Override
    public void mouseMoved(double relMouseX, double relMouseY) {
        final int xOffset = NODE_PADDING;
        int yOffset = NODE_PADDING;
        for (NodeComponentRenderer<?> comp : this.components) {
            if (comp.isHidden()) {
                continue;
            }

            if (comp.isMouseOver(relMouseX - xOffset, relMouseY - yOffset)) {
                if (!comp.isHovered()) {
                    comp.setHovered(true);
                    comp.mouseHoverStart();
                }
            } else {
                if (comp.isHovered()) {
                    comp.setHovered(false);
                    comp.mouseHoverEnd();
                }
            }

            comp.mouseMoved(relMouseX - xOffset, relMouseY - yOffset);

            yOffset += comp.getHeight() + COMPONENT_PADDING;
        }
    }

    @Override
    public boolean mouseClicked(double relMouseX, double relMouseY, int button) {
        // TODO: apply focus
        final int xOffset = NODE_PADDING;
        int yOffset = NODE_PADDING;
        for (NodeComponentRenderer<?> comp : this.components) {
            if (comp.isHidden()) {
                continue;
            }

            if (!comp.isMouseOver(relMouseX - xOffset, relMouseY - yOffset)) {
                continue;
            }

            boolean res = comp.mouseClicked(relMouseX - xOffset, relMouseY - yOffset, button);
            if (res) {
                return true;
            }

            yOffset += comp.getHeight() + COMPONENT_PADDING;
        }

        return false;
    }

    @Override
    public boolean mouseReleased(double relMouseX, double relMouseY, int button) {
        final int xOffset = NODE_PADDING;
        int yOffset = NODE_PADDING;
        for (NodeComponentRenderer<?> comp : this.components) {
            if (comp.isHidden()) {
                continue;
            }

            if (!comp.isMouseOver(relMouseX - xOffset, relMouseY - yOffset)) {
                continue;
            }

            boolean res = comp.mouseReleased(relMouseX - xOffset, relMouseY - yOffset, button);
            if (res) {
                return true;
            }

            yOffset += comp.getHeight() + COMPONENT_PADDING;
        }

        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (NodeComponentRenderer<?> comp : this.components) {
            if (comp.isHidden()) {
                continue;
            }

            if (!comp.isFocused() && !comp.bypassFocus()) {
                continue;
            }

            boolean res = comp.keyPressed(keyCode, scanCode, modifiers);
            if (res) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        for (NodeComponentRenderer<?> comp : this.components) {
            if (comp.isHidden()) {
                continue;
            }

            if (!comp.isFocused() && !comp.bypassFocus()) {
                continue;
            }

            boolean res = comp.keyReleased(keyCode, scanCode, modifiers);
            if (res) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        for (NodeComponentRenderer<?> comp : this.components) {
            if (comp.isHidden()) {
                continue;
            }

            if (!comp.isFocused() && !comp.bypassFocus()) {
                continue;
            }

            boolean res = comp.charTyped(codePoint, modifiers);
            if (res) {
                return true;
            }
        }

        return false;
    }

    public UUID getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ControlFlowInput getControlFlowInputState() {
        return this.controlFlowInputState;
    }

    public ControlFlowOutput getControlFlowOutputState() {
        return this.controlFlowOutputState;
    }
}
