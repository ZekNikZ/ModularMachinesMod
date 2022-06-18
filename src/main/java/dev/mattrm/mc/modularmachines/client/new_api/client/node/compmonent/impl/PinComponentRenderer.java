package dev.mattrm.mc.modularmachines.client.new_api.client.node.compmonent.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.client.gui.IControllerRenderContext;
import dev.mattrm.mc.modularmachines.client.gui.util.Justification;
import dev.mattrm.mc.modularmachines.client.new_api.client.node.compmonent.NodeComponentRenderer;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.ModNodeComponents;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.component.impl.PinComponent;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.InputPin;
import dev.mattrm.mc.modularmachines.client.new_api.common.node.pin.impl.OutputPin;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.List;

public class PinComponentRenderer extends NodeComponentRenderer<PinComponent> {
    private static final int LINE_HEIGHT = 10;
    private static final int PIN_SEPARATION = 10;
    private final int width;
    private final int height;

    public PinComponentRenderer(PinComponent component) {
        super(ModNodeComponents.PINS.get(), component);

        List<InputPin> ip = this.component.getInputPins();
        List<OutputPin> op = this.component.getOutputPins();
        // TODO: make this properly work
        this.width = PIN_SEPARATION + ip.stream().mapToInt(pin -> Minecraft.getInstance().font.width(new TranslatableComponent(pin.translationKey()))).max().orElse(0) + op.stream().mapToInt(pin -> Minecraft.getInstance().font.width(new TranslatableComponent(pin.translationKey()))).max().orElse(0);
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
        for (int i = 0; i < this.component.getInputPins().size(); i++) {
            ctx.drawString(poseStack, new TranslatableComponent(this.component.getInputPins().get(i).translationKey()), 0, 10 * i);
        }

        for (int i = 0; i < this.component.getOutputPins().size(); i++) {
            ctx.drawStringJustified(poseStack, new TranslatableComponent(this.component.getOutputPins().get(i).translationKey()), fullWidth, 10 * i, Justification.RIGHT);
        }
    }
}
