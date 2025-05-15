package models.shop;

import models.shop.enums.MarnieRanchProducts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MarnieRanch extends Shop {
    private final Map<MarnieRanchProducts, Integer> marnieRanchShopProducts = new HashMap<>();

    public MarnieRanch() {
        this.shopkeeperName = ShopkeeperName.MARNIE;
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