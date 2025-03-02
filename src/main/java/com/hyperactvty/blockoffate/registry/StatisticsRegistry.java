package com.hyperactvty.blockoffate.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

import static com.hyperactvty.blockoffate.registry.Statistics.BOF_STAT_GROUP;

public class StatisticsRegistry {
    public static void registerStatistics() {
//        Registry.register(BuiltInRegistries.STAT_TYPE, /*new ResourceLocation*/("blockoffate:custom_tab"), BOF_STAT_GROUP);
        Registry.register(BuiltInRegistries.STAT_TYPE, /*new ResourceLocation*/("custom_tab"), BOF_STAT_GROUP);
//        Registry.register(BuiltInRegistries.STAT_TYPE, new ResourceLocation("blockoffate:custom_tab"), BOF_STAT_GROUP);

        Registry.register(BuiltInRegistries.CUSTOM_STAT, "blocks_opened", Statistics.BLOCKS_OPENED);
        Stats.CUSTOM.get(Statistics.BLOCKS_OPENED, StatFormatter.DEFAULT);

        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_cursed", Statistics.FATES_CURSED);
        Stats.CUSTOM.get(Statistics.FATES_CURSED, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_f", Statistics.FATES_F);
        Stats.CUSTOM.get(Statistics.FATES_F, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_d", Statistics.FATES_D);
        Stats.CUSTOM.get(Statistics.FATES_D, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_c", Statistics.FATES_C);
        Stats.CUSTOM.get(Statistics.FATES_C, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_b", Statistics.FATES_B);
        Stats.CUSTOM.get(Statistics.FATES_B, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_a", Statistics.FATES_A);
        Stats.CUSTOM.get(Statistics.FATES_A, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_s", Statistics.FATES_S);
        Stats.CUSTOM.get(Statistics.FATES_S, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_ss", Statistics.FATES_SS);
        Stats.CUSTOM.get(Statistics.FATES_SS, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "fates_sss", Statistics.FATES_SSS);
        Stats.CUSTOM.get(Statistics.FATES_SSS, StatFormatter.DEFAULT);

        Registry.register(BuiltInRegistries.CUSTOM_STAT, "player_luck", Statistics.PLAYER_LUCK);
        Stats.CUSTOM.get(Statistics.PLAYER_LUCK, StatFormatter.DEFAULT);
    }
}