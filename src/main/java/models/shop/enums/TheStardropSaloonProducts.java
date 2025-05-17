package models.shop.enums;

import models.shop.TheStardropSaloonItem;

public enum TheStardropSaloonProducts {
    BEER("Beer", 400, -1),
    SALAD("Salad", 220, -1),
    BREAD("Bread", 120, -1),
    SPAGHETTI("Spaghetti", 240, -1),
    PIZZA("Pizza", 600, -1),
    COFFEE("Coffee", 300, -1),
    HASHBROWNS_RECIPE("Hashbrowns", 50, 1),
    OMELET_RECIPE("Omelet", 100, 1),
    PANCAKES_RECIPE("Pancakes", 100, 1),
    BREAD_RECIPE("Bread", 100, 1),
    TORTILLA_RECIPE("Tortilla", 100, 1),
    PIZZA_RECIPE("Pizza", 150, 1),
    MAKI_ROLL_RECIPE("Maki Roll", 300, 1),
    TRIPLE_SHOT_ESPRESSO_RECIPE("Triple Shot Espresso", 5000, 1),
    COOKIE_RECIPE("Cookie", 300, 1);

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

