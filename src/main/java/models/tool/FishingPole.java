package models.tool;

import models.enums.Direction;
import models.tool.enums.FishingPoleType;

// related to fishing
public class FishingPole extends Tool {
    FishingPoleType type;

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    @Override
    public void use(Direction direction) {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
