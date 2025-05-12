package models.tool;

import models.enums.Direction;
import models.tool.enums.WateringCanType;

// related to farming.
public class WateringCan extends Tool {
    private WateringCanType type;

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
