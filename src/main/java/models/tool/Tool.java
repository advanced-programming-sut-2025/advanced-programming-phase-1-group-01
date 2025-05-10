package models.tool;

import models.InventoryItem;
import models.character.player.Inventory;

public abstract class Tool implements InventoryItem {
    protected Inventory inventory;
    protected String name;
    protected double baseEnergyCost;

    public double getEffectiveEnergyCost() {
        return inventory.getPlayer().getGame().getWeatherManager().getToolEnergyCostMultiplier() * baseEnergyCost;
    }

    public abstract void use();

    @Override
    public String getName() {
        return name;
    }
}