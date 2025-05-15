package models;

import java.util.*;
import java.util.Map;
import models.animal.AnimalProductType;
import models.enums.OtherIngredients;
import models.ingredients.Ingredient;

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
