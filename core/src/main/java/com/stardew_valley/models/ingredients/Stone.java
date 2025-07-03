package com.stardew_valley.models.ingredients;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.building.TileObject;
import com.stardew_valley.models.enums.StoneType;

public class Stone implements TileObject, Item {
    private final StoneType type;

    public Stone(StoneType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return type.toString();
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String getSymbol() {
        return type.getSymbol();
    }
}
