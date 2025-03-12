package com.hyperactvty.blockoffate.networking;

import com.hyperactvty.blockoffate.MainMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;

public class NetworkHandler {
    private static final int PROTOCOL_VERSION = 1;
//    public static final SimpleChannel CHANNEL = ChannelBuilder.simpleChannel(
//            ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "main"),
//            () -> PROTOCOL_VERSION//,
////            PROTOCOL_VERSION::equals,
////            PROTOCOL_VERSION::equals
//    );

    public static final SimpleChannel CHANNEL = ChannelBuilder.named(
                    ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "main"))
            .networkProtocolVersion(PROTOCOL_VERSION)
            .simpleChannel();


    public static void register() {
        int id = 0; // Packet ID starts at 0 and increments
        CHANNEL.messageBuilder(SyncKarmaPacket.class, id++)
                .encoder(SyncKarmaPacket::encode)
                .decoder(SyncKarmaPacket::decode)
                .consumerMainThread(SyncKarmaPacket::handle)
                .add();
    }
}

