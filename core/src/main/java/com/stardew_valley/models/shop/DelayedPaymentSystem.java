package com.stardew_valley.models.shop;

import com.stardew_valley.models.character.player.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelayedPaymentSystem {
    private final Map<User, List<PendingSale>> pendingSales = new HashMap<>();

    public void addPendingSale(User player, String itemName, int count, int totalPrice) {
        pendingSales.computeIfAbsent(player, k -> new ArrayList<>())
                .add(new PendingSale(itemName, count, totalPrice));
    }

    public void processSalesForPlayer(User player) {
        List<PendingSale> sales = pendingSales.remove(player);
        if (sales == null) return;

        int totalGold = 0;
        for (PendingSale sale : sales) {
            totalGold += sale.getTotalPrice();
        }
        player.increaseCoins(totalGold);
    }
}

