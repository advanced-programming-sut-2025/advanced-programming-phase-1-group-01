package com.stardew_valley.models.shop;

import com.stardew_valley.models.shop.enums.MarnieRanchProducts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MarnieRanch {
    private final Map<MarnieRanchProducts, Integer> marnieRanchShopProducts = new HashMap<>();

    public MarnieRanch() {
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (MarnieRanchProducts product : MarnieRanchProducts.values()) {
            marnieRanchShopProducts.put(product, product.getDailyLimit());
        }
    }

    public Set<MarnieRanchProducts> getAllProducts() {
        return marnieRanchShopProducts.keySet();
    }

    public int getProductStock(MarnieRanchProducts item) {
        return marnieRanchShopProducts.getOrDefault(item, 0);
    }

    public void updateProductPurchase(MarnieRanchProducts item, int amount) {
        marnieRanchShopProducts.put(item, getProductStock(item) - amount);
    }
}
