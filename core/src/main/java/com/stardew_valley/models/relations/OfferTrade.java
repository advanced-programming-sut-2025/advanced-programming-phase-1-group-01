package com.stardew_valley.models.relations;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.User;

public class OfferTrade extends Trade {
    private final Item suggestionItem;
    private final int suggestionAmount;

    public OfferTrade(User sender, User receiver, Item item, int amount, Item suggestionitem, int suggestionamount) {
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
