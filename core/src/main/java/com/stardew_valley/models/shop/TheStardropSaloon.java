package com.stardew_valley.models.shop;

import com.stardew_valley.models.shop.enums.TheStardropSaloonProducts;

import java.util.HashMap;
import java.util.Map;

public class TheStardropSaloon {

    private final Map<TheStardropSaloonProducts, Integer> saloonProducts = new HashMap<>();

    public TheStardropSaloon() {
    resetDailyStock();}


    public void resetDailyStock() {
        for (TheStardropSaloonProducts product : TheStardropSaloonProducts.values()) {
            saloonProducts.put(product, product.getDailyLimit());
        }
    }

    public Map<TheStardropSaloonProducts, Integer> getAllProducts() {
        return saloonProducts;
    }

    public int getProductStock(TheStardropSaloonProducts product) {
        return saloonProducts.getOrDefault(product, 0);
    }

    public void updateProductPurchase(TheStardropSaloonProducts product, int amount) {
        saloonProducts.put(product, getProductStock(product) - amount);
    }
}
