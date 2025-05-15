package models.ingredients;

import models.character.NPC.TradeItem;
import models.weather.Weather;

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
}
