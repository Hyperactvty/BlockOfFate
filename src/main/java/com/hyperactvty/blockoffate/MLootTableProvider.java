package com.hyperactvty.blockoffate;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
//
//public class MLootTableProvider extends LootTableProvider {
//
//    private final CompletableFuture<HolderLookup.Provider> registries;
//    private final ExistingFileHelper fileHelper;
//
//    // Constructor
//    public MLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
//        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)), registries);
//        this.registries = registries;
//        this.fileHelper = fileHelper;
//    }
//
//    // This method will run the loot table generation process
//    @Override
//    public CompletableFuture<Void> run(DirectoryCache cache) {
//        return registries.thenApplyAsync(provider -> {
//            // Generate loot tables
//            this.getTables().forEach(pair -> {
//                // Write out the generated loot tables here
//                this.saveLootTable(pair, cache);
//            });
//        }).exceptionally(ex -> {
//            // Handle any exceptions
//            ex.printStackTrace();
//            return null;
//        });
//    }
//
//    // Return a list of loot tables to be registered
//    @Override
//    protected List<Pair<ResourceLocation, LootTable.Builder>> getTables() {
//        // You can define multiple loot tables here
//        return List.of(
//                Pair.of(ResourceKey.create(Registries.LOOT_TABLE, new ResourceLocation("modid", "first_join_world")),
//                        createFirstJoinLootTable())
//        );
//    }
//
//    // Method to create the loot table
//    private LootTable.Builder createFirstJoinLootTable() {
//        // Here you can define the loot table structure
//        return LootTable.lootTable()
//                .addLootPool(LootPool.lootPool()
//                        .add(LootItem.lootTableItem(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("modid", "special_block"))))
//                        .setRolls(ConstantLootNumberProvider.create(1)));
//    }
//
//    // Save the loot tables
//    private void saveLootTable(Pair<ResourceLocation, LootTable.Builder> pair, DirectoryCache cache) {
//        // Save the loot table to the correct directory
//        save(pair.getSecond(), cache, pair.getFirst());
//    }
//}

//public class MLootTableProvider extends LootTableProvider {
//
//    public MLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
////        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)).resultNow(), registries);
//        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)).join(), registries);
//    }
//
//    @Override
//    public List<SubProviderEntry> getTables() {
//        // Return the list of SubProviderEntry that registers your loot tables
//        return List.of(
//                new SubProviderEntry(
//                        (HolderLookup.Provider provider) -> new LootTableSubProvider() {
//                            @Override
//                            public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
//                                // Register custom loot table generation logic here
//                                lootTableConsumer.accept(
//                                        ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
//                                        createYourCustomLootTable()
//                                );
//                            }
//                        },
//                        new ContextKeySet.Builder().build()
//                )
//        );
//    }
//
//    // Method that generates a custom loot table
//    private LootTable.Builder createYourCustomLootTable() {
//        // Your custom loot table generation logic here
//        // Example:
//        return LootTable.lootTable()
//                .withPool(
//                        LootPool.lootPool()
//                                .add(LootItem.lootTableItem(Items.DIAMOND))
//                );
//    }
//
//    private static class ModLootTables implements LootTableSubProvider {
//
//        public ModLootTables(HolderLookup.Provider provider) {
//        }
//
////        public static List<SubProviderEntry> toSubProvider(HolderLookup.Provider provider, ExistingFileHelper fileHelper) {
////            return List.of(new SubProviderEntry(ModLootTables::new, ContextKeySet.Builder(), fileHelper));
////        }
//
//        public static List<SubProviderEntry> toSubProvider(HolderLookup.Provider provider, ExistingFileHelper fileHelper) {
//            ContextKeySet emptyContext = new ContextKeySet.Builder().build();
//
////            spe.provider().generate(provider);  // Manually triggering the generation
//            List<SubProviderEntry> lSpe = Collections.singletonList(new SubProviderEntry(
//                    // Creating the LootTableSubProvider function
//                    (HolderLookup.Provider p) -> lootTableConsumer -> {
//                        System.err.println("[ModLootTableProvider.java > toSubProvider] C: `generate`");
//                        // Accepting the loot table generation for your custom loot table
//                        lootTableConsumer.accept(
//                                ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
//                                createFirstJoinLootTable()
//                        );
//                        System.err.println("\tDone? > ");
//                    },
//                    // Providing the ContextKeySet.EMPTY as no specific context is needed for this example
//                    emptyContext //ContextKeySet.EMPTY
//            ));
//            System.err.println("\tAfter List > "+lSpe);
//            for(Object o: lSpe.toArray()) {
//                System.err.println("\to > "+o);
//            }
//            return lSpe;
//        }
//
//
////        @Override
////        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> lootTableConsumer) {
////            lootTableConsumer.accept(
////                    FIRST_JOIN_WORLD_LOOT_TABLE.location(),
////                    createFirstJoinLootTable()
////            );
////        }
//
//        @Override
//        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
//            System.err.println("ModLootTableProvider.generate");
//            lootTableConsumer.accept(
//                    ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
////                    ResourceKey.create(ResourceKey.createRegistryKey(ResourceLocation.parse("blockoffate:advancements/first_join_world")), ResourceLocation.parse("blockoffate:advancements/first_join_world")),
//
//                    //                        ResourceLocation.parse("blockoffate:advancements/first_join_world"), // Loot table ID
//                    createFirstJoinLootTable()
//            );
//            System.err.println("\tModLootTableProvider.generate -> Done? > ");
//        }
//
//        private static LootTable.Builder createFirstJoinLootTable() {
//            return LootTable.lootTable()
//                    .withPool(LootPool.lootPool()
//                            .setRolls(ConstantValue.exactly(1))
//                            .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1))
//                            .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(1))
//                    );
//        }
//    }
//}

