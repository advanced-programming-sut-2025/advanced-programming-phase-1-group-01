package com.stardew_valley.models.tool;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.enums.Direction;

public abstract class Tool implements Item {
    protected Inventory inventory;
    protected String name;

    public Tool(Inventory inventory) {
        this.inventory = inventory;
    }

    public double getEffectiveEnergyCost() {
        return inventory.getPlayer().getGame().getWeatherManager().getToolEnergyCostMultiplier() * getBaseEnergyCost();
    }

    public abstract int getBaseEnergyCost();

    public abstract void use(Direction direction);

    public abstract void upgrade();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
