package models.tool;

import models.tool.enums.PickaxeType;

// related to mining.
public class Pickaxe extends Tool {
    private PickaxeType type;

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
