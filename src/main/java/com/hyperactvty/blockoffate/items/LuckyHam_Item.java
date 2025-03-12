package com.hyperactvty.blockoffate.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class LuckyHam_Item extends Item {
    public LuckyHam_Item(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 20*600, 2)); // 30 seconds of Luck I
        }

        return super.finishUsingItem(stack, level, entity);
    }


    public boolean mineBlock(ItemStack p_41416_, Level p_41417_, BlockState p_41418_, BlockPos p_41419_, LivingEntity p_41420_) {
        System.err.println("mineBlock ItemStack > "+p_41416_);
        System.err.println("mineBlock Level > "+p_41417_);
        System.err.println("mineBlock BlockState > "+p_41418_);
        System.err.println("mineBlock BlockPos > "+p_41419_);
//        Tool tool = p_41416_.get(DataComponents.TOOL);
//        if (tool == null) {
//            return false;
//        } else {
//            if (!p_41417_.isClientSide && p_41418_.getDestroySpeed(p_41417_, p_41419_) != 0.0F && tool.damagePerBlock() > 0) {
//                p_41416_.hurtAndBreak(tool.damagePerBlock(), p_41420_, EquipmentSlot.MAINHAND);
//            }
//
//            return true;
//        }
        return true;
    }
}
