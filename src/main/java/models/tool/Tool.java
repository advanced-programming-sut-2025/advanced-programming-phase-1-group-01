package models.tool;

import models.Item;
import models.character.player.Inventory;
import models.enums.Direction;

public abstract class Tool implements Item {
    protected Inventory inventory;
    protected String name;

    public double getEffectiveEnergyCost() {
        return inventory.getPlayer().getGame().getWeatherManager().getToolEnergyCostMultiplier() * getBaseEnergyCost();
    }

    public abstract int getBaseEnergyCost();

    public abstract void use(Direction direction);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}