package models.cooking;

import models.character.NPC.TradeItem;
import models.ingredients.Ingredient;

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
}