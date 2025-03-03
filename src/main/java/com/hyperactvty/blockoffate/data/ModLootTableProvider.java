package com.hyperactvty.blockoffate.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.util.context.ContextKeySet;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}

//public class ModLootTableProvider extends LootTableProvider {
//
//    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
////        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)), registries);
//        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)).join(), registries);
////        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)).join(), registries);
//    }
//
//    private static final ResourceKey<LootTable> FIRST_JOIN_WORLD_LOOT_TABLE =
//            ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world"));
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
//            // Creating a LootTableSubProvider instance using a lambda expression that generates loot tables
////            SubProviderEntry spe = new SubProviderEntry(
////                    // Creating the LootTableSubProvider function
////                    (HolderLookup.Provider p) -> lootTableConsumer -> {
////                        System.err.println("[ModLootTableProvider.java > toSubProvider] C: `generate`");
////                        // Accepting the loot table generation for your custom loot table
////                        lootTableConsumer.accept(
////                                ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")), // works
////                                createFirstJoinLootTable() // works
////                        );
////                        System.err.println("\tDone? > "+lootTableConsumer);
////                    },
////                    // Providing the ContextKeySet.EMPTY as no specific context is needed for this example
////                    emptyContext //ContextKeySet.EMPTY
////            );
//
////            SubProviderEntry spe = new SubProviderEntry(
////                    // Creating the LootTableSubProvider function
////                    (HolderLookup.Provider p) -> new LootTableSubProvider() {
////                        @Override
////                        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
////                            System.err.println("ModLootTableProvider.toSubProvider.generate");
////                            // Accepting the loot table generation for your custom loot table
////                            lootTableConsumer.accept(
////                                    ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
////                                    createFirstJoinLootTable()
////                            );
////                            System.err.println("\ttoSubProvider -> Done? > ");
////                        }
////                    },
////                    // Providing the ContextKeySet.EMPTY as no specific context is needed for this example
////                    emptyContext //ContextKeySet.EMPTY
////            );
//
//            SubProviderEntry spe = new SubProviderEntry(
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
//            );
//            System.err.println("\tThe provider > "+spe.provider());
////            spe.provider().generate(provider);  // Manually triggering the generation
//            System.err.println("\tThe spe > "+spe);
//            System.err.println("\tThe LIST > "+List.of(spe));
//            System.err.println("\tBefore LIST > ");
//            List<SubProviderEntry> lSpe = List.of(spe);
//            System.err.println("\tBefore RETURN > ");
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
//        //                        ResourceLocation.parse("blockoffate:advancements/first_join_world"), // Loot table ID
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

