package models.tool.enums;

public enum FishingPoleType {
   PRACTICAL(8), BAMBOO(8), FIBERGLASS(6), IRIDIUM(4);

   private final int energyCost;

    FishingPoleType(int energyCost) {
        this.energyCost = energyCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }
}