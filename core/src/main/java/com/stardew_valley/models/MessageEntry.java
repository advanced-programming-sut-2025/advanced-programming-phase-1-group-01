package com.stardew_valley.models;

import com.stardew_valley.models.character.player.User;

public record MessageEntry(User sender, String message) {

    @Override
    public String toString() {
        return sender.getUser().getUsername() + ": " + message;
    }
}
