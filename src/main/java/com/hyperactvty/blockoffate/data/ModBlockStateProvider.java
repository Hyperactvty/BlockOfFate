package com.hyperactvty.blockoffate.data;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.registry.BlockItems;
import com.hyperactvty.blockoffate.blocks.BlockOfFate_Block;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.util.Tuple;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends ModelProvider {
    public ModBlockStateProvider(PackOutput output) {
        super(output);
    }
//    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
//        super(output, MainMod.MODID, exFileHelper);
//    }
//
//    @Override
//    protected void registerStatesAndModels() {
//        blockWithItem(BlockItems.BoF_GENERIC_BLOCK);
//        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK);
//
//        blockWithItem(ModBlocks.SAPPHIRE_ORE);
//        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
//        blockWithItem(ModBlocks.END_STONE_SAPPHIRE_ORE);
//        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE);
//
//        blockWithItem(ModBlocks.SOUND_BLOCK);
//    }
//
//    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
//        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
//    }
}