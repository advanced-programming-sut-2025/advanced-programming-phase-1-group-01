package models.tool;

import models.character.player.Inventory;
import models.enums.Direction;
import models.tool.enums.AxeType;

// related to foraging
public class Axe extends Tool {
    private AxeType type;

    public Axe(Inventory inventory) {
        super(inventory);
        name = "axe";
        type = AxeType.PRIMARY;
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
}