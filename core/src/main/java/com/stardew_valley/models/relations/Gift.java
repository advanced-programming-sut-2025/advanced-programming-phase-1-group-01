package com.stardew_valley.models.relations;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.DateTime;

public class Gift {
    private transient final Player sender;
    private transient final Player receiver;
    private final String itemName;
    private final int amount;
    private final int giftNumber;
    private int rate;

    private static int nextGiftNumber = 1;

    public Gift(Player sender, Player receiver, String itemName, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.itemName = itemName;
        this.amount = amount;
        this.giftNumber = nextGiftNumber++;
    }

    public Gift(Player sender, Player receiver, String itemName, int amount, int giftNumber) {
        this.sender = sender;
        this.receiver = receiver;
        this.itemName = itemName;
        this.amount = amount;
        this.giftNumber = giftNumber;
    }

    public Player sender() {
        return sender;
    }

    public Player receiver() {
        return receiver;
    }

    public String itemName() {
        return itemName;
    }

    public int amount() {
        return amount;
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
        return "%s sent %s, %d number of %s".formatted(sender.getUser().getUsername(), receiver.getUser().getUsername(), amount, itemName);
    }

    public int getGiftXp() {
        return (rate - 3) * 30 + 15;
    }

    public String toJson() {
        Gift gift = this;

        JsonObject json = new JsonObject();
        json.addProperty("sender", sender.getUser().getUsername());
        json.addProperty("receiver", receiver.getUser().getUsername());
        json.addProperty("item", itemName);
        json.addProperty("amount", amount);
        json.addProperty("number", giftNumber);

        return new Gson().toJson(json);
    }

    public static Gift fromJson(String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

        String senderUN = json.has("sender") ? json.get("sender").getAsString() : "";
        String receiverUN = json.has("receiver") ? json.get("receiver").getAsString() : "";
        String itemName = json.has("item") ? json.get("item").getAsString() : "";
        int amount = json.has("amount") ? json.get("amount").getAsInt() : 0;
        int giftNumber = json.has("number") ? json.get("number").getAsInt() : 0;

        Player sender = Repository.getRepo().getUserByUsername(senderUN).getPlayer();
        Player receiver = Repository.getRepo().getUserByUsername(receiverUN).getPlayer();

        return new Gift(sender, receiver, itemName, amount, giftNumber);
    }

    public static class Builder {
        private Player sender;
        private Player receiver;
        private String item;
        private int amount;

        public Gift.Builder setSender(Player sender) {
            this.sender = sender;
            return this;
        }

        public Gift.Builder setReceiver(Player receiver) {
            this.receiver = receiver;
            return this;
        }

        public Gift.Builder setItem(String item) {
            this.item = item;
            return this;
        }

        public Gift.Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Gift build() {
            return new Gift(sender, receiver, item, amount);
        }
    }
}
