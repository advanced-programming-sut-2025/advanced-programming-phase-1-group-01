package models.shop.enums;

public enum CarpenterShopBuildings {
    BARN("Barn", 6000, 350, 150, 7, 4, 1),
    BIG_BARN("Big Barn", 12000, 450, 200, 7, 4, 1),
    DELUXE_BARN("Deluxe Barn", 25000, 550, 300, 7, 4, 1),
    COOP("Coop", 4000, 300, 100, 6, 3, 1),
    BIG_COOP("Big Coop", 10000, 400, 150, 6, 3, 1),
    DELUXE_COOP("Deluxe Coop", 20000, 500, 200, 6, 3, 1),
    WELL("Well", 1000, 0, 75, 3, 3, 1),
    SHIPPING_BIN("Shipping Bin", 250, 150, 0, 1, 1,-1);

    private final String name;
    private final int cost;
    private final int woodRequired;
    private final int stoneRequired;
    private final int width;
    private final int height;
    private final int dailyLimit;

    CarpenterShopBuildings(String name, int cost, int woodRequired, int stoneRequired,
                       int width, int height, int dailyLimit) {
        this.name = name;
        this.cost = cost;
        this.woodRequired = woodRequired;
        this.stoneRequired = stoneRequired;
        this.width = width;
        this.height = height;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getWoodRequired() {
        return woodRequired;
    }

    public int getStoneRequired() {
        return stoneRequired;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
