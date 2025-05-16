package models.dateTime;

import models.Random;
import models.farming.CropInfo;
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

    public List<CropInfo> getMixedSeeds() {
        return switch (this) {
            case SPRING -> List.of(CropInfo.CAULIFLOWER, CropInfo.PARSNIP, CropInfo.POTATO, CropInfo.BLUE_JAZZ, CropInfo.TULIP);
            case SUMMER -> List.of(CropInfo.CORN, CropInfo.HOT_PEPPER, CropInfo.RADISH, CropInfo.WHEAT, CropInfo.POPPY, CropInfo.SUNFLOWER, CropInfo.SUMMER_SPANGLE);
            case FALL -> List.of(CropInfo.ARTICHOKE, CropInfo.CORN, CropInfo.EGGPLANT, CropInfo.PUMPKIN, CropInfo.SUNFLOWER, CropInfo.FAIRY_ROSE);
            case WINTER -> List.of(CropInfo.POWDERMELON);
            case SPECIAL -> List.of();
        };
    }

    public CropInfo getRandomMixedSeed() {
        return getMixedSeeds().get(Random.rand(0, getMixedSeeds().size() - 1));
    }
}