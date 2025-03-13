package com.hyperactvty.blockoffate.registry;

import com.hyperactvty.blockoffate.MainMod;
//import com.hyperactvty.blockoffate.items.KarmaMeterModel;
import com.hyperactvty.blockoffate.items.KarmaMeter_Item;
import com.hyperactvty.blockoffate.items.LuckyHam_Item;
import com.hyperactvty.blockoffate.utilities.ModItemModelResolver;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.Time;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraft.client.resources.model.ItemProperties;
import net.minecraftforge.event.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.ChatFormatting;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.client.resources.model.ModelBakery;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ModClientEvents {
//    protected final ItemModelOutput itemModelOutput;

    public ModClientEvents(/*ItemModelOutput itemModelOutput*/) {
//        this.itemModelOutput = itemModelOutput;
    }

//    public ModClientEvents(IEventBus eventBus) {
////        this.itemModelOutput = itemModelOutput;
//        subscribe(eventBus);
//    }
//
//    // Registering the client tick event
//    public void subscribe(IEventBus eventBus) {
//        eventBus.addListener(this::onClientTick);
//        // Register item property for dynamic texture changes
////        ModelPredicateProviderRegistry
////        ModelManager.register(BlockItems.BoF_KARMA_METER_ITEM.get(), ResourceLocation.parse(MainMod.MODID+":karma"),
////                (stack, world, entity, seed) -> {
////                    if (world == null) return 0.0F;
////
////                    // Get the player's karma or some other value to modify the texture
////                    LocalPlayer player = Minecraft.getInstance().player;
////                    if (player != null) {
////                        double karma = getKarma(player); // You can replace this with how karma is tracked
////                        return (float) karma; // This is where you tie karma to the texture change
////                    }
////                    return 0.0F;
////                });
//    }

    // The client tick event
//    @SubscribeEvent
//    public void onClientTick(TickEvent.ClientTickEvent event) {
//        if (event.phase == TickEvent.Phase.START) {
//            Minecraft minecraft = Minecraft.getInstance();
//            LocalPlayer player = minecraft.player;
//
//            if (player != null) {
//                // Check if player is holding the Karma Meter Item
//                ItemStack heldItem = player.getMainHandItem();
//                if (heldItem.getItem() instanceof BlockItem && heldItem.getItem() == BlockItems.BoF_KARMA_METER_ITEM.get()) {
//                    Level world = Minecraft.getInstance().level;
//                    long worldTime = world.getDayTime();
//                    float timeNormalized = (float) (worldTime % 24000L) / 24000.0F;
//                    double karmaNormalized = (worldTime % 24000L) / 24000.0F;
//
////                    generateClockItem(heldItem.getItem());
//
//                    System.err.println("onClientTick > "+"Karma Meter: " + (int) (timeNormalized * 100) + "%");
//
//
//                    // Modify the item's tooltip dynamically
////                    heldItem.getTooltip(player, minecraft.options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL)
////                            .add(Component.literal("Karma Meter: " + (int) (timeNormalized * 100) + "%")
////                                    .withStyle(Style.EMPTY.withBold(true).withColor(ChatFormatting.GREEN)));
//                }
//            }
//        }
//    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer player = minecraft.player;

            if (player != null) {
                // Check if player is holding the Karma Meter Item
                ItemStack heldItem = player.getMainHandItem();
                if (heldItem.getItem() instanceof KarmaMeter_Item && heldItem.getItem() == BlockItems.BoF_KARMA_METER_ITEM.get()) {
//                    ((KarmaMeter_Item) heldItem.getItem()).getCustomModel(player);
//                    heldItem.getItem().getOrCreateTag().putInt("karma", newValue); // Set the new value of karma
//                    System.err.println("onClientTick > heldItem = "+heldItem);
//                    heldItem.set(DataComponents.ITEM_MODEL, ((KarmaMeter_Item) heldItem.getItem()).getCustomModel(player));
//                    System.err.println("heldItem > "+heldItem.getComponents().get(DataComponents.ITEM_MODEL));
////                    Minecraft.getInstance().levelRenderer.allChanged(); // Forces a render refresh
                    System.err.println("Item CUSTOM_MODEL_DATA > "+heldItem.getItem().components().get(DataComponents.CUSTOM_MODEL_DATA));
//
////                    ResourceLocation texture = heldItem.getItem().getRegistryName(); // Get the item's registered name
//                    ResourceLocation texture = ((KarmaMeter_Item) heldItem.getItem()).getCustomModel(player); // Get the item's registered name
//                    System.out.println("Current Item Texture: " + texture);
//
//                    System.err.println("getItemHolder > "+heldItem.getItemHolder().unwrap());
//                    heldItem.getItemHolder().getTagKeys().forEach(i -> System.err.println("\tgetTagKeys > "+i));

//                    CompoundTag tag = heldItem.serializeNBT().getCompound("tag");

                    System.err.println("getTags > "+heldItem.getTags());
                    heldItem.getTags().forEach(i->System.err.println("\tgetTags > "+i));
                    System.err.println("immutableComponents > "+heldItem.immutableComponents());
                    heldItem.immutableComponents().forEach(i->System.err.println("\timmutableComponents > "+i));


                    /** ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma_meter_00") */
                    // In the future, call `RenderProperties.get`
//                    heldItem.getItem().initClient();
                    System.err.println(heldItem.getItem().getRenderPropertiesInternal());


                }
                if (heldItem.getItem() instanceof LuckyHam_Item && heldItem.getItem() == BlockItems.BoF_LUCKY_HAM_ITEM.get()) {
//                    ((KarmaMeter_Item) heldItem.getItem()).getCustomModel(player);
//                    heldItem.getItem().getOrCreateTag().putInt("karma", newValue); // Set the new value of karma
                    System.err.println("onClientTick > heldItem = "+heldItem);
                    System.err.println("heldItem > "+heldItem.getComponents().get(DataComponents.ITEM_MODEL));
//                    Minecraft.getInstance().levelRenderer.allChanged(); // Forces a render refresh
                    System.err.println("Item Texture > "+heldItem.getItem().components().get(DataComponents.CUSTOM_MODEL_DATA));
                    System.err.println("Item Texture 2  > "+heldItem.getItem().getDefaultInstance().getItemHolder());

//                    ResourceLocation texture = heldItem.getItem().getRegistryName(); // Get the item's registered name

                    System.err.println("getItemHolder > "+heldItem.getItemHolder().unwrap());
                    heldItem.getItemHolder().getTagKeys().forEach(i -> System.err.println("\tgetTagKeys > "+i));
                    System.err.println("getTags > "+heldItem.getTags());
                    heldItem.getTags().forEach(i->System.err.println("\tgetTags > "+i));
                    System.err.println("immutableComponents > "+heldItem.immutableComponents());
                    heldItem.immutableComponents().forEach(i->System.err.println("\timmutableComponents > "+i));


                    // In the future, call `RenderProperties.get`
//                    heldItem.getItem().initClient();
                    System.err.println(heldItem.getItem().getRenderPropertiesInternal());

                }
                if (heldItem.getItem() instanceof Item && heldItem.getItem() == Items.CLOCK.asItem()) {
//                    ((KarmaMeter_Item) heldItem.getItem()).getCustomModel(player);
//                    heldItem.getItem().getOrCreateTag().putInt("karma", newValue); // Set the new value of karma
                    System.err.println("onClientTick > heldItem = "+heldItem);
//                    heldItem.set(DataComponents.ITEM_MODEL, ((KarmaMeter_Item) heldItem.getItem()).getCustomModel(player));
                    System.err.println("heldItem > "+heldItem.getComponents().get(DataComponents.ITEM_MODEL));
                    System.err.println("heldItem > "+heldItem.getComponents().get(DataComponents.ENTITY_DATA));
                    System.err.println("heldItem > "+heldItem.getComponents().get(DataComponents.PROFILE));
                    System.err.println("Item Texture > "+heldItem.getItem().components().get(DataComponents.CUSTOM_MODEL_DATA));

                    DataComponents.COMMON_ITEM_COMPONENTS.stream().forEach(i -> System.err.println("\t Component > "+i));
//                    Minecraft.getInstance().levelRenderer.allChanged(); // Forces a render refresh

//                    ResourceLocation texture = heldItem.getItem().getRegistryName(); // Get the item's registered name
                    System.err.println("getItemHolder > "+heldItem.getItemHolder().unwrap());
                    heldItem.getItemHolder().getTagKeys().forEach(i -> System.err.println("\tgetTagKeys > "+i));
                    System.err.println("getTags > "+heldItem.getTags());
                    heldItem.getTags().forEach(i->System.err.println("\tgetTags > "+i));
                    System.err.println("immutableComponents > "+heldItem.immutableComponents());
                    heldItem.immutableComponents().forEach(i->System.err.println("\timmutableComponents > "+i));

                    // In the future, call `RenderProperties.get`
                    System.err.println(heldItem.getItem().getRenderPropertiesInternal());

                }
            }
        }
    }

