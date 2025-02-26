package com.hyperactvty.blockoffate.registry.forge;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistryImpl {
    public static Map<ResourceLocation, SoundEvent> soundEventMap = new LinkedHashMap();

    public static void register(ResourceLocation identifier, SoundEvent event) {
        soundEventMap.put(identifier, event);
    }
}