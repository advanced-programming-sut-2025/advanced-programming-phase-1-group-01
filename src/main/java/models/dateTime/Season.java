package models.dateTime;

import models.weather.Weather;

import java.util.List;

public enum Season {
    SPRING(List.of(Weather.SUNNY, Weather.RAINY, Weather.STORMY)),
    SUMMER(List.of(Weather.SUNNY, Weather.RAINY, Weather.STORMY)),
    FALL(List.of(Weather.SUNNY, Weather.RAINY, Weather.STORMY)),
    WINTER(List.of(Weather.SNOWY, Weather.SUNNY)),
    SPECIAL(List.of());

    private final List<Weather> weathers;

    Season(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    @Override
    public String toString() {
        if (this == SPRING) {
            return "Spring";
        } else if (this == SUMMER) {
            return "Summer";
        } else if (this == FALL) {
            return "Fall";
        } else if (this == WINTER) {
            return "Winter";
        } else if (this == SPECIAL) {
            return "Special";
        } else {
            return super.toString();
        }
    }
}
