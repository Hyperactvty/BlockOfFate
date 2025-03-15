package com.hyperactvty.blockoffate.utilities;

import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.List;
import java.util.Optional;

public class ModItemProperties {
    public static final Property<Direction> FACING = BlockStateProperties.FACING;
//    public static final Property<Integer> SELECT = ;
    public static final SelectItemModelProperties SELECT = new SelectItemModelProperties();


    public static void addCustomItemProperties() {
//        PropertyDispatch propertyDispatch = PropertyDispatch.property(FACING)
//                .select(Direction.NORTH, BlockFamily.Variant.valueOf("north_model"))
//                .select(Direction.SOUTH, Variant.of("south_model"))
//                .select(Direction.EAST, Variant.of("east_model"))
//                .select(Direction.WEST, Variant.of("west_model"));
//
//        PropertyDispatch kDispatch = PropertyDispatch.property(SELECT)
//                .select(Direction.NORTH, BlockFamily.Variant.valueOf("north_model"))
//                .select(Direction.SOUTH, Variant.of("south_model"))
//                .select(Direction.EAST, Variant.of("east_model"))
//                .select(Direction.WEST, Variant.of("west_model"));
//
////        propertyDispatch.generate(facing -> Variant.of(facing.getName() + "_model"));


        Property<String> karmaProperty = new Property<>("karma", String.class) {
            @Override
            public List<String> getPossibleValues() {
                return List.of();
            }

            @Override
            public String getName(String p_61696_) {
                return p_61696_;
            }

            @Override
            public Optional<String> getValue(String p_61701_) {
                return p_61701_.describeConstable(); // Optional.empty();
            }

            @Override
            public int getInternalIndex(String p_366384_) {
                return 0;
            }
        };

// Example logic to use item registry names for the property
//        PropertyDispatch.properties(
//                karmaProperty,
//                ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma"),
//                (itemStack, clientLevel, livingEntity, seed) -> {
////                    String registryName = itemStack.getItem().getRegistryName().toString();
//                    System.out.println("Debug Info > " + itemStack + " | " + clientLevel + " | " + livingEntity + " | " + seed);
//                    return 1f; // Or some computed value
//                }
//        );


        /*Item$Properties*/
//        PropertyDispatch.properties(
////                BlockItems.BoF_KARMA_METER_ITEM.get(),
//                karmaProperty,
//                ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma"),
//                propertyFunction
//        );
//        PropertyDispatch.register(ModItems.CHISEL.get(), ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "used"),
//                (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponentTypes.COORDINATES.get()) != null ? 1f : 0f);
//
//        makeCustomBow(ModItems.KAUPEN_BOW.get());
    }

//    private static void makeCustomBow(Item item) {
//        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), (p_340951_, p_340952_, p_340953_, p_340954_) -> {
//            if (p_340953_ == null) {
//                return 0.0F;
//            } else {
//                return p_340953_.getUseItem() != p_340951_ ? 0.0F : (float)(p_340951_.getUseDuration(p_340953_) - p_340953_.getUseItemRemainingTicks()) / 20.0F;
//            }
//        });
//        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
//            return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
//        });
//    }
}