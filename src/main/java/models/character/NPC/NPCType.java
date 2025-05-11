package models.character.NPC;

import java.util.ArrayList;
import java.util.List;

public enum NPCType {
    SEBASTIAN("Sebastian", List.of(TradeItemType.WOOL, TradeItemType.PUMPKIN_PIE, TradeItemType.PIZZA)),
    ABIGAIL("Abigail", List.of(TradeItemType.STONE, TradeItemType.IRON_ORE, TradeItemType.COFFEE)),
    HARVEY("Harvey", List.of(TradeItemType.COFFEE, TradeItemType.PICKLE, TradeItemType.WINE)),
    LEAH("Leah", List.of(TradeItemType.SALAD, TradeItemType.GRAPE, TradeItemType.WINE)),
    ROBIN("Robin", List.of(TradeItemType.SPAGHETTI, TradeItemType.WOOD, TradeItemType.IRON_BAR));

    private final String name;
    private List<TradeItemType> favoriteItems = new ArrayList<>();



    NPCType(String name, List<TradeItemType> favoriteItems) {
        this.name = name;
        this.favoriteItems = favoriteItems;
    }

    public String getName() {
        return name;
    }
}
