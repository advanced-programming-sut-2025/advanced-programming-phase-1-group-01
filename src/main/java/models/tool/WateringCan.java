package models.tool;

import models.tool.enums.WateringCanType;

// related to farming.
public class WateringCan extends Tool {
    private WateringCanType type;

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
