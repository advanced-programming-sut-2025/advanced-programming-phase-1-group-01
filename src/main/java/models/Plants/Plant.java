package models.Plants;

import models.character.NPC.NPC;
import models.character.NPC.TradeItem;

public class Plant extends TradeItem {
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
}
