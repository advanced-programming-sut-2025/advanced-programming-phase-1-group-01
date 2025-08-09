package com.stardew_valley.models.relations;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.User;

public abstract class Trade {
    private final User sender;
    private final User receiver;
    private final Item item;
    private final int amount;
    private int index = 1;
    private int id;

    public Trade(User sender, User receiver, Item item, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.item = item;
        this.amount = amount;
        this.id = index++;
    }

    public int getAmount() {
        return amount;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public Item getItem() {
        return item;
    }

    public int getId() {
        return id;
    }
}
