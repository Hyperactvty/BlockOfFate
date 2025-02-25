package com.hyperactvty.blockoffate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FateExecution {
    public static void Execute(Rate rate, BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        System.out.println("Inside FateExecution.java...");
        JSONArray pool = MainMod.fatePools;
        Random rand = new Random();
        Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);

        List<JSONObject> filteredPools = new ArrayList<>();

        for (int i = 0; i < pool.length(); i++) {
            // If the `POOL` tier doesn't match the `TIER`, continues...
            if(!pool.getJSONObject(i).get("tier").equals(rate.tier())) continue;
            JSONObject jsonObject = pool.getJSONObject(i);
//            System.out.println("Keys in item " + (i + 1) + ": " + jsonObject.keySet());
//            System.out.println("Pool Items > " + jsonObject.get("pools"));
            pool = (JSONArray) jsonObject.get("pools");
            break;
        }

        System.out.println(pool.getJSONObject(0).get("entries"));

        JSONArray entries = pool.getJSONObject(0).getJSONArray("entries");
        int i = rand.nextInt(entries.length());
        JSONObject fateDetermined = entries.getJSONObject(i);
        System.out.println("POOL ITEM FOR TIER ["+rate.tier()+"] > "+fateDetermined + " | "+fateDetermined.get("functionType").toString());

        switch (fateDetermined.get("functionType").toString()) {
            case "giveItem":
                String itemId = fateDetermined.getString("name");
                Item item = BuiltInRegistries.ITEM.getValue(ResourceLocation.parse(itemId)); // Convert String to Minecraft Item
                int count = 1;
                if (fateDetermined.has("functions")) {
                    JSONArray functionsArray = fateDetermined.getJSONArray("functions");
                    for (int j = 0; j < functionsArray.length(); j++) {
                        JSONObject functionObject = functionsArray.getJSONObject(j);

                        // Check if function is "minecraft:set_count"
                        if (functionObject.getString("function").equals("minecraft:set_count")) {
                            // Get "count" object
                            JSONObject countObject = functionObject.getJSONObject("count");
                            count = rand.nextInt(countObject.getInt("max") - countObject.getInt("min") + 1) + countObject.getInt("min"); // Generate random number in range [min, max]
                        }
                    }
                }



                if (item != null) {
                    assert player != null;
                    if (!player.getInventory().add(new ItemStack(item,count))) {
                        player.drop(new ItemStack(item,count), false); // If inventory is full, drop the item at the player's position
                    }
                    System.out.println("Dropped item: " + itemId);
                } else {
                    System.err.println("Invalid item ID: " + itemId);
                }
                break;
            default:
                // Just kill the player
                break;
        }

    }
}
