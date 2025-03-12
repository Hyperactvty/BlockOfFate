package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.records.Rate;
import com.hyperactvty.blockoffate.registry.CustomFateRegistry;
import com.hyperactvty.blockoffate.registry.Statistics;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.stats.StatsCounter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.json.JSONObject;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static final Map<String, ResourceLocation> statList = new HashMap<>();
    static {
//        statList.put("blocks_opened", Statistics.BLOCKS_OPENED);
        statList.put(/*"fates_cursed"*/"CURSE", Statistics.FATES_CURSED);
        statList.put(/*"fates_f"*/"F", Statistics.FATES_F);
        statList.put(/*"fates_d"*/"D", Statistics.FATES_D);
        statList.put(/*"fates_c"*/"C", Statistics.FATES_C);
        statList.put(/*"fates_b"*/"B", Statistics.FATES_B);
        statList.put(/*"fates_a"*/"A", Statistics.FATES_A);
        statList.put(/*"fates_s"*/"S", Statistics.FATES_S);
        statList.put(/*"fates_ss"*/"SS", Statistics.FATES_SS);
        statList.put(/*"fates_sss"*/"SSS", Statistics.FATES_SSS);
        statList.put(/*"player_luck"*/"player_luck", Statistics.PLAYER_LUCK);

        statList.put(/*"karma"*/"karma", Statistics.KARMA);
    }

    private static final int SCALE_FACTOR = 1000; // Precision

    public static <T> double getStat(Player player, String statName, T type) {
        double amt = 0;
        if (player instanceof ServerPlayer serverPlayer) {
            StatsCounter statsCounter = serverPlayer.getStats();
            amt = statsCounter.getValue(Stats.CUSTOM.get(statList.get(statName)));
            System.err.println("Player Stat > " + amt);
        }
        return amt / (type.equals(double.class) ? (double) SCALE_FACTOR : 1);
    }//Stats.CUSTOM.get(statList.get(statName)); }

    public static void incrementStat(Player player, ResourceLocation stat) {
        player.awardStat(Stats.CUSTOM.get(stat));
    }

    public static void incrementStat(Player player, String statName) {
        ResourceLocation stat = statList.get(statName);
        player.awardStat(Stats.CUSTOM.get(stat));
    }

    public static void incrementStat(Player player, String statName, int amount) {
        ResourceLocation stat = statList.get(statName);
//        StatsCounter statsManager = new StatsCounter();
//        statsManager.increment(player, Stats.CUSTOM.get(stat), amount);
//        float currentValue = statsManager.getValue(Stats.CUSTOM.get(stat));
//        System.err.println("Player Luck > "+currentValue);
//
//
//        if (currentValue > 0) { // Prevent negative stats
//            statsManager.setValue(player, Stats.CUSTOM.get(stat), (int) (currentValue + amount));
//        }
        player.awardStat(Stats.CUSTOM.get(stat), amount);
    }

    public static void incrementStat(Player player, String statName, double amount) {
        int scaledAmount = (int) Math.round(amount * SCALE_FACTOR);
        ResourceLocation stat = statList.get(statName);
        player.awardStat(Stats.CUSTOM.get(stat), scaledAmount); // Right one

//        player.awardStat(Stats.CUSTOM.get(stat), Math.ceil(amount * 100));
    }

    public static void decrementStat(Player player, String statName, int amount) {
        ResourceLocation stat = statList.get(statName);

        StatsCounter statsManager = new StatsCounter();
        statsManager.getValue(Stats.CUSTOM.get(stat));
//        statsManager.increment(player, Stats.CUSTOM.get(stat), amount);
//        float currentValue = statsManager.getValue(Stats.CUSTOM.get(stat));
        if (player instanceof ServerPlayer serverPlayer) {
            StatsCounter statsCounter = serverPlayer.getStats();
            int amt = statsCounter.getValue(Stats.CUSTOM.get(stat));
            System.err.println("Player Luck > "+amt);
        }

        player.awardStat(Stats.CUSTOM.get(stat), amount);
    }

    public static void displayTitle(Player player, TextColor color, String... titleText) {
        // Send a title message to the player (this will display above the inventory)
//        Minecraft.getInstance().getConnection().send(new ClientboundSetTitleTextPacket(new TextComponent(titleText[0]), 10, 70, 20));
//
//        // Optional: You can also add a subtitle if you'd like
//        Minecraft.getInstance().getConnection().send(new ClientboundSetSubtitleTextPacket(new Component(titleText[0]));
////        Minecraft.getInstance().getConnection().send(new ClientboundSetSubtitleTextPacket(titleText[1], 10, 70, 20));
//
//        // Send the main title (this will appear above the inventory)
//        Minecraft.getInstance().getConnection().send(new ClientboundSetTitleTextPacket(new TextComponent("Hello!")));
//
//        // Send the subtitle (this will appear below the title)
//        Minecraft.getInstance().getConnection().send(new ClientboundSetSubtitleTextPacket(new TextComponent("This is a test.")));
//
//        // Set title display timings (fade-in, stay, fade-out)
        ClientboundSetTitleTextPacket titlePacket = new ClientboundSetTitleTextPacket(new ClientboundSetTitleTextPacket(Component.literal(titleText[0])).text());
        System.err.println("Attempting COMPONENT > " + titlePacket);
        Minecraft.getInstance().getConnection().sendCommand("execute at "+player.getName().getString()+" run title @p actionbar {\"text\":\""+titleText[0]+"\",\"color\":\""+color+"\"}");
//        Minecraft.getInstance().getConnection().send(titlePacket);

//        System.err.println("Attempting COMPONENT > " + Minecraft.getInstance());
//        System.err.println("Attempting COMPONENT > " + Minecraft.getInstance().getConnection());
//        System.err.println("Attempting COMPONENT > " + Minecraft.getInstance().gui.getChat());
//        System.err.println("Attempting TITLE > " + Component.literal(titleText[0]).toString());
////        System.err.println("Attempting TITLE > " + Component.nbt(titleText[0], true, null, null).toString());
////        Component actionBarMessage = Component.translatable(titleText[0]);
////        Minecraft.getInstance().getConnection().send(new ClientboundSetActionBarTextPacket(actionBarMessage)); // 1, 20, 1 are in ticks
//        Minecraft.getInstance().getConnection().send(new ClientboundSetActionBarTextPacket(Component.literal(titleText[0]))); // 1, 20, 1 are in ticks
    }

    public static void parseDropData(JSONObject data[]) {
        System.err.println("parseDropData > "+data);
        for (JSONObject datum : data) {
            System.err.println("datum > "+datum);
            switch (datum) {
                default -> throw new IllegalStateException("Unexpected value: " + datum);
            }
        }
        return;
    }

    public static int interpolateColor(int startColor, int endColor, float ratio) {
        int startRed = (startColor >> 16) & 0xFF;
        int startGreen = (startColor >> 8) & 0xFF;
        int startBlue = startColor & 0xFF;

        int endRed = (endColor >> 16) & 0xFF;
        int endGreen = (endColor >> 8) & 0xFF;
        int endBlue = endColor & 0xFF;

        int red = (int) (startRed + (endRed - startRed) * ratio);
        int green = (int) (startGreen + (endGreen - startGreen) * ratio);
        int blue = (int) (startBlue + (endBlue - startBlue) * ratio);

        return (red << 16) | (green << 8) | blue;
    }
}
