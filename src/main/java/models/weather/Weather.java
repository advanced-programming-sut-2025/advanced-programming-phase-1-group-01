package models.weather;

import models.Game;

import java.lang.annotation.Inherited;

public enum Weather {
    SUNNY(1.5) {
        @Override
        public void applyEffect(Game game) {
            // do nothing
        }
    },
    RAINY(1.2) {
        @Override
        public void applyEffect(Game game) {
            game.getFarmingManager().autoWaterAllPlants();
        }
    },
    STORMY(0.5) {
        @Override
        public void applyEffect(Game game) {
            game.getWeatherManager().handleDailyThor();
            game.getFarmingManager().autoWaterAllPlants();
        }
    }, SNOWY(1) {
        @Override
        public void applyEffect(Game game) {

        }
    };

    Weather(double fishingFactor) {
        this.fishingFactor = fishingFactor;
    }

    public abstract void applyEffect(Game game);

    private final double fishingFactor; // this property is used on fishing formulae

    public double getFishingFactor() {
        return fishingFactor;
    }
}