package com.stardew_valley.models.character.player;

import java.util.HashMap;
import java.util.Map;

public class Refrigerator {
    private final Map<String, Integer> items;
    private Player player;

    public Refrigerator(Player player) {
        this.player = player;
        items = new HashMap<>();
    }

    public boolean addItem(String itemName, int quantity) {
        items.put(itemName, items.getOrDefault(itemName, 0) + quantity);
        return true;
    }

    public boolean removeItem(String itemName, int quantity) {
        if (!items.containsKey(itemName)) return false;

        int currentQty = items.get(itemName);
        if (quantity > currentQty) return false;

        if (quantity == currentQty) items.remove(itemName);
        else items.put(itemName, currentQty - quantity);

        return true;
    }

    public boolean containsItem(String itemName) {
        return items.containsKey(itemName);
    }

    public boolean containsItem(String itemName, int quantity) {
        return items.getOrDefault(itemName, 0) >= quantity;
    }

    public int getQuantity(String itemName) {
        return items.getOrDefault(itemName, 0);
    }

    public boolean refrigerateHasCapacity() {
        return items.size() < 10;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
