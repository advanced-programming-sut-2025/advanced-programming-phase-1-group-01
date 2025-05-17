package models.character.player;

import models.Item;

import models.cooking.CookingRecipes;
import models.cooking.Foods;
import models.cooking.FoodsEnum;
import models.crafting.*;
import models.crafting.enums.CraftingRecipes;
import models.farming.*;
import models.foraging.ForagingTreeInfo;
import models.shop.CarpenterShopProductsItem;
import models.shop.enums.*;
import models.tool.*;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final Player player;
    private int capacity;
    private final List<Slot> slots;
    private Slot equippedSlot;

    private static final int INVENTORY_CAPACITY = 50;

    public Inventory(Player player) {
        this.player = player;
        capacity = INVENTORY_CAPACITY;
        slots = new ArrayList<>();
        slots.add(new Slot(this, "scythe", 1));
        slots.add(new Slot(this, "trash can", 1));
        slots.add(new Slot(this, "hoe", 1));
        slots.add(new Slot(this, "pickaxe", 1));
    }
    public List<Slot> getSlots() {
        return slots;
    }

    public Slot getSlot(String itemName) {
        for (Slot slot : slots) {
            if (slot.getItem().getName().equalsIgnoreCase(itemName)) {
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

    public Item getNewItem(String itemName) {
        return switch (itemName.trim().toLowerCase()) {
            case "axe" -> new Axe(this);
            case "backpack" -> new Backpack(this);
            case "fishing pole" -> new FishingPole(this);
            case "hoe" -> new Hoe(this);
            case "milk pail" -> new MilkPail(this);
            case "pickaxe" -> new Pickaxe(this);
            case "scythe" -> new Scythe(this);
            case "shear" -> new Shear(this);
            case "trash can" -> new TrashCan(this);
            case "watering can" -> new WateringCan(this);

            case "bee house" -> new BeeHouse();
            case "cheese press" -> new CheesePress();
            case "keg" -> new Keg();
            case "dehydrator" -> new Dehydrator();
            case "charcoal kiln" -> new CharcoalKlin();
            case "loom" -> new Loom();
            case "mayonnaise machine" -> new MayonnaiseMachine();
            case "oil maker" -> new OilMaker();
            case "preserves jar" -> new PreservesJar();
            case "fish smoker" -> new FishSmoker();
            case "furnace" -> new Furnace();

            default -> {
                for (CookingRecipes recipeEnum : CookingRecipes.values()) {
                    if (recipeEnum.getName().equalsIgnoreCase(itemName)) {
                        yield recipeEnum.toRecipe();
                    }
                }

                for (CraftingRecipes recipeEnum : CraftingRecipes.values()) {
                    if (recipeEnum.getName().equalsIgnoreCase(itemName)) {
                        yield recipeEnum.toRecipe();
                    }
                }

                for (FoodsEnum foodEnum : FoodsEnum.values()) {
                    if (foodEnum.getName().equalsIgnoreCase(itemName)) {
                        yield foodEnum.toFood();
                    }
                }

                for (BlacksmithProducts products : BlacksmithProducts.values()) {
                    if (products.getName().equalsIgnoreCase(itemName)) {
                        yield products.toItem();
                    }
                }

                for (MarnieRanchProducts products : MarnieRanchProducts.values()) {
                    if (products.getName().equalsIgnoreCase(itemName)) {
                        yield products.toItem();
                    }
                }

                for (TheStardropSaloonProducts products : TheStardropSaloonProducts.values()) {
                    if (products.getName().equalsIgnoreCase(itemName)) {
                        yield products.toItem();
                    }
                }

                for (CarpenterShopBuildings buildings : CarpenterShopBuildings.values()) {
                    if (buildings.getName().equalsIgnoreCase(itemName)) {
                        yield buildings.toItem();
                    }
                }

                for (CarpenterShopProducts product : CarpenterShopProducts.values()) {
                    if (product.getName().equalsIgnoreCase(itemName)) {
                        yield product.toItem();
                    }
                }

                for (SeedInfo seedInfo : SeedInfo.values()) {
                    if (seedInfo.getName().equalsIgnoreCase(itemName)) {
                        yield seedInfo.toItem();
                    }
                }

                for (ForagingTreeInfo foragingTreeInfo : ForagingTreeInfo.values()) {
                    if (foragingTreeInfo.getName().equalsIgnoreCase(itemName)) {
                        yield foragingTreeInfo.toItem();
                    }
                }

                for (CropInfo cropInfo : CropInfo.values()) {
                    if (cropInfo.getName().equalsIgnoreCase(itemName)) {
                        yield cropInfo.toItem();
                    }
                }

                yield null;
            }
        };
    }

    public Slot getEquippedSlot() {
        return equippedSlot;
    }

    public void setEquippedSlot(Slot equippedSlot) {
        this.equippedSlot = equippedSlot;
    }

    public List<Tool> getTools() {
        List<Tool> tools = new ArrayList<>();
        for (Slot slot : slots) {
            if (slot.getItem() instanceof Tool) {
                tools.add((Tool) slot.getItem());
            }
        }
        return tools;
    }
}
