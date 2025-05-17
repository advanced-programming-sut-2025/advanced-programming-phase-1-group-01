package models.enums.commands;

public enum DateTimeCommands implements Command {
    TIME("time"),
    DATE("date"),
    DATETIME("datetime"),
    DAY_OF_THE_WEEK("day of the week"),
    SEASON("season"),
    CHEAT_ADVANCE_TIME("cheat advance time (?<hour>\\S+) h"),
    CHEAT_ADVANCE_DATE("cheat advance date (?<day>\\S+) d"),
    ;

    private final String regex;

    DateTimeCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

}
