package models.crafting.enums;

import models.ingredients.CraftingIngredientType;
import models.ingredients.CraftingIngredients;

import java.util.List;
import java.util.Map;

public enum AllCraftedProductsType {
    HONEY(CraftingDevices.BEE_HOUSE, Map.of(List.of(new CraftingIngredients(null, 1)), 75), Map.of(List.of(new CraftingIngredients(null, 1)), 96), Map.of(List.of(new CraftingIngredients(null, 1)), 350)),
    CHEESE(CraftingDevices.CHEESE_PRESS, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.MILK, 1)), 100, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_MILK, 1)), 100), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.MILK, 1)), 3, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_MILK, 1)), 3), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.MILK, 1)), 230, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_MILK, 1)), 345)),
    GOAT_CHEESE(CraftingDevices.CHEESE_PRESS, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.GOAT_MILK, 1)), 100, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_GOAT_MILK, 1)), 100), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.GOAT_MILK, 1)), 3, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_GOAT_MILK, 1)), 3), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.GOAT_MILK, 1)), 400, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_GOAT_MILK, 1)), 600)),
    BEER(CraftingDevices.KEG, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WHEAT, 1)), 50), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WHEAT, 1)), 24), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WHEAT, 1)), 200)),
    VINEGAR(CraftingDevices.KEG, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.RICE, 1)), 13), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.RICE, 1)), 24), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.RICE, 1)), 100)),
    COFFEE(CraftingDevices.KEG, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.COFFEE_BEAN, 5)), 75), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.COFFEE_BEAN, 5)), 2), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.COFFEE_BEAN, 5)), 150)),
    JUICE(CraftingDevices.KEG, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.VEGETABLE, 1)), -1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.VEGETABLE, 1)), 96), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.VEGETABLE, 1)), 100 /*#*/)),
    MEAD(CraftingDevices.KEG, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.HONEY, 1)), 100), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.HONEY, 1)), 10), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.VEGETABLE, 1)), 300)),
    PALE_ALE(CraftingDevices.KEG, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.HOPS, 1)), 50), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.HOPS, 1)), 72), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.HOPS, 1)), 300)),
    WINE(CraftingDevices.KEG, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 1)), -1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 1)), 168), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 1)), 150)),
    DRIED_MASHROOMS(CraftingDevices.DEHYDRATOR, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.MUSHROOM, 5)), 50), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.MUSHROOM, 5)), -1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.MUSHROOM, 5)), 250)),
    DRIED_FRUIT(CraftingDevices.DEHYDRATOR, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 5)), 75), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 5)), -1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 5)), 200)),
    RAISINS(CraftingDevices.DEHYDRATOR, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.GRAPES, 5)), 125), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.GRAPES, 5)), -1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.GRAPES, 5)), 600)),
    COAL(CraftingDevices.CHARCOAL_KILN, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WOOD, 10)), 0), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WOOD, 10)), 1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WOOD, 10)), 50)),
    ORE_TO_BAR(CraftingDevices.FURNACE, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.ORE, 1), new CraftingIngredients(CraftingIngredientType.COAL, 1)), 0), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.ORE, 1), new CraftingIngredients(CraftingIngredientType.COAL, 1)), 4), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.ORE, 1), new CraftingIngredients(CraftingIngredientType.COAL, 1)), 200)),
    SMOKED_FISH(CraftingDevices.FISH_SMOKER, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FISH, 1), new CraftingIngredients(CraftingIngredientType.COAL, 1)), -1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FISH, 1), new CraftingIngredients(CraftingIngredientType.COAL, 1)), 1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FISH, 1), new CraftingIngredients(CraftingIngredientType.COAL, 1)), 300)),
    JELLY(CraftingDevices.PRESERVE_JAR, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 1)), -1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 1)), 72), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.FRUIT, 1)), 200)),
    PICKLES(CraftingDevices.PRESERVE_JAR, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.VEGETABLE, 1)), -1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.VEGETABLE, 1)), 6), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.VEGETABLE, 1)), 230)),
    OIL(CraftingDevices.OIL_MAKER, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.CORN, 1)), 13, List.of(new CraftingIngredients(CraftingIngredientType.SUNFLOWER_SEEDS, 1)), 13, List.of(new CraftingIngredients(CraftingIngredientType.SUNFLOWER, 1)), 13), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.CORN, 1)), 6, List.of(new CraftingIngredients(CraftingIngredientType.SUNFLOWER_SEEDS, 1)), 24, List.of(new CraftingIngredients(CraftingIngredientType.SUNFLOWER, 1)), 1), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.CORN, 1)), 100, List.of(new CraftingIngredients(CraftingIngredientType.SUNFLOWER_SEEDS, 1)), 100, List.of(new CraftingIngredients(CraftingIngredientType.SUNFLOWER, 1)), 100)),
    TRUFFLE_OIL(CraftingDevices.OIL_MAKER, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.TRUFFLE, 1)), 38), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.TRUFFLE, 1)), 6),  Map.of(List.of(new CraftingIngredients(CraftingIngredientType.TRUFFLE, 1)), 1065)),
    DINOSAUR_MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.DINOSAUR_EGG, 1)), 125), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.DINOSAUR_EGG, 1)), 3), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.DINOSAUR_EGG, 1)), 800)),
    DUCK_MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.DUCK_EGG, 1)), 75), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.DUCK_EGG, 1)), 3), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.DUCK_EGG, 1)), 37)),
    MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.EGG, 1)), 50, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_EGG, 1)), 50), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.EGG, 1)), 3, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_EGG, 1)), 3), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.EGG, 1)), 237, List.of(new CraftingIngredients(CraftingIngredientType.LARGE_EGG, 1)), 190)),
    CLOTH(CraftingDevices.LOOM, Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WOOL, 1)), 0), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WOOL, 1)), 4), Map.of(List.of(new CraftingIngredients(CraftingIngredientType.WOOL, 1)), 470));

    private final CraftingDevices craftingDevice;
    private final Map<List<CraftingIngredients>, Integer> energy;
    private final Map<List<CraftingIngredients>, Integer> processingTime;
    private final Map<List<CraftingIngredients>, Integer> sellingPrice;


    
    AllCraftedProductsType(CraftingDevices craftingDevice, Map<List<CraftingIngredients>, Integer> energy,
                           Map<List<CraftingIngredients>, Integer> processingTime, Map<List<CraftingIngredients>, Integer> sellingPrice) {
        this.craftingDevice = craftingDevice;
        this.energy = energy;
        this.processingTime = processingTime;
        this.sellingPrice = sellingPrice;

    }
}
