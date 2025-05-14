package models.dateTime;

import models.Random;
import models.farming.CropInfo;
import models.weather.Weather;

import java.util.List;

public enum Season {
    SPRING(List.of(Weather.SUNNY, Weather.RAINY, Weather.STORMY), List.of(CropInfo.CAULIFLOWER, CropInfo.PARSNIP, CropInfo.POTATO, CropInfo.BLUE_JAZZ, CropInfo.TULIP)),
    SUMMER(List.of(Weather.SUNNY, Weather.RAINY, Weather.STORMY), List.of(CropInfo.CORN, CropInfo.HOT_PEPPER, CropInfo.RADISH, CropInfo.WHEAT, CropInfo.POPPY, CropInfo.SUNFLOWER, CropInfo.SUMMER_SPANGLE)),
    FALL(List.of(Weather.SUNNY, Weather.RAINY, Weather.STORMY), List.of(CropInfo.ARTICHOKE, CropInfo.CORN, CropInfo.EGGPLANT, CropInfo.PUMPKIN, CropInfo.SUNFLOWER, CropInfo.FAIRY_ROSE)),
    WINTER(List.of(Weather.SNOWY, Weather.SUNNY), List.of(CropInfo.POWDERMELON)),
    SPECIAL(List.of(), List.of());

    private final List<Weather> weathers;
    private final List<CropInfo> mixedSeeds;

    Season(List<Weather> weathers, List<CropInfo> mixedSeeds) {
        this.weathers = weathers;
        this.mixedSeeds = mixedSeeds;
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
        return mixedSeeds;
    }

    public CropInfo getRandomMixedSeed() {
        return mixedSeeds.get(Random.rand(0, mixedSeeds.size() - 1));
    }
}
