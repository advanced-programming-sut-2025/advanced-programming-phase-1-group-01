package models.enums;

public enum BanItem {
    WOOD("wood"),
    STONE("stone"),
    IRON_BAR("iron bar"),
    COPPER_BAR("copper bar");

    private final String name;

    BanItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isBanItem(String itemName) {
        for (BanItem item : values()) {
            if (item.name.equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }
}

