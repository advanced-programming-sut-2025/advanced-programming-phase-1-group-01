package com.stardew_valley.models.ingredients;

public enum RewardItemType {
    DELUXE_SCARECROW(500),
    BEE_HOUSE(300),
    DINNER_SALMON(150),
    IRIDIUM_SPRINKLER(800),
    QUARTZ(40),
    SALAD(30),
    DIAMOND(600);

    private final int price;

    RewardItemType(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
//        return switch (this) {
//            case DELUXE_SCARECROW -> "Deluxe Scarecrow";
//            case BEE_HOUSE -> "Bee House";
//            case DINNER_SALMON -> "Dinner Salmon";
//            case IRIDIUM_SPRINKLER -> "Iridium Sprinkler";
//            case QUARTZ -> "Quartz";
//            case SALAD -> "Salad";
//            case DIAMOND -> "Diamond";
//        };
        switch (this) {
            case DELUXE_SCARECROW:
                return "Deluxe Scarecrow";
            case BEE_HOUSE:
                return "Bee House";
            case DINNER_SALMON:
                return "Dinner Salmon";
            case IRIDIUM_SPRINKLER:
                return "Iridium Sprinkler";
            case QUARTZ:
                return "Quartz";
            case SALAD:
                return "Salad";
            case DIAMOND:
                return "Diamond";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public String getName() {
        return toString();
    }

    public int getPrice() {
        return price;
    }
}
