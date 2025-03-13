package com.hyperactvty.blockoffate.registry;

import java.util.*;
import java.util.function.Supplier;

//import com.hyperactvty.blockoffate.blocks.ExampleStoneBlock;
import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.blocks.BlockOfFate_Block;
import com.hyperactvty.blockoffate.items.KarmaMeter_Item;
import com.hyperactvty.blockoffate.items.LuckyHam_Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainMod.MODID);

//    public static final DeferredRegister<BlockItem> BLOCK_ITEMS =
//            DeferredRegister.create(ForgeRegistries.ITEMS, MainMod.MODID);

    public static final Map<Tuple<String, CreativeModeTab>, Set<Item>> ITEM_GROUP_LIST_MAP = new LinkedHashMap();

    //region BLOCKS
    public static final RegistryObject<Block> BoF_GENERIC_BLOCK = BLOCKS.register/*registerBlock*/("bof_generic",
            () -> new BlockOfFate_Block(BlockBehaviour.Properties.of()
                    .setId(BLOCKS.key("bof_generic"))
                    .mapColor(MapColor.QUARTZ)
                    .destroyTime(0.5f) // .overrideLootTable(Optional.ofNullable(LootTables.FIRST_JOIN_WORLD1))
            )
    );
    public static final RegistryObject<Block> BoF_SLAB_OF_HAM_BLOCK = /*BLOCKS.register*/registerBlock("slab_of_ham",
            () -> new Block(BlockBehaviour.Properties.of()
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
            () -> new LuckyHam_Item(new Item.Properties()
                    .setId(ITEMS.key("lucky_ham"))
                    .stacksTo(16)
//                    .food(new FoodProperties.Builder()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(1)
                            .saturationModifier(2f)
                            .build()
                    )
            )
    );

//    ItemProperties.register(myItem, new ResourceLocation("modid", "karma"), (stack, world, entity, seed) -> {
//        if (entity instanceof Player player) {
//            int karma = player.getCapability(KarmaCapabilityProvider.KARMA).orElse(0);
//            return karma;
//        }
//        return 0;
//    });


    public static final RegistryObject<Item> BoF_KARMA_METER_ITEM = ITEMS.register("karma_meter",
            () -> new KarmaMeter_Item(new Item.Properties()
                    .setId(ITEMS.key("karma_meter"))
                    .stacksTo(1)
//                    .effectiveModel()
            )
    );

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("bof_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> BoF_LUCKY_HAM_ITEM.get().getDefaultInstance())
            .title(Component.nullToEmpty("Blocks of Fate"))
            .withLabelColor(4)
            .displayItems((parameters, output) -> {
                output.accept(BoF_LUCKY_HAM_ITEM.get());
                output.accept(BoF_KARMA_METER_ITEM.get());
                output.accept(BoF_GENERIC_ITEM.get());
                output.accept(BoF_SLAB_OF_HAM_BLOCK.get());
            }).build());

//    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
//            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

//    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
//            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

//    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
//            () -> new FuelItem(new Item.Properties(), 400));
    //endregion ITEMS

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        System.out.println("registerBlock block ["+name + "] >"+block);
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
//    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
//        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()
                .setId(ITEMS.key(name))
        ));
        System.out.println("registering blockItem ["+name + "] >"+ITEMS.key(name));
    }

    public static void register(IEventBus eventBus) {
        System.out.println("register called.");
        System.out.println("Block Count > "+BLOCKS.getEntries().size());
        System.out.println("Block Count > "+BLOCKS.getEntries());
        System.out.println("Item Count > "+ITEMS.getEntries().size());
        System.out.println("Item Count > "+ITEMS.getEntries());
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        CREATIVE_MODE_TABS.register(eventBus);
        for(Object i : ITEMS.getEntries()) {
            System.out.println("register -> ITEMS > "+i.getClass().getName());
        }
    }

//    public static Stream<Block> streamBlocks() {
//        return BLOCKS.stream();
//    }

//    static {
////        EXAMPLE_STONE = new ExampleStoneBlock(Properties.ofFullCopy(Blocks.STONE).sound(SoundType.WOOD).setId( DeferredRegister.create(ForgeRegistries.BLOCKS, "blockoffate").key("example_stone")));
//    }
}
