package models.tool;

import models.enums.Direction;
import models.tool.enums.BackpackType;

public class Backpack extends Tool {
    private BackpackType type;

    @Override
    public int getBaseEnergyCost() {
        return 0;
    }

    @Override
    public void use(Direction direction) {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
