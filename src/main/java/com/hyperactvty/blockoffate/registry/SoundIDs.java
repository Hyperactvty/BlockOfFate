package com.hyperactvty.blockoffate.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundIDs {
    public static final ResourceLocation FATE_BLOCK_CHIMING_ID;
    public static SoundEvent FATE_BLOCK_CHIMING_EVENT;

    static {
        FATE_BLOCK_CHIMING_ID = ResourceLocation.fromNamespaceAndPath("blockoffate", "fate_chiming_constant");
        FATE_BLOCK_CHIMING_EVENT = SoundEvent.createVariableRangeEvent(FATE_BLOCK_CHIMING_ID);
    }
}