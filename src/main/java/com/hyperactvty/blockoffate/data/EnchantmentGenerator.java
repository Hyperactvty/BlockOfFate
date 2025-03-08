//package com.hyperactvty.blockoffate.data;
//
//import com.hyperactvty.blockoffate.MainMod;
//import com.hyperactvty.blockoffate.enchantments.ModEnchantments;
//import com.hyperactvty.blockoffate.MyMod;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.*;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.ItemEnchantments;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.eventbus.api.ModEventBus;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.eventbus.api.EventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.concurrent.CompletableFuture;
//
//public class EnchantmentGenerator extends DataGenerator {
//
//    // Create Deferred Register for Enchantments
//    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MainMod.MODID);
//
//    public EnchantmentGenerator(DataGenerator generator) {
//        super(generator);
//    }
//
//    @Override
//    protected void run() {
//        // Register our custom enchantment
//        registerEnchantment();
//    }
//
//    private void registerEnchantment() {
//        // Define the new custom enchantment, "Thundering."
//        RegistryObject<Enchantment> fateEnchantment = ENCHANTMENTS.register("fate", () ->
//                new Enchantment(Rarity.RARE, ItemEnchantments.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}) {
//                    @Override
//                    public int getMaxLevel() {
//                        return 8;
//                    }
//
//                    @Override
//                    public int getMinCost(int level) {
//                        return 10 + (level - 1) * 10;
//                    }
//
//                    @Override
//                    public int getMaxCost(int level) {
//                        return getMinCost(level) + 15;
//                    }
//
////                    @Override
////                    public boolean isTreasureOnly() {
////                        return false;
////                    }
////
////                    @Override
////                    public boolean isTradeable() {
////                        return true;
////                    }
////
////                    @Override
////                    public boolean isDiscoverable() {
////                        return true;
////                    }
//                }
//        );
//
//        // Add any effects or other properties to the enchantment here if needed
//        // You can add a lightning effect or custom behavior in the enchantment as needed.
//    }
//
//    // Register all the objects to the EventBus so they get registered
//    public static void register(IEventBus eventBus) {
//        // Register the Deferred Register to the mod event bus so the enchantments are registered
//        ENCHANTMENTS.register(eventBus);
//    }
//}
