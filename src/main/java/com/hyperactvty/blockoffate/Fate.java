package com.hyperactvty.blockoffate;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import org.json.JSONArray;
import org.json.JSONObject;
import net.minecraft.core.particles.ParticleTypes;

import java.lang.reflect.Field;
import java.util.*;

public class Fate {
    private static JSONArray rates;
    private static List<Rate> rObjects = new ArrayList<>();
    private Random rand = new Random();
    private static int totalWeight = 0;

    public Fate(JSONArray _rates) {
        this.rates = _rates;
    }

    public JSONArray getRates() {
        return rates;
    }

    public static void onStartUp() {
        for (int i = 0; i < rates.length(); i++) {
            JSONObject jsonObject = rates.getJSONObject(i);
//            System.out.println("Keys in item " + (i + 1) + ": " + jsonObject.keySet());

            // Iterate over keys
//            for (String key : jsonObject.keySet()) {
//                System.out.println(key + " -> " + jsonObject.get(key));
//            }
            Rate _rO = new Rate(null, null, null, 0, null, null);
            for (String key : jsonObject.keySet()) {
                System.out.println(key + " -> " + jsonObject.get(key));
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
                        // Calculate total weight
                        totalWeight += _rO.weight();
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
//                // Calculate total weight
//                if(Objects.equals(key, "weight")) {
//                    totalWeight += (int)jsonObject.get(key);
//                }
            }
            rObjects.add(_rO);
            System.out.println("------------------");
        }
        System.out.println("Weight so far > "+totalWeight);
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

//    public static GenItem selectItem(JSONObject itemRarity, List<GenItem> itemList, Map<String, Boolean> keyEvents) {
//        GenItem selectedItem = null;
//        Random gi_rand = new Random();
//
//        // Calculate total weight
//        int totalWeight = 0;
//        for (String key : itemRarity.keySet()) {
//            System.out.println("KEY > " + itemRarity.get(key));
//            totalWeight += itemRarity.get(key).getInt("weight");
//        }
//
//        // Weighted random selection
//        int wp = gi_rand.nextInt(totalWeight) + 1;
//        int rar = 0;
//        for (String key : itemRarity.keySet()) {
//            int weight = itemRarity.getJSONObject(key).getInt("weight");
//            if (wp <= weight) break;
//            rar++;
//            wp -= weight;
//        }
//
//        // Do-while equivalent
//        do {
//            List<GenItem> filteredItems = new ArrayList<>();
//            String targetType = new ArrayList<>(itemRarity.values()).get(rar).getString("type");
//
//            for (GenItem i : itemList) {
//                if (i.getRarityType().equals(targetType)) {
//                    filteredItems.add(i);
//                }
//            }
//
//            if (filteredItems.isEmpty()) continue;
//
//            selectedItem = filteredItems.get(gi_rand.nextInt(filteredItems.size()));
//
//            boolean allConditionsMet = true;
//            for (Map<String, Boolean> event : selectedItem.getRequiredKeyEvents()) {
//                for (Map.Entry<String, Boolean> entry : event.entrySet()) {
//                    if (!keyEvents.getOrDefault(entry.getKey(), false).equals(entry.getValue())) {
//                        System.out.println("A parameter of [" + selectedItem.getName() + "] is not fulfilled.");
//                        allConditionsMet = false;
//                        break;
//                    }
//                }
//                if (!allConditionsMet) break;
//            }
//
//            if (allConditionsMet) {
//                System.out.println("[SUCCESS] -> " + selectedItem.getName());
//                break;
//            }
//        } while (true);
//
//        return selectedItem;
//    }

    public static Rate determine(String args[]) {
        GenItem selectedItem = null;
        Random gi_rand = new Random();


        // Weighted random selection
        int wp = gi_rand.nextInt(totalWeight) + 1;
        int rar = 0;
        for (Rate key : rObjects) {
//            int weight = itemRarity.getJSONObject(key).getInt("weight");
            int weight = key.weight();
            if (wp <= weight) break;
            rar++;
            wp -= weight;
        }

        System.out.println("Chosen > "+rObjects.get(rar).tier());
        return rObjects.get(rar);

//
//        // Do-while equivalent
//        do {
//            List<GenItem> filteredItems = new ArrayList<>();
//            String targetType = new ArrayList<>(itemRarity.values()).get(rar).getString("type");
//
//            for (GenItem i : itemList) {
//                if (i.getRarityType().equals(targetType)) {
//                    filteredItems.add(i);
//                }
//            }
//
//            if (filteredItems.isEmpty()) continue;
//
//            selectedItem = filteredItems.get(gi_rand.nextInt(filteredItems.size()));
//
//            boolean allConditionsMet = true;
//            for (Map<String, Boolean> event : selectedItem.getRequiredKeyEvents()) {
//                for (Map.Entry<String, Boolean> entry : event.entrySet()) {
//                    if (!keyEvents.getOrDefault(entry.getKey(), false).equals(entry.getValue())) {
//                        System.out.println("A parameter of [" + selectedItem.getName() + "] is not fulfilled.");
//                        allConditionsMet = false;
//                        break;
//                    }
//                }
//                if (!allConditionsMet) break;
//            }
//
//            if (allConditionsMet) {
//                System.out.println("[SUCCESS] -> " + selectedItem.getName());
//                break;
//            }
//        } while (true);
//        return null;
    }
}
