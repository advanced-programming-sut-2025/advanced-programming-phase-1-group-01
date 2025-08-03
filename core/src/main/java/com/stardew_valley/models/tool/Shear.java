package com.stardew_valley.models.tool;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.enums.Direction;

public class Shear extends Tool {
    private static final int INITIAL_SHEAR_USE_ENERGY = 4;

    public Shear(Inventory inventory) {
        super(inventory);
        name = "Shear";
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

    @Override
    public Texture getTexture() {
        return AssetManager.getAssetManager().getShear();
    }
}
