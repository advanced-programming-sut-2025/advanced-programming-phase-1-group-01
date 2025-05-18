package models;

import models.character.player.Player;

public record MessageEntry(Player sender, String message) {

    @Override
    public String toString() {
        return sender.getUser().getNickname() + ": " + message;
    }
}