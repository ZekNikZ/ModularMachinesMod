package dev.mattrm.mc.modularmachines.client.gui;

public interface FocusableEventListener {
    boolean isFocused();

    void setFocused(boolean focus);

    boolean isHovered();

    void setHovered(boolean hover);

    default boolean canFocus() {
        return false;
    }

    default boolean bypassFocus() {
        return false;
    }

    default boolean isMouseOver(double relMouseX, double relMouseY) {
        return false;
    }

    default void mouseHoverStart() {
    }

    default void mouseHoverEnd() {
    }

    default void mouseMoved(double relMouseX, double relMouseY) {
    }

    default boolean mouseClicked(double relMouseX, double relMouseY, int button) {
        return false;
    }

    default boolean mouseReleased(double relMouseX, double relMouseY, int button) {
        return false;
    }

    default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    default boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    default boolean charTyped(char codePoint, int modifiers) {
        return false;
    }
}
