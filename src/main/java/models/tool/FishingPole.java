package models.tool;

import models.enums.Direction;
import models.tool.enums.FishingPoleInfo;

// related to fishing
public class FishingPole extends Tool {
    FishingPoleInfo info;

    public FishingPole() {
        name = "fishing pole";
    }

    @Override
    public int getBaseEnergyCost() {
        return info.getEnergyCost();
    }

    public FishingPoleInfo getInfo() {
        return info;
    }

    @Override
    public void use(Direction direction) {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
