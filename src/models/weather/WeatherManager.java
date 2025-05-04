package models.weather;

import models.Game;
import models.building.Tile;
import models.dateTime.DateTime;

import java.util.List;
import java.util.Random;

public class WeatherManager {
    private final Game game;
    private Weather todayWeather;
    private Weather tomorrowWeather;

    public WeatherManager(Game game) {
        this.game = game;
        this.tomorrowWeather = Weather.SUNNY;
    }

    public Weather getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(Weather todayWeather) {
        this.todayWeather = todayWeather;
    }

    public Weather getTomorrowWeather() {
        return tomorrowWeather;
    }

    public void setTomorrowWeather(Weather tomorrowWeather) {
        this.tomorrowWeather = tomorrowWeather;
    }

    // breaks trees and eliminates products
    public void invokeThor(Tile tile) {

    }

    public void handleDailyThor() {
        if (game.getWeatherManager().getTodayWeather() == Weather.STORMY) {
            final int THORS_PER_DAY_IF_STORMY = 3;
            for (int i = 0; i < THORS_PER_DAY_IF_STORMY; i++) {
                Tile randomTile = game.getCurrentPlayer().getFarm().getRandomTile();
                invokeThor(randomTile);
            }
        }
    }

    Random random = new Random();
    public Weather getRandomTomorrowWeather() {
        DateTime tomorrow = game.getTimeManager().getNow().clone().advanceDay();
        List<Weather> currSeasonWeathers = tomorrow.getSeason().getWeathers();
        int index = random.nextInt(currSeasonWeathers.size());
        return currSeasonWeathers.get(index);
    }

    public void prepareNewDayWeather() {
        todayWeather = tomorrowWeather;
        tomorrowWeather = getRandomTomorrowWeather();
    }
}