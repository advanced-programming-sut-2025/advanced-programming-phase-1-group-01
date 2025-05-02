package models.farming;

import models.Game;

public class FarmingManager {
    private final Game game;
    private Crop crop;

    public FarmingManager(Game game) {
        this.game = game;
    }
}
