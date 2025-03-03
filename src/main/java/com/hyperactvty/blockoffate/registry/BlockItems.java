package com.hyperactvty.blockoffate.registry;

import java.util.*;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

//import com.hyperactvty.blockoffate.blocks.ExampleStoneBlock;
import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.blocks.BlockOfFate_Block;
import net.minecraft.util.Tuple;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockItems {
//    public static final List<Block> BLOCKS = new ArrayList();
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MainMod.MODID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MainMod.MODID);

    public static final Map<Tuple<String, CreativeModeTab>, Set<Item>> ITEM_GROUP_LIST_MAP = new LinkedHashMap();

    //region BLOCKS
    public static final RegistryObject<Block> BoF_GENERIC_BLOCK = BLOCKS.register("bof_generic",
            () -> new BlockOfFate_Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("bof_generic"))
                    .mapColor(MapColor.QUARTZ)
                    .destroyTime(0.5f) // .overrideLootTable(Optional.ofNullable(LootTables.FIRST_JOIN_WORLD1))
            )
    );
    public static final RegistryObject<Block> BoF_SLAB_OF_HAM_BLOCK = BLOCKS.register("slab_of_ham",
            () -> new BlockOfFate_Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("slab_of_ham"))
                    .mapColor(MapColor.COLOR_PINK)
                    .destroyTime(0.5f)
            )
    );
    //endregion

    //region ITEMS
//    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
//            () -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
//            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BoF_GENERIC_ITEM = ITEMS.register("bof_generic",
            () -> new BlockItem(BoF_GENERIC_BLOCK.get(), new Item.Properties()
                    .setId(ITEMS.key("bof_generic")) //block_of_fate
                    .rarity(Rarity.RARE)
                    .stacksTo(64))
    );

    public static final RegistryObject<Item> BoF_LUCKY_HAM_ITEM = ITEMS.register("lucky_ham",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("lucky_ham"))
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(1)
                            .saturationModifier(2f)
                            .build()
                    )
            )
    );

//    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
//            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

//    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
//            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

//    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
//            () -> new FuelItem(new Item.Properties(), 400));
    //endregion ITEMS

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

//    public static Stream<Block> streamBlocks() {
//        return BLOCKS.stream();
//    }

//    static {
////        EXAMPLE_STONE = new ExampleStoneBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.WOOD).setId( DeferredRegister.create(ForgeRegistries.BLOCKS, "blockoffate").key("example_stone")));
//    }
}
