package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.MainMod;
import com.hyperactvty.blockoffate.records.Rate;
import com.hyperactvty.blockoffate.registry.CustomFateRegistry;
import com.hyperactvty.blockoffate.registry.LootTables;
import com.hyperactvty.blockoffate.registry.PlayerRegistry;
import com.hyperactvty.blockoffate.registry.Statistics;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
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
        // Get the player's `luck` stat
        float playerLuck = 0.0f;
        PlayerRegistry.checkPlayerLuck(player);
        Utils.incrementStat(player, "player_luck", 1);

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
            case "loot_table":
                JSONArray lootPoolJSON = fateDetermined.getJSONArray("pool");
                JSONObject lootPoolResult = lootPoolJSON.getJSONObject(rand.nextInt(lootPoolJSON.length()));
                System.out.println("Pool Result > "+lootPoolResult);
                for(Object lpi : fateDetermined.getJSONArray("pool").toList()) {
                    System.out.println("\t lpi > "+lpi);
                    LootTable.lootTable();
                }

                String lootPoolId = lootPoolResult.getString("name");
                System.err.println("Parse > "+lootPoolId+" | "+ResourceLocation.parse(lootPoolId));

                if (!world.isClientSide && world instanceof ServerLevel server) {
                    System.err.println("LT > "+ LootTables.FIRST_JOIN_WORLD1.toString());
                    System.err.println("LT > "+ LootTables.FIRST_JOIN_WORLD1.registryKey());
                    System.err.println("LT > "+ LootTables.FIRST_JOIN_WORLD1.registry());
                    System.err.println("LT > "+ LootTables.FIRST_JOIN_WORLD1.location());
                    // Get the LootTableManager
//                    lootTableManager = server.getServer().getLootTables();
//                    lootTableManager = server.getLootTables();
//                    LootTable lootTable = lootTableManager.get(lootPoolId);

//                    Level level = player.level();
//                    // Create a LootContext for the player (this could be for other contexts too)
//                    LootContext.Builder contextBuilder = new LootContext.Builder(level)
//                            .withParameter(LootContextParams.THIS_ENTITY, player)
//                            .withParameter(LootContextParams.ORIGIN, player.position())
//                            .withLuck(player.getLuck());

                    // Get the pools from the LootTable
//                    for (LootPool lpool : lootTable.pools) {
//                        // Find the specific LootPool by name
//                        if (lpool.getName().toString().equals(lootPoolId)) {
//                            // Iterate over the entries in this pool
//                            for (LootEntry entry : lpool.entries) {
//                                // Generate the loot and add it to the player's inventory
//                                ItemStack itemStack = entry.getItemStack(contextBuilder.build());
//
//                                if (!player.getInventory().add(itemStack)) {
//                                    player.drop(itemStack, false); // Drop item if inventory is full
//                                }
//                                System.out.println("Dropped item: " + entry.getItem().getDescriptionId());
//                            }
//                            return; // Exit once the correct pool is processed
//                        }
//                    }
//
//                    System.err.println("Invalid loot pool ID: " + lootPoolId);
                }

                System.err.println("Loot Pool Result > "+BuiltInRegistries.LOOT_POOL_ENTRY_TYPE.getValue(ResourceLocation.parse(lootPoolId)));
//                LootPool lootPool = BuiltInRegistries.LOOT_POOL_ENTRY_TYPE.getValue(ResourceLocation.parse(lootPoolId)); // Convert String to Minecraft Item
//                LootPool lootPool = BuiltInRegistries.LOOT_POOL_ENTRY_TYPE.getValue(ResourceLocation.parse(lootPoolId)); // Convert String to Minecraft Loot Pool
//
//
//                if (lootPool != null) {
//
//                    assert player != null;
//                    if (!player.getInventory().add(new ItemStack(lootPool))) {
//                        player.drop(new ItemStack(lootPool), false); // If inventory is full, drop the item at the player's position
//                    }
//                    System.out.println("Dropped item: " + lootPoolId);
//                } else {
//                    System.err.println("Invalid item ID: " + lootPoolId);
//                }
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
