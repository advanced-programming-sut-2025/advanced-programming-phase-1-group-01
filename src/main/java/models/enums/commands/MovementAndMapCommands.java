package models.enums.commands;

public enum MovementAndMapCommands {
    WALK("walk -l <(?<X>\\S+),(?<Y>\\S+)>"),
    PRINT_MAP("print map -l <(?<X>\\S+),(?<Y>\\S+)> -s (?<size>\\S+)"),
    HELP_READING_MAP("help reading map"),
    ENTER_OTHERS_ROOM("enter others room -r (?<roomNumber>\\S+)");

    private final String regex;

    private MovementAndMapCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
