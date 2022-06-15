package dev.mattrm.mc.modularmachines.client.gui;

public class ControllerScreenState {
    private static int nextId = 0;
    private static boolean panningLockState = false;
    private static boolean zoomingLockState = false;

    private static FocusableEventListener focusedElement = null;

    public static int getNextId() {
        return nextId++;
    }

    public static boolean isPanningLocked() {
        return panningLockState;
    }

    public static void setPanningLockState(boolean value) {
        panningLockState = value;
    }

    public static void lockPanning() {
        setPanningLockState(true);
    }

    public static void unlockPanning() {
        setPanningLockState(false);
    }

    public static boolean isZoomingLocked() {
        return zoomingLockState;
    }

    public static void setZoomingLockState(boolean value) {
        zoomingLockState = value;
    }

    public static void lockZooming() {
        setZoomingLockState(true);
    }

    public static void unlockZooming() {
        setZoomingLockState(false);
    }

    public static void setFocusedElement(FocusableEventListener element) {
        if (focusedElement != null) {
            focusedElement.setFocused(false);
        }

        focusedElement = element;

        if (focusedElement != null) {
            focusedElement.setFocused(true);
        }
    }
}
