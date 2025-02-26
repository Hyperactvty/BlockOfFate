package com.hyperactvty.blockoffate.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

//import com.hyperactvty.blockoffate.blocks.ExampleStoneBlock;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockItems {
    public static final List<Block> BLOCKS = new ArrayList();
    public static final Map<Tuple<String, CreativeModeTab>, Set<Item>> ITEM_GROUP_LIST_MAP = new LinkedHashMap();
//    public static final Block EXAMPLE_STONE;

    public static Stream<Block> streamBlocks() {
        return BLOCKS.stream();
    }

    static {
//        EXAMPLE_STONE = new ExampleStoneBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.WOOD).setId( DeferredRegister.create(ForgeRegistries.BLOCKS, "blockoffate").key("example_stone")));
    }
}
