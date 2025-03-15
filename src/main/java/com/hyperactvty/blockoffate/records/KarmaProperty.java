package com.hyperactvty.blockoffate.records;
//package net.minecraft.client.renderer.item.properties.select;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.component.DataComponents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public record KarmaProperty() implements SelectItemModelProperty<Integer> {
    public static final SelectItemModelProperty.Type<KarmaProperty, Integer> TYPE = SelectItemModelProperty.Type.create(
            // MapCodec for your property
            MapCodec.unit(new KarmaProperty()),
            // Codec for Integer values
            Codec.INT
    );

    @Nullable
    @Override
    public Integer get(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int seed, ItemDisplayContext context) {
        // Retrieve "karma" value from item data
//        return itemStack.get(DataComponents.INT_PROPERTIES).get("karma");
//        return itemStack.get(DataComponents.CUSTOM_DATA.codec().fieldOf("karma"));//.parseEntityId().hashCode();
        return 1;
    }

    @Override
    public SelectItemModelProperty.Type<KarmaProperty, Integer> type() {
        return TYPE;
    }
}
