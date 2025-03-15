package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.MainMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ComparableItem implements Comparable<ComparableItem> {
    private final Item item;

    public ComparableItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

//    @Override
//    public int compareTo(ComparableItem other) {
//        // Define a meaningful comparison, e.g., by registry name
////        this.item.STREAM_CODEC
//        this.item.builtInRegistryHolder().value();
//        BuiltInRegistries.ITEM.getId(this.item.components().get())
//        return this.item.getRegistryName().compareTo(other.getItem().getRegistryName());
//    }

    @Override
    public int compareTo(ComparableItem other) {
//        this.item.builtInRegistryHolder().value();
//        BuiltInRegistries.ITEM.getId(this.item.components().get())
        ResourceLocation.fromNamespaceAndPath(MainMod.MODID, this.item.toString());
        return this.item.toString().compareTo(other.getItem().toString());
//        return this.item.getRegistryName().compareTo(other.getItem().getRegistryName());
    }
}
