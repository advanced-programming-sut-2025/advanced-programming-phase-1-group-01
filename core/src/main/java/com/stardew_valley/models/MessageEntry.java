package com.stardew_valley.models;

import com.stardew_valley.models.character.player.Player;

public class MessageEntry {
    private final transient Player sender;
    private final transient String message;

    public MessageEntry(Player sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public String toString() {
        return sender.getUser().getUsername() + ": " + message;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    public Player sender() {
        return sender;
    }

    public String message() {
        return message;
    }
}
