package com.stardew_valley.models;

import com.stardew_valley.models.character.player.Player;

public record MessageEntry(Player sender, String message) {

    @Override
    public String toString() {
        return sender.getUser().getNickname() + ": " + message;
    }
}
