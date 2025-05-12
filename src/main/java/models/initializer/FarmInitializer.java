package models.initializer;

import models.Position;
import models.building.*;
import models.farming.TreeInfo;
import models.enums.StoneType;
import models.foraging.ForagingCrop;
import models.foraging.ForagingMineral;
import models.foraging.ForagingTree;
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
    private final static Position SHIPPING_BIN_1_TP = new Position(4, 5);
    private final static Position SHIPPING_BIN_1_BR = new Position(5, 6);
    private final static Position SHIPPING_BIN_2_TP = new Position(70, 68);
    private final static Position SHIPPING_BIN_2_BR = new Position(71, 69);

    private final static List<List<Tile>> tiles = new ArrayList<>();
    private static Cottage cottage;
    private static Greenhouse greenhouse;
    private static Lake lake;
    private static Quarry quarry;

    private static void initializeTiles() {

        for (int i = FARM_TP.x(); i < FARM_BR.x(); i++) {
            tiles.add(new ArrayList<>());
        }

        for (int i = GROUND_TP.x(); i < GROUND_BR.x(); i++) {
            for (int j = GROUND_TP.y(); j < GROUND_BR.y(); j++) {
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

        for (int j = MINE_TP.y(); j < MINE_BR.y(); j++) {
            for (int i = MINE_TP.x(); i < MINE_BR.x(); i++) {
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

        for (int j = RIVER_TP.y(); j < RIVER_BR.y(); j++) {
            for (int i = RIVER_TP.x(); i < RIVER_BR.x(); i++) {
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

        for (int j = GREENHOUSE_TP.y(); j < GREENHOUSE_BR.y(); j++) {
            for (int i = GREENHOUSE_TP.x(); i < GREENHOUSE_BR.x(); i++) {
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

        for (int j = COTTAGE_TP.y(); j < COTTAGE_BR.y(); j++) {
            for (int i = COTTAGE_TP.x(); i < COTTAGE_BR.x(); i++) {
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

        for (int i = COTTAGE_TP.x() - 1; i < COTTAGE_BR.x() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, COTTAGE_TP.y() - 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();;
            tiles.get(i).set(COTTAGE_TP.y() - 1, tile);
        }

        for (int i = COTTAGE_TP.x() - 1; i < COTTAGE_BR.x() + 1; i++) {
            if (i != (COTTAGE_BR.x() + COTTAGE_TP.x()) / 2) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, COTTAGE_BR.y() + 1))
                        .setType(TileType.WALL)
                        .setMovable(false)
                        .setBuilding(null)
                        .setObject(null)
                        .build();;
                tiles.get(i).set(COTTAGE_BR.y() + 1, tile);
            }
        }

        for (int i = COTTAGE_TP.y() - 1; i < COTTAGE_BR.y() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, COTTAGE_TP.x() - 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();;
            tiles.get(COTTAGE_TP.x() - 1).set(i, tile);
        }

        for (int i = COTTAGE_TP.y() - 1; i < COTTAGE_BR.y() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, COTTAGE_BR.x() + 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            tiles.get(COTTAGE_BR.x() + 1).set(i, tile);
        }

        for (int i = GREENHOUSE_TP.x() - 1; i < GREENHOUSE_BR.x() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, COTTAGE_TP.y() - 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            tiles.get(i).set(GREENHOUSE_TP.y() - 1, tile);
        }

        for (int i = GREENHOUSE_TP.x() - 1; i < GREENHOUSE_BR.x() + 1; i++) {
            if (i != (GREENHOUSE_BR.x() + GREENHOUSE_TP.x()) / 2) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, GREENHOUSE_BR.y() + 1))
                        .setType(TileType.WALL)
                        .setMovable(false)
                        .setBuilding(null)
                        .setObject(null)
                        .build();;
                tiles.get(i).set(GREENHOUSE_BR.y() + 1, tile);
            }
        }

        for (int i = GREENHOUSE_TP.y() - 1; i < GREENHOUSE_BR.y() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, GREENHOUSE_TP.x() - 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            tiles.get(GREENHOUSE_TP.x() - 1).set(i, tile);
        }

        for (int i = GREENHOUSE_TP.y() - 1; i < GREENHOUSE_BR.y() + 1; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, GREENHOUSE_BR.x() + 1))
                    .setType(TileType.WALL)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            tiles.get(GREENHOUSE_BR.x() + 1).set(i, tile);
        }

        for (int i = SHIPPING_BIN_1_TP.getX(); i < SHIPPING_BIN_1_BR.getX(); i++) {
            for (int j = SHIPPING_BIN_1_TP.getY(); j < SHIPPING_BIN_1_BR.getY(); j++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.SALE_BUCKET)
                        .setMovable(false)
                        .build();
                tiles.get(i).set(j, tile);
            }
        }

        for (int i = SHIPPING_BIN_2_TP.getX(); i < SHIPPING_BIN_2_BR.getX(); i++) {
            for (int j = SHIPPING_BIN_2_TP.getY(); j < SHIPPING_BIN_2_BR.getY(); j++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.SALE_BUCKET)
                        .setMovable(false)
                        .build();
                tiles.get(i).set(j, tile);
            }
        }

        for (int i = 0; i < NUMBER_OF_TREES; i++) {
            TreeInfo tree = TreeInfo.randomTree();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.x()).get(position.y()).setObject(tree);
                tiles.get(position.x()).get(position.y()).setMovable(false);
            } else i--;
        }

        for (int i = 0; i < NUMBER_OF_STONES; i++) {
            StoneType stone = StoneType.randomStone();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.x()).get(position.y()).setObject(stone);
                tiles.get(position.x()).get(position.y()).setMovable(false);
            } else i--;
        }

        for (int i = 0; i < NUMBER_OF_FORAGING_CROPS; i++) {
            ForagingCrop foragingCrop = ForagingCrop.randomForagingCrop();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.x()).get(position.y()).setObject(foragingCrop);
                tiles.get(position.x()).get(position.y()).setMovable(false);
            } else i--;
        }

        for (int i = 0; i < NUMBER_OF_FORAGING_MINERALS; i++) {
            ForagingMineral foragingMineral = ForagingMineral.randomForagingMineral();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.x()).get(position.y()).setObject(foragingMineral);
                tiles.get(position.x()).get(position.y()).setMovable(false);
            } else i--;
        }

        for (int i = 0; i < NUMBER_OF_FORAGING_TREES; i++) {
            ForagingTree foragingTree = ForagingTree.randomForagingTree();
            Position position = randomPosition();

            if (canBePlanted(position)) {
                tiles.get(position.x()).get(position.y()).setObject(foragingTree);
                tiles.get(position.x()).get(position.y()).setMovable(false);
            } else i--;
        }
    }


    private static void initializeBuildings() {
        cottage = new Cottage(new Position(COTTAGE_TP.x(), COTTAGE_TP.y()));
        greenhouse = new Greenhouse(new Position(GREENHOUSE_TP.x(), GREENHOUSE_TP.y()));
        lake = new Lake(new Position(RIVER_TP.x(), RIVER_TP.y()));
        quarry = new Quarry(new Position(MINE_TP.x(), MINE_TP.y()));
    }


    private static boolean canBePlanted(Position position) {
        return tiles.get(position.x()).get(position.y()).getType() == TileType.GROUND
                && tiles.get(position.x()).get(position.y()).getObject() == null;
    }

    private static Position randomPosition() {
        return new Position(random.rand(GROUND_TP.x(), GROUND_BR.x()), random.rand(GROUND_TP.y(), GROUND_BR.y()));
    }

    public static Farm initializeFarm() {
        initializeTiles();
        initializeBuildings();

        return new Farm(tiles, lake, cottage, quarry, greenhouse);
    }
}
