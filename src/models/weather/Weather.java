package models.weather;

import models.Game;

import java.lang.annotation.Inherited;

public enum Weather {
    SUNNY {
        @Override
        public void applyEffect(Game game) {
            // do nothing
        }
    },
    RAINY {
        @Override
        public void applyEffect(Game game) {
            game.getFarmingManager().autoWaterAllPlants();
        }
    },
    STORMY {
        @Override
        public void applyEffect(Game game) {

        }
    }, SNOWY {
        @Override
        public void applyEffect(Game game) {

        }
    };


    public abstract void applyEffect(Game game);
}
