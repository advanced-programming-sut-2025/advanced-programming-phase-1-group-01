package com.stardew_valley.models.character.NPC;

import com.stardew_valley.models.Item;

public interface TradeItem extends Item {
    int getAmount();
    void setAmount(int amount);
}
