package models.relations;

import models.Item;
import models.character.player.Player;

public abstract class Trade {
    private final Player sender;
    private final Player receiver;
    private final Item item;
    private final int amount;
    private int id = 1;

    public Trade(Player sender, Player receiver, Item item, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.item = item;
        this.amount = amount;
        this.id = ++id;
    }

    public int getAmount() {
        return amount;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public Item getItem() {
        return item;
    }

    public int getId() {
        return id;
    }
}
