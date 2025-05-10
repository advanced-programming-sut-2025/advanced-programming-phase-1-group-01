package models.shop;

import models.shop.enums.PierreGeneralStoreProducts;
import models.shop.enums.PierreGeneralStoreUpgrade;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PierreGeneralStore extends Shop {
    private final Map<PierreGeneralStoreProducts, Integer> pierreGeneralStoreProducts = new HashMap<>();
    private final Map<PierreGeneralStoreUpgrade, Integer> pierreGeneralStoreUpgrades = new HashMap<>();

    public PierreGeneralStore() {
        this.shopkeeperName = ShopkeeperName.PIERRE;
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (PierreGeneralStoreProducts product : PierreGeneralStoreProducts.values()) {
            pierreGeneralStoreProducts.put(product, product.getDailyLimit());
        }
        for (PierreGeneralStoreUpgrade upgrade : PierreGeneralStoreUpgrade.values()) {
            pierreGeneralStoreUpgrades.put(upgrade, upgrade.getDailyLimit());
        }
    }

    public Set<PierreGeneralStoreProducts> getAllProducts() {
        return pierreGeneralStoreProducts.keySet();
    }

    public Set<PierreGeneralStoreUpgrade> getAllUpgrades() {
        return pierreGeneralStoreUpgrades.keySet();
    }

    public int getProductStock(PierreGeneralStoreProducts product) {
        return pierreGeneralStoreProducts.getOrDefault(product, 0);
    }

    public int getUpgradeStock(PierreGeneralStoreUpgrade upgrade) {
        return pierreGeneralStoreUpgrades.getOrDefault(upgrade, 0);
    }

    public void updateProductPurchase(PierreGeneralStoreProducts product, int amount) {
        pierreGeneralStoreProducts.put(product, getProductStock(product) - amount);
    }

    public void updateUpgradePurchase(PierreGeneralStoreUpgrade upgrade) {
        pierreGeneralStoreUpgrades.put(upgrade, getUpgradeStock(upgrade) - 1);
    }
}

