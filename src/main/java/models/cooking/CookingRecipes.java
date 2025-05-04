package models.cooking;

import java.util.Map;

public enum CookingRecipes {
    FRIED_EGG("Fried egg", Map.of("egg", 1), null, 50, "35g", "Starter"),

    BAKED_FISH("Baked Fish", Map.of("Sardine", 1, "Salmon", 1, "wheat", 1), null, 75, "100g", "Starter"),

    SALAD("Salad", Map.of("leek", 1, "dandelion", 1), null, 113, "110g", "Starter"),

    OMELET("Omelet", Map.of("egg", 1, "milk", 1), null, 100, "125g", "Stardrop Saloon"),

    PUMPKIN_PIE("Pumpkin Pie", Map.of("pumpkin", 1, "wheat flour", 1, "milk", 1, "sugar", 1), null, 225, "385g", "Stardrop Saloon"),

    SPAGHETTI("Spaghetti", Map.of("wheat flour", 1, "tomato", 1), null, 75, "120g", "Stardrop Saloon"),

    PIZZA("Pizza", Map.of("wheat flour", 1, "tomato", 1, "cheese", 1), null, 150, "300g", "Stardrop Saloon"),

    TORTILLA("Tortilla", Map.of("corn", 1), null, 50, "50g", "Stardrop Saloon"),

    MAKI_ROLL("Maki Roll", Map.of("any fish", 1, "rice", 1, "fiber", 1), null, 100, "220g", "Stardrop Saloon"),

    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", Map.of("coffee", 3), "Max Energy + 100 (5 hours)", 200, "450g", "Stardrop Saloon"),

    COOKIE("Cookie", Map.of("wheat flour", 1, "sugar", 1, "egg", 1), null, 90, "140g", "Stardrop Saloon"),

    HASH_BROWNS("Hash Browns", Map.of("potato", 1, "oil", 1), "Farming (5 hours)", 90, "120g", "Stardrop Saloon"),

    PANCAKES("Pancakes", Map.of("wheat flour", 1, "egg", 1), "Foraging (11 hours)", 90, "80g", "Stardrop Saloon"),

    FRUIT_SALAD("Fruit Salad", Map.of("blueberry", 1, "melon", 1, "apricot", 1), null, 263, "450g", "Stardrop Saloon"),

    RED_PLATE("Red Plate", Map.of("red cabbage", 1, "radish", 1), "max Energy +50 (3 hours)", 240, "400g", "Stardrop Saloon"),

    BREAD("Bread", Map.of("wheat flour", 1), null, 50, "60g", "Stardrop Saloon"),

    SALMON_DINNER("Salmon Dinner", Map.of("salmon", 1, "Amaranth", 1, "Kale", 1), "Leah reward", 125, "300g", "Leah reward"),

    VEGETABLE_MEDLEY("Vegetable Medley", Map.of("tomato", 1, "beet", 1), "Foraging Level 2", 165, "120g", "Foraging Level 2"),

    FARMERS_LUNCH("Farmer's Lunch", Map.of("omelet", 1, "parsnip", 1), "Farming (5 hours)", 200, "150g", "Farming level 1"),

    SURVIVAL_BURGER("Survival Burger", Map.of("bread", 1, "carrot", 1, "eggplant", 1), "Foraging (5 hours)", 125, "180g", "Foraging level 3"),

    DISH_O_THE_SEA("Dish O' the Sea", Map.of("sardine", 2, "hash browns", 1), "Fishing (5 hours)", 150, "220g", "Fishing level 2"),

    SEAFORM_PUDDING("Seaform Pudding", Map.of("Flounder", 1, "midnight carp", 1), "Fishing (10 hours)", 175, "300g", "Fishing level 3"),

    MINERS_TREAT("Miner's Treat", Map.of("carrot", 2, "sugar", 1, "milk", 1), "Mining (5 hours)", 125, "200g", "Mining level 1");

    private final String name;
    private final Map<String, Integer> ingredients;
    private final String buff;
    private final int energy;
    private final String sellPrice;
    private final String source;

    CookingRecipes(String name, Map<String, Integer> ingredients, String buff, int energy, String sellPrice, String source) {
        this.name = name;
        this.ingredients = ingredients;
        this.buff = buff;
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.source = source;
    }
}