public class MLootTableProvider extends LootTableProvider {

    public MLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)).join(), registries);
    }

    @Override
    public List<SubProviderEntry> getTables() {
        // Return the list of SubProviderEntry that registers your loot tables
        return List.of(
                new SubProviderEntry(
                        (HolderLookup.Provider provider) -> new LootTableSubProvider() {
                            @Override
                            public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
                                // Register custom loot table generation logic here
                                System.out.println("Generating Loot Table for: first_join_world");
                                lootTableConsumer.accept(
                                        ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
                                        createYourCustomLootTable()
                                );
                            }
                        },
                        new ContextKeySet.Builder().build()
                )
        );
    }

    // Method that generates a custom loot table
    private LootTable.Builder createYourCustomLootTable() {
        if (Items.DIAMOND == null) {
            System.err.println("Diamond item is null!");
        } else {
            System.err.println("Diamond item is NOT null!");
        }
        // Your custom loot table generation logic here
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.DIAMOND))
                );
    }

    private static class ModLootTables implements LootTableSubProvider {

        public ModLootTables(HolderLookup.Provider provider) {
            // Log provider to ensure it's not null
            System.out.println("ModLootTables Initialized with provider: " + provider);
        }

        public static List<SubProviderEntry> toSubProvider(HolderLookup.Provider provider, ExistingFileHelper fileHelper) {
            // Log fileHelper to ensure it's not null
            System.out.println("toSubProvider called with provider: " + provider + " and fileHelper: " + fileHelper);

            ContextKeySet emptyContext = new ContextKeySet.Builder().build();

            List<SubProviderEntry> lSpe = Collections.singletonList(new SubProviderEntry(
                    (HolderLookup.Provider p) -> lootTableConsumer -> {
                        System.out.println("[ModLootTableProvider > toSubProvider] Generating loot table...");
                        // Accepting the loot table generation for your custom loot table
                        lootTableConsumer.accept(
                                ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
                                createFirstJoinLootTable()
                        );
                        System.out.println("[ModLootTableProvider > toSubProvider] Loot table generation complete.");
                    },
                    emptyContext //ContextKeySet.EMPTY
            ));
            System.out.println("[ModLootTableProvider > toSubProvider] Returning SubProviderEntry list: " + lSpe);
            return lSpe;
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
            System.out.println("ModLootTableProvider.generate");
            lootTableConsumer.accept(
                    ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
                    createFirstJoinLootTable()
            );
            System.out.println("ModLootTableProvider.generate completed.");
        }

        private static LootTable.Builder createFirstJoinLootTable() {
            return LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1))
                            .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(1))
                    );
        }
    }
}

