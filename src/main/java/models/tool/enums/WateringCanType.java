package models.tool.enums;

public enum WateringCanType {
    PRIMARY(40, 5), COPPER(55, 4), IRON(70, 3), GOLD(85, 2), IRIDIUM(100, 1);

    private int wateringTileCapacity;
    private final int energyCost;

    WateringCanType(int wateringTileCapacity, int energyCost) {
        this.wateringTileCapacity = wateringTileCapacity;
        this.energyCost = energyCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }
}
