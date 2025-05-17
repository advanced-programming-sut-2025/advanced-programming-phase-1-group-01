package models.shop.enums;

public enum PierreCommands {
    SHOW_ALL_PRODUCTS("show all products"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products"),
    PIERRE_STORE("purchase pierre (.+?) -n (.+?)"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    ;

    private final String regex;

    PierreCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
