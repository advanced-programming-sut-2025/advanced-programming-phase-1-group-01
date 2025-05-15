package models.crafting;

import models.Item;

import java.util.Map;

public record CraftingRecipe(String name, Map<String, Integer> ingredients, String ability, int level, int sellPrice) implements Item {
    @Override
    public String getName() {
        return name;
    }
}
