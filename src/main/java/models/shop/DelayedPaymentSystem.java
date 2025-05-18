package models.shop;

import models.character.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelayedPaymentSystem {
    private final Map<Player, List<PendingSale>> pendingSales = new HashMap<>();

    public void addPendingSale(Player player, String itemName, int count, int totalPrice) {
        pendingSales.computeIfAbsent(player, k -> new ArrayList<>())
                .add(new PendingSale(itemName, count, totalPrice));
    }

    public void processSalesForPlayer(Player player) {
        List<PendingSale> sales = pendingSales.remove(player);
        if (sales == null) return;

        int totalGold = 0;
        for (PendingSale sale : sales) {
            totalGold += sale.getTotalPrice();
        }
        player.increaseCoins(totalGold);
    }
}

