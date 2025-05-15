package models.cooking;

import models.Item;

import java.util.Map;

public record CookingRecipe(String name, Map<String, Integer> ingredients, String buff, int energy, int sellPrice,
                            String source) implements Item {

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return -1;
    }
}
