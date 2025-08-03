package com.stardew_valley.models.character.player;

import com.stardew_valley.models.Item;

import com.stardew_valley.models.animal.AnimalProduct;
import com.stardew_valley.models.animal.AnimalProductType;
import com.stardew_valley.models.animal.ProductQuality;
import com.stardew_valley.models.cooking.CookingRecipes;
import com.stardew_valley.models.cooking.FoodsEnum;
import com.stardew_valley.models.crafting.*;
import com.stardew_valley.models.crafting.enums.AllCraftedProductsType;
import com.stardew_valley.models.crafting.enums.CraftingRecipes;
import com.stardew_valley.models.farming.*;
import com.stardew_valley.models.foraging.ForagingCrop;
import com.stardew_valley.models.foraging.ForagingCropInfo;
import com.stardew_valley.models.foraging.ForagingMineralInfo;
import com.stardew_valley.models.foraging.ForagingTreeInfo;
import com.stardew_valley.models.shop.enums.*;
import com.stardew_valley.models.ingredients.QuestItemType;
import com.stardew_valley.models.tool.*;
import com.stardew_valley.models.tool.enums.BackpackType;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final Player player;
    private int capacity;
    private final List<Slot> slots;
    private Slot equippedSlot;

    public Inventory(Player player) {
        this.player = player;
        capacity = BackpackType.SMALL.getCapacity();
        slots = new ArrayList<>();
        slots.add(new Slot(this, "trash can", 1));
        slots.add(new Slot(this, "scythe", 1));
        slots.add(new Slot(this, "hoe", 1));
        slots.add(new Slot(this, "pickaxe", 1));
    }
    public List<Slot> getSlots() {
        List<Slot> result = new ArrayList<>(slots);
        result.remove(0);
        return result;
    }

    public Slot getSlot(String itemName) {
        itemName = itemName.toLowerCase().trim();

        for (Slot slot : slots) {
            if (slot.getItem().getName().trim().equalsIgnoreCase(itemName)) {
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

    public void removeItem(String itemName) {
        for (Slot slot : slots) {
            if (slot.getItem().getName().equalsIgnoreCase(itemName)) {
                removeSlot(slot);
            }
        }
    }

    public boolean addItem(String itemName, int quantity) {
        itemName = itemName.toLowerCase().trim();

        for (Slot slot : slots) {
            if (slot.getItem() != null && slot.getItem().getName().trim().equalsIgnoreCase(itemName)) {
                slot.addQuantity(quantity);
                return true;
            }
        }

        if (hasCapacity()) {
            slots.add(new Slot(this, itemName, quantity));
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

    public boolean hasCapacity() {
        return slots.size() < capacity;
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
            case "egg" -> new AnimalProduct(AnimalProductType.EGG, ProductQuality.getRandomProductQuality());
            case "big egg" -> new AnimalProduct(AnimalProductType.BIG_EGG, ProductQuality.getRandomProductQuality());
            case "duck egg" -> new AnimalProduct(AnimalProductType.DUCK_EGG, ProductQuality.getRandomProductQuality());
            case "duck feather" -> new AnimalProduct(AnimalProductType.DUCK_FEATHER, ProductQuality.getRandomProductQuality());
            case "rabbit wool" -> new AnimalProduct(AnimalProductType.RABBIT_WOOL, ProductQuality.getRandomProductQuality());
            case "rabbit leg" -> new AnimalProduct(AnimalProductType.RABBIT_LEG, ProductQuality.getRandomProductQuality());
            case "dinosaur egg" -> new AnimalProduct(AnimalProductType.DINOSAUR_EGG, ProductQuality.getRandomProductQuality());
            case "honey" -> new CraftedProducts(AllCraftedProductsType.HONEY);
            case "cheese" -> new CraftedProducts(AllCraftedProductsType.CHEESE);
            case "goat cheese" -> new CraftedProducts(AllCraftedProductsType.GOAT_CHEESE);
            case "beer" -> new CraftedProducts(AllCraftedProductsType.BEER);
            case "vinegar" -> new CraftedProducts(AllCraftedProductsType.VINEGAR);
            case "coffee" -> new CraftedProducts(AllCraftedProductsType.COFFEE);
            case "juice" -> new CraftedProducts(AllCraftedProductsType.JUICE);
            case "mead" -> new CraftedProducts(AllCraftedProductsType.MEAD);
            case "pale ale" -> new CraftedProducts(AllCraftedProductsType.PALE_ALE);
            case "wine" -> new CraftedProducts(AllCraftedProductsType.WINE);
            case "dried mushrooms" -> new CraftedProducts(AllCraftedProductsType.DRIED_MASHROOMS);
            case "dried fruit" -> new CraftedProducts(AllCraftedProductsType.DRIED_FRUIT);
            case "raisins" -> new CraftedProducts(AllCraftedProductsType.RAISINS);
            case "coal" -> new CraftedProducts(AllCraftedProductsType.COAL);
            case "gold bar" -> new CraftedProducts(AllCraftedProductsType.GOLD_BAR);
            case "iron bar" -> new CraftedProducts(AllCraftedProductsType.IRON_BAR);
            case "copper bar" -> new CraftedProducts(AllCraftedProductsType.COPPER_BAR);
            case "smoked fish" -> new CraftedProducts(AllCraftedProductsType.SMOKED_FISH);
            case "jelly" -> new CraftedProducts(AllCraftedProductsType.JELLY);
            case "pickles" -> new CraftedProducts(AllCraftedProductsType.PICKLES);
            case "oil" -> new CraftedProducts(AllCraftedProductsType.OIL);
            case "truffle oil" -> new CraftedProducts(AllCraftedProductsType.TRUFFLE_OIL);
            case "dinosaur mayonnaise" -> new CraftedProducts(AllCraftedProductsType.DINOSAUR_MAYONNAISE);
            case "duck mayonnaise" -> new CraftedProducts(AllCraftedProductsType.DUCK_MAYONNAISE);
            case "mayonnaise" -> new CraftedProducts(AllCraftedProductsType.MAYONNAISE);
            case "cloth" -> new CraftedProducts(AllCraftedProductsType.CLOTH);

            default -> {

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

                for (ForagingCropInfo foragingCropInfo : ForagingCropInfo.values()) {
                    if (foragingCropInfo.getName().equalsIgnoreCase(itemName)) {
                        yield foragingCropInfo.toItem();
                    }
                }

                for (ForagingMineralInfo foragingMineralInfo : ForagingMineralInfo.values()) {
                    if (foragingMineralInfo.getName().equalsIgnoreCase(itemName)) {
                        yield foragingMineralInfo.toItem();
                    }
                }

                for (QuestItemType QuestItem : QuestItemType.values()) {
                    if (QuestItem.getName().equalsIgnoreCase(itemName)) {
                        yield QuestItem.toItem();
                    }
                }

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

                for (CarpenterShopProducts product : CarpenterShopProducts.values()) {
                    if (product.getName().equalsIgnoreCase(itemName)) {
                        yield product.toItem();
                    }
                }

                for (JojaMartProducts product : JojaMartProducts.values()) {
                    if (product.getName().equalsIgnoreCase(itemName)) {
                        yield product.toItem();
                    }
                }

                for (PierreGeneralStoreProducts product : PierreGeneralStoreProducts.values()) {
                    if (product.getName().equalsIgnoreCase(itemName)) {
                        yield product.toItem();
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
