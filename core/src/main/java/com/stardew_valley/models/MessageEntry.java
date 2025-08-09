package com.stardew_valley.models;

import com.stardew_valley.models.character.player.Player;

public record MessageEntry(Player sender, String message) {

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
}