//
//public class ModLootTableProvider extends LootTableProvider {
//
////        public ModLootTableProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
////            super(output, Set.of(), existingFileHelper, List.of(new ModLootTables()));
////        }
//
//    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
////            super(output, fileHelper, List.of(new ModLootTables()), registries);
//
////        super(output, Set.of(), (ModLootTables.toSubProvider((HolderLookup.Provider) registries, fileHelper)), registries);
//        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)).join(), registries);
////        super(output, Set.of(), (ModLootTables.toSubProvider((HolderLookup.Provider) registries, fileHelper)), registries);
////        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)), registries);
////        super(output, Set.of(), registries.thenApply(provider -> ModLootTables.toSubProvider(provider, fileHelper)), registries);
//
////        super(output, Set.of(), (ModLootTables.toSubProvider((HolderLookup.Provider) registries)), registries);
////        super(output, fileHelper, new SubProviderEntry(ModLootTables::toSubProvider, registries), registries);
//    }
//
//
////    public ModLootTableProvider(PackOutput output, /*Set<ResourceKey<LootTable>> setOf,*/ CompletableFuture<HolderLookup.Provider> registries) {
////        super(output, Set.of(), (ModLootTables.toSubProvider((HolderLookup.Provider) registries)), registries);
////    }
//
////    private static class ModLootTables implements LootTableSubProvider {
////        private final ExistingFileHelper fileHelper;
////        private final HolderLookup.Provider provider;
////
////        public ModLootTables(HolderLookup.Provider provider, ExistingFileHelper fileHelper) {
////
////            this.fileHelper = fileHelper;
////            this.provider = provider;
////        }
//
//    private class ModLootTables implements LootTableSubProvider {
//
//        private final ExistingFileHelper fileHelper;
//        private final HolderLookup.Provider provider;
//
//        public ModLootTables(HolderLookup.Provider provider, ExistingFileHelper fileHelper) {
//
//            this.fileHelper = fileHelper;
//            this.provider = provider;
//        }
//        //            public LootTableSubProvider toSubProvider(ExistingFileHelper existingFileHelper) {
////                return (registries, saver) -> this.generate(registries, saver, existingFileHelper);
////            }
////        public static LootTableSubProvider toSubProvider(HolderLookup.Provider provider) {
////            return (registries, saver) -> this.generate(registries, saver, provider);
////        }
//
//
////        public static List<SubProviderEntry> toSubProvider(HolderLookup.Provider provider, ExistingFileHelper fileHelper) {
////            return List.of(new ModLootTables(provider, fileHelper));
////
//////            return generate((key, builder) -> lootTableConsumer.accept(key, builder));
//////            return (registries, saver) -> generate((BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) provider);
//////            return generate((BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) provider);
////        }
//
//        public static List<SubProviderEntry> toSubProvider(HolderLookup.Provider provider, ExistingFileHelper fileHelper) {
////            return List.of(new SubProviderEntry((lootTableConsumer) -> lootTableConsumer.accept(
////                    ResourceKey.create(
////                            net.minecraft.core.registries.Registries.LOOT_TABLE,
////                            ResourceLocation.parse("blockoffate:advancements/first_join_world")
//////                                new ResourceLocation("blockoffate", "advancements/first_join_world")
////                    ),
////                    createFirstJoinLootTable()
////            ), fileHelper));
//
////            return List.of(new SubProviderEntry(
////                    (BiConsumer<ResourceLocation, LootTable.Builder> lootTableConsumer) -> {
////                        lootTableConsumer.accept(
////                                ResourceLocation.parse("blockoffate:advancements/first_join_world"),
////                                createFirstJoinLootTable()
////                        );
////                    },
////                    fileHelper
////            ));
//
//            return List.of(new SubProviderEntry(
//                    (BiConsumer<ResourceLocation, LootTable.Builder> lootTableConsumer) -> lootTableConsumer.accept(
//                            ResourceLocation.parse("blockoffate:advancements/first_join_world"),
//                            createFirstJoinLootTable()
//                    ),
//                    fileHelper
//            ));
//
////            return List.of(new SubProviderEntry(
////                    new LootTableSubProvider() {
////                        @Override
////                        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> lootTableConsumer) {
////                            lootTableConsumer.accept(
////                                    new ResourceLocation("blockoffate", "advancements/first_join_world"),
////                                    createFirstJoinLootTable()
////                            );
////                        }
////                    },
////                    fileHelper
////            ));
//        }
//
//
//
////            @Override
////            public void generate(BiConsumer<ResourceLocation, LootTable.Builder> lootTableConsumer) {
////                // Register custom loot tables here
////                lootTableConsumer.accept(
////                        ResourceLocation.parse("blockoffate:advancements/first_join_world"), // Loot table ID
////                        createFirstJoinLootTable()
////                );
////            }
//
//        private static LootTable.Builder createFirstJoinLootTable() {
//            return LootTable.lootTable()
//                    .withPool(LootPool.lootPool()
//                            .setRolls(ConstantValue.exactly(1)) // 1 Roll per pool
//                            .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)) // 1 Diamond as a reward
//                            .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(1)) // 1 Golden Apple as a reward
//                    );
//        }
//
//        @Override
//        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
//            lootTableConsumer.accept(
//                    ResourceKey.create(ResourceKey.createRegistryKey(ResourceLocation.parse("blockoffate:advancements/first_join_world")), ResourceLocation.parse("blockoffate:advancements/first_join_world")),
//
////                        ResourceLocation.parse("blockoffate:advancements/first_join_world"), // Loot table ID
//                    createFirstJoinLootTable()
//            );
//        }
//
//
//    }
//}
