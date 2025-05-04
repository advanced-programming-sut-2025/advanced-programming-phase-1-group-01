package models.tool;

import models.tool.enums.AxeType;

// related to foraging
public class Axe extends Tool {
    private AxeType type;

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}