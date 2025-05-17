package models.shop;

import models.Item;

public class CarpenterShopBuildingsItem implements Item {
    private final String name;
    private final int cost;
    private final int woodRequired;
    private final int stoneRequired;
    private final int width;
    private final int height;
    private final int dailyLimit;

    public CarpenterShopBuildingsItem(String name, int cost, int woodRequired, int stoneRequired, int width, int height, int dailyLimit) {
        this.name = name;
        this.cost = cost;
        this.woodRequired = woodRequired;
        this.stoneRequired = stoneRequired;
        this.width = width;
        this.height = height;
        this.dailyLimit = dailyLimit;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return cost;
    }
}
