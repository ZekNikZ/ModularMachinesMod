package dev.mattrm.mc.modularmachines.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.mattrm.mc.modularmachines.client.gui.util.Justification;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;

public interface IControllerRenderContext {
    Font font();

    Style textStyle();

    default Component text(String text) {
        return new TextComponent(text).setStyle(this.textStyle());
    }

    default void drawString(PoseStack poseStack, Component component, float x, float y) {
        this.drawStringInternal(poseStack, component, x, y, 0, false);
    }

    default void drawStringShadow(PoseStack poseStack, Component component, float x, float y) {
        this.drawStringInternal(poseStack, component, x, y, 0, true);
    }

    default void drawString(PoseStack poseStack, Component component, float x, float y, int color) {
        this.drawStringInternal(poseStack, component, x, y, color, false);
    }

    default void drawStringShadow(PoseStack poseStack, Component component, float x, float y, int color) {
        this.drawStringInternal(poseStack, component, x, y, color, true);
    }

    default void drawString(PoseStack poseStack, Component component, float x, float y, ChatFormatting color) {
        this.drawStringInternal(poseStack, component, x, y, color.getColor(), false);
    }

    default void drawStringShadow(PoseStack poseStack, Component component, float x, float y, ChatFormatting color) {
        this.drawStringInternal(poseStack, component, x, y, color.getColor(), true);
    }

    default void drawStringJustified(PoseStack poseStack, Component component, float x, float y, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, component, x, y, 0, false, justification);
    }

    default void drawStringShadowJustified(PoseStack poseStack, Component component, float x, float y, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, component, x, y, 0, true, justification);
    }

    default void drawStringJustified(PoseStack poseStack, Component component, float x, float y, int color, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, component, x, y, color, false, justification);
    }

    default void drawStringShadowJustified(PoseStack poseStack, Component component, float x, float y, int color, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, component, x, y, color, true, justification);
    }

    default void drawStringJustified(PoseStack poseStack, Component component, float x, float y, ChatFormatting color, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, component, x, y, color.getColor(), false, justification);
    }

    default void drawStringShadowJustified(PoseStack poseStack, Component component, float x, float y, ChatFormatting color, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, component, x, y, color.getColor(), true, justification);
    }

    /// ----------------------------------------
    
    default void drawString(PoseStack poseStack, String text, float x, float y) {
        this.drawStringInternal(poseStack, this.text(text), x, y, 0, false);
    }

    default void drawStringShadow(PoseStack poseStack, String text, float x, float y) {
        this.drawStringInternal(poseStack, this.text(text), x, y, 0, true);
    }

    default void drawString(PoseStack poseStack, String text, float x, float y, int color) {
        this.drawStringInternal(poseStack, this.text(text), x, y, color, false);
    }

    default void drawStringShadow(PoseStack poseStack, String text, float x, float y, int color) {
        this.drawStringInternal(poseStack, this.text(text), x, y, color, true);
    }

    default void drawString(PoseStack poseStack, String text, float x, float y, ChatFormatting color) {
        this.drawStringInternal(poseStack, this.text(text), x, y, color.getColor(), false);
    }

    default void drawStringShadow(PoseStack poseStack, String text, float x, float y, ChatFormatting color) {
        this.drawStringInternal(poseStack, this.text(text), x, y, color.getColor(), true);
    }

    default void drawStringJustified(PoseStack poseStack, String text, float x, float y, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, this.text(text), x, y, 0, false, justification);
    }

    default void drawStringShadowJustified(PoseStack poseStack, String text, float x, float y, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, this.text(text), x, y, 0, true, justification);
    }

    default void drawStringJustified(PoseStack poseStack, String text, float x, float y, int color, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, this.text(text), x, y, color, false, justification);
    }

    default void drawStringShadowJustified(PoseStack poseStack, String text, float x, float y, int color, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, this.text(text), x, y, color, true, justification);
    }

    default void drawStringJustified(PoseStack poseStack, String text, float x, float y, ChatFormatting color, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, this.text(text), x, y, color.getColor(), false, justification);
    }

    default void drawStringShadowJustified(PoseStack poseStack, String text, float x, float y, ChatFormatting color, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, this.text(text), x, y, color.getColor(), true, justification);
    }

    /// ----------------------------------------

    default void drawStringInternal(PoseStack poseStack, String text, float x, float y, int color, boolean shadow) {
        this.drawStringInternal(poseStack, this.text(text), x, y, color, shadow);
    }
    
    default void drawStringInternalJustified(PoseStack poseStack, String text, float x, float y, int color, boolean shadow, Justification justification) {
        this.drawStringJustifiedInternal(poseStack, this.text(text), x, y, color, shadow, justification);
    }

    default void drawStringInternal(PoseStack poseStack, Component component, float x, float y, int color, boolean shadow) {
        if (shadow) {
            this.font().drawShadow(poseStack, component, x, y, color);
        } else {
            this.font().draw(poseStack, component, x, y, color);
        }
    }

    default void drawStringJustifiedInternal(PoseStack poseStack, Component component, float x, float y, int color, boolean shadow, Justification justification) {
        float dx = switch (justification) {
            case LEFT -> 0;
            case CENTER -> -this.font().width(component) / 2.0f;
            case RIGHT -> -this.font().width(component);
        };

        this.drawStringInternal(poseStack, component, x + dx, y, color, shadow);
    }
}
