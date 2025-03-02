package com.hyperactvty.blockoffate.utilities;

import com.hyperactvty.blockoffate.records.Rate;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.*;

public class Fate {
    private static JSONArray rates;
    private static List<Rate> rObjects = new ArrayList<>();
    private static int totalWeight = 0;

    public Fate(JSONArray _rates) {
        this.rates = _rates;
    }

    public JSONArray getRates() { return rates; }

    public void setRates(JSONArray rates) { Fate.rates = rates; }

    public void onStartUp() {
        for (int i = 0; i < rates.length(); i++) {
            JSONObject jsonObject = rates.getJSONObject(i);
            Rate _rO = new Rate(null, null, null, 0, null, null);
            for (String key : jsonObject.keySet()) {
                switch (key) {
                    case "tier":
                        _rO = new Rate((String) jsonObject.get(key), _rO.color(), _rO.abbv(), _rO.weight(), _rO.type(), _rO.particleType());
                        break;
                    case "color":
                        _rO = new Rate(_rO.tier(), (String) jsonObject.get(key), _rO.abbv(), _rO.weight(), _rO.type(), _rO.particleType());
                        break;
                    case "abbv":
                        _rO = new Rate(_rO.tier(), _rO.color(), (String) jsonObject.get(key), _rO.weight(), _rO.type(), _rO.particleType());
                        break;
                    case "weight":
                        _rO = new Rate(_rO.tier(), _rO.color(), _rO.abbv(), (int) jsonObject.get(key), _rO.type(), _rO.particleType());
                        totalWeight += _rO.weight(); // Calculate total weight
                        break;
                    case "type":
                        _rO = new Rate(_rO.tier(), _rO.color(), _rO.abbv(), _rO.weight(), (String) jsonObject.get(key), _rO.particleType());
                        break;
                    case "particleType":
                        _rO = new Rate(_rO.tier(), _rO.color(), _rO.abbv(), _rO.weight(), _rO.type(), getParticleTypeFromString((String) jsonObject.get(key)));
                        break;
                    default:
                        break;
                }
            }
            rObjects.add(_rO);
        }
    }

    public static ParticleType<?> getParticleTypeFromString(String name) {
        try {
            // Get the ParticleTypes class
            Class<?> particleClass = ParticleTypes.class;

            // Get the field by name
            Field field = particleClass.getDeclaredField(name);
            return (ParticleType<?>) field.get(null); // Get the static field value
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Invalid ParticleType: " + name);
            e.printStackTrace();
            return null; // Return null if not found
        }
    }

    public static Rate determine(String args[]) {
        Random gi_rand = new Random();

        // Weighted random selection
        int wp = gi_rand.nextInt(totalWeight) + 1;
        int rar = 0;
        for (Rate key : rObjects) {
            int weight = key.weight();
            if (wp <= weight) break;
            rar++;
            wp -= weight;
        }

        return rObjects.get(rar);
    }
}
