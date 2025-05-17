package models.shop.enums;

public enum ShopCommands {

    SHOW_ALL_PRODUCTS("show all products"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    ;

    private final String regex;

    ShopCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}

