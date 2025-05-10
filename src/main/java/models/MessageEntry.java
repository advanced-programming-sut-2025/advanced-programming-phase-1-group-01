package models;

import models.character.player.Player;

public record MessageEntry(Player sender, String message) {
}