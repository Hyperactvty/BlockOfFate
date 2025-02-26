package com.hyperactvty.blockoffate.registry.forge;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.hyperactvty.blockoffate.registry.BlockItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class BlockItemRegistryImpl {
    public static Map<String, Supplier<Item>> items = new LinkedHashMap();
    public static Map<String, Block> blocks = new HashMap();
    public static Map<String, Tuple<String, CreativeModeTab>> itemNameToGroup = new HashMap();

    public static void registerItemPlatformSpecific(String itemId, Supplier<Item> item, Tuple<String, CreativeModeTab> group) {
        items.put(itemId, item);
        itemNameToGroup.put(itemId, group);
    }

    public static void registerBlockPlatformSpecific(String blockId, Block block, boolean registerItem) {
        if (registerItem) {
            BlockItems.BLOCKS.add(block);
            registerBlockItemPlatformSpecific(blockId, block, new Tuple("building_blocks", (CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.getValue(CreativeModeTabs.BUILDING_BLOCKS)));
        }

        blocks.put(blockId, block);
    }

    public static void registerBlockItemPlatformSpecific(String itemName, Block block, Tuple<String, CreativeModeTab> group) {

        registerItemPlatformSpecific(itemName, () -> {
            return new BlockItem(block, (new Properties()).useBlockDescriptionPrefix().setId( DeferredRegister.create(ForgeRegistries.ITEMS, "blockoffate").key(itemName)));
        }, group);
    }

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}