package com.stardew_valley.models.farming;

import com.stardew_valley.models.Item;

public class Fertilizer implements Item {
    FertilizerType type;

    @Override
    public String getName() {
        return type.toString();
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
