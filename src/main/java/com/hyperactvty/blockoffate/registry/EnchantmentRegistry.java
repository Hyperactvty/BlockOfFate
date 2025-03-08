package com.hyperactvty.blockoffate.registry;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.enchantments.FateEnchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentRegistry {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(Registries.ENCHANTMENT, MainMod.MODID);

//    public static final RegistryObject<Enchantment> FATE = ENCHANTMENTS.register(
//            "fate", FateEnchantment::new);

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
