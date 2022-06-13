package dev.mattrm.mc.modularmachines.client.gui;

import dev.mattrm.mc.modularmachines.common.blockentity.ISynchedDataManager;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class SynchedDataScreen<M extends ISynchedDataManager<?>> extends Screen implements ISynchedDataScreen<M> {
    private final M manager;

    protected SynchedDataScreen(M manager, Component title) {
        super(title);
        this.manager = manager;
    }

    @Override
    public M getManager() {
        return this.manager;
    }
}
