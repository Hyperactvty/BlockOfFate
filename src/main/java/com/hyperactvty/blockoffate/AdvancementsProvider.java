package com.hyperactvty.blockoffate;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AdvancementsProvider extends ForgeAdvancementProvider {

    /* net.minecraftforge.common.extensions.IForgeAdvancementBuilder.save(Consumer, ResourceLocation, ExistingFileHelper) */
    /**
     * Constructs an advancement provider using the generators to write the
     * advancements to a file.
     *
     * @param output             the target directory of the data generator
     * @param registries         a future of a lookup for registries and their objects
     * @param existingFileHelper a helper used to find whether a file exists
     * @param subProviders       the generators used to create the advancements
     */
    public AdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper, List<AdvancementGenerator> subProviders) {
        super(output, registries, existingFileHelper, subProviders);
    }

    public AdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
        super(output, registries, fileHelper, List.of(new MyAdvancementGenerator())); // Register your generator here
        System.out.println("AdvancementsProvider Constructor called.");
    }

// Implement the AdvancementGenerator to generate the advancement
    public static class MyAdvancementGenerator implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            System.err.println("[AdvancementsProvider.java > MyAdvancementGenerator] C: `generate`");
            CriterionTriggerInstance cti = new ImpossibleTrigger.TriggerInstance(); // add to blog on how to make custom advancements for minecraft 1.21.4
            ImpossibleTrigger.TriggerInstance ti = new ImpossibleTrigger.TriggerInstance(); // add to blog on how to make custom advancements for minecraft 1.21.4
            Criterion<?> criterion = new Criterion<>(CriteriaTriggers.TICK,new PlayerTrigger.TriggerInstance(Optional.ofNullable(null)));


            DisplayInfo di = new DisplayInfo(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("blockoffate:bof_lucky_ham")))), // Icon
                    Component.translatable("advancement.blockoffate.first_join_world.title"), // Title
                    Component.translatable("advancement.blockoffate.first_join_world.desc"), // Description
                    Optional.of(ResourceLocation.parse("minecraft:textures/block/crying_obsidian")), // Background
                    AdvancementType.GOAL, // Display format (null for default)
                    true, true, false); // Toast and chat announcements

            List<ResourceKey<LootTable>> lootTable = List.of(
//                    ResourceKey.createRegistryKey(ResourceLocation.parse("minecraft:chests/abandoned_mineshaft")),
//                    ResourceKey.createRegistryKey(ResourceLocation.parse("minecraft:chests/ancient_city")),
//                    ResourceKey.create(ForgeRegistries.LOOT_TABLES, ResourceLocation.parse("minecraft:chests/ancient_city")),
//                    ResourceKey.create(Registry.LOOT_TABLE_REGISTRY, ResourceLocation.parse("minecraft:chests/ancient_city")),
                    ResourceKey.create(ResourceKey.createRegistryKey(ResourceLocation.parse("minecraft:chests/end_city_treasure")), ResourceLocation.parse("minecraft:chests/end_city_treasure"))
            );
            AdvancementRewards rewards = new AdvancementRewards(100, lootTable,null,null);
            // ResourceKey.codec(Registries.LOOT_TABLE).listOf().optionalFieldOf("loot", List.of()).forGetter(AdvancementRewards::loot),
            AdvancementRewards.Builder rewardBuilder = new AdvancementRewards.Builder()
                .addExperience(250)
                .addLootTable(ResourceKey.create(ResourceKey.createRegistryKey(ResourceLocation.parse("minecraft:chests/end_city_treasure")), ResourceLocation.parse("minecraft:chests/end_city_treasure")))
                .addLootTable(ResourceKey.create(ResourceKey.createRegistryKey(ResourceLocation.parse("blockoffate:loot_tables/advancements/first_join_world")), ResourceLocation.parse("blockoffate:loot_tables/advancements/first_join_world")));
            rewardBuilder.build();

//            {
//                "pools": [
//                {
//                    "rolls": {
//                    "min": 8,
//                            "max": 8
//                },
//                    "entries": [
//                    {
//                        "type": "item",
//                            "name": "blockoffate:generic",
//                            "weight": 1
//                    }
//      ]
//                }
//  ]
//            }

//            {
//                "type": "minecraft:item",
//                    "functions": [
//                {
//                    "function": "minecraft:enchant_with_levels",
//                        "levels": 30,
//                        "treasure": true
//                }
//          ],
//                "name": "minecraft:book"
//            },

            // Create your custom advancement using IForgeAdvancementBuilder
            System.err.println("[AdvancementsProvider.java > MyAdvancementGenerator] di > "+di.toString());
            System.err.println("[AdvancementsProvider.java > rewards] rewards > "+rewards);
            Advancement.Builder builder = Advancement.Builder.advancement()
                .addCriterion("first_join_world", criterion) // Criterion for the advancement
                .rewards(rewardBuilder)
                .display(di);

            System.err.println("[AdvancementsProvider.java > MyAdvancementGenerator] builder > "+builder);
            // Save the advancement to the file system
            builder.save(saver, "blockoffate:first_join_world"/*, existingFileHelper*/);
//            builder.save(saver, ResourceLocation.fromNamespaceAndPath("blockoffate","first_join_world")/*, existingFileHelper*/);
            System.err.println("[AdvancementsProvider.java > MyAdvancementGenerator] builder.save? > "+builder);
        }
    }
}
