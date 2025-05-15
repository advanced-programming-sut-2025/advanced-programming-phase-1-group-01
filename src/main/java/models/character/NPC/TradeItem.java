package models.character.NPC;

import models.Item;

public interface TradeItem extends Item {
    int getAmount();
    void setAmount(int amount);
}