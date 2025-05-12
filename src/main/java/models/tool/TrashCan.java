package models.tool;

import models.enums.Direction;

public class TrashCan extends Tool {

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
