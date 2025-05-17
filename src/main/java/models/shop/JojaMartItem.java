package models.shop;

import models.Item;
import models.dateTime.Season;

public class JojaMartItem implements Item {
    private String name;
    private int price;
    private Season season;

    public JojaMartItem(String name, int price, Season season) {
        this.name = name;
        this.price = price;
        this.season = season;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
