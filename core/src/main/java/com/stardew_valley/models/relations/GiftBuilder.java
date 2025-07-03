package com.stardew_valley.models.relations;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.dateTime.DateTime;

public class GiftBuilder {
    private Player sender;
    private Player receiver;
    private Item item;
    private int amount;
    private DateTime sentTime;

    public GiftBuilder setSender(Player sender) {
        this.sender = sender;
        return this;
    }

    public GiftBuilder setReceiver(Player receiver) {
        this.receiver = receiver;
        return this;
    }

    public GiftBuilder setItem(Item item) {
        this.item = item;
        return this;
    }

    public GiftBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public GiftBuilder setSentTime(DateTime sentTime) {
        this.sentTime = sentTime;
        return this;
    }

    public Gift build() {
        return new Gift(sender, receiver, item, amount, sentTime);
    }
}
