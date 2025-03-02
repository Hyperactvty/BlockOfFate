package com.hyperactvty.blockoffate.registry;

import net.minecraft.world.entity.player.Player;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayerRegistry {
    public static void checkPlayerLuck(/*String UUID, */Player player) {
        try {
            /* If the player is new, set the player's luck to 1000 */

//            String usersPath = "./config/users.json";
//            Files.createFile(Path.of(usersPath));
//            String content = new String(Files.readAllBytes(Paths.get(usersPath)));
//
//            // Convert string to JSONObject
//            JSONObject jsonObject = new JSONObject(content);
            /* {"uuid": UUID, "luck": "0.0f"} */
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // The actions that raise or lower the player's `luck` stat
    /** Maybe dependent on killing certain mobs for more, or maybe based off of EXP
     *
     *
     *
     */
}
