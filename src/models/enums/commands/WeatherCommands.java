package models.enums.commands;

public enum WeatherCommands {
    CHEAT_THOR("cheat Thor -l (.+?)"),
    WEATHER("weather"),
    WEATHER_FORECAST("weather forecast"),
    CHEAT_WEATHER_SET("cheat weather set (.+?)");

    private final String regex;

    WeatherCommands(String regex) {
        this.regex = regex;
    }

    public String regex() {
        return regex;
    }
}
