package com.stardew_valley.models.ingredients;

import com.stardew_valley.models.character.NPC.TradeItem;

public class Ingredient implements TradeItem {
    protected int amount;

    public Ingredient(int amount) {
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
        return Ingredient.class.getSimpleName();
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
