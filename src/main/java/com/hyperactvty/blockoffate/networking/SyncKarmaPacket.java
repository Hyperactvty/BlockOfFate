package com.hyperactvty.blockoffate.networking;

import com.hyperactvty.blockoffate.registry.CapabilityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkContext;

import java.util.function.Supplier;

public class SyncKarmaPacket {
    private final int karma;

    public SyncKarmaPacket(int karma) {
        this.karma = karma;
    }

    public static void encode(SyncKarmaPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.karma);
    }

    public static SyncKarmaPacket decode(FriendlyByteBuf buffer) {
        return new SyncKarmaPacket(buffer.readInt());
    }

//    public static void handle(SyncKarmaPacket packet, Supplier<CustomPayloadEvent.Context> contextSupplier) {
//        contextSupplier.get().enqueueWork(() -> {
//            // Handle packet on the client side
//            Player player = Minecraft.getInstance().player;
//            if (player != null) {
//                player.getCapability(CapabilityRegistry.KARMA).ifPresent(karmaCap -> karmaCap.setKarma(packet.karma));
//            }
//        });
//        contextSupplier.get().setPacketHandled(true);
//    }

//    public static void syncKarmaToClient(Player player) {
//        player.getCapability(CapabilityRegistry.KARMA).ifPresent(karmaCap -> {
//            int karma = karmaCap.getKarma();
//            NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
//                    new SyncKarmaPacket(karma));
//        });
//    }

    public static void handle(SyncKarmaPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            // Handle packet on the client side
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                player.getCapability(CapabilityRegistry.KARMA).ifPresent(karmaCap -> karmaCap.setKarma(packet.karma));
            }
        });
        context.setPacketHandled(true);
    }


//    public SyncKarmaPacket(FriendlyByteBuf buf) {
//        this.karma = buf.readInt();
//    }
//
//    public void toBytes(FriendlyByteBuf buf) {
//        buf.writeInt(karma);
//    }
//
//    public void handle(Supplier<ClientPlayerNetworkEvent.Context> context) {
//        context.get().enqueueWork(() -> {
//            Player player = Minecraft.getInstance().player;
//            if (player != null) {
//                player.getCapability(CapabilityRegistry.KARMA).ifPresent(karmaCap -> karmaCap.setKarma(karma));
//            }
//        });
//        context.get().setPacketHandled(true);
//    }
}
