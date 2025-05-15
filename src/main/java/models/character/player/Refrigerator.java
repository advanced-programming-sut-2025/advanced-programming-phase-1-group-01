package models.character.player;

import models.Item;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Refrigerator {
    private final Map<Item, Integer> items;
    private Player player;

    public Refrigerator(Player player) {
        this.player = player;
        items = new HashMap<>();
    }

    public boolean addItem(Item item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
        return true;
    }

    public boolean removeItem(Item item, int quantity) {
        if (!items.containsKey(item)) return false;

        int currentQty = items.get(item);
        if (quantity > currentQty) return false;

        if (quantity == currentQty) items.remove(item);
        else items.put(item, currentQty - quantity);

        return true;
    }

    public boolean containsItem(Item item) {
        return items.containsKey(item);
    }

    public boolean containsItem(Item item, int quantity) {
        return items.getOrDefault(item, 0) >= quantity;
    }

    public int getQuantity(String item) {
        return items.getOrDefault(item, 0);
    }
}
