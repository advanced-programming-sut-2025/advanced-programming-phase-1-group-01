package models.ingredients;

import models.character.NPC.TradeItem;

public class Ingredient implements TradeItem {
    private int amount;

    public Ingredient(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
