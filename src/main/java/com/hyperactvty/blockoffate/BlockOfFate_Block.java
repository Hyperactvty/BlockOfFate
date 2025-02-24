package com.hyperactvty.blockoffate;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

public class BlockOfFate_Block extends Block { //BoF_Generic_BLOCK
    public BlockOfFate_Block(Properties properties) {
        super(properties);
    }

    private final String mod_group_id="com.hyperactvty.blockoffate";


    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, world, pos, newState, isMoving);

        if (!world.isClientSide && state.getBlock() != newState.getBlock()) {
            determineFate(state, world, pos, newState); // Determines the FATE
            LogUtils.getLogger().info("BROKE BLOCK >> {} @ [{}, {}, {}]", state.getBlock().getName(), pos.getX(), pos.getY(), pos.getZ());
            // Drop an item when the block is removed
//            ItemEntity drop = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.EMERALD));
//            world.addFreshEntity(drop);

//            if (!world.isClientSide && state.getBlock() == BoF_Generic_BLOCK.get()) {
            ItemEntity extraDrop = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.DIAMOND));
            world.addFreshEntity(extraDrop);

            // Play a custom sound
            world.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 1.0F);

            // Get the player breaking the block
            Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);

            if (player != null) {
                // Generate random teleport coordinates within 10 blocks
                double newX = pos.getX() + (world.random.nextInt(20) - 10);
                double newY = pos.getY() + (world.random.nextInt(5) - 2); // Keeps Y-level more stable
                double newZ = pos.getZ() + (world.random.nextInt(20) - 10);

                // Play enderman teleport sound
                world.playSound(null, pos, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);

                // Teleport the player
                player.teleportTo(newX, newY, newZ);

                // Apply glowing effect for 5 seconds
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0, false, false));

                // List of effects (some good, some bad)
                MobEffect[] effects = {(MobEffect)MobEffects.REGENERATION, (MobEffect)MobEffects.POISON, (MobEffect)MobEffects.MOVEMENT_SPEED, (MobEffect)MobEffects.WEAKNESS, (MobEffect)MobEffects.DAMAGE_BOOST, (MobEffect)MobEffects.BLINDNESS};
                MobEffect chosenEffect = effects[world.random.nextInt(effects.length)];

                // Apply effect for 5 seconds
                player.addEffect(new MobEffectInstance((Holder<MobEffect>) chosenEffect, 100, 0, false, true));
            }

            // Spawn a random mob
            EntityType<?>[] mobs = {EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER};
            EntityType<?> chosenMob = mobs[world.random.nextInt(mobs.length)];

//            LivingEntity mob = (LivingEntity)chosenMob.create(world);
//            if (mob != null) {
//                mob.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
//                world.addFreshEntity(mob);
//                world.playSound(null, pos, SoundEvents.ZOMBIE_AMBIENT, SoundSource.HOSTILE, 1.0F, 1.0F);
//            }

//            if (!world.isClientSide) {
//                // Summon lightning at the block's position
//                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
//                if (lightning != null) {
//                    lightning.moveTo(Vec3.atBottomCenterOf(pos));
//                    world.addFreshEntity(lightning);
//                }
//
//                // Play thunder sound
//                world.playSound(null, pos, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, 2.0F, 1.0F);
//            }
        }
    }

//    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);

        if (random.nextFloat() < 0.5F) { // 50% chance to spawn particles
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
            double y = pos.getY() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.6;

            world.addParticle(ParticleTypes.CHERRY_LEAVES, x, y, z, 0, 0.02, 0);
        }
    }

    public void determineFate(BlockState state, Level world, BlockPos pos, BlockState newState) {
        LogUtils.getLogger().info("BLOCK >> {} | {}", state.getBlock(), mod_group_id);
        if (!world.isClientSide && state.getBlock().getClass().getName().equals(mod_group_id.concat(".BlockOfFate_Block"))) {
            // block.blockoffate.bof_generic
            LogUtils.getLogger().info("Yes");
        }
    }

}
