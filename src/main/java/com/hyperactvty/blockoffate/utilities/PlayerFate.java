package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.registry.FateActions;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;

public class PlayerFate {
    public static double getKarma(Player player) {
//        double playerLuck = player.getLuck();
//        System.err.println("Karma > "+Utils.getStat(player, "karma", double.class));
        return Utils.getStat(player, "karma", double.class);
    }

    // The actions that raise or lower the player's `luck` stat
    /** Maybe dependent on killing certain mobs for more, or maybe based off of EXP
     *
     *
     *
     */

    public static void setKarma(Player player, FateActions.Actions action) {
        System.err.println("[Karma] "+player+" "+action+" : "+FateActions.getActionEffect(action)+" karma...");
        // DEBUG
        if(action.equals(FateActions.Actions.Debug_Reset)) {
            Utils.resetStat(player, "karma");
            return;
        }
        Utils.incrementStat(player, "karma", FateActions.getActionEffect(action));
    }
}
