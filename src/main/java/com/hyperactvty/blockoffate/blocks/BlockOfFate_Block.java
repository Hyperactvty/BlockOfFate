package com.hyperactvty.blockoffate.blocks;

import com.hyperactvty.blockoffate.registry.Statistics;
import com.hyperactvty.blockoffate.utilities.Fate;
import com.hyperactvty.blockoffate.utilities.FateExecution;
import com.hyperactvty.blockoffate.records.Rate;
import com.hyperactvty.blockoffate.utilities.Utils;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class BlockOfFate_Block extends Block { //BoF_Generic_BLOCK
    public BlockOfFate_Block(Properties properties) {
        super(properties);
    }

    private final String mod_group_id="com.hyperactvty.blockoffate";
    Random random = new Random();
    private static Rate rObj = new Rate(null, null, null, 0, null, null);

//    public List<RegistryObject<Item>> FATE_BLOCKS = new ArrayList<>();

    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(world, pos, state, player);


        // Server-side check
        if (!world.isClientSide && world instanceof ServerLevel serverWorld) {
            Rate _det = determineFate(state, world); // Determines the FATE
            FateExecution.Execute(_det, world, pos, player);
            // Play a custom sound
            world.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 1.0F);

            serverWorld.sendParticles(rObj.particleType()!= null ? (ParticleOptions) rObj.particleType() : ParticleTypes.SPIT,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    20, 0.5, 0.5, 0.5, 0.02);
        }
        return state;
    }

//    @Override
//    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
//        super.onRemove(state, world, pos, newState, isMoving);
//
//        if (!world.isClientSide && state.getBlock() != newState.getBlock()) {
//            Rate _det = determineFate(state, world); // Determines the FATE
//            FateExecution.Execute(_det, world, pos);
//
//            LogUtils.getLogger().info("BROKE BLOCK >> {} @ [{}, {}, {}]", state.getBlock().getName(), pos.getX(), pos.getY(), pos.getZ());
//
////            ItemEntity extraDrop = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.DIAMOND));
////            world.addFreshEntity(extraDrop);
//
//            // Play a custom sound
//            world.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 1.0F);
//
//            // Get the player breaking the block
//            Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
//
//            if (player != null && false==true) {
//                // Generate random teleport coordinates within 10 blocks
//                double newX = pos.getX() + (world.random.nextInt(20) - 10);
//                double newY = pos.getY() + (world.random.nextInt(5) - 2); // Keeps Y-level more stable
//                double newZ = pos.getZ() + (world.random.nextInt(20) - 10);
//
//                // Play enderman teleport sound
//                world.playSound(null, pos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
//
//                // Teleport the player
//                player.teleportTo(newX, newY, newZ);
//
//                // Apply glowing effect for 5 seconds
//                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0, false, false));
//
//                // List of effects (some good, some bad)
//                MobEffect[] effects = {(MobEffect)MobEffects.REGENERATION, (MobEffect)MobEffects.POISON, (MobEffect)MobEffects.MOVEMENT_SPEED, (MobEffect)MobEffects.WEAKNESS, (MobEffect)MobEffects.DAMAGE_BOOST, (MobEffect)MobEffects.BLINDNESS};
//                MobEffect chosenEffect = effects[world.random.nextInt(effects.length)];
//
//                // Apply effect for 5 seconds
//                player.addEffect(new MobEffectInstance((Holder<MobEffect>) chosenEffect, 100, 0, false, true));
//            }
//
//            // Spawn a random mob
//            EntityType<?>[] mobs = {EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER};
//            EntityType<?> chosenMob = mobs[world.random.nextInt(mobs.length)];
//
////            LivingEntity mob = (LivingEntity)chosenMob.create(world);
////            if (mob != null) {
////                mob.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
////                world.addFreshEntity(mob);
////                world.playSound(null, pos, SoundEvents.ZOMBIE_AMBIENT, SoundSource.HOSTILE, 1.0F, 1.0F);
////            }
//
////            if (!world.isClientSide) {
////                // Summon lightning at the block's position
////                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
////                if (lightning != null) {
////                    lightning.moveTo(Vec3.atBottomCenterOf(pos));
////                    world.addFreshEntity(lightning);
////                }
////
////                // Play thunder sound
////                world.playSound(null, pos, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, 2.0F, 1.0F);
////            }
//        }
//    }

//    @Override
//    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
//        super.animateTick(state, world, pos, random);
//
//        if (random.nextFloat() < 0.5F) { // 50% chance to spawn particles
//            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
//            double y = pos.getY() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
//            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
//
//            world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, x, y, z, 0, 0.02, 0);
//        }
//    }

    public Rate determineFate(BlockState state, Level world) {
        LogUtils.getLogger().info("BLOCK >> {} | {}", state.getBlock(), mod_group_id);
//        LogUtils.getLogger().info("Will it work... > {}", state.getBlock().getName().getContents());
//        LogUtils.getLogger().info("Will it work... > {}", state.getBlock().getName());
//        LogUtils.getLogger().info("Will it work... > {}", state.getBlock().getName().toString().contains("block.blockoffate.bof_generic"));
//        LogUtils.getLogger().info("Will it work... > {}", state.getBlock());


//        if (!world.isClientSide && state.getBlock().getClass().getName().equals(mod_group_id.concat(".BlockOfFate_Block"))) {
        if (!world.isClientSide) {
            rObj = Fate.determine(null);
            LogUtils.getLogger().info("Fate is pondering... > {}", rObj);
            return rObj;
        }
        LogUtils.getLogger().info("Fate frowns in your favor... > {}", rObj);
        return rObj;
    }

//    public static GenItem selectItem(JSONObject itemRarity, List<GenItem> itemList, Map<String, Boolean> keyEvents) {
//        GenItem selectedItem = null;
//        Random gi_rand = new Random();
//
//        // Calculate total weight
//        int totalWeight = 0;
//        for (String key : itemRarity.keySet()) {
//            System.out.println("KEY > " + itemRarity.get(key));
//            totalWeight += itemRarity.get(key).getInt("weight");
//        }
//
//        // Weighted random selection
//        int wp = gi_rand.nextInt(totalWeight) + 1;
//        int rar = 0;
//        for (String key : itemRarity.keySet()) {
//            int weight = itemRarity.getJSONObject(key).getInt("weight");
//            if (wp <= weight) break;
//            rar++;
//            wp -= weight;
//        }
//
//        // Do-while equivalent
//        do {
//            List<GenItem> filteredItems = new ArrayList<>();
//            String targetType = new ArrayList<>(itemRarity.values()).get(rar).getString("type");
//
//            for (GenItem i : itemList) {
//                if (i.getRarityType().equals(targetType)) {
//                    filteredItems.add(i);
//                }
//            }
//
//            if (filteredItems.isEmpty()) continue;
//
//            selectedItem = filteredItems.get(gi_rand.nextInt(filteredItems.size()));
//
//            boolean allConditionsMet = true;
//            for (Map<String, Boolean> event : selectedItem.getRequiredKeyEvents()) {
//                for (Map.Entry<String, Boolean> entry : event.entrySet()) {
//                    if (!keyEvents.getOrDefault(entry.getKey(), false).equals(entry.getValue())) {
//                        System.out.println("A parameter of [" + selectedItem.getName() + "] is not fulfilled.");
//                        allConditionsMet = false;
//                        break;
//                    }
//                }
//                if (!allConditionsMet) break;
//            }
//
//            if (allConditionsMet) {
//                System.out.println("[SUCCESS] -> " + selectedItem.getName());
//                break;
//            }
//        } while (true);
//
//        return selectedItem;
//    }

}
