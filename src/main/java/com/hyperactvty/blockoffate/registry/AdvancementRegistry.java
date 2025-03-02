//package com.hyperactvty.blockoffate.registry;
//
//import net.minecraft.advancements.*;
//import net.minecraft.core.Registry;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.stats.StatFormatter;
//import net.minecraft.stats.Stats;
//import net.minecraft.world.entity.ai.behavior.declarative.Trigger;
//import net.minecraft.world.level.block.state.predicate.BlockPredicate;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import com.mojang.serialization.Codec;
//import net.minecraft.advancements.Advancement;
//import net.minecraft.advancements.Advancement.Builder;
//import net.minecraft.advancements.critereon.ImpossibleTrigger;
//
//
////import net.minecraft.advancements.criterion.BlockPredicate;
////import net.minecraft.advancements.criterion.BlockTrigger;
////import net.minecraft.advancements.criterion.ItemPredicate;
////import net.minecraft.advancements.criterion.Trigger;
//
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.function.Consumer;
//
//public class AdvancementRegistry {
//
//    // Register advancements (use a Consumer to save them)
//    public static void registerAdvancements(Consumer<Advancement> writer) {
//        CriterionTriggerInstance cti = new ImpossibleTrigger.TriggerInstance(); // add to blog on how to make custom advancements for minecraft 1.21.4
//        Criterion<?> criterion = new Criterion((CriterionTrigger) new ImpossibleTrigger(), cti);
//        // Create the impossible trigger criterion
//
//        // Build the advancement
//        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
//                .addCriterion("first_join_world", criterion) // Use the criterion here
//                .display(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", "diamond")), // Icon
//                        Component.translatable("advancement.blockoffate.first_join_world.title"), // Title
//                        Component.translatable("advancement.blockoffate.first_join_world.desc"), // Description
//                        null, // Background
//                        null, // Display format (null for default)
//                        true, true, true); // Toast and chat announcements
//
//        // Save the advancement (this will be registered by the writer)
//        advancementBuilder.save(writer, new ResourceLocation("blockoffate", "first_join_world"));
//    }
//
//    public static void registerAdvancements() {
//        ImpossibleTrigger trigger = new ImpossibleTrigger();
//        CriterionTriggerInstance cti = new ImpossibleTrigger.TriggerInstance(); // add to blog on how to make custom advancements for minecraft 1.21.4
//        Criterion<?> criterion = new Criterion((CriterionTrigger) new ImpossibleTrigger(),  cti);
//
//        // Build the advancement
//        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
//                .addCriterion("first_join_world", criterion) // Always true since we're using IMPOSSIBLE
////                .rewards(Advancement.Rewards.Builder.experience(100).build())
//                .display(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("minecraft:diamond")), // Icon
//                        Component.translatable("advancement.blockoffate.first_join_world.title"), // Title
//                        Component.translatable("advancement.blockoffate.first_join_world.desc"), // Description
//                        null, // Background
//                        null,//Advancement.DisplayFormat.RANDOM, // Display format
//                        true, true, true);
//        advancementBuilder.save(registerAdvancements());
//
////        Registry.register(BuiltInRegistries.STAT_TYPE, /*new ResourceLocation*/("custom_tab"), BOF_STAT_GROUP);
//
//        /*  Advancement(Optional<ResourceLocation> parent, Optional<DisplayInfo> display, AdvancementRewards rewards, Map<String, Criterion<?>> criteria, AdvancementRequirements requirements, boolean sendsTelemetryEvent, Optional<Component> name) */
//
//        // In AdvancementProvider#registerAdvancements(writer, fileHelper)
////        Advancement example = Advancement.Builder.advancement()
////                .addCriterion("example_criterion", triggerInstance) // How the advancement is unlocked
////                .save(writer, name, fileHelper); // Add data to builder
////        Registry.register(new Advancement("mymod:first_entry", "Welcome")); // Advancements.FIRST_JOIN_WORLD
////        Advancement.Builder.advancement();
//    }
//
////    // Register your advancement (e.g., for when a player breaks a diamond ore)
////    public static void registerAdvancements(IEventBus eventBus) {
////
////        // Create the trigger (criterion)
////        ImpossibleTrigger trigger = new ImpossibleTrigger();
//////        BlockPredicate blockPredicate = BlockPredicate.forBlock(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "diamond_ore")));
//////        trigger.addCriterion("break_diamond_ore", blockPredicate);
//////        trigger.trigger(trigger);
////
////        // Build the advancement
////        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
////                .addCriterion("None",trigger)
////                .parent((AdvancementHolder) null) // No parent for this advancement (can be set if you want it to be part of a chain)
////                .rewards(Advancement.Rewards.Builder.experience(100).build()) // Optionally add rewards (like experience)
////                .display(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", "diamond")), // Display icon (here we use a diamond item)
////                        Component.translatable("advancement.mymod.break_diamond_ore"), // Advancement title
////                        Component.translatable("advancement.mymod.break_diamond_ore.desc"), // Description
////                        null, // Background (optional)
////                        Advancement.DisplayFormat.RANDOM, // Display format
////                        true, true, true); // Other display properties (icon, show in sidebar, etc.)
////
////        // Save the advancement
////        advancementBuilder.save(writer, new ResourceLocation("mymod", "break_diamond_ore"), fileHelper);
////    }
//
////    public static void registerAdvancements(IEventBus eventBus) {
////        // Create the trigger (which is impossible, always triggers)
//////        Trigger<?> trigger = Trigger.IMPOSSIBLE;
////        ImpossibleTrigger trigger = new ImpossibleTrigger();
//////        Criterion<?> criterion = new Criterion("first_join_world", trigger);
////        Criterion<?> criterion = new Criterion((CriterionTrigger) new ImpossibleTrigger(), (CriterionTriggerInstance) trigger);
////
////        // Build the advancement
////        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
////                .addCriterion("first_join_world", criterion) // Always true since we're using IMPOSSIBLE
////                .rewards(Advancement.Rewards.Builder.experience(100).build())
////                .display(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("minecraft:diamond")), // Icon
////                        Component.translatable("advancement.mymod.first_join_world.title"), // Title
////                        Component.translatable("advancement.mymod.first_join_world.desc"), // Description
////                        null, // Background
////                        null,//Advancement.DisplayFormat.RANDOM, // Display format
////                        true, true, true);
////
////        // Save the advancement
////        advancementBuilder.save(writer, new ResourceLocation("blockoffate", "first_join_world"), fileHelper);
////        advancementBuilder.save(writer, Advancements.FIRST_JOIN_WORLD, fileHelper);
////    }
//
//    public static <DataProviderFileHelper> void registerAdvancements(Consumer<Advancement> writer, DataProviderFileHelper fileHelper) {
//        System.err.println("registerAdvancements > WRITER : " + writer+" | FILE_HELPER : "+fileHelper);
//
////        ImpossibleTrigger trigger = new ImpossibleTrigger();
////        Criterion<?> criterion = new Criterion((CriterionTrigger) new ImpossibleTrigger(), (CriterionTriggerInstance) trigger);
////
////        // Build the advancement
////        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
////                .addCriterion("first_join_world", criterion) // Always true since we're using IMPOSSIBLE
////                .display(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", "diamond")), // Icon
////                        Component.translatable("advancement.mymod.first_join_world.title"), // Title
////                        Component.translatable("advancement.mymod.first_join_world.desc"), // Description
////                        null, // Background
//////                        Advancement.DisplayFormat.RANDOM, // Display format
////                        true, true, true);
////
////        // Save the advancement
////        advancementBuilder.save(writer, new ResourceLocation("blockoffate", "first_join_world"), fileHelper);
////        advancementBuilder.save(writer, Advancements.FIRST_JOIN_WORLD, fileHelper);
//
//    }
//}
