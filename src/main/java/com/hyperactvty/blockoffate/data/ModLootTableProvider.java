package com.hyperactvty.blockoffate.data;

import com.hyperactvty.blockoffate.MLootTableProvider;
import com.hyperactvty.blockoffate.data.loot.ModBlockLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
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
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.util.context.ContextKeySet;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ModLootTableProvider extends LootTableProvider {

//    private static CompletableFuture<HolderLookup.Provider> registries;

    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ), registries);
        /**
        System.out.println("ModLootTableProvider `create` called.");
//        System.out.println(ModLootTableProvider.getTables());
        return new LootTableProvider(output, Set.of(), List.of(new SubProviderEntry(
                provider -> (LootTableSubProvider) lootTableConsumer -> {
                    System.out.println("Generating Loot Table for: first_join_world");
                    System.out.println("provider > "+provider);
                    System.out.println("lootTableConsumer > "+lootTableConsumer);
                    System.out.println("PackOutput > "+output);
                    System.out.println("registries > "+registries);

                    // Define the LootTable.Builder correctly
                    LootTable.Builder builder = LootTable.lootTable();

                    // Register the loot table
                    lootTableConsumer.accept(
                            ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
                            builder
                    );
                    lootTableConsumer.accept(
                            ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:block/bof_generic")),
                            builder
                    );
                    lootTableConsumer.accept(
                            ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:block/slab_of_ham")),
                            builder
                    );
                    builder.build();
                    System.out.println("lootTableConsumer > "+lootTableConsumer);
                    System.out.println("builder > "+builder);
                    System.out.println("builder > "+builder.getClass());
                    System.out.println("builder > "+builder.unwrap());

                },
                LootContextParamSets.BLOCK // Use the correct context param set
        )), registries);
*/
    }


    public ModLootTableProvider(PackOutput output, Set<ResourceKey<LootTable>> keySet, List<SubProviderEntry> subProviderEntries, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, keySet, subProviderEntries, registries);
//        this.registries = registries;
    }

//    ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
////        super(output, registries, existingFileHelper);
////        super(output, Set.of(), Collections.singletonList(new SubProviderEntry((Function<HolderLookup.Provider, LootTableSubProvider>) registries, LootContextParamSets.BLOCK)), registries);
//        super(output, Set.of(), List.of(new SubProviderEntry(
//                (HolderLookup.Provider provider) -> new LootTableSubProvider() {
//                    @Override
//                    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
//                        // Register custom loot table generation logic here
//                        System.out.println("Generating Loot Table for: first_join_world");
//                        lootTableConsumer.accept(
//                                ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
//                                (LootTable.Builder b) -> new LootTableProvider(output,Set.of(),this,registries) {
//
////                                    LootTable.lootTable()
////                                    .withPool(
////                                    LootPool.lootPool()
////                                    .add(LootItem.lootTableItem(Items.DIAMOND))
////                                    );
//                                }
//                        );
//                    }
//                },
//                new ContextKeySet.Builder().build()
//        )), registries);
//        this.registries = registries;
//    }

    ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, Set.of(), List.of(new SubProviderEntry(
                provider -> new LootTableSubProvider() {
                    @Override
                    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
                        System.out.println("Generating Loot Table for: first_join_world");
                        System.out.println("PackOutput > "+output);
                        System.out.println("registries > "+registries);
                        System.out.println("ExistingFileHelper > "+existingFileHelper);

                        // Define the LootTable.Builder correctly
                        LootTable.Builder builder = LootTable.lootTable();

                        // Register the loot table
                        lootTableConsumer.accept(
                                ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.parse("blockoffate:advancements/first_join_world")),
                                builder
                        );
                    }
                },
                LootContextParamSets.BLOCK // Use the correct context param set
        )), registries);

        System.out.println("ModLootTableProvider Constructor called.");

//        this.registries = registries;
    }

/** // Good One
    private static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
//        new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ), registries);
    }
 */

    @Override
    public List<SubProviderEntry> getTables() {
        // Return the list of SubProviderEntry that registers your loot tables
        return List.of(
                new SubProviderEntry(
                        (HolderLookup.Provider provider) -> new LootTableSubProvider() {
                            @Override
                            public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableConsumer) {
                                // Register custom loot table generation logic here
                                System.out.println("Generating Get Tables Loot Table for: first_join_world > "+provider);
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
//        if (Items.DIAMOND == null) {
//            System.err.println("Diamond item is null!");
//        } else {
//            System.err.println("Diamond item is NOT null!");
//        }
        // Your custom loot table generation logic here
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.DIAMOND))
                );
    }

//    public static ModLootTableProvider createModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
//        return new ModLootTableProvider(output, registries, existingFileHelper);
//    }
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
