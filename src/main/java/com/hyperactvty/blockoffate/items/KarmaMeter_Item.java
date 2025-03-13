package com.hyperactvty.blockoffate.items;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.interfaces.CustomModelDataProvider;
import com.hyperactvty.blockoffate.utilities.ModItemModelResolver;
import com.hyperactvty.blockoffate.utilities.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

// RegisterClientTooltipComponentFactoriesEvent

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class KarmaMeter_Item extends Item {
    public KarmaMeter_Item(Properties properties) {
        super(properties);
    }

    public final static int KARMA_LEVEL_ITERATIONS = 32;
//    public static final DataComponentType<Integer> KARMA_COMPONENT = DataComponents.register("karma", /*Integer*/double.class);
//    public static final DataComponentType<Integer> KARMA_COMPONENT = DataComponents.register("karma", /*Integer*/double.class);

    private Level level;
    private Player player;
    private float prevKarmaValue = 0f;
    private ItemStack stack = null;
    int karmaLevelThreshold = 750;
    int karmaLevel=0;

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext tooltip, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, tooltip, components, flag);
//        this.getBlock().appendHoverText(p_40572_, p_327780_, p_40574_, p_40575_);

        if (level != null && level.isClientSide) {
            double karma = Utils.getStat(MainMod.mcs.getPlayerList().getPlayerByName(player.getDisplayName().getString()), "karma", double.class);
//            karmaLevel = (int) (Math.floor(karma / karmaLevelThreshold) + (KARMA_LEVEL_ITERATIONS/2));
            karmaLevel = getPlayerKarma(player);
            float kf = (float) karmaLevel / KARMA_LEVEL_ITERATIONS;
//            System.err.println("prevKarmaValue != kf > "+prevKarmaValue+" != "+kf);

            if (prevKarmaValue != kf) {
                prevKarmaValue = kf;
            }

            int startColor = 0xFF0000; // Red
            int midColor = 0xFFFFFF; // White
            int endColor = 0x0000FF; // Blue
            String text = "01234567890";
//            List<String> ctrlExplainText = new ArrayList<>() {
//                "0 - Very not good."
//            };
//            level.getServer().getPlayerList().getPlayerByName(player.getDisplayName().getString());

//            int karmaColor = Utils.interpolateColor(startColor, midColor, (float) karmaLevel / KARMA_LEVEL_ITERATIONS);
//            int interpolatedColor = Utils.interpolateThreeColors(startColor, midColor, endColor, kf);
            AtomicInteger col = new AtomicInteger(0xFFFFFF); // White

            /** GET CAPABILITIES*/
            stack.getCapability(CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY).ifPresent(cap -> {
                CustomModelData data = cap.getCustomModelData();
                col.set(data.getColor(0));
            });

//            System.out.printf("Interpolated Color (ratio=%.2f): #%06X%n", kf, interpolatedColor);
//            double karma = Utils.getStat(level.getServer().getPlayerList().getPlayerByName(player.getDisplayName().getString()), "karma", double.class);
            Component karmaLevelText = Component.literal("Karma Level: " + karmaLevel+"/"+KARMA_LEVEL_ITERATIONS)
                    .withStyle(ChatFormatting.BOLD)
                    .withColor(col.get());
            Component karmaText = Component.literal("Karma: " + karma)
                    .withStyle(ChatFormatting.GOLD);

            components.add(karmaLevelText);
            components.add(karmaText);

            /* TESTING TO SEE IF CAPABILITIES WORK */
            stack.getCapability(CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY).ifPresent(cap -> {
                CustomModelData data = cap.getCustomModelData();
                System.err.println("CustomModelData > "+data);
                components.add(Component.literal("Model Data: ").append(String.valueOf(data.getFloat(0)))); // Example tooltip text
            });


            List<Component> componentsList = new ArrayList<>();
            // Create a gradient between startColor and endColor
            for (int i = 0; i < text.length(); i++) {
                int color = Utils.interpolateThreeColors(startColor, midColor, endColor, (float) i / (text.length() - 1));
//                int color = Utils.interpolateColor(karmaColor, karmaColor1, (float) i / (text.length() - 1));
                componentsList.add(Component.literal(String.valueOf(text.charAt(i)))
                        .setStyle(Style.EMPTY.withColor(color)));
            }

            components.addAll(componentsList);
//            for(Component c : componentsList) {
//                components.addAll(componentsList).append(c));
//            }

            ///  Below is for when we want to create custom hover things.
//            tooltip.add(Component.literal("Click me!").withStyle(ChatFormatting.BLUE)
//                    .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://example.com")))
//                    .withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Opens a website!"))))
//            );



        }
    }

    /*

    // The CustomModelData capability integrates well with persistence. Use it during NBT serialization for saving/loading custom data.
    // Save Data:
    // Automatically serialize CustomModelData when the ItemStack is saved to NBT:
    @Override
    public CompoundTag save(CompoundTag tag) {
        stack.getCapability(CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY).ifPresent(cap -> {
            CompoundTag customTag = new CompoundTag();
            cap.writeNBT(customTag);
            tag.put("CustomModelData", customTag);
        });
        return super.save(tag);
    }

    // Load Data:
    // Deserialize CustomModelData when loading the ItemStack:
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        stack.getCapability(CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY).ifPresent(cap -> {
            if (tag.contains("CustomModelData")) {
                cap.readNBT(tag.getCompound("CustomModelData"));
            }
        });
    }


     */


