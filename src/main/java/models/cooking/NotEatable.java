package models.cooking;

public enum NotEatable {
    WOOD("wood"),
    STONE("stone"),
    IRON_BAR("iron bar"),
    COPPER_BAR("copper bar");

    private final String name;

    NotEatable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isNotEatable(String itemName) {
        for (NotEatable item : values()) {
            if (item.name.equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }
}
