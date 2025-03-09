package com.hyperactvty.blockoffate.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;

public class Statistics {
//    public static final StatType<ResourceLocation> BOF_STAT_GROUP = new StatType<>(BuiltInRegistries.CUSTOM_STAT, StatFormatter.DEFAULT);
    public static final StatType<ResourceLocation> BOF_STAT_GROUP = new StatType<>(BuiltInRegistries.CUSTOM_STAT, null);
//    public static final StatType<ResourceLocation> BOF_STAT_GROUP = new StatType<>(BuiltInRegistries.CUSTOM_STAT, null);
//    ResourceLocation.fromNamespaceAndPath("blockoffate", "custom_group")
    public static final ResourceLocation BLOCKS_OPENED = ResourceLocation.fromNamespaceAndPath("blockoffate", "blocks_opened");
    public static final ResourceLocation FATES_CURSED = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_cursed");
    public static final ResourceLocation FATES_F = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_f");
    public static final ResourceLocation FATES_D = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_d");
    public static final ResourceLocation FATES_C = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_c");
    public static final ResourceLocation FATES_B = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_b");
    public static final ResourceLocation FATES_A = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_a");
    public static final ResourceLocation FATES_S = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_s");
    public static final ResourceLocation FATES_SS = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_ss");
    public static final ResourceLocation FATES_SSS = ResourceLocation.fromNamespaceAndPath("blockoffate", "fates_sss");

    public static final ResourceLocation PLAYER_LUCK = ResourceLocation.fromNamespaceAndPath("blockoffate", "player_luck");
    public static final ResourceLocation KARMA = ResourceLocation.fromNamespaceAndPath("blockoffate", "karma");
}