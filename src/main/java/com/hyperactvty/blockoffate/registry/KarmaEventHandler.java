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
        System.out.println("onEntityKilled enter > "+event.getEntity());
//        ResourceKey<Registry<EntityType<?>>> entityTypeReg = Registries.ENTITY_TYPE;
//        System.out.println("entityType > "+entityTypeReg);

//        for (ResourceKey<? extends Registry<?>> resourceKey : EntityType.FILTERED_REGISTRIES) {
//            // Ensure we're working with the EntityType registry
//            if (resourceKey == BuiltInRegistries.ENTITY_TYPE.key()) {
//                // Get the entity type registry
//                Registry<EntityType<?>> entityRegistry = BuiltInRegistries.ENTITY_TYPE;
//
//                // Iterate over all registered entity types
//                for (ResourceKey<EntityType<?>> entityKey : entityRegistry.registryKeySet()) {
//                    if (entityRegistry.getValue(entityKey) == null) { return; }
//                    Optional<Holder.Reference<EntityType<?>>> entityTypeRef = entityRegistry.get(entityKey);
////                    EntityType entityType0 = ResourceLocation.parse(String.valueOf(entityKey));//.getCategory();
////                    EntityType entityType = entityKey;
//                    Object et = entityRegistry.getValue(entityKey).getCategory();
//
////                    System.out.println("Entity: " + entityKey.location()+" @ "+entityTypeRef.get());
////                    System.out.println("Entity: " + et);
//
//                    if (entityRegistry.getValue(entityKey).getCategory().equals(MobCategory.MONSTER)) {
//                        System.out.println("Hostile Entity: " + entityKey.location());
//                        System.out.println("Entity: " + entityKey.location()+" @ "+entityTypeRef.get());
//                    }
//                }
//            }
//        }


        if (!(event.getSource().getEntity() instanceof Player player)) {
            return; // Not a player kill
        }

        LivingEntity victim = event.getEntity();
        System.out.println("victim > "+victim);
        System.out.println("victim.getType().toShortString() > "+victim.getType().toShortString());
        System.out.println("getKillCredit > "+victim.getKillCredit());
        System.out.println("getName > "+victim.getName());
        System.out.println("getTags > "+victim.getTags());
        System.out.println("getType > "+victim.getType());
        System.out.println("getType.getTags() > "+victim.getType().getTags());
        System.out.println("victim.getName().getString() > "+victim.getName().getString());
        System.out.println("victim.getType().getDescription() > "+victim.getType().getDescription());
        System.out.println("victim.getType().getCategory() > "+victim.getType().getCategory());
        System.out.println("victim.getType().getBaseClass() > "+victim.getType().getBaseClass());
        System.out.println("victim instanceof Monster > "+(victim instanceof Monster));
        System.out.println("victim instanceof Animal > "+(victim instanceof Animal));

        // Check if the victim is a hostile mob (Monster class covers most hostiles)
        // MobCategory.MONSTER
        if (victim instanceof Monster) {
            System.err.println("victim is Hostile Entity (Monster): " + victim.getType().getCategory() + " [" + victim + "]");
            switch (victim.getType().toShortString()) {
//            switch (victim.getType().toString()) {
                case "bogged", "caveSpider", "creeper", "drowned", "endermite", "husk", "magmaCube", "silverfish",
                     "skeleton", "slime", "spider", "stray", "vex", "witch", "zombie":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Common);
                    break;
                case "blaze", "enderMan", "evoker", "ghast", "giant", "guardian", "illusioner", "Phantom", "pillager",
                     "shulker", "vindicator", "witherSkeleton", "zoglin", "zombifiedPiglin", "breeze", "creaking",
                     "hoglin", "piglinBrute":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                case "ravager", "zombieVillager":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Epic);
                    break;
                case "elderGuardian":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Elite);
                    break;
                case "warden":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Boss);
                    break;
                default:
                    System.err.println(event.getSource().getEntity().getName()+" killed an entity outside of the hostile bounds.");
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Common);
                    break;
            }
            // Example: Award a custom stat
//            player.awardStat(Stats.CUSTOM.get(new ResourceLocation("blockoffate", "hostile_kills")), 1);
        }

//        else if (victim instanceof Animal) {
//            System.err.println("victim is Animal Entity: " + victim);
//            switch (victim.getType().toString()) {
//                case "WanderingTrader":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
//                    break;
//                case "Piglin":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
//                    break;
//                case "Strider":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
//                    break;
//                default: break;
//            }
//        }
        else {
            System.err.println("victim is OTHER Entity: " + victim.getType().getCategory() + " [" + victim + "]");
            switch (victim.getType().toShortString()) {
//            switch (victim.getType().toString()) {
                case "villager":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_Villager);
                    break;
                case "bat":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Common);
                    break;
                case "wanderingTrader", "pufferfish":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                case "skeletonHorse;":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Elite);
                    break;  // give ++ Karma
                case "traderLlama":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Epic);
                    break;
                case "enderDragon", "witherBoss":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Boss);
                    break;
                case "strider", "mushroomCow", "parrot", "snowGolem", "wolf", "camel", "frog", "horse", "llama":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_Animal_Common);
                    break;
                case "bee", "dolphin", "fox", "ocelot", "polarBear", "turtle", "armadillo", "tadpole", "goat", "mule":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_Animal_Rare);
                    break;
                case "cat", "ironGolem", "panda", "allay", "axolotl", "donkey", "sniffer":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_Animal_Epic);
                    break;
                case "chicken":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_Animal_Common);
                    break;
                case "cod":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                case "cow":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                case "pig":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                case "rabbit":
                    PlayerFate.setKarma(player, FateActions.Actions.Killed_Rabbit);
                    break;
                case "salmon":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                case "sheep":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                case "squid":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                case "tropicalFish":
//                    PlayerFate.setKarma(player, FateActions.Actions.Killed_HostileMob_Rare);
                    break;
                default:
                    System.err.println(event.getSource().getEntity().getName()+" killed an entity outside of the OTHER bounds.");
                    break;
            }
        }
    }

//    public static void register(IEventBus eventBus) {
//        this.register(eventBus);
//    }
}
