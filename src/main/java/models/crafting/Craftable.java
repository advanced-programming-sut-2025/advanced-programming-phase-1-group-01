package models.crafting;

import models.character.player.Player;
import models.crafting.enums.AllCraftedProductsType;

public interface Craftable {
    boolean canCraft(AllCraftedProductsType product, Player player);
    void craftProduct(AllCraftedProductsType product, Player player);
}
