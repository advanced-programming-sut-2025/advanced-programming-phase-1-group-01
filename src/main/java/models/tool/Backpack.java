package models.tool;

import models.tool.enums.BackpackType;

public class Backpack extends Tool {
    private BackpackType type;

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
