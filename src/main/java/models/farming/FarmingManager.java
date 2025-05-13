package models.farming;

import models.Game;
import models.building.Greenhouse;
import models.building.Tile;
import models.building.TileObject;

import java.util.List;

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

    public void plant(Seed seed, Tile tile) {
        Crop crop = new Crop(seed);
        tile.setObject(crop);
    }
}
