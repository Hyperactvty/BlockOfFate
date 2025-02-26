package com.hyperactvty.blockoffate.registry.forge;

import com.hyperactvty.blockoffate.registry.SoundRegistry;
import java.util.Map;
import java.util.Objects;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;

@EventBusSubscriber(
        modid = "blockoffate",
        bus = Bus.MOD
)
public class SoundRegistryForge {
    @SubscribeEvent
    public static void registerSounds(RegisterEvent event) {
        event.register(Keys.SOUND_EVENTS, (soundEventRegisterHelper) -> {
            SoundRegistry.registerSounds();
            Map<ResourceLocation, SoundEvent> var10000 = SoundRegistryImpl.soundEventMap;
            Objects.requireNonNull(soundEventRegisterHelper);
            var10000.forEach(soundEventRegisterHelper::register);
        });
    }
}