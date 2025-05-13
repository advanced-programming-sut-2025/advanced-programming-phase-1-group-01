package models.crafting;

import models.character.player.Inventory;
import models.crafting.enums.AllCraftedProductsType;

public abstract class CraftingDevice {
    protected String name;
    protected int energy;
    protected int hours;

    public CraftingDevice(String name, int energy, int hours) {
        this.name = name;
        this.energy = energy;
        this.hours = hours;
    }

    public abstract boolean canCraft(Inventory inventory, AllCraftedProductsType productType);

    public abstract void craftProduct(Inventory inventory, AllCraftedProductsType productType);

    public int getEnergyRequired() {
        return energy;
    }

    public int getCraftingTime() {
        return hours;
    }
}

