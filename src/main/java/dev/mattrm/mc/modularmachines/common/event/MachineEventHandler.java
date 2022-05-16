package dev.mattrm.mc.modularmachines.common.event;

import dev.mattrm.mc.modularmachines.api.block.IMachinePart;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MachineEventHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock() instanceof IMachinePart && ((IMachinePart) event.getState().getBlock()).isConnected(event.getWorld(), event.getPos())) {
            event.setCanceled(true);
        }
    }
}
