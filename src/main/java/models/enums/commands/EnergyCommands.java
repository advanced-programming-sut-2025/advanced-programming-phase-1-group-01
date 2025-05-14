package models.enums.commands;

public enum EnergyCommands implements Command {
    ENERGY_SHOW("show energy"),
    CHEAT_ENERGY("energy set -v (.+?)"),
    ENERGY_UNLIMITED("energy unlimited"),
    ;

    private final String regex;

    EnergyCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }

}
