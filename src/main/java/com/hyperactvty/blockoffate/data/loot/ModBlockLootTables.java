package com.hyperactvty.blockoffate.data.loot;
//import com.hyperactvty.blockoffate.block.ModBlocks;
//import com.hyperactvty.blockoffate.item.ModItems;

import com.hyperactvty.blockoffate.registry.BlockItems;
import com.mojang.datafixers.util.Either;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ModBlockLootTables extends BlockLootSubProvider {
//    public ModBlockLootTables() {
//        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
//    }

//    public ModBlockLootTables(Set<Item> itemSet, FeatureFlagSet flagSet, HolderLookup.Provider provider) {
//        super(itemSet, flagSet, provider);
//    }

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        System.out.println("ModBlockLootTables called.");
    }

//    public ModBlockLootTables(HolderLookup.Provider provider) {
//        super(provider);
//    }

    @Override
    protected void generate() {
        System.out.println("ModBlockLootTables `generate` called.");
//        this.dropSelf(BlockItems.BoF_SLAB_OF_HAM_BLOCK.get());

        this.add(BlockItems.BoF_SLAB_OF_HAM_BLOCK.get(),
                block -> createCopperLikeOreDrops(BlockItems.BoF_SLAB_OF_HAM_BLOCK.get(), BlockItems.BoF_LUCKY_HAM_ITEM.get()));
//        this.add(BlockItems.DEEPSLATE_SAPPHIRE_ORE.get(),
//                block -> createCopperLikeOreDrops(BlockItems.DEEPSLATE_SAPPHIRE_ORE.get(), BlockItems.RAW_SAPPHIRE.get()));
//        this.add(BlockItems.NETHER_SAPPHIRE_ORE.get(),
//                block -> createCopperLikeOreDrops(BlockItems.NETHER_SAPPHIRE_ORE.get(), BlockItems.RAW_SAPPHIRE.get()));
//        this.add(BlockItems.END_STONE_SAPPHIRE_ORE.get(),
//                block -> createCopperLikeOreDrops(BlockItems.END_STONE_SAPPHIRE_ORE.get(), BlockItems.RAW_SAPPHIRE.get()));
        this.dropSelf(BlockItems.BoF_GENERIC_BLOCK.get());

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        System.out.println("Attempt [createCopperLikeOreDrops] > ");
//        for (Object i : BuiltInRegistries.ENCHANTMENT_LEVEL_BASED_VALUE_TYPE.asHolderIdMap()) {
//            System.out.println("Attempt ENCHANTMENT_LEVEL_BASED_VALUE_TYPE > "+i);
//        }
//
//        for (Object i : BuiltInRegistries.ENCHANTMENT_PROVIDER_TYPE.asHolderIdMap()) {
//            System.out.println("Attempt ENCHANTMENT_PROVIDER_TYPE > "+i);
//        }
//

//        BuiltInRegistries.LOOT_CONDITION_TYPE.getTags("random_chance_with_enchanted_bonus");

//        RegistryAccess registryAccess = Minecraft.getInstance().level.registryAccess();
//        enchantment = Enchantments.FORTUNE;
//        System.out.println("Attempt ApplyBonusCount > "+ApplyBonusCount.addOreBonusCount(enchantment));


//        for (Object i : registryAccess.lookup(Registries.ENCHANTMENT).stream().toList()) {
//            System.out.println("Attempt ENCHANTMENT > "+i);
//        }
//        Holder<Enchantment> fortuneHolder = registryAccess.lookup(Registries.ENCHANTMENT).get();
//                .get(Enchantments.FORTUNE);

//        return createSilkTouchDispatchTable(pBlock,
//                this.applyExplosionDecay(pBlock,
//                        LootItem.lootTableItem(item)
//                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
////                                .apply(ApplyBonusCount.addOreBonusCount(
////                                        BuiltInRegistries.ENCHANTMENT_PROVIDER_TYPE.get(Enchantments.FORTUNE)
////                                        BuiltInRegistries.ENCHANTMENT_LEVEL_BASED_VALUE_TYPE.asHolderIdMap().byIdOrThrow()
//////                                        BuiltInRegistries.ENCHANTMENT.getHolderOrThrow(Enchantments.FORTUNE)
////                                ))));
////                                .apply(ApplyBonusCount.addOreBonusCount(
////                                        BuiltInRegistries.LOOT_CONDITION_TYPE..getHolderOrThrow(Enchantments.FORTUNE)
////                                ))
////                                .apply(ApplyBonusCount.getType)
//                                // itemContext
//
//                                /**.apply(ApplyBonusCount.addOreBonusCount((Holder<Enchantment>) Enchantments.FORTUNE))));*/
////                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
//                )
//        );
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockItems.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}