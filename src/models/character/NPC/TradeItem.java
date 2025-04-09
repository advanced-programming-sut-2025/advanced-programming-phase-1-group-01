package models.character.NPC;

import models.Ingredient;

public class TradeItem {
    private Ingredient ingredient;
    private int amount;

    TradeItem(Ingredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }
}
