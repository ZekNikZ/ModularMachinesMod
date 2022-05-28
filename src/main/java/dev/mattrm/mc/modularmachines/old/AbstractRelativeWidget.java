package dev.mattrm.mc.modularmachines.old;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRelativeWidget extends GuiComponent implements RelativeWidget {
    public List<RelativeWidget> children = new ArrayList<>();
    public int x;
    public int y;

    public AbstractRelativeWidget(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public List<RelativeWidget> getChildren() {
        return this.children;
    }

    @Override
    public int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected void addWidget(RelativeWidget widget) {
        this.children.add(widget);
    }

    protected void clearWidgets() {
        this.children.clear();
    }

    @Override
    public final void render(@NotNull PoseStack poseStack, int relMouseX, int relMouseY, float partialTick) {
        RelativeWidget.super.render(poseStack, relMouseX, relMouseY, partialTick);
    }

    @Override
    public final void render(@NotNull PoseStack poseStack, int relMouseX, int relMouseY, float partialTick, int absMouseX, int absMouseY) {
        RelativeWidget.super.render(poseStack, relMouseX, relMouseY, partialTick, absMouseX, absMouseY);
    }
}