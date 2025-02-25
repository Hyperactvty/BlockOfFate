package com.hyperactvty.blockoffate;

import java.util.List;
import java.util.Map;

public class GenItem {
    private String name;
    private String rarityType;
    private List<Map<String, Boolean>> requiredKeyEvents;

    public GenItem(String name, String rarityType, List<Map<String, Boolean>> requiredKeyEvents) {
        this.name = name;
        this.rarityType = rarityType;
        this.requiredKeyEvents = requiredKeyEvents;
    }

    public String getName() {
        return name;
    }

    public String getRarityType() {
        return rarityType;
    }

    public List<Map<String, Boolean>> getRequiredKeyEvents() {
        return requiredKeyEvents;
    }
}
