package com.stardew_valley.models.shop.enums;

import com.stardew_valley.models.shop.TheStardropSaloonItem;

public enum TheStardropSaloonProducts {
    BEER("beer", 400, -1),
    SALAD("salad", 220, -1),
    BREAD("bread", 120, -1),
    SPAGHETTI("spaghetti", 240, -1),
    PIZZA("pizza", 600, -1),
    COFFEE("coffee", 300, -1),
    HASHBROWNS_RECIPE("hashbrowns", 50, 1),
    OMELET_RECIPE("omelet", 100, 1),
    PANCAKES_RECIPE("pancakes", 100, 1),
    BREAD_RECIPE("bread", 100, 1),
    TORTILLA_RECIPE("tortilla", 100, 1),
    PIZZA_RECIPE("Pizza", 150, 1),
    MAKI_ROLL_RECIPE("maki poll", 300, 1),
    TRIPLE_SHOT_ESPRESSO_RECIPE("triple shot espresso", 5000, 1),
    COOKIE_RECIPE("cookie", 300, 1);

    private final String name;
    private final int price;
    private final int dailyLimit;

    TheStardropSaloonProducts(String name, int price, int dailyLimit) {
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public TheStardropSaloonItem toItem() {
        return new TheStardropSaloonItem(name,price);
    }
}

