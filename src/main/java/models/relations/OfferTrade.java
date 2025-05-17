package models.relations;

import models.Item;
import models.character.player.Player;

public class OfferTrade extends Trade {
    private final Item suggestionItem;
    private final int suggestionAmount;

    public OfferTrade(Player sender, Player receiver, Item item, int amount, Item suggestionitem, int suggestionamount) {
        super(sender, receiver, item, amount);
        this.suggestionItem = suggestionitem;
        this.suggestionAmount = suggestionamount;
    }

    public Item getSuggestionitem() {
        return suggestionItem;
    }

    public int getSuggestionamount() {
        return suggestionAmount;
    }
}
