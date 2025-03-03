package com.hyperactvty.blockoffate.data.loot;
//import com.hyperactvty.blockoffate.block.ModBlocks;
//import com.hyperactvty.blockoffate.item.ModItems;

import com.hyperactvty.blockoffate.registry.BlockItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    public ModBlockLootTables(Set<Item> itemSet, FeatureFlagSet flagSet, HolderLookup.Provider provider) {
        super(itemSet, flagSet, provider);
    }

    @Override
    protected void generate() {
        this.dropSelf(BlockItems.BoF_GENERIC_BLOCK.get());
//        this.dropSelf(BlockItems.BoF_SLAB_OF_HAM_BLOCK.get());

        this.add(BlockItems.BoF_SLAB_OF_HAM_BLOCK.get(),
                block -> createCopperLikeOreDrops(BlockItems.BoF_SLAB_OF_HAM_BLOCK.get(), BlockItems.BoF_LUCKY_HAM_ITEM.get()));
//        this.add(BlockItems.DEEPSLATE_SAPPHIRE_ORE.get(),
//                block -> createCopperLikeOreDrops(BlockItems.DEEPSLATE_SAPPHIRE_ORE.get(), BlockItems.RAW_SAPPHIRE.get()));
//        this.add(BlockItems.NETHER_SAPPHIRE_ORE.get(),
//                block -> createCopperLikeOreDrops(BlockItems.NETHER_SAPPHIRE_ORE.get(), BlockItems.RAW_SAPPHIRE.get()));
//        this.add(BlockItems.END_STONE_SAPPHIRE_ORE.get(),
//                block -> createCopperLikeOreDrops(BlockItems.END_STONE_SAPPHIRE_ORE.get(), BlockItems.RAW_SAPPHIRE.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount((Holder<Enchantment>) Enchantments.FORTUNE))));
//                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockItems.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}