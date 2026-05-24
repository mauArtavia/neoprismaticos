package com.martaviadev.neoprismaticos.network;

import com.martaviadev.neoprismaticos.NeoPrismaticos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(NeoPrismaticos.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        CHANNEL.registerMessage(
                packetId++,
                PacketSelectClass.class,
                PacketSelectClass::encode,
                PacketSelectClass::decode,
                PacketSelectClass::handle
        );
    }
}