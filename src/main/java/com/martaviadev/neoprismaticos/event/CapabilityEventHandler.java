package com.martaviadev.neoprismaticos.event;

import com.martaviadev.neoprismaticos.capability.PlayerClassProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityEventHandler {

    private static final ResourceLocation PLAYER_CLASS_CAP =
            new ResourceLocation("neoprismaticos", "player_class");

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            PlayerClassProvider provider = new PlayerClassProvider();
            event.addCapability(PLAYER_CLASS_CAP, provider);
            event.addListener(provider::invalidate);
        }
    }

    // Copia la clase elegida cuando el jugador muere y respawnea
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        event.getOriginal().reviveCaps();
        event.getOriginal().getCapability(PlayerClassProvider.PLAYER_CLASS).ifPresent(oldCap ->
                event.getEntity().getCapability(PlayerClassProvider.PLAYER_CLASS).ifPresent(newCap ->
                        newCap.setSelectedClass(oldCap.getSelectedClass())
                )
        );
        event.getOriginal().invalidateCaps();
    }
}