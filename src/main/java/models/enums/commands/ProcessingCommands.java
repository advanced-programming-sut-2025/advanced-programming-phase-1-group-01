package models.enums.commands;

public enum ProcessingCommands implements Command {
    ARTISAN_USE("artisan use (?<artisanName>\\S+) (?<itemName>\\S+(?: \\S+))"),
    ARTISAN_GET("artisan get (?<artisanName>\\S+)");

    private final String regex;
    ProcessingCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
