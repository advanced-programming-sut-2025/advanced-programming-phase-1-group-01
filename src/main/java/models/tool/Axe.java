package models.tool;

import models.enums.Direction;
import models.tool.enums.AxeType;

// related to foraging
public class Axe extends Tool {
    private AxeType type;

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