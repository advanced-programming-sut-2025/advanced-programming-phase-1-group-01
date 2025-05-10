package models.relations;

import models.InventoryItem;
import models.character.player.Player;
import models.dateTime.DateTime;

public record Gift(Player sender, Player receiver, InventoryItem item, int amount, DateTime dateTime, int rate, int giftNumber) {
    public static int nextGiftNumber = 1;

    @Override
    public String toString() {
        return "%s -> %s : %s".formatted(sender.getUser().getUsername(), receiver.getUser().getUsername(), item.getName());
    }
}
