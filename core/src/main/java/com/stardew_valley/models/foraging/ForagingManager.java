package com.stardew_valley.models.foraging;

import com.stardew_valley.models.Game;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.dateTime.Season;
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
        for (List<Tile> row : game.getFarm().getTiles()) {
            for (Tile tile : row) {
                if (!tile.isMovable() || tile.getType() != TileType.GROUND) continue;
                int prob = RANDOM.nextInt(100);
                if (prob == 0) {
                    prob = RANDOM.nextInt(2);
                    if (prob == 0) {
                        Season currSeason = game.getTimeManager().getNow().getSeason();
                        tile.setObject(new ForagingCrop(currSeason.getRandomForagingCrop()));
                        System.out.println(tile.getPosition().x() + " " + tile.getPosition().y() + " " + "FC");
                    } else {
                        if (tile.isPlowed())
                            game.getFarmingManager().plant(new Seed(SeedInfo.randomForagingSeed()), tile);
                    }
                    tile.setMovable(false);
                }
            }
        }
    }
}
