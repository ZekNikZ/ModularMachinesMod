package dev.mattrm.mc.modularmachines.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.api.machine.Node;
import dev.mattrm.mc.modularmachines.api.machine.pin.InputPin;
import dev.mattrm.mc.modularmachines.api.machine.pin.OutputPin;
import dev.mattrm.mc.modularmachines.client.gui.util.Justification;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.components.NodeComponent;
import net.minecraft.client.Minecraft;

import java.util.List;

public class PinComponent extends NodeComponent {
    private static final int LINE_HEIGHT = 10;
    private static final int PIN_SEPARATION = 10;

    private final Node node;
    private final int width;
    private final int height;

    public PinComponent(Node node) {
        this.node = node;

        List<InputPin<?>> ip = this.node.getInputPins();
        List<OutputPin<?, ?>> op = this.node.getOutputPins();
        // TODO: make this properly work
        this.width = PIN_SEPARATION + ip.stream().mapToInt(pin -> Minecraft.getInstance().font.width(pin.name())).max().orElse(0) + op.stream().mapToInt(pin -> Minecraft.getInstance().font.width(pin.name())).max().orElse(0);
        this.height = LINE_HEIGHT * Math.max(ip.size(), op.size());
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int fullWidth, IControllerRenderContext ctx) {
        for (int i = 0; i < this.node.getInputPins().size(); i++) {
            ctx.drawString(poseStack, this.node.getInputPins().get(i).name(), 0, 10 * i);
        }

        for (int i = 0; i < this.node.getOutputPins().size(); i++) {
            ctx.drawStringJustified(poseStack, this.node.getOutputPins().get(i).name(), fullWidth, 10 * i, Justification.RIGHT);
        }
    }
}
