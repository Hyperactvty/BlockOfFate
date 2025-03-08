package com.hyperactvty.blockoffate.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;


public class FateEnchantment implements EnchantmentProvider {
    public FateEnchantment() {
    }

    @Override
    public void enchant(ItemStack p_345206_, ItemEnchantments.Mutable p_342143_, RandomSource p_342566_, DifficultyInstance p_344663_) {

    }

    @Override
    public MapCodec<? extends EnchantmentProvider> codec() {
        return null;
    }
//
//    @Override
//    public EnchantmentEffectComponents getEffectComponent() {
//        return new EnchantmentEffectComponents() {
//            // Implement specific effects here
//        };
//    }
//
//    @Override
//    public Formula getFormula() {
//        return Formula.NORMAL; // You can define a custom formula for your enchantment here
//    }
//    @Override
//    public void enchant(ItemStack p_345206_, ItemEnchantments.Mutable p_342143_, RandomSource p_342566_, DifficultyInstance p_344663_) {
//
//    }
//
//    @Override
//    public MapCodec<? extends EnchantmentProvider> codec() {
//        return null;
//    }
//    public FateEnchantment() {
//        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
//    }

//
//    @Override
//    public int getMaxLevel() {
//        return 8; // Maximum level of the enchantment
//    }
//
//    @Override
//    public int getMinCost(int level) {
//        return 10 + (level - 1) * 10; // Cost scaling per level
//    }
//
//    @Override
//    public int getMaxCost(int level) {
//        return super.getMinCost(level) + 20;
//    }
//
//    @Override
//    public boolean isTreasureOnly() {
//        return false; // Set true if it should only be found in loot
//    }
//
//    @Override
//    public boolean isTradeable() {
//        return true; // Can villagers trade this enchantment?
//    }
//
//    @Override
//    public boolean isDiscoverable() {
//        return true; // Can be found in loot or enchanting tables?
//    }
}