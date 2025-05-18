package models.shop;

import models.building.Building;

public class Shop extends Building {
    protected ShopkeeperName shopkeeperName;
    protected int x;
    protected int y;

    public Shop(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}