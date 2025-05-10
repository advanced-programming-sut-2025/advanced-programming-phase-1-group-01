package models.shop;

import models.shop.enums.BlacksmithProducts;
import models.shop.enums.BlacksmithUpgrade;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Blacksmith extends Shop {
    private final Map<BlacksmithProducts, Integer> blacksmithProducts = new HashMap<>();
    private final Map<BlacksmithUpgrade, Integer> blacksmithUpgrades = new HashMap<>();

    public Blacksmith() {
        this.shopkeeperName = ShopkeeperName.CLINT;
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (BlacksmithProducts product : BlacksmithProducts.values()) {
            blacksmithProducts.put(product, product.getDailyLimit());
        }
        for (BlacksmithUpgrade upgrade : BlacksmithUpgrade.values()) {
            blacksmithUpgrades.put(upgrade, upgrade.getDailyLimit());
        }
    }

    public Set<BlacksmithProducts> getAllProducts() {
        return blacksmithProducts.keySet();
    }

    public Set<BlacksmithUpgrade> getAllUpgrades() {
        return blacksmithUpgrades.keySet();
    }

    public int getProductStock(BlacksmithProducts product) {
        return blacksmithProducts.getOrDefault(product, 0);
    }

    public int getUpgradeStock(BlacksmithUpgrade upgrade) {
        return blacksmithUpgrades.getOrDefault(upgrade, 0);
    }

    public void updateProductPurchase(BlacksmithProducts product, int amount) {
        blacksmithProducts.put(product, getProductStock(product) - amount);
    }

    public void updateUpgradePurchase(BlacksmithUpgrade upgrade) {
        blacksmithUpgrades.put(upgrade, getUpgradeStock(upgrade) - 1);
    }
}
