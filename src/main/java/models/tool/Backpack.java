package models.tool;

import models.enums.Direction;
import models.tool.enums.BackpackType;

public class Backpack extends Tool {
    private BackpackType type;

    public Backpack() {
        name = "backpack";
    }

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

    @Override
    public void upgrade() {
        switch (type) {
            case SMALL -> inventory.setCapacity(BackpackType.BIG.getCapacity());
            case BIG -> inventory.setCapacity(BackpackType.DELUXE.getCapacity());
        }
    }

    public BackpackType getType() {
        return type;
    }
}
