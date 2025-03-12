package com.hyperactvty.blockoffate.registry;

import com.hyperactvty.blockoffate.utilities.PlayerFate;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Optional;

public class KarmaEventHandler {

    public KarmaEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityKilled(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) {
            return; // Not a player kill
        }

        LivingEntity victim = event.getEntity();
        System.err.println("victim.getType().toShortString() > "+victim.getType().toShortString());
        // MobCategory.MONSTER
//        if (victim instanceof Monster) {
//        System.err.println("victim is Hostile Entity (Monster): " + victim.getType().getCategory() + " [" + victim + "]");
        switch (victim.getType().toShortString()) {
//            switch (victim.getType().toString()) {
            case "bat", "bogged", "cave_spider", "creeper", "drowned", "endermite", "husk", "magma_cube", "silverfish",
                 "skeleton", "slime", "spider", "stray", "vex", "witch", "zombie":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Common);
                break;
            case "blaze", "enderman", "evoker", "ghast", "giant", "guardian", "illusioner", "phantom", "pillager",
                 "shulker", "vindicator", "wither_skeleton", "zoglin", "zombified_piglin", "breeze", "creaking",
                 "hoglin", "piglin_brute", "wandering_trader", "pufferfish":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                break;
            case "ravager", "zombie_villager", "trader_llama":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Epic);
                break;
            case "elder_guardian", "skeleton_horse", "zombie_horse":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Elite);
                break;
            case "warden", "ender_dragon", "wither":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Boss);
                break;
            case "villager":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_Villager);
                break;
            case "strider", "mushroom", "parrot", "glow_squid", "snow_golem", "wolf", "camel", "frog", "horse", "llama":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_Animal_Common);
                break;
            case "bee", "dolphin", "fox", "ocelot", "polar_bear", "turtle", "armadillo", "tadpole", "goat", "mule", "piglin":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_Animal_Rare);
                break;
            case "cat", "iron_golem", "panda", "allay", "axolotl", "donkey", "sniffer":
                PlayerFate.setKarma(player, FateActions.Actions.Killed_Animal_Epic);
                break;
            case "chicken", "cod", "cow", "pig", "salmon", "sheep", "squid", "tropical_fish":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_Animal_Common);
                break;
            default:
                System.err.println(event.getSource().getEntity().getName()+" killed an entity outside of the bounds.");
                PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Common);
                break;
        }

    }
}
