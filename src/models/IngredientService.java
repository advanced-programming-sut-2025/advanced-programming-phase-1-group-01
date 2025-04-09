//yo nigga

package models;

import java.util.*;
import java.util.Map;
import models.animal.AnimalProduct;
import models.enums.OtherIngredients;

public class IngredientService {
    private static Map<String, Ingredient> ingredients = new HashMap<>();

    public IngredientService() {
        for (AnimalProduct a : AnimalProduct.values()) {
            ingredients.put(a.name(), a);
        }

        for (OtherIngredients a : OtherIngredients.values()) {
            ingredients.put(a.name(), a);
        }
    }

    public static Map<String, Ingredient> getIngredients() {
        return ingredients;
    }
}
