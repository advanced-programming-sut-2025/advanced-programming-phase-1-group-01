package models.shop.enums;

public enum PierreGeneralStoreUpgrade {
    LARGE_PACK("Large Pack", 2000, null, 1),
    DELUXE_PACK("Deluxe Pack", 10000, LARGE_PACK, 1);

    private final String name;
    private final int price;
    private final PierreGeneralStoreUpgrade requirement;
    private final int dailyLimit;

    PierreGeneralStoreUpgrade(String name, int price, PierreGeneralStoreUpgrade requirement, int dailyLimit) {
        this.name = name;
        this.price = price;
        this.requirement = requirement;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public PierreGeneralStoreUpgrade getRequirement() {
        return requirement;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
