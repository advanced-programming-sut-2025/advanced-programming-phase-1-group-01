package models.crafting.enums;

import models.character.NPC.TradeItem;

import java.util.List;

public enum AllCraftedProductsType {
    HONEY(CraftingDevices.BEE_HOUSE, 75, 96, List.of(), 350, false),
    CHEESE(CraftingDevices.CHEESE_PRESS, 100, 3, List.of(), 230, false),
    GOAT_CHEESE(CraftingDevices.CHEESE_PRESS, 100, 3, List.of(), 345, false),
    BEER(CraftingDevices.KEG, 50, 24, List.of(), 200, false),
    VINEGAR(CraftingDevices.KEG, 13, 10, List.of(), 100, false),
    COFFEE(CraftingDevices.KEG, 75, 2, List.of(), 150, false),
    JUICE(CraftingDevices.KEG, 50, 96, List.of(), 100, false),
    MEAD(CraftingDevices.KEG, 100, 10, List.of(), 300, false),
    PALE_ALE(CraftingDevices.KEG, 50, 72, List.of(), 300, false),
    WINE(CraftingDevices.KEG, 20, 168, List.of(), 200, false),
    DRIED_MASHROOMS(CraftingDevices.DEHYDRATOR, 50, -1, List.of(), 200, false),
    DRIED_FRUIT(CraftingDevices.DEHYDRATOR, 75, -1, List.of(), 200, false),
    RAISINS(CraftingDevices.DEHYDRATOR, 125, -1, List.of(), 600, false),
    COAL(CraftingDevices.CHARCOAL_KILN, 0, 1, List.of(), 50, false),
    ORE_TO_BAR(CraftingDevices.FURNACE, 0, 4, List.of(), 50, true),
    SMOKED_FISH(CraftingDevices.FISH_SMOKER, 50, 1, List.of(), 50, false),
    JELLY(CraftingDevices.PRESERVE_JAR, 50, 72, List.of(), 200, false),
    PICKLES(CraftingDevices.PRESERVE_JAR, 75, 6, List.of(), 200, false),
    OIL(CraftingDevices.OIL_MAKER, 13, 18, List.of(), 100, false),
    TRUFFLE_OIL(CraftingDevices.OIL_MAKER, 38, 6, List.of(), 1065, false),
    DINOSAUR_MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, 125, 3, List.of(), 800, false),
    DUCK_MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, 75, 3, List.of(), 37, false),
    MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, 50, 3, List.of(), 190, false),
    CLOTH(CraftingDevices.LOOM, 0, 4, List.of(), 470, false);

    private final CraftingDevices craftingDevice;
    private final int energy;
    private final int hours;
    private final List<TradeItem> ingredients;
    private final int sellPrice;
    private final boolean isAnd;


    
    AllCraftedProductsType(CraftingDevices craftingDevice, int energy, int hours,
                           List<TradeItem> ingredients, int sellPrice, boolean isAnd) {
        this.craftingDevice = craftingDevice;
        this.energy = energy;
        this.hours = hours;
        this.ingredients = ingredients;
        this.sellPrice = sellPrice;
        this.isAnd = isAnd;
    }
}
