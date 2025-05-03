package models.crafting;

import models.character.NPC.TradeItem;

public class CraftingSystem extends TradeItem {
    private int amount;

    public CraftingSystem(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}