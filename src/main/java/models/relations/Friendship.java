package models.relations;

import models.Item;
import models.MessageEntry;
import models.character.Character;
import models.character.player.Player;
import models.dateTime.DateTime;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Friendship extends Relationship {
    private final Character firstFriend;
    private final Character secondFriend;

    private final Map<MessageEntry, Boolean> messages;
    private final Map<Integer, Gift> gifts;

    public static final int TALK_XP;
    public static final int DEAL_SUCCESS_XP;
    public static final int DEAL_FAILURE_XP;
    public static final int HUG_XP;

    static {
        TALK_XP = 20;
        DEAL_SUCCESS_XP = 50;
        DEAL_FAILURE_XP = 30;
        HUG_XP = 60;
    }

    public Friendship(Character p1, Character p2) {
        firstFriend = p1;
        secondFriend = p2;
        messages = new LinkedHashMap<>();
        gifts = new LinkedHashMap<Integer, Gift>();
    }

    public Character getFirstFriend() {
        return firstFriend;
    }

    public Character getSecondFriend() {
        return secondFriend;
    }

    public Character getFriend(Player p) {
        if (p != firstFriend && p != secondFriend) {
            return null;
        }

        if (p == firstFriend) {
            return secondFriend;
        }
        return firstFriend;
    }

    public Map<Integer, Gift> getGifts() {
        return gifts;
    }

    public void sendMessage(Player sender, String message) {
        messages.put(new MessageEntry(sender, message), false);
    }

    public Map<MessageEntry, Boolean> getMessages() {
        return messages;
    }

    public void addGift(Player sender, Player receiver, Item item, int amount, DateTime now) {
        Gift gift = new GiftBuilder()
                .setSender(sender)
                .setReceiver(receiver)
                .setItem(item)
                .setAmount(amount)
                .setSentTime(now)
                .build();
        gifts.put(gift.giftNumber(), gift);
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

    public Gift getGift(int giftNumber) {
        return gifts.get(giftNumber);
    }
}