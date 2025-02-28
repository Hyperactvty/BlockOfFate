package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.records.Rate;
import com.hyperactvty.blockoffate.registry.CustomFateRegistry;
import com.hyperactvty.blockoffate.registry.Statistics;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;

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
    }

    public static void incrementStat(Player player, ResourceLocation stat) {
        player.awardStat(Stats.CUSTOM.get(stat));
    }

    public static void incrementStat(Player player, String statName) {
        ResourceLocation stat = statList.get(statName);
        player.awardStat(Stats.CUSTOM.get(stat));
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
}
