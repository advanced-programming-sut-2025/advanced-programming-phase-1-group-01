package com.stardew_valley.models.tool.enums;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.tool.FishingPole;

public enum FishingPoleInfo {
    TRAINING(8, 0.1), BAMBOO(8, 0.5), FIBERGLASS(6, 0.9), IRIDIUM(4, 1.2);

    private final int energyCost;
    private final double fishingFactor;

    FishingPoleInfo(int energyCost, double fishingFactor) {
        this.energyCost = energyCost;
        this.fishingFactor = fishingFactor;
    }

    public String getName() {
        return switch (this) {
            case TRAINING -> "Training Rod";
            case BAMBOO -> "Bamboo Rod";
            case FIBERGLASS -> "Fiberglass Rod";
            case IRIDIUM -> "Iridium Rod";
        };
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public double getFishingFactor() {
        return fishingFactor;
    }

    public Item toItem() {
        FishingPole pole = new FishingPole(Repository.getRepo().getCurrentGame().getCurrentPlayer().getInventory());
        pole.setInfo(this);
        return pole;
    }
}
