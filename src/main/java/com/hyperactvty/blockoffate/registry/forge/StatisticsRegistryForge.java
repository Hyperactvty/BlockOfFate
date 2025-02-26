package com.hyperactvty.blockoffate.registry.forge;

import com.hyperactvty.blockoffate.registry.StatisticsRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;

@EventBusSubscriber(
        modid = "blockoffate",
        bus = Bus.MOD
)
public class StatisticsRegistryForge {
    @SubscribeEvent
    public static void registerStatistics(RegisterEvent event) {
        event.register(Keys.STAT_TYPES, (statRegisterHelper) -> {
            StatisticsRegistry.registerStatistics();
        });
    }
}