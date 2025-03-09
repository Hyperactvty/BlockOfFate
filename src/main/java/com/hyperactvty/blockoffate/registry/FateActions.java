package com.hyperactvty.blockoffate.registry;

import net.minecraft.util.Tuple;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class FateActions {
    public enum Actions {
//        Killed_Villager() {
//
//        }
        //#region NEGATIVE
        Killed_Villager,
        Killed_Rabbit,
        BlockOfFate_Cursed_Broken,
        //#endregion NEGATIVE

        //#region POSITIVE
        Killed_HostileMob,
        BlockOfFate_Broken
        //#endregion POSITIVE
    }

    public static double getActionEffect(Actions action) {
        return switch (action) {
            //#region NEGATIVE
            case Killed_Villager -> -25.00;
            case Killed_Rabbit -> -3.75;
            case BlockOfFate_Cursed_Broken -> -15.00;
            //#endregion NEGATIVE

            //#region POSITIVE
            case Killed_HostileMob -> 1.0;
            case BlockOfFate_Broken -> 5.0;
            //#endregion POSITIVE
        };
    }
}
