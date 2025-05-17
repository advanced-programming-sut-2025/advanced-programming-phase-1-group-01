package models.relations;

import models.Item;
import models.character.player.Player;

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
