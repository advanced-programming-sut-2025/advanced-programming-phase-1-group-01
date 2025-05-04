package models.tool.enums;

public enum WateringCanType {
    PRIMARY(40, 5), COPPER(55, 4), IRON(70, 3), GOLD(85, 2), IRIDIUM(100, 1);

    private int wateringTileCapacity;
    private int useEnergy;

    WateringCanType(int wateringTileCapacity, int useEnergy) {
        this.wateringTileCapacity = wateringTileCapacity;
        this.useEnergy = useEnergy;
    }
}
