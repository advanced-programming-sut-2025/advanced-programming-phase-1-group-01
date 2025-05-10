package models.shop.enums;

public enum BlacksmithUpgrade {
    COPPER_TOOL("Copper Tool", "Copper Bar", 5, 2000, 1),
    STEEL_TOOL("Steel Tool", "Iron Bar", 5, 5000, 1),
    GOLD_TOOL("Gold Tool", "Gold Bar", 5, 10000, 1),
    IRIDIUM_TOOL("Iridium Tool", "Iridium Bar", 5, 25000, 1),

    COPPER_TRASH("Copper Trash Can", "Copper Bar", 5, 1000, 1),
    STEEL_TRASH("Steel Trash Can", "Iron Bar", 5, 2500, 1),
    GOLD_TRASH("Gold Trash Can", "Gold Bar", 5, 5000, 1),
    IRIDIUM_TRASH("Iridium Trash Can", "Iridium Bar", 5, 12500, 1);

    private final String name;
    private final String requiredItem;
    private final int requiredAmount;
    private final int price;
    private final int dailyLimit;

    BlacksmithUpgrade(String name, String requiredItem, int requiredAmount, int cost, int dailyLimiteed) {
        this.name = name;
        this.requiredItem = requiredItem;
        this.requiredAmount = requiredAmount;
        this.price = cost;
        this.dailyLimit = cost;
    }

    public String getName() {
        return name;
    }

    public String getRequiredItem() {
        return requiredItem;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
