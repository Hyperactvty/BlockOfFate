package com.hyperactvty.blockoffate.registry;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.interfaces.CustomModelDataProvider;
import com.hyperactvty.blockoffate.interfaces.IKarma;
import com.hyperactvty.blockoffate.items.KarmaMeter_Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MainMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityRegistry {
    public static final Capability<IKarma> KARMA = CapabilityManager.get(new CapabilityToken<>() {});

//    @SubscribeEvent
//    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
//        event.register(IKarma.class);
//    }

    @SubscribeEvent
    public static void onAttachCapabilitiesItem(AttachCapabilitiesEvent<ItemStack> event) {
        // Attach capability only to specific items, e.g., KarmaMeter_Item
        if (event.getObject().getItem() instanceof KarmaMeter_Item) {
            event.addCapability(
//                    ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "custom_model_data"),
                    ResourceLocation.fromNamespaceAndPath(MainMod.MODID, "karma"),
                    new CustomModelDataProvider()
            );
        }
    }
}
