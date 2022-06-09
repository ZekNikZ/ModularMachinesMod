package dev.mattrm.mc.modularmachines.api.machine.pin;

import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public abstract class Pin<T> {
    public abstract String name();

    public enum PinType {
        /**
         * An INPUT pin, meaning that it accepts input from output pins.
         */
        INPUT,

        /**
         * An OUTPUT pin, meaning that it pushes output to input pins.
         */
        OUTPUT
    }

    /**
     * The I/O type of this pin.
     *
     * @return INPUT or OUTPUT, correspondingly
     */
    public abstract PinType ioType();

    /**
     * The type of this pin. Used to determine if two pins are compatible.
     *
     * @return the type of the pin
     */
    public abstract Class<T> type();

    /**
     * The icon associated with this pin type.
     *
     * @return the location of the icon file
     */
    public abstract ResourceLocation icon();

    /**
     * The color of the pin icon and text.
     *
     * @return the color
     */
    public abstract Color color();

    /**
     * The maximum number of connections to this pin. Return -1 if INFINITE.
     *
     * @return the maximum of connections
     */
    public abstract int maxConnectionCount();
}

