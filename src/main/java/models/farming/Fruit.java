package models.farming;

import models.Item;

public class Fruit implements Item {
    private final FruitInfo info;

    public Fruit(FruitInfo info) {
        this.info = info;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
