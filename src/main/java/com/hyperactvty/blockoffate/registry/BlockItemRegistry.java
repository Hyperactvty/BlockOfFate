package com.hyperactvty.blockoffate.registry;

import java.util.function.Supplier;

import com.hyperactvty.blockoffate.registry.forge.BlockItemRegistryImpl;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;

public class BlockItemRegistry {
//    public static void registerCommonBlocks() {
////        registerBlock("example_stone", BlockItems.EXAMPLE_STONE, true);
//    }
//
//    public static void registerBlock(String blockName, Block block, BlockItem item, Tuple<String, CreativeModeTab> group) {
//        BlockItems.BLOCKS.add(block);
//        registerBlockPlatformSpecific(blockName, block, false);
//        registerItemPlatformSpecific(blockName, () -> {
//            return item;
//        }, group);
//    }
//
//    public static void registerBlock(String blockName, Block block, boolean registerItem) {
//        if (registerItem) {
//            BlockItems.BLOCKS.add(block);
//            // bof_tab
//            registerBlockItemPlatformSpecific(blockName, block, new Tuple("building_blocks", (CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.BUILDING_BLOCKS)));
//        }
//
//        registerBlockPlatformSpecific(blockName, block, false);
//    }
//
//    public static void registerBlockItemPlatformSpecific(String itemName, Block block, Tuple<String, CreativeModeTab> group) {
//        BlockItemRegistryImpl.registerBlockItemPlatformSpecific(itemName, block, group);
//    }
//
//    public static void registerBlockPlatformSpecific(String blockName, Block block, boolean registerItem) {
//        BlockItemRegistryImpl.registerBlockPlatformSpecific(blockName, block, registerItem);
//    }
//
//    public static void registerItemPlatformSpecific(String itemName, Supplier<Item> itemSupplier, Tuple<String, CreativeModeTab> group) {
//        BlockItemRegistryImpl.registerItemPlatformSpecific(itemName, itemSupplier, group);
//    }
}