package models.shop;

import models.shop.enums.CarpenterShopProducts;
import models.shop.enums.CarpenterShopBuildings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CarpenterShop extends Shop {
    private final Map<CarpenterShopProducts, Integer> carpenterShopProducts = new HashMap<>();
    private final Map<CarpenterShopBuildings, Integer> carpenterShopBuildings = new HashMap<>();

    public CarpenterShop() {
        this.shopkeeperName = ShopkeeperName.ROBIN;
        resetDailyStock();
    }

    public void resetDailyStock() {
        for (CarpenterShopProducts product : CarpenterShopProducts.values()) {
            carpenterShopProducts.put(product, product.getDailyLimit());
        }

        for (CarpenterShopBuildings building : CarpenterShopBuildings.values()) {
            carpenterShopBuildings.put(building, building.getDailyLimit());
        }
    }

    public Set<CarpenterShopProducts> getAllProducts() {
        return carpenterShopProducts.keySet();
    }

    public Set<CarpenterShopBuildings> getAllBuildings() {
        return carpenterShopBuildings.keySet();
    }

    public int getProductAmount(CarpenterShopProducts product) {
        return carpenterShopProducts.getOrDefault(product, 0);
    }

    public int getBuildingProduct(CarpenterShopBuildings building) {
        return carpenterShopBuildings.getOrDefault(building, 0);
    }

    public void updateProductPurchase(CarpenterShopProducts product, int amount) {
        carpenterShopProducts.put(product, getProductAmount(product) - amount);
    }

    public void updateBuildingPurchase(CarpenterShopBuildings building) {
        carpenterShopBuildings.put(building, getBuildingProduct(building) - 1);
    }
}


