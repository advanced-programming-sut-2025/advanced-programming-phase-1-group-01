package models.initializer;

import models.Position;
import models.building.*;
import models.enums.TreeType;
import models.enums.StoneType;
import models.foraging.ForagingCrops;
import models.foraging.ForagingMinerals;
import models.foraging.ForagingTrees;
import models.random;

import java.util.ArrayList;
import java.util.List;

public class FarmInitializer {
    private final static int NUMBER_OF_TREES = 70;
    private final static int NUMBER_OF_STONES = 120;
    private final static int NUMBER_OF_FORAGING_CROPS = 80;
    private final static int NUMBER_OF_FORAGING_MINERALS = 60;
    private final static int NUMBER_OF_FORAGING_TREES = 50;
    private final static Position FARM_TP = new Position(0, 0);
    private final static Position FARM_BR = new Position(75, 75);
    private final static Position GROUND_TP = new Position(0, 0);
    private final static Position GROUND_BR = new Position(75, 75);
    private final static Position RIVER_TP = new Position(29, 62);
    private final static Position RIVER_BR = new Position(44, 74);
    private final static Position MINE_TP = new Position(1, 1);
    private final static Position MINE_BR = new Position(13, 10);
    private final static Position GREENHOUSE_TP = new Position(19, 2);
    private final static Position GREENHOUSE_BR = new Position(26, 7);
    private final static Position COTTAGE_TP = new Position(65, 4);
    private final static Position COTTAGE_BR = new Position(69, 9);

    private final static List<List<Tile>> tiles = new ArrayList<>();
    private static Cottage cottage;
    private static Greenhouse greenhouse;
    private static Lake lake;
    private static Quarry quarry;

    private static void initializeTiles() {

        for (int i = FARM_TP.getX(); i < FARM_BR.getX(); i++) {
            tiles.add(new ArrayList<>());
        }

        for (int i = GROUND_TP.getX(); i < GROUND_BR.getX(); i++) {
            for (int j = GROUND_TP.getY(); j < GROUND_BR.getY(); j++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.GROUND)
                        .setMovable(true)
                        .setBuilding(null)
                        .setObject(null)
                        .build();
                tiles.get(i).add(tile);
            }
        }

