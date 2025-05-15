package models.crafting.enums;

import java.util.Map;

public enum AllCraftedProductsType {
    HONEY(CraftingDevices.BEE_HOUSE, Map.of(), Map.of(), Map.of()),
    CHEESE(CraftingDevices.CHEESE_PRESS, Map.of(), Map.of(), Map.of()),
    GOAT_CHEESE(CraftingDevices.CHEESE_PRESS, Map.of(), Map.of(), Map.of()),
    BEER(CraftingDevices.KEG, Map.of(), Map.of(), Map.of()),
    VINEGAR(CraftingDevices.KEG, Map.of(), Map.of(), Map.of()),
    COFFEE(CraftingDevices.KEG, Map.of(), Map.of(), Map.of()),
    JUICE(CraftingDevices.KEG, Map.of(), Map.of(), Map.of()),
    MEAD(CraftingDevices.KEG, Map.of(), Map.of(), Map.of()),
    PALE_ALE(CraftingDevices.KEG, Map.of(), Map.of(), Map.of()),
    WINE(CraftingDevices.KEG, Map.of(), Map.of(), Map.of()),
    DRIED_MASHROOMS(CraftingDevices.DEHYDRATOR, Map.of(), Map.of(), Map.of()),
    DRIED_FRUIT(CraftingDevices.DEHYDRATOR, Map.of(), Map.of(), Map.of()),
    RAISINS(CraftingDevices.DEHYDRATOR, Map.of(), Map.of(), Map.of()),
    COAL(CraftingDevices.CHARCOAL_KILN, Map.of(), Map.of(), Map.of()),
    ORE_TO_BAR(CraftingDevices.FURNACE, Map.of(), Map.of(), Map.of()),
    SMOKED_FISH(CraftingDevices.FISH_SMOKER, Map.of(), Map.of(), Map.of()),
    JELLY(CraftingDevices.PRESERVE_JAR, Map.of(), Map.of(), Map.of()),
    PICKLES(CraftingDevices.PRESERVE_JAR, Map.of(), Map.of(), Map.of()),
    OIL(CraftingDevices.OIL_MAKER, Map.of(), Map.of(), Map.of()),
    TRUFFLE_OIL(CraftingDevices.OIL_MAKER, Map.of(), Map.of(), Map.of()),
    DINOSAUR_MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, Map.of(), Map.of(), Map.of()),
    DUCK_MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, Map.of(), Map.of(), Map.of()),
    MAYONNAISE(CraftingDevices.MAYONNAISE_MACHINE, Map.of(), Map.of(), Map.of()),
    CLOTH(CraftingDevices.LOOM, Map.of(), Map.of(), Map.of());

    private final CraftingDevices craftingDevice;
    private final Map<String, Integer> energy;
    //@ add item to keys and hashmap
    private final Map<String, Integer> processingTime;
    private final Map<String, Integer> sellingPrice;


    
    AllCraftedProductsType(CraftingDevices craftingDevice, Map<String, Integer> energy,
                           Map<String, Integer> processingTime, Map<String, Integer> sellingPrice) {
        this.craftingDevice = craftingDevice;
        this.energy = energy;
        this.processingTime = processingTime;
        this.sellingPrice = sellingPrice;

    }
}