//    @SubscribeEvent
//    public static void onKeyPress(InputEvent event) {
//        if (Minecraft.getInstance().options.keyInventory.consumeClick()) {
//            ItemStack heldItem = Minecraft.getInstance().player.getMainHandItem();
//            ItemStackRenderState renderState = new ItemStackRenderState();
//
//            ModItemModelResolver customResolver = new ModItemModelResolver(
//                    Minecraft.getInstance().getModelManager());
//            customResolver.appendItemLayers(renderState, heldItem, ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, null, Minecraft.getInstance().player, 0);
//
//            System.out.println("Manually triggered appendItemLayers for testing.");
//        }
//    }


//    @SubscribeEvent
//    public static void onModelBake(ModelBakeEvent event) {
//        ResourceLocation itemModelLocation = new ResourceLocation(MainMod.MODID, "item/karma_meter");
//
//        BakedModel defaultModel = event.getModelRegistry().get(itemModelLocation);
//        if (defaultModel != null) {
//            event.getModelRegistry().put(itemModelLocation, new KarmaMeterModel(defaultModel));
//        }
//    }

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            event.addCapability(KarmaCapabilityProvider.KARMA_CAP_ID, new KarmaCapabilityProvider());
            System.err.println("onAttachCapabilities");
//            event.addCapability(ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma"), new KarmaCapabilityProvider());
        }
    }


