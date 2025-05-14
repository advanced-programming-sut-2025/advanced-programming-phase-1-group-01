package models.fish;

import models.Item;

public class Fish implements Item {
    private FishInfo info;

    @Override
    public String getName() {
        return info.getName();
    }
}
