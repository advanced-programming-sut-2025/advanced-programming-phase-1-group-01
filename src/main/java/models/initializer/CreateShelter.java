package models.initializer;

import models.Position;
import models.Size;
import models.animal.AnimalHouse;
import models.animal.AnimalHouseType;
import models.building.Farm;
import models.building.Tile;
import models.building.TileType;

public class CreateShelter {
    public static AnimalHouse createShelter(Position position, Farm farm, AnimalHouseType animalHouseType) {
        setInteriorTiles(position, farm, animalHouseType.getSize());
        setFence(position.getX() - 1, position.getX() + animalHouseType.getSize().getWidth(),
                farm, position.getY() - 1, false);
        setFence(position.getX() - 1, position.getX() + animalHouseType.getSize().getWidth() + 1,
                farm, position.getY() + animalHouseType.getSize().getHeight() + 1, true);
        setFence(position.getY() - 1, position.getY() + animalHouseType.getSize().getHeight() + 1,
                farm, position.getX() - 1, false);
        setFence(position.getY() - 1, position.getY() + animalHouseType.getSize().getHeight() + 1,
                farm, position.getX() + animalHouseType.getSize().getWidth() + 1, false);
        return new AnimalHouse(animalHouseType, position);
    }

    public static void setInteriorTiles(Position position, Farm farm, Size size) {
        Position end = new Position(position.getX() + size.getWidth(), position.getY() + size.getHeight());
        for (int i = position.getX(); i < end.getX(); i++) {
            for (int j = position.getY(); j < end.getY(); j++) {
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

    public static void setFence(int start, int end, Farm farm, int constantVar, boolean hasDoor) {
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
}
