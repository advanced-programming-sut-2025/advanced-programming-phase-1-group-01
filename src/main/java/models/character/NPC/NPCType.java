package models.character.NPC;

import models.Item;
import models.enums.Emoji;

import java.util.ArrayList;
import java.util.List;

public enum NPCType {
    SEBASTIAN("Sebastian", List.of(TradeItemType.WOOL, TradeItemType.PUMPKIN_PIE, TradeItemType.PIZZA), Emoji.SEBASTIAN.getSymbol()),
    ABIGAIL("Abigail", List.of(TradeItemType.STONE, TradeItemType.IRON_ORE, TradeItemType.COFFEE), Emoji.ABIGAIL.getSymbol()),
    HARVEY("Harvey", List.of(TradeItemType.COFFEE, TradeItemType.PICKLE, TradeItemType.WINE), Emoji.HARVEY.getSymbol()),
    LEAH("Leah", List.of(TradeItemType.SALAD, TradeItemType.GRAPE, TradeItemType.WINE), Emoji.LEAH.getSymbol()),
    ROBIN("Robin", List.of(TradeItemType.SPAGHETTI, TradeItemType.WOOD, TradeItemType.IRON_BAR), Emoji.ROBIN.getSymbol()),;

    private final String name;
    private List<Item> favoriteItems = new ArrayList<>();
    private final String symbol;



    NPCType(String name, List<Item> favoriteItems, String symbol) {
        this.name = name;
        this.favoriteItems = favoriteItems;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isFavorite(Item item) {
        return favoriteItems.contains(item);
    }
}
