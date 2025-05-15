package models.character.NPC;

import models.Item;
import models.enums.Emoji;

import java.util.ArrayList;
import java.util.List;

public enum NPCType {
    SEBASTIAN("Sebastian", List.of(TradeItemType.WOOL, TradeItemType.PUMPKIN_PIE, TradeItemType.PIZZA), Emoji.DISGUISED_FACE.getSymbol()),
    ABIGAIL("Abigail", List.of(TradeItemType.STONE, TradeItemType.IRON_ORE, TradeItemType.COFFEE), Emoji.SMILING_SUNGLASSES.getSymbol()),
    HARVEY("Harvey", List.of(TradeItemType.COFFEE, TradeItemType.PICKLE, TradeItemType.WINE), Emoji.WORRIED_FACE.getSymbol()),
    LEAH("Leah", List.of(TradeItemType.SALAD, TradeItemType.GRAPE, TradeItemType.WINE), Emoji.ANGRY_FACE.getSymbol()),
    ROBIN("Robin", List.of(TradeItemType.SPAGHETTI, TradeItemType.WOOD, TradeItemType.IRON_BAR), Emoji.MONOCLE_FACE.getSymbol()),;

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
