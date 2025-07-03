package com.stardew_valley.models.ingredients;

import com.stardew_valley.models.Item;

public class QuestItem implements Item {
    private final QuestItemType type;

    public QuestItem(QuestItemType type) {
        this.type = type;
    }


    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getPrice() {
        return type.getPrice();
    }

}
