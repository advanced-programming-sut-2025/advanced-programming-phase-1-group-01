package models.crafting;

import models.Material;
import models.character.player.Inventory;

import java.util.List;

public class PreservesJar extends CraftingSystem implements Device {
    public PreservesJar(int amount) {
        super(amount);
    }

    @Override
    public List<Material> getRequiredMaterials() {
        return List.of();
    }

    @Override
    public boolean canCraft(Inventory inventory) {
        return false;
    }

    @Override
    public void craft(Inventory inventory) {

    }

    @Override
    public int getEnergyCost() {
        return 0;
    }
}
