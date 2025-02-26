package com.hyperactvty.blockoffate.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.hyperactvty.blockoffate.utilities.FateExecution.player;

interface Command {
    void execute(JSONObject data, Level world, BlockPos pos);
}

public class CustomFateRegistry {
    // Store function references
    private static final Map<String, Command> fateList = new HashMap<>();

    // Register commands in static block
    static {
        fateList.put("dummy", CustomFateRegistry::dummy);
        fateList.put("death_by_anvil", CustomFateRegistry::curse_Anvil);
        fateList.put("slippery_hands", CustomFateRegistry::curse_SlipperyHands);
    }

    // Method to execute a command from JSON
    public static void executeCommand(JSONObject data, Level world, BlockPos pos) {
        Command cmd = fateList.getOrDefault(data.get("customFuncID").toString(), null);
        System.err.println("Command > " + data);

        if (cmd != null) {
            cmd.execute(data, world, pos);
        } else {
            System.out.println("Command not found: " + data.get("customFuncID").toString());
        }
    }

    // Example command method
    public static void dummy(JSONObject data, Level world, BlockPos pos) {
        System.out.println("Params are > " + data + " | " + world + " | " + pos);
    }

    public static void curse_SlipperyHands(JSONObject data, Level world, BlockPos pos) {
        if (!player.level().isClientSide) { // Run on server only
            // Drop main hand item
            if (!player.getMainHandItem().isEmpty()) {
                player.drop(player.getMainHandItem(), true, false);
                player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            }

            // Drop offhand item
            if (!player.getOffhandItem().isEmpty()) {
                player.drop(player.getOffhandItem(), true, false);
                player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
            }
        }
    }


    public static void curse_Anvil(JSONObject data, Level world, BlockPos pos) {
        System.err.println("Fate has decided..."); // Output this in chat or as a subtitle in red.
        if (!world.isClientSide) { // Ensure this runs only on the server

            player.setDeltaMovement(Vec3.ZERO); // Stop movement
//            player.setJumping(false);
//            player.setSprinting(false);
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 5, false, false)); // -1 for FLASHBANG
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 100, false, false));
//            player.input.leftImpulse = 0;
//            player.input.forwardImpulse = 0;
//            Player p = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
//            p.setDeltaMovement(Vec3.ZERO); // Stops all movement
//            p.hasImpulse = false; // Prevents movement updates
//            p.fallDistance = 0; // Stops fall damage in case needed

            BlockPos targetPos = player.blockPosition().above(50); // 50 blocks above player

            FallingBlockEntity anvil = FallingBlockEntity.fall(world, targetPos, Blocks.DAMAGED_ANVIL.defaultBlockState());
            anvil.time = 1; // Ensures it starts falling immediately
            anvil.setHurtsEntities(2.0F, 40);
            world.addFreshEntity(anvil);
        }
    }

}
