package models.tool.enums;

public enum FishingPoleType {
   PRACTICAL(8), BAMBOO(8), FIBERGLASS(6), IRIDIUM(4);

   private int useEnergy;

    FishingPoleType(int useEnergy) {
        this.useEnergy = useEnergy;
    }
}