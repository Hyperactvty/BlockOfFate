package com.hyperactvty.blockoffate.items;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.utilities.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
//import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

// RegisterClientTooltipComponentFactoriesEvent

import java.awt.*;
import java.util.List;

public class KarmaMeter_Item extends Item {
    public KarmaMeter_Item(Properties properties) {
        super(properties);
    }

    public final static int KARMA_LEVEL_ITERATIONS = 32;
//    public static final DataComponentType<Integer> KARMA_COMPONENT = DataComponents.register("karma", /*Integer*/double.class);
//    public static final DataComponentType<Integer> KARMA_COMPONENT = DataComponents.register("karma", /*Integer*/double.class);

    private Level level;
    private Player player;
    private double prevKarmaValue = 0.0;

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext tooltip, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, tooltip, components, flag);
//        this.getBlock().appendHoverText(p_40572_, p_327780_, p_40574_, p_40575_);
        if (level != null && level.isClientSide) {
//            if (prevKarmaValue != ) {
//
//            }


            double karma = Utils.getStat(player, "karma", double.class);
            Component karmaText = Component.literal("Karma: " + karma)
                    .withStyle(ChatFormatting.BOLD, ChatFormatting.GOLD);

            components.add(karmaText);

            // Define start and end colors for the gradient
            int startColor = 0xFF0000; // Red
            int endColor = 0x0000FF; // Blue
            String text = "Gradient Text";

            // Create a gradient between startColor and endColor
            for (int i = 0; i < text.length(); i++) {
                int color = Utils.interpolateColor(startColor, endColor, (float) i / (text.length() - 1));
                components.add(Component.literal(String.valueOf(text.charAt(i)))
                        .setStyle(Style.EMPTY.withColor(color)));
            }

            ///  Below is for when we want to create custom hover things.
//            tooltip.add(Component.literal("Click me!").withStyle(ChatFormatting.BLUE)
//                    .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://example.com")))
//                    .withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Opens a website!"))))
//            );

        }
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

    @Override
//    public void inventoryTick(ItemStack stack, Level level, LivingEntity entity, int slot, boolean selected) {
    public void inventoryTick(ItemStack stack, Level _level, Entity _entity, int slot, boolean selected) {
        if (!_level.isClientSide) {
            return; // Only update on the client
        }

        if (_level != level) {
            level = _level;
        }
        if (_entity instanceof Player _player) {
            if (_player != player) {
                player = _player;
            }
        }

        // The game already provides a "time" property for clocks, so we'll use it
//        ItemProperties.get(stack, new ResourceLocation("time"));
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

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getCustomModel(ItemStack stack, @Nullable Level level, @Nullable LivingEntity entity) {
        if (entity instanceof Player player) {
            int karma = getPlayerKarma(player); // Fetch player karma dynamically
            System.err.println("Karma Value > "+karma);
//            return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_00");
            if (karma <= 0) return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_00");
            else if (karma <= 10) return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_01");
            else if (karma <= 20) return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_02");
        }
        return ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "item/karma_meter_default");
    }

    private int getPlayerKarma(Player player) {
        double karma = Utils.getStat(player, "karma", double.class);
        int karmaLevelThreshold = 750;
        int karmaCalculation = (int) Math.floor(karma / karmaLevelThreshold) + (karmaLevelThreshold * (KARMA_LEVEL_ITERATIONS/2));
        // Fetch the player's karma value using your custom Capability or logic
        return karmaCalculation;//player.getCapability(KarmaCapabilityProvider.KARMA).orElse(0);


    }
}