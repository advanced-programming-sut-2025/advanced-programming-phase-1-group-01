package com.stardew_valley.models.ingredients;

import com.stardew_valley.models.Item;

public class RewardItem implements Item {
    private final RewardItemType type;

    public RewardItem(RewardItemType type) {
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
}
