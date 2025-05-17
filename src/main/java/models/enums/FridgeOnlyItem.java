package models.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum FridgeOnlyItem {
    EGG,
    LARGE_EGG,
    MILK,
    LARGE_MILK,
    GOAT_MILK,
    LARGE_GOAT_MILK,
    DUCK_EGG,
    WOOL,
    RABBIT_FOOT,
    TRUFFLE,
    CHEESE,
    GOAT_CHEESE,
    MAYONNAISE,
    DUCK_MAYONNAISE,
    OIL,
    HONEY,
    FRUIT,
    VEGETABLE,
    FLOUR,
    SUGAR,
    VINEGAR,
    WHEAT_FLOUR,
    CORN,
    TOMATO,
    ARTICHOKE,
    BLUE_JAZZ,
    EEL,
    CRAB,
    SARDINE,
    SALMON,
    SHRIMP,
    TUNA;

    private static final Set<String> fridgeOnlyNames = Arrays.stream(values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    public static boolean isFridgeItem(String name) {
        return fridgeOnlyNames.contains(name.toUpperCase().replace(" ", "_"));
    }
}


