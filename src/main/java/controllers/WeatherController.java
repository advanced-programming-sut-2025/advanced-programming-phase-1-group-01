package controllers;

import models.Position;
import models.Result;
import models.building.Tile;
import models.data.Repository;
import models.enums.commands.WeatherCommands;
import models.weather.Weather;

public class WeatherController extends Controller {
    public WeatherController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        WeatherCommands matchedCommand = null;

        for (WeatherCommands cmd : WeatherCommands.values()) {
            if (commandLine.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case CHEAT_THOR:
                String[] parts = commandLine.split("\\s+");
                int x, y;
                try {
                    x = Integer.parseInt(parts[3]);
                    y = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    return new Result(false, "x & y are invalid");
                }
                return handleCheatThor(new Position(x, y));
            case WEATHER:
                return showWeather();
            case WEATHER_FORECAST:
                return weatherForecast();
            case CHEAT_WEATHER_SET:
                String weatherStr = commandLine.split("\\s+")[3];
                Weather weather;
                try {
                    weather = Weather.valueOf(weatherStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    return new Result(false, "invalid weather");
                }
                return cheatWeatherSet(weather);
            case GREENHOUSE_BUILD:
                return handleGreenhouseBuildCommand();
        }

        return new Result(false, "invalid command");
    }

    private Result handleCheatThor(Position position) {
        Tile tile = repo.getCurrentGame().getCurrentPlayer().getFarm().getTile(position);
        repo.getCurrentGame().getWeatherManager().invokeThor(tile);
        return new Result(true, "cheat thor applied on <%d, %d> tile.".formatted(position.x(), position.y()));
    }

    private Result showWeather() {
        return new Result(true,
                repo.getCurrentGame().getWeatherManager().getTodayWeather().name().toLowerCase());
    }

    private Result weatherForecast() {
        Weather tomorrowWeather = repo.getCurrentGame().getWeatherManager().getTomorrowWeather();
        return new Result(true, tomorrowWeather.name().toLowerCase());
    }

    private Result cheatWeatherSet(Weather weather) {
        repo.getCurrentGame().getWeatherManager().setTomorrowWeather(weather);
        return new Result(true, "tomorrow weather set to " + weather.name().toLowerCase() + ".");
    }

    private Result handleGreenhouseBuildCommand() {
        return null;
    }
}
