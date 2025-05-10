package models.shop;

import models.shop.enums.JojaMartProducts;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JojaMart extends Shop {
    private final Map<JojaMartProducts, Integer> jojaMartProducts = new HashMap<>();

    public JojaMart() {
        this.shopkeeperName = ShopkeeperName.MORRIS;
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (JojaMartProducts product : JojaMartProducts.values()) {
            jojaMartProducts.put(product, product.getDailyLimit());
        }
    }

    public Set<JojaMartProducts> getAllProducts() {
        return jojaMartProducts.keySet();
    }

    public int getProductStock(JojaMartProducts product) {
        return jojaMartProducts.getOrDefault(product, 0);
    }

    public void updateProductPurchase(JojaMartProducts product, int amount) {
        jojaMartProducts.put(product, getProductStock(product) - amount);
    }
}
