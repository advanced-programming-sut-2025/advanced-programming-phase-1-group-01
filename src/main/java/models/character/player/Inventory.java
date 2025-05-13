package models.character.player;

import models.Item;
import models.tool.*;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final Player player;
    private int capacity;
    private final List<Slot> slots;

    private static final int INVENTORY_CAPACITY = 50;

    public Inventory(Player player) {
        this.player = player;
        capacity = INVENTORY_CAPACITY;
        slots = new ArrayList<>();
    }

    public Slot getSlot(String itemName) {
        for (Slot slot : slots) {
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

    public void removeSlot(Slot slot) {
        slots.remove(slot);
    }

    public boolean addItem(String itemName, int quantity) {
        for (Slot slot : slots) {
            if (slot.getItem().getName().equals(itemName)) {
                slot.addQuantity(quantity);
                return true;
            }
        }

        if (slots.size() < capacity) {
            slots.add(new Slot(this, itemName, quantity));
            return true;
        }

        return false;
    }


    public Player getPlayer() {
        return player;
    }

    public int getCapacity() {
        return slots.size();
    }

    public boolean hasCapacity() {
        return slots.size() <= INVENTORY_CAPACITY;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static Item getNewItem(String itemName) {
        return switch (itemName.toLowerCase()) {
            case "axe" -> new Axe();
            case "backpack" -> new Backpack();
            case "fishing pole" -> new FishingPole();
            case "hoe" -> new Hoe();
            case "milk pail" -> new MilkPail();
            case "pickaxe" -> new Pickaxe();
            case "scythe" -> new Scythe();
            case "shear" -> new Shear();
            case "trash can" -> new TrashCan();
            case "watering can" -> new WateringCan();
            default -> null;
        };
    }
}
