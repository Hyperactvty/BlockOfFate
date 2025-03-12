package com.hyperactvty.blockoffate.registry;

import com.hyperactvty.blockoffate.MainMod;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.Time;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.TooltipFlag;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ModClientEvents {
//    protected final ItemModelOutput itemModelOutput;

    public ModClientEvents(/*ItemModelOutput itemModelOutput*/) {
//        this.itemModelOutput = itemModelOutput;
    }

    public ModClientEvents(IEventBus eventBus) {
//        this.itemModelOutput = itemModelOutput;
        subscribe(eventBus);
    }

    // Registering the client tick event
    public void subscribe(IEventBus eventBus) {
        eventBus.addListener(this::onClientTick);
        // Register item property for dynamic texture changes
//        ModelPredicateProviderRegistry
//        ModelManager.register(BlockItems.BoF_KARMA_METER_ITEM.get(), ResourceLocation.parse(MainMod.MODID+":karma"),
//                (stack, world, entity, seed) -> {
//                    if (world == null) return 0.0F;
//
//                    // Get the player's karma or some other value to modify the texture
//                    LocalPlayer player = Minecraft.getInstance().player;
//                    if (player != null) {
//                        double karma = getKarma(player); // You can replace this with how karma is tracked
//                        return (float) karma; // This is where you tie karma to the texture change
//                    }
//                    return 0.0F;
//                });
    }

    // The client tick event
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer player = minecraft.player;

            if (player != null) {
                // Check if player is holding the Karma Meter Item
                ItemStack heldItem = player.getMainHandItem();
                if (heldItem.getItem() instanceof BlockItem && heldItem.getItem() == BlockItems.BoF_KARMA_METER_ITEM.get()) {
                    Level world = Minecraft.getInstance().level;
                    long worldTime = world.getDayTime();
                    float timeNormalized = (float) (worldTime % 24000L) / 24000.0F;
                    double karmaNormalized = (worldTime % 24000L) / 24000.0F;

//                    generateClockItem(heldItem.getItem());

                    System.err.println("onClientTick > "+"Karma Meter: " + (int) (timeNormalized * 100) + "%");


                    // Modify the item's tooltip dynamically
//                    heldItem.getTooltip(player, minecraft.options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL)
//                            .add(Component.literal("Karma Meter: " + (int) (timeNormalized * 100) + "%")
//                                    .withStyle(Style.EMPTY.withBold(true).withColor(ChatFormatting.GREEN)));
                }
            }
        }
    }

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            event.addCapability(KarmaCapabilityProvider.KARMA_CAP_ID, new KarmaCapabilityProvider());
//            event.addCapability(ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma"), new KarmaCapabilityProvider());
        }
    }




    // Dummy function to simulate karma value, replace with actual karma tracking system
    private static double getKarma(LocalPlayer player) {
        // Replace with your actual karma calculation logic
        return 1.0; // Karma value between 0 and 1
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
