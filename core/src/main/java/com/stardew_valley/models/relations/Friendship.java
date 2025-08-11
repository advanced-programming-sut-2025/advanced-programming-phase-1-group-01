package com.stardew_valley.models.relations;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.MessageEntry;
import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.DateTime;
import com.stardew_valley.network.GameClient;
import com.stardew_valley.network.JsonUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Friendship extends Relationship {
    private transient final Character firstFriend;
    private transient final Character secondFriend;

    private final Map<MessageEntry, Boolean> messages;
    private final Map<Integer, Gift> gifts;

    private static final List<MessageEntry> publicMessages = new ArrayList<>();

    private int lastHugDay;
    private int lastTalkDay;
    private int lastGiftDay;
    private int lastTradeDay;
    private int lastRelation;

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
        gifts = new LinkedHashMap<>();
        lastHugDay = 0;
        lastTalkDay = 0;
        lastGiftDay = 0;
        lastTradeDay = 0;
        lastRelation = 0;
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
        GameClient.getInstance().sendMessageToFriend(((Player) getFriend(sender)).getUser().getUsername(), message);
    }

    public Map<MessageEntry, Boolean> getMessages() {
        return messages;
    }

    public void addGift(Player sender, Player receiver, String itemName, int amount) {
        Gift gift = new Gift.Builder()
                .setSender(sender)
                .setReceiver(receiver)
                .setItem(itemName)
                .setAmount(amount)
                .build();
        gifts.put(gift.giftNumber(), gift);

        GameClient.getInstance().sendGift(gift);
    }

    public List<Gift> getReceivedGifts(Player receiver) {
        List<Gift> receivedGifts = new ArrayList<>();
        for (Gift gift : gifts.values()) {
            if (gift.receiver().getUser().getUsername().equals(receiver.getUser().getUsername())) {
                receivedGifts.add(gift);
            }
        }
        return receivedGifts;
    }

    public Gift getGift(int giftNumber) {
        return gifts.get(giftNumber);
    }

    public int getLastHugDay() {
        return lastHugDay;
    }

    public void setLastHugDay(int lastHugDay) {
        this.lastHugDay = lastHugDay;
    }

    public void setLastRelation(int lastRelation) {
        this.lastRelation = lastRelation;
    }

    public int getLastRelation() {
        return lastRelation;
    }

    public void setLastTalkDay(int lastTalkDay) {
        this.lastTalkDay = lastTalkDay;
    }

    public int getLastTalkDay() {
        return lastTalkDay;
    }

    public void setLastGiftDay(int lastGiftDay) {
        this.lastGiftDay = lastGiftDay;
    }

    public int getLastGiftDay() {
        return lastGiftDay;
    }

    public void setLastTradeDay(int lastTradeDay) {
        this.lastTradeDay = lastTradeDay;
    }

    public int getLastTradeDay() {
        return lastTradeDay;
    }

    public String toJson() {
        return JsonUtils.getInstance().toJson(this);
    }

    public static Friendship fromJson(String json) {
        return JsonUtils.getInstance().fromJson(json, Friendship.class);
    }

//    public void updateMessages(Map<MessageEntry, Boolean> messages) {
//        this.messages.clear();
//        for (MessageEntry messageEntry : messages.keySet()) {
//            this.messages.put(messageEntry, messages.get(messageEntry));
//        }
//    }

    public static void sendPublicMessage(Player sender, String message) {
        publicMessages.add(new MessageEntry(sender, message));
        GameClient.getInstance().sendPublicMessage(message);

        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {
            String username = matcher.group(1);
            Player player = Repository.getRepo()
                .getUserByUsername(username)
                .getPlayer();

            if (player != null) {
                player.addNotification(sender, "tagged you in public chat!");
            }
        }
    }

    public static List<MessageEntry> getPublicMessages() {
        return publicMessages;
    }
}
