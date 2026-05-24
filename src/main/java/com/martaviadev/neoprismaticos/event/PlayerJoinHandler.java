package com.martaviadev.neoprismaticos.event;

import com.martaviadev.neoprismaticos.capability.PlayerClassProvider;
import com.martaviadev.neoprismaticos.gui.ClassSelectionScreen;
import com.martaviadev.neoprismaticos.network.NetworkHandler;
import com.martaviadev.neoprismaticos.network.PacketSelectClass;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerJoinHandler {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        event.getEntity().getCapability(PlayerClassProvider.PLAYER_CLASS).ifPresent(cap -> {
            if (!cap.hasChosenClass()) {
                Minecraft.getInstance().tell(() ->
                        Minecraft.getInstance().setScreen(
                                new ClassSelectionScreen(rpgClass ->
                                        NetworkHandler.CHANNEL.sendToServer(new PacketSelectClass(rpgClass))
                                )
                        )
                );
            }
        });
    }
}