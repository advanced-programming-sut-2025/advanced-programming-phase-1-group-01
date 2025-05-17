package models.tool;

import models.enums.Direction;
import models.tool.enums.AxeType;

// related to foraging
public class Axe extends Tool {
    private AxeType type;

    public Axe() {
        name = "axe";
    }

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

    @Override
    public void upgrade() {
        switch (type) {
            case PRIMARY -> type = AxeType.COPPER;
            case COPPER -> type = AxeType.IRON;
            case IRON -> type = AxeType.GOLD;
            case GOLD -> type = AxeType.IRIDIUM;
        }
    }

    public AxeType getType() {
        return type;
    }
}