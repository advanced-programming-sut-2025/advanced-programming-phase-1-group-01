package models.ingredients;

import models.Item;

public class QuestItem implements Item {
    private final QuestItemType type;

    public QuestItem(QuestItemType type) {
        this.type = type;
    }


    @Override
    public String getName() {
        return type.toString();
    }

    @Override
    public int getPrice() {
        return type.getPrice();
    }

}
