package com.stardew_valley.models.weather;

import com.stardew_valley.models.Game;
import com.stardew_valley.models.building.Greenhouse;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileObject;
import com.stardew_valley.models.dateTime.DateTime;
import com.stardew_valley.models.farming.Crop;
import com.stardew_valley.models.farming.Tree;

import java.util.List;
import java.util.Random;

public class WeatherManager {
    private final Game game;
    private Weather todayWeather;
    private Weather tomorrowWeather;

    public WeatherManager(Game game) {
        this.game = game;
        todayWeather = Weather.SUNNY;
        tomorrowWeather = Weather.SUNNY;
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
        TileObject object = tile.getObject();

        if (tile.getBuilding() instanceof Greenhouse) {
            return;
        }

        if (object instanceof Tree tree) {
            tree.burn();
        } else if (object instanceof Crop) {
            tile.removeObject();
        }
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
        DateTime tomorrow = game.getTimeManager().getNow().clone();
        tomorrow.incrementDay();
        List<Weather> currSeasonWeathers = tomorrow.getSeason().getWeathers();
        int index = random.nextInt(currSeasonWeathers.size());
        return currSeasonWeathers.get(index);
    }

    public void prepareNewDayWeather() {
        todayWeather = tomorrowWeather;
        tomorrowWeather = getRandomTomorrowWeather();
    }

    public double getToolEnergyCostMultiplier() {
        switch (todayWeather) {
            case RAINY, STORMY -> {
                return 1.5;
            }
            case SNOWY -> {
                return 2.0;
            }
            default -> {
                return 1.0;
            }
        }
    }

    public boolean isAGrazingDay() {
        return todayWeather == Weather.SUNNY;
    }
}
