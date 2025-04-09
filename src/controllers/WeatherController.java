package controllers;

import models.Position;
import models.Result;
import models.data.Repository;

public class WeatherController extends Controller {
    public WeatherController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private void handleThorHits() {

    }

    private Position chooseRandomPosition() {
        return null;
    }

    private Result handleCheatThor() {
        return null;
    }

    private Result showWeather() {
        return null;
    }

    private Result weatherForecast() {
        return null;
    }

    private Result cheatWeatherSet() {
        return null;
    } // sets tomorrow weather
}
