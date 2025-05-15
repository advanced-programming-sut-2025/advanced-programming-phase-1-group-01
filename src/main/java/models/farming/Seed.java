package models.farming;

import models.Item;

public class Seed implements Item {
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
}