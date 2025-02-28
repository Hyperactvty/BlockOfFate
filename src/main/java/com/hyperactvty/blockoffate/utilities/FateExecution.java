package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.records.Rate;
import com.hyperactvty.blockoffate.registry.CustomFateRegistry;
import com.hyperactvty.blockoffate.registry.Statistics;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FateExecution {
    public static Player player = null;
    CustomFateRegistry cfr;

    public static void Execute(Rate rate, Level world, BlockPos pos, Player _player) {
        System.out.println("Inside FateExecution.java...");
        JSONArray pool = MainMod.fatePools;
        player = _player;

        List<JSONObject> filteredPools = new ArrayList<>();

        try {
            for (int i = 0; i < pool.length(); i++) {
                System.err.println(pool.getJSONObject(i).get("tier") + " == " + rate.tier());
                // If the `POOL` tier doesn't match the `TIER`, continues...
                if (!pool.getJSONObject(i).get("tier").equals(rate.tier())) continue;
                JSONObject jsonObject = pool.getJSONObject(i);
                //            System.out.println("Keys in item " + (i + 1) + ": " + jsonObject.keySet());
                System.out.println("Pool Items > " + jsonObject.get("pools"));
                pool = (JSONArray) jsonObject.get("pools");
                ExecuteFunctionality(pool, rate, world, pos);
                break;
            }

            Utils.incrementStat(player, Statistics.BLOCKS_OPENED);
        } catch (Exception e) {
            System.err.println("Error in [FateExecution] > "+e);
//            throw new RuntimeException(e);
        }
    }

    public static void ExecuteFunctionality(JSONArray pool, Rate rate, Level world, BlockPos pos) {
//        Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
        Random rand = new Random();
        System.out.println("POOL > "+pool.getJSONObject(0));
        System.out.println(pool.getJSONObject(0).get("entries"));

        JSONArray entries = pool.getJSONObject(0).getJSONArray("entries");
        int i = rand.nextInt(entries.length());
        JSONObject fateDetermined = entries.getJSONObject(i);
        System.out.println("POOL ITEM FOR TIER ["+rate.tier()+"] > "+fateDetermined + " | "+fateDetermined.get("functionType").toString());

        switch (fateDetermined.get("functionType").toString()) {
            case "giveItem":
                JSONArray itemPool = fateDetermined.getJSONArray("pool");
                JSONObject poolResult = itemPool.getJSONObject(rand.nextInt(itemPool.length()));
                System.out.println("Pool Result > "+poolResult);

                String itemId = poolResult.getString("name");
                Item item = BuiltInRegistries.ITEM.getValue(ResourceLocation.parse(itemId)); // Convert String to Minecraft Item
                int min = poolResult.optInt("min", 1), max = poolResult.optInt("max",1);
                int count = rand.nextInt(max - min + 1) + min;


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
            case "custom":
//                CustomFateRegistry.fateList.getOrDefault(fateDetermined, null);
                CustomFateRegistry.executeCommand(fateDetermined, world, pos);
                break;
            default:
                // Just kill the player
                break;
        }

        // Checks to see if there is a title
        String displayTitle = "I HAVE SUFFERED A GREEEVIOUS WOUND!";
        try {
            displayTitle = fateDetermined.getJSONArray("title").toList().get(rand.nextInt(fateDetermined.getJSONArray("title").length())).toString();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

        System.err.println("Maybe? "+TextColor.parseColor(rate.color()));
        Utils.displayTitle(player, TextColor.parseColor(rate.color()).getOrThrow(),displayTitle); // Example white title
        Utils.incrementStat(player, rate.tier());

    }

    //    @Override
//    public InteractionResult useOn(UseOnContext context) {
//        if (!context.getLevel().isClientSide) {
//            Utils.incrementStat(context.getPlayer(), Statistics.BLOCKS_OPENED);
//        }
//        return InteractionResult.SUCCESS;
//    }
}
