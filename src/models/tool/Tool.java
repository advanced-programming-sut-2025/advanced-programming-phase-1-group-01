package models.tool;

import models.Equipment;
import models.character.player.Inventory;

public abstract class Tool implements Equipment {
    protected Inventory inventory;
    protected double baseEnergyCost;

    public double getEffectiveEnergyCost() {
        return inventory.getPlayer().getGame().getWeatherManager().getToolEnergyCostMultiplier() * baseEnergyCost;
    }

    public abstract void use();
}