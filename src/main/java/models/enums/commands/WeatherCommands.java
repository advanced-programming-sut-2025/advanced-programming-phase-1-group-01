package models.enums.commands;

public enum WeatherCommands implements Command {
    CHEAT_THOR("cheat Thor -l (.+?)"),
    WEATHER("weather"),
    WEATHER_FORECAST("weather forecast"),
    CHEAT_WEATHER_SET("cheat weather set (.+?)"),
    GREENHOUSE_BUILD("greenhouse build"),;

    private final String regex;

    WeatherCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
