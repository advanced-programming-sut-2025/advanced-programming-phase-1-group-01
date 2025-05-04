package models.crafting.enums;

import models.ingredients.Ingredient;

import java.util.Map;

public enum BeeHouseProducts {
    Honey("Honey", "it's a sweet syrup produced by bees", 75, "4 Days", Map.of(null, 350));

    private final String name;
    private final String description;
    private final int energy;
    private final String processingTime;
    private final Map<Ingredient, Integer> sellPricesByIngredient;

    BeeHouseProducts(String name, String description, int energy, String processingTime, Map<Ingredient, Integer> sellPricesByIngredient) {
        this.name = name;
        this.description = description;
        this.energy = energy;
        this.processingTime = processingTime;
        this.sellPricesByIngredient = sellPricesByIngredient;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getEnergy() {
        return energy;
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public Map<Ingredient, Integer> getSellPricesByIngredient() {
        return sellPricesByIngredient;
    }
}
