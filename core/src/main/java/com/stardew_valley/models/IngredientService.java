package com.stardew_valley.models;

import java.util.*;
import java.util.Map;
import com.stardew_valley.models.animal.AnimalProductType;
import com.stardew_valley.models.enums.OtherIngredients;
import com.stardew_valley.models.ingredients.Ingredient;

public class IngredientService {
    private static Map<String, Ingredient> ingredients = new HashMap<>();

    public IngredientService() {
        for (AnimalProductType a : AnimalProductType.values()) {
//            ingredients.put(a.name(), a);
        }

        for (OtherIngredients a : OtherIngredients.values()) {
//            ingredients.put(a.name(), a);
        }
    }

    public static Map<String, Ingredient> getIngredients() {
        return ingredients;
    }
}