//    @Override
//    public void addPropertyOverrides(ItemOverrides.Builder builder) {
//        builder.add(new ResourceLocation("karma"), (stack, world, entity, seed) -> {
//            // Return the "karma" value, which should be stored in the item's NBT data.
//            return stack.getOrCreateTag().getInt("karma");
//        });
//    }

    //    @Override
    public void render(
            ItemStack stack,
            ItemDisplayContext context,
            Level world,
            LivingEntity entity
    ) {
        ModItemModelResolver customResolver = new ModItemModelResolver(Minecraft.getInstance().getModelManager());
        ItemStackRenderState renderState = new ItemStackRenderState(); // Create a new render state object
        renderState.ensureCapacity(32);
        renderState.newLayer();
        customResolver.appendItemLayers(
                renderState, stack, context, world, entity, entity.getId()
        );

        // Apply the resolved render state to the item
        System.out.println("Called ModItemModelResolver.appendItemLayers for dynamic rendering.");
    }


//    @Override
//    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//        if (level != null && level.isClientSide) {
//            if (stack.getTag() != null && stack.getTag().contains("Karma")) {
//                int karma = stack.getTag().getInt("Karma");
//                tooltip.add(new TextComponent("Karma: " + karma));
//            } else {
//                tooltip.add(new TextComponent("Karma: 0"));
//            }
//        }
//    }

    // Setting CustomModelData
    public static void setCustomModelData(ItemStack stack, List<Float> floats, List<Boolean> flags, List<String> strings, List<Integer> colors) {
        CustomModelData customModelData = new CustomModelData(floats, flags, strings, colors);
        // Use the appropriate way to associate this with the stack (e.g., through capabilities or serialization)
//        stack.getCapability(CustomModelDataCapability.INSTANCE).ifPresent(cap -> {
//            cap.setCustomModelData(customModelData);
//        });

        stack.getCapability(CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY).ifPresent(cap -> {
            cap.setCustomModelData(new CustomModelData(List.of(1.0F), List.of(), List.of(), List.of(0xFFFFFF)));
        });

    }

    @Override
//    public void inventoryTick(ItemStack stack, Level level, LivingEntity entity, int slot, boolean selected) {
    public void inventoryTick(ItemStack _stack, Level _level, Entity _entity, int slot, boolean selected) {
        if (!_level.isClientSide) {
            return; // Only update on the client
        }

        if (_level != level) {
            level = _level;
        }

        if (_stack != stack) {
            stack = _stack;
        }

        if (_entity instanceof Player _player) {
            if (_player != player) {
                player = _player;
            }
            ResourceLocation gcm = getCustomModel(/*stack, _level, */_player);
//            DataComponents dc = new DataComponents();
//            new CustomModelData()

            this.render(stack, ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, _level, (LivingEntity) _entity);

            /** Use the capability in inventoryTick to update the item’s CustomModelData based on in-game conditions,
             *  like player actions or environmental factors.*/
            if (!level.isClientSide && _entity instanceof Player player) {
                stack.getCapability(CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY).ifPresent(cap -> {
                    // Update the model data dynamically based on some condition
                    int karma = getPlayerKarma(player);
                    cap.setCustomModelData(new CustomModelData(List.of((float) (karma / KARMA_LEVEL_ITERATIONS)), List.of(), List.of(), List.of()));
                });
            }

            /** GET CAPABILITIES*/
            stack.getCapability(CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY).ifPresent(cap -> {
                CustomModelData data = cap.getCustomModelData();
                System.out.println("CustomModelData: " + data);
            });
//            System.err.println("getPlayerKarma > "+getPlayerKarma(MainMod.mcs.getPlayerList().getPlayerByName(player.getDisplayName().getString()))+" | "+gcm.toString());
        }

//        System.err.println("getNamespace() > "+new Item.Properties().effectiveModel().getNamespace());

        // The game already provides a "time" property for clocks, so we'll use it
//        Item.Properties.get(stack, new ResourceLocation("time"));
//        if (_level != null) {
//            long worldTime = _level.getDayTime(); // Total ticks since the world started
//            float timeNormalized = (float) (worldTime % 24000L) / 24000.0F; // Normalize to 0.0 - 1.0
//
//            // Use timeNormalized for your custom logic
//            System.out.println("Normalized Time: " + timeNormalized);
//        }
    }

