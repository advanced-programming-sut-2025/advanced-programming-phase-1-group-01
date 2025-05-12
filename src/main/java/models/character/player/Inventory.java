package models.character.player;

import models.InventoryItem;
import models.tool.*;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final Player player;
    private int capacity;
    private final List<InventorySlot> slots;

    private static final int INVENTORY_CAPACITY = 50;

    public Inventory(Player player) {
        this.player = player;
        capacity = INVENTORY_CAPACITY;
        slots = new ArrayList<>();
    }

    public InventorySlot getSlot(String itemName) {
        for (InventorySlot slot : slots) {
            if (slot.getItem().getName().equals(itemName)) {
                if (slot.getQuantity() > 0) {
                    return slot;
                } else {
                    removeSlot(slot);
                }
            }
        }
        return null;
    }

    public void removeSlot(InventorySlot slot) {
        slots.remove(slot);
    }

    public boolean addItem(String itemName, int quantity) {
        for (InventorySlot slot : slots) {
            if (slot.getItem().getName().equals(itemName)) {
                slot.addQuantity(quantity);
                return true;
            }
        }

        if (slots.size() < capacity) {
            slots.add(new InventorySlot(this, itemName, quantity));
            return true;
        }

        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static InventoryItem getNewItem(String itemName) {
        switch (itemName.toLowerCase()) {
            case "axe":
                return new Axe();
            case "backpack":
                return new Backpack();
            case "fishing pole":
                return new FishingPole();
            case "hoe":
                return new Hoe();
            case "milk pail"
                : return new MilkPail();
            case "pickaxe":
                return new Pickaxe();
            case "scythe":
                return new Scythe();
            case "shear":
                return new Shear();
            case "trash can":
                return new TrashCan();
            case "watering can":
                return new WateringCan();
            default:
                return null;
        }
    }
}
