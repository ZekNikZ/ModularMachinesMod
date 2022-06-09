package dev.mattrm.mc.modularmachines.api.machine;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.api.client.gui.AbstractFocusableEventListener;
import dev.mattrm.mc.modularmachines.api.client.gui.NodeComponent;
import dev.mattrm.mc.modularmachines.api.client.gui.PinComponent;
import dev.mattrm.mc.modularmachines.api.machine.pin.InputPin;
import dev.mattrm.mc.modularmachines.api.machine.pin.OutputPin;
import dev.mattrm.mc.modularmachines.api.machine.pin.impl.ControlFlowInputPin;
import dev.mattrm.mc.modularmachines.api.machine.pin.impl.ControlFlowOutputPin;
import dev.mattrm.mc.modularmachines.client.gui.IControllerRenderContext;
import dev.mattrm.mc.modularmachines.client.gui.StretchableTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class Node extends AbstractFocusableEventListener {
    private INodeManager manager;

    public enum ControlFlowInput {
        /**
         * No input pin and no automatic control flow.
         * Example: external machine nodes
         */
        DISABLED(false, false),
        /**
         * No input pin and automatic control flow (activates upon input push).
         * Example: logic gates
         */
        AUTOMATIC(false, true),
        /**
         * Input pin and no automatic control flow.
         * Example: conditional nodes
         */
        ENABLED(true, false),
        /**
         * Input pin and automatic control flow.
         * Example: most processing nodes
         */
        ENABLED_AUTOMATIC(true, true);

        private final boolean inputPin;
        private final boolean automatic;

        ControlFlowInput(boolean inputPin, boolean automatic) {
            this.inputPin = inputPin;
            this.automatic = automatic;
        }

        public boolean hasInputPin() {
            return this.inputPin;
        }

        public boolean isAutomatic() {
            return this.automatic;
        }
    }

    public enum ControlFlowOutput {
        /**
         * No input pin and no automatic control flow.
         * Example: external machine nodes
         */
        DISABLED(false, false),
        /**
         * Input pin and no automatic control flow.
         * Example: conditional nodes
         */
        ENABLED(true, false);

        private final boolean outputPin;

        ControlFlowOutput(boolean inputPin, boolean automatic) {
            this.outputPin = inputPin;
        }

        public boolean hasOutputPin() {
            return this.outputPin;
        }
    }

    private static final int NODE_PADDING = 10;
    private static final int COMPONENT_PADDING = 5;
    private static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation(Constants.MOD_ID, "textures/gui/node.png");
    private static final StretchableTexture BACKGROUND_TEXTURE = new StretchableTexture(BACKGROUND_LOCATION, 0, 0, 48, 48, 16);
    private final UUID id;
    private final ControlFlowInput controlFlowInputState;
    private final ControlFlowOutput controlFlowOutputState;
    private final List<InputPin<?>> inputPins = new ArrayList<>();
    private final List<OutputPin<?, ?>> outputPins = new ArrayList<>();
    private final List<NodeComponent> components = new ArrayList<>();

    private int x = 0;
    private int y = 0;

    public Node(INodeManager manager, UUID id, ControlFlowInput controlFlowInputState, ControlFlowOutput controlFlowOutputState) {
        this.manager = manager;
        this.id = id;
        this.controlFlowInputState = controlFlowInputState;
        this.controlFlowOutputState = controlFlowOutputState;

        if (this.controlFlowInputState.hasInputPin()) {
            this.addInputPin(new ControlFlowInputPin(this));
        }

        if (this.controlFlowOutputState.hasOutputPin()) {
            this.addOutputPin(new ControlFlowOutputPin());
        }

        this.initComponents();
        this.addComponent(new PinComponent(this));
    }

    abstract protected void initComponents();

    protected void addInputPin(InputPin<?> pin) {
        this.inputPins.add(pin);
    }

    protected void addOutputPin(OutputPin<?, ?> pin) {
        this.outputPins.add(pin);
    }

    public final ControlFlowInput getControlFlowInputState() {
        return this.controlFlowInputState;
    }

    public final ControlFlowOutput getControlFlowOutputState() {
        return this.controlFlowOutputState;
    }

    public final List<InputPin<?>> getInputPins() {
        return this.inputPins;
    }

    public final List<OutputPin<?, ?>> getOutputPins() {
        return this.outputPins;
    }

    protected final void addComponent(NodeComponent component) {
        this.components.add(component);
    }

    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, IControllerRenderContext ctx) {
        final int componentMaxWidth = this.components.stream().filter(Predicate.not(NodeComponent::isHidden)).mapToInt(NodeComponent::getWidth).max().orElse(0);
        final int componentTotalHeight = this.components.stream().filter(Predicate.not(NodeComponent::isHidden)).mapToInt(NodeComponent::getHeight).sum();
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
        for (NodeComponent comp : this.components) {
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

    public final UUID getId() {
        return this.id;
    }

    @Override
    public boolean bypassFocus() {
        return true;
    }

    @Override
    public boolean isMouseOver(double relMouseX, double relMouseY) {
        final int xOffset = NODE_PADDING;
        int yOffset = NODE_PADDING;
        for (NodeComponent comp : this.components) {
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
        for (NodeComponent comp : this.components) {
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
        for (NodeComponent comp : this.components) {
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
        for (NodeComponent comp : this.components) {
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
        for (NodeComponent comp : this.components) {
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
        for (NodeComponent comp : this.components) {
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
        for (NodeComponent comp : this.components) {
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final void activate() {
        if (!this.activationAllowed()) return;
        if (!this.activateNode()) return;
        this.activateConnections();
    }

    /**
     * Check whether this node can be activated before actually activating it. Intended to be overwritten in subclasses.
     *
     * @return whether to go through with the activation
     */
    @Deprecated
    public boolean activationAllowed() {
        return true;
    }

    /**
     * Perform activation logic.
     *
     * @return whether this node should provide control flow to connected nodes
     */
    public abstract boolean activateNode();

    public void activateConnections() {
        // TODO: this is a stupid way to do this, figure out a better way
        this.manager.activateConnections(this);
    }
}
