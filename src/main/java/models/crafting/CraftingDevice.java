package models.crafting;

import models.character.player.Player;
import models.crafting.enums.AllCraftedProductsType;

public abstract class CraftingDevice implements Craftable {
    protected String name;
    protected int energyRequired;
    protected int workingTimeInHours;

    public CraftingDevice(String name, int energyRequired, int workingTimeInHours) {
        this.name = name;
        this.energyRequired = energyRequired;
        this.workingTimeInHours = workingTimeInHours;
    }

    public String getName() {
        return name;
    }

    public int getEnergyRequired() {
        return energyRequired;
    }

    public int getWorkingTimeInHours() {
        return workingTimeInHours;
    }

    public void logCrafting(String itemName) {
        System.out.println("Crafted: " + itemName + " using " + name);
    }

    public abstract boolean canCraft(AllCraftedProductsType product, Player player);
    public abstract void craftProduct(AllCraftedProductsType product, Player player);
}

