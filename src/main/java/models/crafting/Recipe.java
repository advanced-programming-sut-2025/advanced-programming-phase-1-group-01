package models.crafting;

import java.util.Map;

public class Recipe {
    private final String name;
    private final Map<String, Integer> ingredients;
    private final String ability;
    private final int level;
    private final int sellPrice;

    public Recipe(String name, Map<String, Integer> ingredients, String ability, int level, int sellPrice) {
        this.name = name;
        this.ingredients = ingredients;
        this.ability = ability;
        this.level = level;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public String getAbility() {
        return ability;
    }

    public int getLevel() {
        return level;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
