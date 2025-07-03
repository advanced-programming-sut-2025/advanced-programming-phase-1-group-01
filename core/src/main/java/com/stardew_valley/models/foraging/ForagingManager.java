package com.stardew_valley.models.foraging;

import com.stardew_valley.models.Game;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.farming.Seed;
import com.stardew_valley.models.farming.SeedInfo;

import java.util.List;
import java.util.Random;

public class ForagingManager {
    private final Game game;

    private static final Random RANDOM = new Random();

    public ForagingManager(Game game) {
        this.game = game;
    }

    public void prepareNewDayForaging() {
        for (List<Tile> row : game.getCurrentPlayer().getFarm().getTiles()) {
            for (Tile tile : row) {
                int prob = RANDOM.nextInt(100);
                if (prob == 0) {
                    prob = RANDOM.nextInt(2);
                    if (prob == 0) {
                        tile.setObject(new ForagingCrop(ForagingCropInfo.randomForagingCrop()));
                    } else {
                        if (tile.isPlowed()) game.getFarmingManager().plant(new Seed(SeedInfo.randomForagingSeed()), tile);
                    }
                }
            }
        }
    }
}
