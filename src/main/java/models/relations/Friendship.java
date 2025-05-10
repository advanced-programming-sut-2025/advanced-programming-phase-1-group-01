package models.relations;

import models.InventoryItem;
import models.MessageEntry;
import models.character.player.Player;
import models.dateTime.DateTime;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Friendship extends Relationship {
    private final Player firstFriend;
    private final Player secondFriend;

    private final Map<MessageEntry, Boolean> messages;
    private final Map<String, Gift> gifts;

    public static final int TALK_XP;
    public static final int DEAL_SUCCESS_XP;
    public static final int DEAL_FAILURE_XP;

    static {
        TALK_XP = 20;
        DEAL_SUCCESS_XP = 50;
        DEAL_FAILURE_XP = 30;
    }

    public Friendship(Player p1, Player p2) {
        firstFriend = p1;
        secondFriend = p2;
        messages = new LinkedHashMap<>();
        gifts = new LinkedHashMap<>();
    }

    public Player getFirstFriend() {
        return firstFriend;
    }

    public Player getSecondFriend() {
        return secondFriend;
    }

    public Player getFriend(Player p) {
        if (p != firstFriend && p != secondFriend) {
            return null;
        }

        if (p == firstFriend) {
            return secondFriend;
        }
        return firstFriend;
    }

    public Map<String, Gift> getGifts() {
        return gifts;
    }

    public void sendMessage(Player sender, String message) {
        messages.put(new MessageEntry(sender, message), false);
    }

    public Map<MessageEntry, Boolean> getMessages() {
        return messages;
    }

    public void addGift(Player sender, Player receiver, InventoryItem item, int amount, DateTime now) {
        gifts.put(item.getName(), new GiftBuilder()
                .setSender(sender)
                .setReceiver(receiver)
                .setItem(item)
                .setAmount(amount)
                .setSentTime(now)
                .build());
    }

    public List<Gift> getReceivedGifts(Player receiver) {
        List<Gift> receivedGifts = new ArrayList<>();
        for (Gift gift : gifts.values()) {
            if (gift.receiver() == receiver) {
                receivedGifts.add(gift);
            }
        }
        return receivedGifts;
    }
}