//package com.hyperactvty.blockoffate;
//
//import net.minecraft.advancements.Advancement;
//import net.minecraft.advancements.Advancement.Builder;
//import net.minecraft.advancements.Criterion;
//import net.minecraft.advancements.CriterionTrigger;
//import net.minecraft.advancements.CriterionTriggerInstance;
//import net.minecraft.advancements.critereon.ImpossibleTrigger;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.Items;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.function.Consumer;
//
//public class ModAdvancements {
//
//    // Register advancements (use a Consumer to save them)
//    public static void registerAdvancements(Consumer<Advancement> writer) {
//        CriterionTriggerInstance cti = new ImpossibleTrigger.TriggerInstance(); // add to blog on how to make custom advancements for minecraft 1.21.4
//        Criterion<?> criterion = new Criterion((CriterionTrigger) new ImpossibleTrigger(), cti);
//        // Create the impossible trigger criterion
////        Criterion<?> firstJoinCriterion = Criterion.trigger("first_join_world", new ImpossibleTrigger.TriggerInstance());
//
//
//        // Build the advancement
//        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
//                .addCriterion("first_join_world", criterion) // Use the criterion here
//                .display(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("minecraft:diamond")), // Icon
//                        Component.translatable("advancement.blockoffate.first_join_world.title"), // Title
//                        Component.translatable("advancement.blockoffate.first_join_world.desc"), // Description
//                        null, // Background
//                        null, // Display format (null for default)
//                        true, true, true); // Toast and chat announcements
//
//        // Save the advancement (this will be registered by the writer)
//        advancementBuilder.save(writer, ResourceLocation.fromNamespaceAndPath("blockoffate","first_join_world"));
//
//        advancementBuilder.save(writer, "blockoffate:first_join_world");
//    }
//}