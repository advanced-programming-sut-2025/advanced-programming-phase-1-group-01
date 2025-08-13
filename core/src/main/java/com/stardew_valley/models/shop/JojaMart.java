package com.stardew_valley.models.shop;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArraySupplier;
import com.stardew_valley.models.shop.enums.JojaMartProducts;

import java.util.*;
import java.util.stream.Collectors;

public class JojaMart {
    private final Map<JojaMartProducts, Integer> jojaMartProducts = new HashMap<>();

    public JojaMart() {
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (JojaMartProducts product : JojaMartProducts.values()) {
            jojaMartProducts.put(product, product.getDailyLimit());
        }
    }

    public Map<JojaMartProducts, Integer> getAllProducts() {
        return jojaMartProducts;
    }

    public int getProductStock(JojaMartProducts product) {
        return jojaMartProducts.getOrDefault(product, 0);
    }

    public void updateProductPurchase(JojaMartProducts product, int amount) {
        jojaMartProducts.put(product, getProductStock(product) - amount);
    }
}