//    // Registering the client tick event
//    public static void subscribe(IEventBus eventBus) {
//        eventBus.addListener(ModClientEvents::onClientTick);
//    }
//
//    // The client tick event
//    @SubscribeEvent
//    public static void onClientTick(TickEvent.ClientTickEvent event) {
//        if (event.phase == TickEvent.Phase.START) {
//            Minecraft minecraft = Minecraft.getInstance();
//            LocalPlayer player = minecraft.player;
//
//            if (player != null) {
//                // Check if player is holding the Karma Meter Item
//                ItemStack heldItem = player.getMainHandItem();
//                if (heldItem.getItem() instanceof BlockItem && heldItem.getItem() == BlockItems.BoF_KARMA_METER_ITEM.get()) {
//                    Level world = Minecraft.getInstance().level;
//                    long worldTime = world.getDayTime();
//                    float timeNormalized = (float) (worldTime % 24000L) / 24000.0F;
//
//                    // Modify the item's tooltip dynamically
//                    heldItem.getTooltip(player, minecraft.options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL)
//                            .add(Component.literal("Karma Meter: " + (int) (timeNormalized * 100) + "%")
//                                    .withStyle(Style.EMPTY.withBold(true).withColor(ChatFormatting.GREEN)));
//                }
//            }
//        }
//    }
//
//    public void generateClockItem(Item p_376265_) {
//        List<RangeSelectItemModel.Entry> list = new ArrayList<>();
//        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.plainModel(this.createFlatItemModel(p_376265_, "_00", ModelTemplates.FLAT_ITEM));
//        list.add(ItemModelUtils.override(itemmodel$unbaked, 0.0F));
//
//        for (int i = 1; i < 32; i++) {
//            ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.plainModel(
//                    this.createFlatItemModel(p_376265_, String.format(Locale.ROOT, "_%02d", i), ModelTemplates.FLAT_ITEM)
//            );
//            list.add(ItemModelUtils.override(itemmodel$unbaked1, (float)i - 0.5F));
//        }
//
//        list.add(ItemModelUtils.override(itemmodel$unbaked, 31.5F/*63.5F*/));
////        this.itemModelOutput
////                .accept(
////                        p_376265_,
////                        ItemModelUtils.inOverworld(
////                                ItemModelUtils.rangeSelect(new Time(true, Time.TimeSource.DAYTIME), 64.0F, list),
////                                ItemModelUtils.rangeSelect(new Time(true, Time.TimeSource.RANDOM), 64.0F, list)
////                        )
////                );
//    }
//
//    protected ResourceLocation createFlatItemModel(Item p_376880_, String p_375748_, ModelTemplate p_375473_) {
//        return p_375473_.create(
//                ModelLocationUtils.getModelLocation(p_376880_, p_375748_), TextureMapping.layer0(TextureMapping.getItemTexture(p_376880_, p_375748_)), this.modelOutput
//        );
//    }


}