//    @Override
//    public ResourceLocation getModelResource(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity) {
//        if (entity instanceof PlayerEntity) {
//            int karma = PlayerKarmaProvider.getKarma((PlayerEntity) entity); // Custom method to retrieve karma
//            if (karma <= 0) {
//                return new ResourceLocation("modid", "item/karma_meter_00");
//            } else if (karma <= 10) {
//                return new ResourceLocation("modid", "item/karma_meter_01");
//            } else {
//                return new ResourceLocation("modid", "item/karma_meter_02");
//            }
//        }
//        return super.getModelResource(stack, world, entity);
//    }

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public ItemOverrides getOverrides() {
//        return new ItemOverrides() {
//            @Override
//            public BakedModel resolve(BakedModel originalModel, ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
//                if (entity instanceof Player player) {
//                    int karma = getPlayerKarma(player);
//
//                    // Return different models based on karma
//                    if (karma <= 0) {
//                        return Minecraft.getInstance().getModelManager()//.getModelManager()
//                                .getModel(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_00"), "inventory"));
//                    } else if (karma <= 10) {
//                        return Minecraft.getInstance().getModelManager()
//                                .getModel(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_01"), "inventory"));
//                    } else if (karma <= 20) {
//                        return Minecraft.getInstance().getModelManager()
//                                .getModel(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_02"), "inventory"));
//                    }
//                }
//
//                // Default model
//                return originalModel;
//            }
//        };
//    }



    // TODO: CALL THIS RESOURCELOCATION METHOD TO CHANGE THE SPRITE!
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getCustomModel(/*ItemStack stack, @Nullable Level level, */@Nullable LivingEntity entity) {
        if (entity instanceof Player player) {
            int karma = getPlayerKarma(player); // Fetch player karma dynamically
            return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma_meter_"+String.format("%02d", karma));
//            if (karma <= 0) return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_00");
//            else if (karma <= 1) return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_01");
//            else if (karma <= 2) return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_02");
        }
        return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma_meter_00");
    }

    private int getPlayerKarma(Player player) {
        double karma = Utils.getStat(MainMod.mcs.getPlayerList().getPlayerByName(player.getDisplayName().getString()), "karma", double.class);
//        int karmaLevelThreshold = 750;
//        System.err.println("karmaCalculation > (int) Math.floor("+karma+" / "+karmaLevelThreshold+") + "+(KARMA_LEVEL_ITERATIONS/2));
        int karmaCalculation = Math.clamp( (int) (Math.floor(karma / karmaLevelThreshold) + (KARMA_LEVEL_ITERATIONS/2)), 0, 31 );

        int startColor = 0xFF0000; // Red
        int midColor = 0xFFFFFF; // White
        int endColor = 0x0000FF; // Blue
        int interpolatedColor = Utils.interpolateThreeColors(startColor, midColor, endColor, (float) karmaCalculation /KARMA_LEVEL_ITERATIONS);

        /** SET CAPABILITIES*/
        stack.getCapability(CustomModelDataProvider.CUSTOM_MODEL_DATA_CAPABILITY).ifPresent(cap -> {
            cap.setCustomModelData(new CustomModelData(List.of((float) karmaCalculation), List.of(), List.of(), List.of(interpolatedColor)));
        });

        // Fetch the player's karma value using your custom Capability or logic
        return karmaCalculation;//player.getCapability(KarmaCapabilityProvider.KARMA).orElse(0);
    }
}