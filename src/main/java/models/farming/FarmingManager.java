package models.farming;

import models.Game;
import models.Item;
import models.building.Greenhouse;
import models.building.Tile;
import models.building.TileObject;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
                    plant.resetIsWatered();
                }
            }
        }
    }

    public void autoWaterAllPlants() {
        for (List<Tile> row : game.getCurrentPlayer().getFarm().getTiles()) {
            for (Tile tile : row) {
                TileObject tileObject = tile.getObject();
                if (tileObject instanceof Plant plant && !(tile.getBuilding() instanceof Greenhouse)) {
                    plant.water();
                }
            }
        }
    }

    public void plant(Item source, Tile tile) {
        if (tile == null || !tile.isEmpty()) {
            return;
        }

        Plant plant;
        if (source.getName().equalsIgnoreCase("mixed seeds")) {
            plant = new Crop((Seed) source, game.getTimeManager().getNow().getSeason());
        } else {
            if (source instanceof Seed) {
                plant = new Crop((Seed) source);
            } else if (source instanceof TreeSource) {
                plant = new Tree((TreeSource) source);
            } else {
                return;
            }
        }

        tile.setObject(plant);
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