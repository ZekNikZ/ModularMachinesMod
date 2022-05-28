package dev.mattrm.mc.modularmachines.api.machine;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.Constants;
import dev.mattrm.mc.modularmachines.api.client.gui.INodeComponent;
import dev.mattrm.mc.modularmachines.client.gui.StretchableTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
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

    private static final int NODE_PADDING = 10;
    private static final int COMPONENT_PADDING = 5;
    private static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation(Constants.MOD_ID, "textures/gui/node.png");
    private static final StretchableTexture BACKGROUND_TEXTURE = new StretchableTexture(BACKGROUND_LOCATION, 0, 0, 48, 48, 16);
    private final int id;
    private final ControlFlowInput controlFlowState;
    private final List<InputPin<?>> inputPins = new ArrayList<>();
    private final List<OutputPin<?, ?>> outputPins = new ArrayList<>();
    private final List<INodeComponent> components = new ArrayList<>();

    public Node(int id, ControlFlowInput controlFlowState) {
        this.id = id;
        this.controlFlowState = controlFlowState;

        this.initComponents();
    }

    protected void initComponents() {

    }

    protected void addInputPin(InputPin<?> pin) {
        this.inputPins.add(pin);
    }

    protected void addOutputPin(OutputPin<?, ?> pin) {
        this.outputPins.add(pin);
    }

    public final ControlFlowInput getControlFlowState() {
        return this.controlFlowState;
    }

    public final List<InputPin<?>> getInputPins() {
        return this.inputPins;
    }

    public final List<OutputPin<?, ?>> getOutputPins() {
        return this.outputPins;
    }

    protected final void addComponent(INodeComponent component) {
        this.components.add(component);
    }

    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        final int componentMaxWidth = this.components.stream().mapToInt(INodeComponent::getWidth).max().orElse(0);
        final int componentTotalHeight = this.components.stream().mapToInt(INodeComponent::getHeight).sum();
        final int totalWidth = componentMaxWidth + 2 * NODE_PADDING;
        final int totalHeight = componentTotalHeight + (this.components.size() - 1) * COMPONENT_PADDING + 2 * NODE_PADDING;

        // Render background
        this.renderBackground(poseStack, mouseX, mouseY, partialTick, totalWidth, totalHeight);

        // Render components
        poseStack.pushPose();
        poseStack.translate(NODE_PADDING, NODE_PADDING, 0);
        this.renderForeground(poseStack, mouseX, mouseY, partialTick, componentMaxWidth);
        poseStack.popPose();
    }

    protected void renderBackground(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int totalWidth, int totalHeight) {
        BACKGROUND_TEXTURE.render(poseStack, 0, 0, totalWidth, totalHeight);
    }

    protected void renderForeground(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth) {
        int y = 0;
        for (INodeComponent comp : this.components) {
            poseStack.pushPose();
            poseStack.translate(0, y, 0);
            comp.render(poseStack, mouseX, mouseY, partialTick, fullWidth);
            poseStack.popPose();

            y += comp.getHeight() + COMPONENT_PADDING;
        }
    }

    public final int getId() {
        return this.id;
    }
}
