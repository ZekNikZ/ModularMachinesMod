package dev.mattrm.mc.modularmachines.old;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class RelativeScreen extends Screen implements RelativeWidget {
    private final List<RelativeWidget> children = new ArrayList<>();

    protected RelativeScreen(Component title) {
        super(title);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int relMouseX, int relMouseY, float partialTick) {
        super.render(poseStack, relMouseX, relMouseY, partialTick);
        this.renderRelative(poseStack, relMouseX, relMouseY, partialTick, relMouseX, relMouseY);
    }

    @Override
    public List<RelativeWidget> getChildren() {
        return this.children;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    protected void addRelativeWidget(RelativeWidget widget) {
        this.children.add(widget);
    }
}
