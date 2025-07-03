package com.stardew_valley.models.shop;

import com.stardew_valley.models.shop.enums.FishShopProducts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FishShop extends Shop {
    private final Map<FishShopProducts, Integer> fishShopProducts = new HashMap<>();

    public FishShop(int x, int y) {
        super(x,y);
        this.shopkeeperName = ShopkeeperName.WILLY;
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (FishShopProducts product : FishShopProducts.values()) {
            fishShopProducts.put(product, product.getDailyLimit());
        }
    }

    public Set<FishShopProducts> getAllProducts() {
        return fishShopProducts.keySet();
    }

    public int getProductStock(FishShopProducts product) {
        return fishShopProducts.getOrDefault(product, 0);
    }

    public void updateProductPurchase(FishShopProducts product) {
        fishShopProducts.put(product, getProductStock(product) - 1);
    }
}
