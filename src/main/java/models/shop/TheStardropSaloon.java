package models.shop;

import models.shop.enums.TheStardropSaloonProducts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TheStardropSaloon extends Shop {
    private final Map<TheStardropSaloonProducts, Integer> theStardropSaloonProducts = new HashMap<>();

    public TheStardropSaloon() {
        this.shopkeeperName = ShopkeeperName.GUS;
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (TheStardropSaloonProducts product : TheStardropSaloonProducts.values()) {
            theStardropSaloonProducts.put(product, product.getDailyLimit());
        }
    }

    public Set<TheStardropSaloonProducts> getAllProducts() {
        return theStardropSaloonProducts.keySet();
    }

    public int getProductStock(TheStardropSaloonProducts item) {
        return theStardropSaloonProducts.getOrDefault(item, 0);
    }

    public void updateProductPurchase(TheStardropSaloonProducts item, int amount) {
        theStardropSaloonProducts.put(item, getProductStock(item) - amount);
    }
}

