package com.stardew_valley.models.shop.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.shop.TheStardropSaloonItem;

public enum TheStardropSaloonProducts {
    BEER("beer", 400, 5, AssetManager.getAssetManager().getBeer(),true),
    SALAD("salad", 220, 5, AssetManager.getAssetManager().getSalad(), true),
    BREAD("bread", 120, 5, AssetManager.getAssetManager().getBread(), true),
    SPAGHETTI("pasta", 240, 5, AssetManager.getAssetManager().getSpaghetti(), true),
    PIZZA("pizza", 600, 5, AssetManager.getAssetManager().getPizza(), true),
    COFFEE("coffee", 300, 5, AssetManager.getAssetManager().getCoffee(), true),;

    private final String name;
    private final int price;
    private int dailyLimit;
    private final Texture texture;
    private boolean isAvailable;

    TheStardropSaloonProducts(String name, int price, int dailyLimit, Texture texture, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.texture = texture;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public TheStardropSaloonItem toItem() {
        return new TheStardropSaloonItem(name,price,texture);
    }
}

