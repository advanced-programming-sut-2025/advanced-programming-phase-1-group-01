package models.crafting;

import models.Item;
import models.crafting.enums.AllCraftedProductsType;
import models.crafting.enums.CraftingDevices;

public class CraftedProducts implements Item {
    private final AllCraftedProductsType type;

    public CraftedProducts(AllCraftedProductsType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return type.toString().toLowerCase().replaceAll("_", " ");
    }

    @Override
    public int getPrice() {
        return type.getPrice();
    }
}
