package models.Plants;

import models.character.NPC.NPC;
import models.character.NPC.TradeItem;

public class Plant implements TradeItem {
    private int amount;

    public Plant(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String getName() {
        return "CookingSystem";
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
