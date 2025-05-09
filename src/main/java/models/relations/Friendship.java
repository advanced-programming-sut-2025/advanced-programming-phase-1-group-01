package models.relations;

import models.MessageEntry;
import models.character.player.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Friendship extends Relationship {
    private final Player firstFriend;
    private final Player secondFriend;

    private final Map<MessageEntry, Boolean> messages;

    public Friendship(Player p1, Player p2) {
        firstFriend = p1;
        secondFriend = p2;
        messages = new LinkedHashMap<>();
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

    public void sendMessage(Player sender, String message) {
        messages.put(new MessageEntry(sender, message), false);
    }

    public Map<MessageEntry, Boolean> getMessages() {
        return messages;
    }
}