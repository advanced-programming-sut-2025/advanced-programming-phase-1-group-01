package com.stardew_valley.models.cooking;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;

public enum IngredientItems {
    WHEAT_FLOUR("Wheat Flour", 60, AssetManager.getAssetManager().getWheatFlour()),
    SUGAR("Sugar", 50, AssetManager.getAssetManager().getSugar()),
    CHEESE("Cheese", 90, AssetManager.getAssetManager().getCheese()),
    RICE("Rice", 50, AssetManager.getAssetManager().getRice()),
    FIBER("Fiber", 10, AssetManager.getAssetManager().getFiber()),
    COFFEE("Coffee", 120,AssetManager.getAssetManager().getCoffee()),
    MELON("Melon", 130, AssetManager.getAssetManager().getMelon()),
    RED_CABBAGE("Red Cabbage", 110, AssetManager.getAssetManager().getRedCabbage()),
    HASH_BROWNS("Hash Browns", 90, AssetManager.getAssetManager().getHashBrowns()),
    COPPER_ORE("Copper Ore", 30, AssetManager.getAssetManager().getCopperOre()),
    COAL("Coal", 50, AssetManager.getAssetManager().getCoal()),
    IRON_ORE("Iron Ore", 40, AssetManager.getAssetManager().getIronOre()),
    GOLD_ORE("Gold Ore", 60, AssetManager.getAssetManager().getGoldOre()),
    COPPER_BAR("Copper Bar", 90, AssetManager.getAssetManager().getCopperBar()),
    IRON_BAR("Iron Bar", 120, AssetManager.getAssetManager().getIronBar()),
    GOLD_BAR("Gold Bar", 180, AssetManager.getAssetManager().getGoldBar()),
    IRIDIUM_BAR("Iridium Bar", 300, AssetManager.getAssetManager().getIridiumBar()),
    IRIDIUM_ORE("Iridium Ore", 150, AssetManager.getAssetManager().getIridiumOre()),
    WOOD("Wood", 5, AssetManager.getAssetManager().getWood()),
    STONE("Stone", 5, AssetManager.getAssetManager().getStone()),
    ACORN("Acorn", 20, AssetManager.getAssetManager().getAcorn()),;


    private final String name;
    private final int price;
    private final Texture texture;

    IngredientItems(String name, int price, Texture texture) {
        this.name = name;
        this.price = price;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Texture getTexture() {
         return texture;
    }

    public Item toItem() {
        return new IngredientItem(this);
    }
}
