package com.stardew_valley.models.farming;

import com.stardew_valley.models.Game;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.building.Greenhouse;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileObject;
import com.stardew_valley.models.building.TileType;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class FarmingManager {
    private final Game game;

    public FarmingManager(Game game) {
        this.game = game;
    }

    public void resetAllPlantsWatered() {
        for (List<Tile> row : game.getCurrentPlayer().getFarm().getTiles()) {
            for (Tile tile : row) {
                TileObject tileObject = tile.getObject();
                if (tileObject instanceof Plant plant) {
                    plant.resetWatered();
                }
            }
        }
    }

    public void autoWaterAllPlants() {
        Map<Plant, Tile> map = game.getCurrentPlayer().getFarm().getPlantsToTilesMap();
        for (Plant plant : map.keySet()) {
            if (map.get(plant).getType() != TileType.GREENHOUSE) {
                plant.water();
            }
        }
    }

    public Plant plant(Item source, Tile tile) {
        if (tile == null || !tile.isEmpty()) {
            return null;
        }

        Plant plant;
        if (source.getName().equalsIgnoreCase("mixed seeds")) {
            plant = new Crop((Seed) source, game.getTimeManager().getNow().getSeason());
        } else {
            if (source instanceof Seed) {
                plant = new Crop((Seed) source);
            } else if (source instanceof TreeSource) {
                plant = new Tree((TreeSource) source);
                tile.setMovable(false);
            } else {
                return null;
            }
        }

        return plant;
    }

    public void growAllPlants() {
        for (Plant plant : game.getCurrentPlayer().getFarm().getPlantsToTilesMap().keySet()) {
            plant.grow();
        }
    }

    public void applyCrowAttack(Tile tile) {
        if (tile.isEmpty() || !(tile.getObject() instanceof Plant)) return;

        if (tile.getObject() instanceof Crop) {
            tile.removeObject();
        } else if (tile.getObject() instanceof Tree tree) {
            tree.applyCrowAttack();
        }
    }

    private static final Random RANDOM = new Random();

    public void handleNightlyCrowsAttack() {
        Map<Plant, Tile> tilesMap = game.getCurrentPlayer().getFarm().getPlantsToTilesMap();
        List<Plant> plants = tilesMap.keySet().stream().toList();
        int size = plants.size();

        for (int i = 0; i < size / 16; i++) {
            int prob = RANDOM.nextInt(4);
            if (prob == 0) { // handle 25% probability
                int index = RANDOM.nextInt(plants.size());
                Plant plant = plants.get(index);
                applyCrowAttack(tilesMap.get(plant));
            }
        }
    }

    private static boolean[] checkWatering = new boolean[]{false, false};

    public void eliminateCropsIfNotWateredSequential() {
        int dayCount = game.getTimeManager().getNow().getDay() % 2;
        Map<Plant, Tile> tilesMap = game.getCurrentPlayer().getFarm().getPlantsToTilesMap();
        List<Plant> plants = tilesMap.keySet().stream().toList();
        for (Plant plant : plants) {
            checkWatering[dayCount] = !plant.isWatered();

            if (checkWatering[0] && checkWatering[1]) {
                tilesMap.get(plant).removeObject();
            }
        }
    }
}
