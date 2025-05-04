package models.tool;

import models.tool.enums.HoeType;

// related to farming.
public class Hoe extends Tool {
    HoeType type;

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
