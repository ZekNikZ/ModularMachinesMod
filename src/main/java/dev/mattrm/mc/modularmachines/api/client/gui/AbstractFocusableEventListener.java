package dev.mattrm.mc.modularmachines.api.client.gui;

public class AbstractFocusableEventListener implements FocusableEventListener {
    private boolean focused = false;
    private boolean hovered = false;

    @Override
    public final boolean isFocused() {
        return this.focused;
    }

    @Override
    public final void setFocused(boolean focused) {
        this.focused = focused;
    }

    @Override
    public boolean isHovered() {
        return this.hovered;
    }

    @Override
    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }
}
