package models.ingredients;

public enum QuestItemType {
    IRON_BAR(100),
    PUMPKIN_PIE(50),
    STONE(80),
    IRON_GOLD(70),
    PUMPKIN(60),
    WHEAT(20),
    CORN(60),
    HOPS(50),
    GARLIC(40),
    CARROT(70),
    MILK(30),
    BIG_MILK(50),
    GOAT_MILK(60),
    BIG_GOAT_MILK(80),
    SHEEP_WOOL(100),
    TRUFFLE(300);

    private final int price;

    QuestItemType(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return switch (this) {
            case IRON_BAR -> "Iron Bar";
            case PUMPKIN_PIE -> "Pumpkin Pie";
            case STONE -> "Stone";
            case IRON_GOLD -> "Iron Gold";
            case PUMPKIN -> "Pumpkin";
            case WHEAT -> "Wheat";
            case CORN -> "Corn";
            case HOPS -> "Hops";
            case GARLIC -> "Garlic";
            case CARROT -> "Carrot";
            case MILK -> "Milk";
            case BIG_MILK -> "Big Milk";
            case GOAT_MILK -> "Goat Milk";
            case BIG_GOAT_MILK -> "Big Goat Milk";
            case SHEEP_WOOL -> "Sheep Wool";
            case TRUFFLE -> "Truffle";
        };
    }


    public String getName() {
        return toString();
    }

    public int getPrice() {
        return price;
    }
}
