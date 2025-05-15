package models.shop.enums;

public enum TheStardropSaloonProducts {
    BEER("Beer", 400, -1),
    SALAD("Salad", 220, -1),
    BREAD("Bread", 120, -1),
    SPAGHETTI("Spaghetti", 240, -1),
    PIZZA("Pizza", 600, -1),
    COFFEE("Coffee", 300, -1),
    HASHBROWNS_RECIPE("Hashbrowns Recipe", 50, 1),
    OMELET_RECIPE("Omelet Recipe", 100, 1),
    PANCAKES_RECIPE("Pancakes Recipe", 100, 1),
    BREAD_RECIPE("Bread Recipe", 100, 1),
    TORTILLA_RECIPE("Tortilla Recipe", 100, 1),
    PIZZA_RECIPE("Pizza Recipe", 150, 1),
    MAKI_ROLL_RECIPE("Maki Roll Recipe", 300, 1),
    TRIPLE_SHOT_ESPRESSO_RECIPE("Triple Shot Espresso Recipe", 5000, 1),
    COOKIE_RECIPE("Cookie Recipe", 300, 1);

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
}

