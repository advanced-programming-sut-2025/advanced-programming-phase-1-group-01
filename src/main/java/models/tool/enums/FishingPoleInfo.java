package models.tool.enums;

public enum FishingPoleInfo {
    TRAINING(8, 0.1), BAMBOO(8, 0.5), FIBERGLASS(6, 0.9), IRIDIUM(4, 1.2);

    private final int energyCost;
    private final double fishingFactor;

    FishingPoleInfo(int energyCost, double fishingFactor) {
        this.energyCost = energyCost;
        this.fishingFactor = fishingFactor;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public double getFishingFactor() {
        return fishingFactor;
    }
}