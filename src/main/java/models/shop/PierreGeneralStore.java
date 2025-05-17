package models.shop;

import models.shop.enums.PierreGeneralStoreProducts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PierreGeneralStore extends Shop {
    private final Map<PierreGeneralStoreProducts, Integer> pierreGeneralStoreProducts = new HashMap<>();

    public PierreGeneralStore() {
        this.shopkeeperName = ShopkeeperName.PIERRE;
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (PierreGeneralStoreProducts product : PierreGeneralStoreProducts.values()) {
            pierreGeneralStoreProducts.put(product, product.getDailyLimit());
        }
    }

    public Set<PierreGeneralStoreProducts> getAllProducts() {
        return pierreGeneralStoreProducts.keySet();
    }

    public int getProductStock(PierreGeneralStoreProducts product) {
        return pierreGeneralStoreProducts.getOrDefault(product, 0);
    }

    public void updateProductPurchase(PierreGeneralStoreProducts product, int amount) {
        pierreGeneralStoreProducts.put(product, getProductStock(product) - amount);
    }
}

