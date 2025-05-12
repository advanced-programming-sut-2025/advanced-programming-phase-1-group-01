package models.animal;

public enum AnimalProductQuality {
    NORMAL(1.0),
    SILVER(1.25),
    GOLD(1.5),
    IRIDIUM(2.0);

    private final double priceCoefficient;

    AnimalProductQuality(double priceCoefficient) {
        this.priceCoefficient = priceCoefficient;
    }

    public double getPriceCoefficient() {
        return priceCoefficient;
    }
}
