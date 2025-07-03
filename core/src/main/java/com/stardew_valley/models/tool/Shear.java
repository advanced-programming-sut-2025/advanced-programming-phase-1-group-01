package com.stardew_valley.models.tool;

import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.enums.Direction;

public class Shear extends Tool {
    private static final int INITIAL_SHEAR_USE_ENERGY = 4;

    public Shear(Inventory inventory) {
        super(inventory);
        name = "shear";
    }

    @Override
    public int getBaseEnergyCost() {
        return INITIAL_SHEAR_USE_ENERGY;
    }

    @Override
    public void use(Direction direction) {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    @Override
    public void upgrade() {

    }
}
