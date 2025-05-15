package models.shop.enums;

public enum CarpenterShopProducts {
    WOOD("Wood", 10, Integer.MAX_VALUE),
    STONE("Stone", 20, Integer.MAX_VALUE);

    private final String name;
    private final int price;
    private final int dailyLimit;

    CarpenterShopProducts(String name, int price, int dailyLimit) {
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
