package com.stardew_valley.models.foraging;

import com.stardew_valley.models.Game;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.farming.Seed;
import com.stardew_valley.models.farming.SeedInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForagingManager {
    private final Game game;
    private final List<Tile> crowsTiles = new ArrayList<>();
    private static final Random RANDOM = new Random();

    public ForagingManager(Game game) {
        this.game = game;
    }

    public void prepareNewDayForaging() {
        for (List<Tile> row : game.getFarm().getTiles()) {
            for (Tile tile : row) {
                if (!tile.isEmpty()) continue;
                if (tile.isMovable() && tile.getType() == TileType.GROUND) {
                    int prob = RANDOM.nextInt(500);
                    if (prob == 0) {
                        Season currSeason = game.getTimeManager().getNow().getSeason();
                        if (tile.isPlowed()) {
//                        game.getFarmingManager().plant(new Seed(SeedInfo.randomForagingSeed()), tile);
                            tile.setObject(new Seed(currSeason.getRandomForagingSeed()));
                        } else {
                            tile.setObject(new ForagingCrop(currSeason.getRandomForagingCrop()));
                            crowsTiles.add(tile);
//                        System.out.println(tile.getPosition().x() + " " + tile.getPosition().y() + " " + "FC");
                        }
                    }
                }

                if (tile.getType() == TileType.MINE) {
                    int prob = RANDOM.nextInt(30);
                    if (prob == 0) {
                        tile.setObject(new ForagingMineral(ForagingMineralInfo.randomForagingMineral()));
                        tile.setMovable(false);
                    }
                }
            }
            }
        }

        public List<Tile> getCrowsTiles() {
            return crowsTiles;
        }
    }
