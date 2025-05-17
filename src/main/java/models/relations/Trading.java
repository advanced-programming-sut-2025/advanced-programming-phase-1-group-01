package models.relations;

import models.MessageEntry;
import models.character.Character;
import models.character.player.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Trading extends Relationship {
    private final Character sender;
    private final Character receiver;
    private final Map<MessageEntry, Boolean> messages = new LinkedHashMap<>();
    private final Map<Integer, Trade> pendingTrades = new LinkedHashMap<>();
    private final Map<Integer, Trade> allTrades = new HashMap<>();

    public Trading(Character sender, Character receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public void sendMessage(Player sender, String message) {
        messages.put(new MessageEntry(sender, message), false);
    }

    public Map<MessageEntry, Boolean> getMessages() {
        return messages;
    }

    public void addPendingTrade(Trade trade) {
        pendingTrades.put(trade.getId(), trade);
        allTrades.put(trade.getId(), trade);
    }

    public Trade getPendingTrade(int id) {
        return pendingTrades.get(id);
    }

    public void removePendingTrade(int id) {
        pendingTrades.remove(id);
    }

    public Map<Integer, Trade> getPendingTrades() {
        return new LinkedHashMap<>(pendingTrades);
    }

    public Map<Integer, Trade> getAllTrades() {
        return new HashMap<>(allTrades);
    }
}