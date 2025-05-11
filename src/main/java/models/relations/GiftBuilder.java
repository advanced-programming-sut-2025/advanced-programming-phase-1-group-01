package models.relations;

import models.InventoryItem;
import models.character.player.Player;
import models.dateTime.DateTime;

public class GiftBuilder {
    private Player sender;
    private Player receiver;
    private InventoryItem item;
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

    public GiftBuilder setItem(InventoryItem item) {
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