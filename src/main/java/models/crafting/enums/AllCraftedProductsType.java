package models.crafting.enums;

import models.ingredients.CraftingIngredientType;
import models.ingredients.CraftingIngredients;

import java.util.List;
import java.util.Map;

public enum AllCraftedProductsType {
    HONEY(350),
    CHEESE(230),
    GOAT_CHEESE(345),
    BEER(400),
    VINEGAR(600),
    COFFEE(200),
    JUICE(100),
    MEAD(150),
    PALE_ALE(150),
    WINE(200),
    DRIED_MASHROOMS(200),
    DRIED_FRUIT(150),
    RAISINS(600),
    COAL(50),
    GOLD_BAR(200),
    IRON_BAR(100),
    COPPER_BAR(150),
    SMOKED_FISH(120),
    JELLY(100),
    PICKLES(100),
    OIL(100),
    TRUFFLE_OIL(1065),
    DINOSAUR_MAYONNAISE(800),
    DUCK_MAYONNAISE(37),
    MAYONNAISE(180),
    CLOTH(470);

    private final int price;
    AllCraftedProductsType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
