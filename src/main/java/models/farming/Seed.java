package models.farming;

import models.Item;
import models.building.TileObject;
import models.farming.SeedInfo;

public class Seed implements Item, TileObject {
    private final SeedInfo info;

    public Seed(SeedInfo info) {
        this.info = info;
    }

    public SeedInfo getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String getSymbol() {
        return info.getSymbol();
    }
}