package models.enums.commands;

public enum ProcessingCommands {
    ARTISAN_USE("artisan use (?<artisanName>\\S+) (?<itemName>\\S+)"),
    ARTISAN_GET("artisan get (?<artisanName>\\S+)");

    private final String regex;
    ProcessingCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
