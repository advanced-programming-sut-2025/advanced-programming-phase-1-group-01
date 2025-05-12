package models.crafting;

import models.Material;
import models.character.player.Inventory;

import java.util.List;

public interface Device {
    List<Material> getRequiredMaterials();
    boolean canCraft(Inventory inventory);
    void craft(Inventory inventory);
    int getEnergyCost();
}