        for (int j = MINE_TP.getY(); j < MINE_BR.getY(); j++) {
            for (int i = MINE_TP.getX(); i < MINE_BR.getX(); i++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.MINE)
                        .setMovable(true)
                        .setBuilding(null)
                        .setObject(null)
                        .build();;
                tiles.get(i).set(j, tile);
            }
        }

        for (int j = RIVER_TP.getY(); j < RIVER_BR.getY(); j++) {
            for (int i = RIVER_TP.getX(); i < RIVER_BR.getX(); i++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.RIVER)
                        .setMovable(false)
                        .setBuilding(null)
                        .setObject(null)
                        .build();;
                tiles.get(i).set(j, tile);
            }
        }

        for (int j = GREENHOUSE_TP.getY(); j < GREENHOUSE_BR.getY(); j++) {
            for (int i = GREENHOUSE_TP.getX(); i < GREENHOUSE_BR.getX(); i++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.GREENHOUSE)
                        .setMovable(false)
                        .setBuilding(null)
                        .setObject(null)
                        .build();;
                tiles.get(i).set(j, tile);
            }
        }

        for (int j = COTTAGE_TP.getY(); j < COTTAGE_BR.getY(); j++) {
            for (int i = COTTAGE_TP.getX(); i < COTTAGE_BR.getX(); i++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.COTTAGE)
                        .setMovable(true)
                        .setBuilding(null)
                        .setObject(null)
                        .build();;
                tiles.get(i).set(j, tile);
            }
        }

        for (int i = COTTAGE_TP.getX() - 1; i < COTTAGE_BR.getX() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, COTTAGE_TP.getY() - 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();;
            tiles.get(i).set(COTTAGE_TP.getY() - 1, tile);
        }

        for (int i = COTTAGE_TP.getX() - 1; i < COTTAGE_BR.getX() + 1; i++) {
            if (i != (COTTAGE_BR.getX() + COTTAGE_TP.getX()) / 2) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, COTTAGE_BR.getY() + 1))
                        .setType(TileType.WALL)
                        .setMovable(false)
                        .setBuilding(null)
                        .setObject(null)
                        .build();;
                tiles.get(i).set(COTTAGE_BR.getY() + 1, tile);
            }
        }

        for (int i = COTTAGE_TP.getY() - 1; i < COTTAGE_BR.getY() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, COTTAGE_TP.getX() - 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();;
            tiles.get(COTTAGE_TP.getX() - 1).set(i, tile);
        }

        for (int i = COTTAGE_TP.getY() - 1; i < COTTAGE_BR.getY() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, COTTAGE_BR.getX() + 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            tiles.get(COTTAGE_BR.getX() + 1).set(i, tile);
        }

        for (int i = GREENHOUSE_TP.getX() - 1; i < GREENHOUSE_BR.getX() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, COTTAGE_TP.getY() - 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();;;
            tiles.get(i).set(GREENHOUSE_TP.getY() - 1, tile);
        }

        for (int i = GREENHOUSE_TP.getX() - 1; i < GREENHOUSE_BR.getX() + 1; i++) {
            if (i != (GREENHOUSE_BR.getX() + GREENHOUSE_TP.getX()) / 2) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, GREENHOUSE_BR.getY() + 1))
                        .setType(TileType.WALL)
                        .setMovable(false)
                        .setBuilding(null)
                        .setObject(null)
                        .build();;
                tiles.get(i).set(GREENHOUSE_BR.getY() + 1, tile);
            }
        }

        for (int i = GREENHOUSE_TP.getY() - 1; i < GREENHOUSE_BR.getY() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, GREENHOUSE_TP.getX() - 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            tiles.get(GREENHOUSE_TP.getX() - 1).set(i, tile);
        }

        for (int i = GREENHOUSE_TP.getY() - 1; i < GREENHOUSE_BR.getY() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, GREENHOUSE_BR.getX() + 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            tiles.get(GREENHOUSE_BR.getX() + 1).set(i, tile);
        }



        for (int i = 0; i < NUMBER_OF_TREES; i++) {
            TreeType tree = TreeType.randomTree();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.getX()).get(position.getY()).setObject(tree);
                tiles.get(position.getX()).get(position.getY()).setMovable(false);
            } else i--;
        }

        for (int i = 0; i < NUMBER_OF_STONES; i++) {
            StoneType stone = StoneType.randomStone();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.getX()).get(position.getY()).setObject(stone);
                tiles.get(position.getX()).get(position.getY()).setMovable(false);
            } else i--;
        }

        for (int i = 0; i < NUMBER_OF_FORAGING_CROPS; i++) {
            ForagingCrops foragingCrop = ForagingCrops.randomForagingCrop();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.getX()).get(position.getY()).setObject(foragingCrop);
                tiles.get(position.getX()).get(position.getY()).setMovable(false);
            } else i--;
        }

        for (int i = 0; i < NUMBER_OF_FORAGING_MINERALS; i++) {
            ForagingMinerals foragingMineral = ForagingMinerals.randomForagingMineral();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.getX()).get(position.getY()).setObject(foragingMineral);
                tiles.get(position.getX()).get(position.getY()).setMovable(false);
            } else i--;
        }

        for (int i = 0; i < NUMBER_OF_FORAGING_TREES; i++) {
            ForagingTrees foragingTree = ForagingTrees.randomForagingTree();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.getX()).get(position.getY()).setObject(foragingTree);
                tiles.get(position.getX()).get(position.getY()).setMovable(false);
            } else i--;
        }
    }


    private static void initializeBuildings() {
        cottage = new Cottage(new Position(COTTAGE_TP.getX(), COTTAGE_TP.getY()));
        greenhouse = new Greenhouse(new Position(GREENHOUSE_TP.getX(), GREENHOUSE_TP.getY()));
        lake = new Lake(new Position(RIVER_TP.getX(), RIVER_TP.getY()));
        quarry = new Quarry(new Position(MINE_TP.getX(), MINE_TP.getY()));
    }


    private static boolean canBePlanted(Position position) {
        return tiles.get(position.getX()).get(position.getY()).getType() == TileType.GROUND
                && tiles.get(position.getX()).get(position.getY()).getObject() == null;
    }

    private static Position randomPosition() {
        return new Position(random.rand(GROUND_TP.getX(), GROUND_BR.getX()), random.rand(GROUND_TP.getY(), GROUND_BR.getY()));
    }

    public static Farm initializeFarm() {
        initializeTiles();
        initializeBuildings();

        return new Farm(tiles, lake, cottage, quarry, greenhouse);
    }
}
