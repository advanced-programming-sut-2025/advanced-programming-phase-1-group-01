package models.enums.commands;

public enum SellCommands {
    SELL("sell (.+?) -n (.+?)");

    private final String regex;

    SellCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
