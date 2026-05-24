package com.martaviadev.neoprismaticos;

import com.martaviadev.neoprismaticos.capability.PlayerClassProvider;
import com.martaviadev.neoprismaticos.event.CapabilityEventHandler;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(NeoPrismaticos.MODID)
public class NeoPrismaticos {

    public static final String MODID = "neoprismaticos";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NeoPrismaticos() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());

        LOGGER.info("NeoPrismaticos cargando...");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("NeoPrismaticos: Common Setup completado.");
    }
}