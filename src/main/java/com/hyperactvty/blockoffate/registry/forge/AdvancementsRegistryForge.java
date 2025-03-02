//package com.hyperactvty.blockoffate.registry.forge;
//
//import com.hyperactvty.blockoffate.registry.AdvancementRegistry;
//import com.hyperactvty.blockoffate.registry.BlockItems;
//import net.minecraft.advancements.Advancement;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.util.Tuple;
//import net.minecraft.world.item.CreativeModeTab;
//import net.minecraft.world.item.CreativeModeTabs;
//import net.minecraft.world.level.block.Block;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
//import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
//import net.minecraftforge.registries.ForgeRegistries.Keys;
//import net.minecraftforge.registries.RegisterEvent;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Consumer;
//
//@EventBusSubscriber(
//        modid = "blockoffate",
//        bus = Bus.MOD
//)
//public class AdvancementsRegistryForge {
//
//    public static Map<String, Advancement> advancements = new HashMap();
//
//    @SubscribeEvent
//    public static void registerAdvancements(RegisterEvent event) {
//        event.register(Keys.STAT_TYPES, (statRegisterHelper) -> {
//            AdvancementRegistry.registerAdvancements();
//        });
////        Advancement example = Advancement.Builder.advancement()
////                .addCriterion("example_criterion", triggerInstance) // How the advancement is unlocked
////                .save(writer, name, fileHelper); // Add data to builder
//    }
//
//    public static void registerAdvancementSpecific(String advancementId, Advancement advancement, boolean registerAdvancement) {
//        if (registerAdvancement) {
////            Advancement.Builder.advancement(advancement);
////            registerAdvancement(advancementId, advancement);
//        }
//
//        advancements.put(advancementId, advancement);
//    }
//
////    @Override
//    public <DataProviderFileHelper> void registerAdvancements(Consumer<Advancement> writer, DataProviderFileHelper fileHelper) {
//        AdvancementRegistry.registerAdvancements(writer, fileHelper);
//    }
//
//    /**
//     *{
//     *   "criteria": {
//     *     "first_join_world": {
//     *       "trigger": "minecraft:tick"
//     *     }
//     *   },
//     *   "rewards": {
//     *     "experience": 100,
//     *     "items": [
//     *       "minecraft:diamond_sword", "blockoffate:generic"
//     *     ]
//     *   }
//     * }
//     * */
//
////    @SubscribeEvent
////    public static void onRegisterAdvancements(Register<Advancement> event) {
////        event.getRegistry().registerAll(
////                // Register your advancements here
////                new Advancement("blockoffate:first_entry")
////        );
////    }
//}