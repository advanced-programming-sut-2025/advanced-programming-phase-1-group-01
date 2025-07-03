package com.stardew_valley.models.shop;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.dateTime.Season;

public class PierreItem implements Item {
    private String name;
    private Season season;
    private final int inSeasonPrice;
    private final int offSeasonPrice;


    public PierreItem(String name, Season season,int inSeasonPrice, int offSeasonPrice) {
        this.name = name;
        this.season = season;
        this.inSeasonPrice = inSeasonPrice;
        this.offSeasonPrice = offSeasonPrice;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
