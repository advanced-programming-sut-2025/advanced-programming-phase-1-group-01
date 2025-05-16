package models.enums.commands;

public enum EnergyCommands implements Command {
    ENERGY_SHOW("energy show"),
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
