package com.stardew_valley.models.enums;

public enum ArtisanType {
    BEE_HOUSE,
    CHARCOAL_KILN,
    CHEESE_PRESS,
    DEHYDRATOR,
    FISH_SMOKER,
    FURNACE,
    KEG,
    LOOM,
    MAYONNAISE_MACHINE,
    OIL_MAKER,
    PRESERVES_JAR;

    public static ArtisanType fromString(String input) {
        if (input == null) {
            return null;
        }

        String normalized = input.trim().toUpperCase().replace(" ", "_").replace("-", "_");

        for (ArtisanType type : ArtisanType.values()) {
            if (type.name().equals(normalized)) {
                return type;
            }
        }
        return null;
    }
}
