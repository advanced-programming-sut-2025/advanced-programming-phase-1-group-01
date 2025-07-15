package com.stardew_valley.models.cooking;

import com.stardew_valley.models.character.NPC.TradeItem;

public class CookingSystem implements TradeItem {
    private int amount;

    CookingSystem(int amount) {
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
