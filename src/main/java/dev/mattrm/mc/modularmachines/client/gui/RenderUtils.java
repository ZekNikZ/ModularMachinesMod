package dev.mattrm.mc.modularmachines.client.gui;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;

import java.awt.*;

public class RenderUtils {
    public static void drawLine(PoseStack poseStack, int x1, int y1, int x2, int y2, Color color) {
        var buf = Tesselator.getInstance().getBuilder();
        Matrix4f matrix = poseStack.last().pose();
        buf.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR);
        buf.vertex(matrix, x1, y1, 0)
            .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
            .endVertex();
        buf.vertex(matrix, x2, y2, 0)
            .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
            .endVertex();
        Tesselator.getInstance().end();
    }
}
