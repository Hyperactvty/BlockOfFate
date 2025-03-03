package com.hyperactvty.blockoffate.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class LootTables {
    public static final ResourceLocation FIRST_JOIN_WORLD = ResourceLocation.fromNamespaceAndPath("blockoffate", "first_join_world");
    public static final ResourceLocation FIRST_JOIN_WORLD0 = ResourceLocation.parse("blockoffate:advancements/first_join_world");
    public static final ResourceKey<LootTable> FIRST_JOIN_WORLD1 = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world"));
}
