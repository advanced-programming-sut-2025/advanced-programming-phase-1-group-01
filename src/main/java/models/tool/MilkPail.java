package models.tool;

import models.enums.Direction;

public class MilkPail extends Tool {
    private static final int INITIAL_MILK_PAIL_USE_ENERGY = 4;

    @Override
    public int getBaseEnergyCost() {
        return INITIAL_MILK_PAIL_USE_ENERGY;
    }

    @Override
    public void use(Direction direction) {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
