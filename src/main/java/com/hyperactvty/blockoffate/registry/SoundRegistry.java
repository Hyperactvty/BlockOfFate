package com.hyperactvty.blockoffate.registry;

import com.hyperactvty.blockoffate.registry.forge.SoundRegistryImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistry {
    public static void registerSounds() {
        register(SoundIDs.FATE_BLOCK_CHIMING_ID, SoundIDs.FATE_BLOCK_CHIMING_EVENT);
    }

    public static void register(ResourceLocation identifier, SoundEvent event) {
        SoundRegistryImpl.register(identifier, event);
    }
}
