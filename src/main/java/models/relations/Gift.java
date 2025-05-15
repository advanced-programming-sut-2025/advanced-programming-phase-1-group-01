package models.relations;

import models.Item;
import models.character.player.Player;
import models.dateTime.DateTime;

public class Gift {
    private final Player sender;
    private final Player receiver;
    private final Item item;
    private final int amount;
    private final DateTime sentTime;
    private final int giftNumber;
    private int rate;

    private static int nextGiftNumber = 1;

    public Gift(Player sender, Player receiver, Item item, int amount, DateTime sentTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.item = item;
        this.amount = amount;
        this.sentTime = sentTime;
        this.giftNumber = nextGiftNumber++;
    }

    public Player sender() {
        return sender;
    }

    public Player receiver() {
        return receiver;
    }

    public Item item() {
        return item;
    }

    public int amount() {
        return amount;
    }

    public DateTime sentTime() {
        return sentTime;
    }

    public int giftNumber() {
        return giftNumber;
    }

    public static int nextGiftNumber() {
        return nextGiftNumber;
    }

    public int rate() {
        return rate;
    }

    public boolean setRate(int rate) {
        if (rate > 5 || rate < 1) return false;
        this.rate = rate;
        return true;
    }

    @Override
    public String toString() {
        return "%s sent %s, %d number of %s".formatted(sender.getUser().getUsername(), receiver.getUser().getUsername(), amount, item.getName());
    }

    public int getGiftXp() {
        if (rate == 0) {
            return 0;
        }

        return (rate - 3) * 30 + 15;
    }
}
