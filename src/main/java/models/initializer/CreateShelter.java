package models.initializer;

import models.Position;
import models.Size;
import models.animal.AnimalHouse;
import models.animal.AnimalHouseType;
import models.building.Farm;
import models.building.Tile;
import models.building.TileType;

import java.util.List;

public class CreateShelter {
    public static AnimalHouse createShelter(Position position, Farm farm, AnimalHouseType animalHouseType) {
        setInteriorTiles(position, farm, animalHouseType.getSize());
        setFence(position.x() - 1, position.x() + animalHouseType.getSize().getWidth(),
                farm, position.y() - 1, false);
        setFence(position.x() - 1, position.x() + animalHouseType.getSize().getWidth() + 1,
                farm, position.y() + animalHouseType.getSize().getHeight() + 1, true);
        setFence(position.y() - 1, position.y() + animalHouseType.getSize().getHeight() + 1,
                farm, position.x() - 1, false);
        setFence(position.y() - 1, position.y() + animalHouseType.getSize().getHeight() + 1,
                farm, position.x() + animalHouseType.getSize().getWidth() + 1, false);
        return new AnimalHouse(animalHouseType, position);
    }

    private static void setInteriorTiles(Position position, Farm farm, Size size) {
        Position end = new Position(position.x() + size.getWidth(), position.y() + size.getHeight());
        for (int i = position.x(); i < end.x(); i++) {
            for (int j = position.y(); j < end.y(); j++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.GREENHOUSE)
                        .setMovable(true)
                        .setBuilding(null)
                        .setObject(null)
                        .build();
                farm.getTiles().get(i).set(j, tile);
            }
        }
    }

    private static void setFence(int start, int end, Farm farm, int constantVar, boolean hasDoor) {
        for (int i = start; i < end; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, constantVar))
                    .setType(TileType.FENCE)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            if (!hasDoor || (i != (start + end) / 2)) farm.getTiles().get(i).set(constantVar, tile);
        }
    }

    public static boolean isEmptyPlace(List<List<Tile>> tiles, int x, int y, int size) {
        if (x + size > tiles.size() || y + size > tiles.get(0).size()) return false;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (tiles.get(i).get(j).getType() != TileType.GROUND || tiles.get(i).get(j).getObject() != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
