package com.stardew_valley.models.relations;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.Player;

public class RequestTrade extends Trade {
    private final int price;

    public RequestTrade(Player sender, Player receiver, Item item, int amount, int price) {
        super(sender, receiver, item, amount);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
