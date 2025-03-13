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
        Killed_Animal_Common,
        Killed_Animal_Rare,
        Killed_Animal_Epic,
        BlockOfFate_Cursed_Broken,
        //#endregion NEGATIVE

        //#region POSITIVE
        Killed_HostileMob_Common,
        Killed_HostileMob_Rare,
        Killed_HostileMob_Epic,
        Killed_HostileMob_Elite,
        Killed_HostileMob_Boss,
        BlockOfFate_Broken,
        //#endregion POSITIVE

        //#region DEBUG
        Debug_Reset,
        Debug_Min,
        Debug_Max
        //#endregion DEBUG
    }

    public static double getActionEffect(Actions action) {
        return switch (action) {
            //#region NEGATIVE
            case Killed_Villager -> -25.00;
            case Killed_Rabbit -> -3.75;
            case Killed_Animal_Common -> -.025;
            case Killed_Animal_Rare -> -8.25;
            case Killed_Animal_Epic -> -27.50;
            case BlockOfFate_Cursed_Broken -> -15.00;
            //#endregion NEGATIVE

            //#region POSITIVE
            case Killed_HostileMob_Common -> 1.0;
            case Killed_HostileMob_Rare -> 2.5;
            case Killed_HostileMob_Epic -> 10.0;
            case Killed_HostileMob_Elite -> 100.0;
            case Killed_HostileMob_Boss -> 250.0;
            case BlockOfFate_Broken -> 5.0;
            //#endregion POSITIVE

            //#region DEBUG
            case Debug_Reset -> 0.0;
            case Debug_Min -> -12000.0;
            case Debug_Max -> 12000.0;
            //#endregion DEBUG
        };
    }
}
