package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;

public enum FruitInfo implements FarmingEnum {
    APRICOT("Apricot", 59, true, 38, AssetManager.getAssetManager().getApricot()),
    CHERRY("Cherry", 80, true, 38, AssetManager.getAssetManager().getCherry()),
    BANANA("Banana", 150, true, 75, AssetManager.getAssetManager().getBanana()),
    MANGO("Mango", 130, true, 100, AssetManager.getAssetManager().getMango()),
    ORANGE("Orange", 100, true, 38, AssetManager.getAssetManager().getOrange()),
    PEACH("Peach", 140, true, 38, AssetManager.getAssetManager().getPeach()),
    APPLE("Apple", 100, true, 38, AssetManager.getAssetManager().getApple()),
    POMEGRANATE("Pomegranate", 140, true, 38, AssetManager.getAssetManager().getPomegranate()),
    OAK_RESIN("Oak Resin", 150, false, 0, AssetManager.getAssetManager().getOakResin()),
    MAPLE_SYRUP("Maple Syrup", 200, false, 0, AssetManager.getAssetManager().getMapleSyrup()),
    PINE_TAR("Pine Tar", 100, false, 0, AssetManager.getAssetManager().getPineTar()),
    SAP("Sap", 2, true, -2, AssetManager.getAssetManager().getSap()),
    COMMON_MUSHROOM("Common Mushroom", 40, true, 38, AssetManager.getAssetManager().getCommonMushroom()),
    MYSTIC_SYRUP("Mystic Syrup", 1000, true, 500, AssetManager.getAssetManager().getMysticSyrup()),
    ;

    private final String name;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final Texture texture;

    FruitInfo(String name, int baseSellPrice, boolean isEdible, int energy, Texture texture) {
        this.name = name;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }

    public Texture getTexture() {
        return texture;
    }
}
