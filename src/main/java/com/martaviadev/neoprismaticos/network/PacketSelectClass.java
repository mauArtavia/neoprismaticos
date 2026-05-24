package com.martaviadev.neoprismaticos.network;

import com.martaviadev.neoprismaticos.capability.PlayerClassProvider;
import com.martaviadev.neoprismaticos.classes.RPGClass;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSelectClass {

    private final RPGClass rpgClass;

    public PacketSelectClass(RPGClass rpgClass) {
        this.rpgClass = rpgClass;
    }

    public static void encode(PacketSelectClass packet, FriendlyByteBuf buf) {
        buf.writeEnum(packet.rpgClass);
    }

    public static PacketSelectClass decode(FriendlyByteBuf buf) {
        return new PacketSelectClass(buf.readEnum(RPGClass.class));
    }

    public static void handle(PacketSelectClass packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            player.getCapability(PlayerClassProvider.PLAYER_CLASS).ifPresent(cap -> {
                // Solo permite elegir si aún no tiene clase
                if (!cap.hasChosenClass()) {
                    cap.setSelectedClass(packet.rpgClass);
                    player.sendSystemMessage(
                            net.minecraft.network.chat.Component.literal(
                                    "¡Elegiste la clase " + packet.rpgClass.getDisplayName() + "!"
                            )
                    );
                }
            });
        });
        context.setPacketHandled(true);
    }
}