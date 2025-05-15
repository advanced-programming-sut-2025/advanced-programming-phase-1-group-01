package models.ingredients;

import models.Item;

public class CraftingIngredients implements Item{
    private final CraftingIngredientType type;
    private final int quantity;

    public CraftingIngredients(CraftingIngredientType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return type.name();
    }

    public int getQuantity() {
        return quantity;
    }

    public CraftingIngredientType getType() {
        return type;
    }
}
