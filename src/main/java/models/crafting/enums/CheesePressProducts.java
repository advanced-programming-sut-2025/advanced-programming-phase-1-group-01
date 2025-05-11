package models.crafting.enums;

import models.ingredients.Ingredient;
import models.IngredientService;

import java.util.Map;

public enum CheesePressProducts { // implements Ingredient
    CHEESE(
            "Cheese",
            "It's your basic cheese.",
            100,
            "3 Hours",
            Map.of(
                    IngredientService.getIngredients().get("MILK"), 230,
                    IngredientService.getIngredients().get("BIG_MILK"), 345
            )
    ),
    GOAT_CHEESE(
            "Goat Cheese",
            "Soft cheese made from goat's milk.",
            100,
            "3 Hours",
            Map.of(
                    IngredientService.getIngredients().get("GOAT_MILK"), 400,
                    IngredientService.getIngredients().get("BIG_GOAT_MILK"), 600
            )
    );

    private final String name;
    private final String description;
    private final int energy;
    private final String processingTime;
    private final Map<Ingredient, Integer> sellPricesByIngredient;

    CheesePressProducts(String name, String description, int energy, String processingTime, Map<Ingredient, Integer> sellPricesByIngredient) {
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
