package models.animal;

import models.Random;

public enum ProductQuality {
    REGULAR(1.0),
    SILVER(1.25),
    GOLD(1.5),
    IRIDIUM(2.0);

    private final double priceCoefficient;

    ProductQuality(double priceCoefficient) {
        this.priceCoefficient = priceCoefficient;
    }

    public double getPriceCoefficient() {
        return priceCoefficient;
    }

    public static ProductQuality getRandomProductQuality() {
        int index = Random.rand(0, ProductQuality.values().length - 1);
        return ProductQuality.values()[index];
    }

    @Override
    public String toString() {
        return switch (this) {
            case REGULAR -> "Regular";
            case SILVER -> "Silver";
            case GOLD -> "Gold";
            case IRIDIUM -> "Iridium";
        };
    }
}
