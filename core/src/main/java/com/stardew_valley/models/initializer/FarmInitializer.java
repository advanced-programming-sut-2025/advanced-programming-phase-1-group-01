package com.stardew_valley.models.initializer;

import com.stardew_valley.models.Position;
import com.stardew_valley.models.Random;
import com.stardew_valley.models.building.*;
import com.stardew_valley.models.farming.FarmingManager;
import com.stardew_valley.models.farming.Seed;
import com.stardew_valley.models.farming.SeedInfo;
import com.stardew_valley.models.farming.Tree;
import com.stardew_valley.models.enums.StoneType;
import com.stardew_valley.models.foraging.*;
import com.stardew_valley.models.ingredients.Stone;
import com.stardew_valley.models.shop.ShopSymbol;

import java.util.ArrayList;
import java.util.List;

public class FarmInitializer {
    private final static Position FARM_BL = new Position(0, 0);
    private final static Position FARM_TR = new Position(225, 225);
    private final static Position GROUND_TP = new Position(0, 0);
    private final static Position GROUND_BR = new Position(75, 75);
    private final static Position RIVER_BL = new Position(40, 5);
    private final static Position RIVER_TR = new Position(10, 13);
    private final static Position MINE_TP = new Position(1, 1);
    private final static Position MINE_BR = new Position(13, 10);
    private final static Position GREENHOUSE_BL = new Position(6, 5);
    private final static Position GREENHOUSE_TR = new Position(20, 22);
    private final static Position MINE_BL = new Position(3, 57);
    private final static Position MINE_TR = new Position(22, 70);
    private final static Position COTTAGE_BL = new Position(50, 35);
    private final static Position COTTAGE_TR = new Position(59, 44);

    private final static List<List<Tile>> tiles = new ArrayList<>();
    private static Cottage cottage;
    private static Greenhouse greenhouse;
    private static Lake lake;
    private static Quarry quarry;

    private static void initializeTiles() {
        firstInitializer();
        surroundWithFence();

        for (int i = 0; i < 4; i++) {
            houseInitializer(i);
            minerInitializer(i);
            lakeInitializer(i);
            greenhouseInitializer(i);
        }
    }

    private static void firstInitializer() {
        for (int i = FARM_BL.x(); i < FARM_TR.x(); i++) {
            List<Tile> row = new ArrayList<>();
            for (int j = FARM_BL.y(); j < FARM_TR.y(); j++) {
                Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, j))
                    .setType(TileType.GROUND)
                    .setMovable(true)
                    .build();
                row.add(tile);
            }
            tiles.add(row);
        }
    }

    private static void greenhouseInitializer(int greenhouseId) {
        int additionalX = getAdditionalX(greenhouseId);
        int additionalY = getAdditionalY(greenhouseId);


        for (int i = GREENHOUSE_BL.x() + additionalX; i <= GREENHOUSE_TR.x() + additionalX; i++) {
            for (int j = GREENHOUSE_BL.y() + additionalY; j <= GREENHOUSE_TR.y() + additionalY; j++) {
                tiles.get(i).set(j, new Tile.Builder()
                    .setPosition(new Position(i, j))
                    .setType(TileType.FENCE)
                    .setMovable(false)
                    .build());
            }
        }

        for (int i = GREENHOUSE_BL.x() + additionalX + 3; i <= GREENHOUSE_TR.x() + additionalX - 3; i++) {
            for (int j = GREENHOUSE_BL.y() + additionalY + 3; j <= GREENHOUSE_TR.y() + additionalY - 7; j++) {
                tiles.get(i).set(j, new Tile.Builder()
                    .setPosition(new Position(i, j))
                    .setType(TileType.GREENHOUSE)
                    .setMovable(true)
                    .build());
            }
        }
    }

    private static void surroundWithFence() {
        int height = tiles.size();
        int width = tiles.get(0).size();

        for (int x = 0; x < width; x++) {
            tiles.get(0).set(x, new Tile.Builder()
                .setPosition(new Position(0, x))
                .setType(TileType.FENCE)
                .setMovable(false)
                .build());

            tiles.get(height - 1).set(x, new Tile.Builder()
                .setPosition(new Position(height - 1, x))
                .setType(TileType.FENCE)
                .setMovable(false)
                .build());
        }

        for (int y = 0; y < height; y++) {
            tiles.get(y).set(0, new Tile.Builder()
                .setPosition(new Position(y, 0))
                .setType(TileType.FENCE)
                .setMovable(false)
                .build());

            tiles.get(y).set(width - 1, new Tile.Builder()
                .setPosition(new Position(y, width - 1))
                .setType(TileType.FENCE)
                .setMovable(false)
                .build());
        }
    }

    private static void houseInitializer(int houseId) {
        int additionalX = getAdditionalX(houseId);
        int additionalY = getAdditionalY(houseId);


        for (int x = COTTAGE_BL.x() + additionalX; x < COTTAGE_TR.x() + additionalX; x++) {
            for (int y = FARM_BL.y() + additionalY; y < FARM_TR.y() + additionalY; y++) {
                Tile tile = new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(TileType.COTTAGE)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
                if (x < 225 && y < 225) { tiles.get(x).set(y, tile); }
                //@
            }
        }
    }

    public static int getHouseStartingPointX() {
        return COTTAGE_BL.x();
    }

    public static int getHouseStartingPointY() {
        return COTTAGE_BL.y();
    }

    public static int getLakeStartingPointX() {
        return COTTAGE_BL.x();
    }

    public static int getLakeStartingPointY() {
        return COTTAGE_BL.y();
    }

    public static int getMineStartingPointX() {
        return COTTAGE_BL.x();
    }

    public static int getMineStartingPointY() {
        return COTTAGE_BL.y();
    }

    public static int getGreenhouseStartingPointX() {
        return COTTAGE_BL.x();
    }

    public static int getGreenhouseStartingPointY() {
        return COTTAGE_BL.y();
    }

    private static void minerInitializer(int mineId) {
        int additionalX = getAdditionalX(mineId);
        int additionalY = getAdditionalY(mineId);

        for (int y = MINE_BL.y() + additionalY; y < MINE_TR.y() + additionalY; y++) {
            switch (y) {
                case 0:
                    for (int x = MINE_BL.x() + additionalX + 5; x <= MINE_BL.x() + additionalX + 13; x++) {
                        tiles.get(y).set(x, new Tile.Builder()
                            .setPosition(new Position(x, y))
                            .setType(TileType.FENCE)
                            .setMovable(false)
                            .build());
                    }
                    break;
                case 1:
                    for (int x = MINE_BL.x() + additionalX + 2; x <= MINE_BL.x() + additionalX + 16; x++) {
                        if (x > 5 && x < 14) {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.MINE)
                                .setMovable(true)
                                .build());
                        } else {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.FENCE)
                                .setMovable(false)
                                .build());
                        }
                    }
                    break;
                case 2:
                    for (int x = MINE_BL.x() + additionalX + 2; x <= MINE_BL.x() + additionalX + 17; x++) {
                        if (x == 2 || x > 14 && x < 18) {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.FENCE)
                                .setMovable(false)
                                .build());
                        } else {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.MINE)
                                .setMovable(true)
                                .build());
                        }
                    }
                    break;
                case 3:
                    for (int x = MINE_BL.x() + additionalX + 1; x <= MINE_BL.x() + additionalX + 18; x++) {
                        if (x == 1 || x == 2 || x == 17 || x == 18) {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.FENCE)
                                .setMovable(false)
                                .build());
                        } else {
                                tiles.get(y).set(x, new Tile.Builder()
                                    .setPosition(new Position(x, y))
                                    .setType(TileType.MINE)
                                    .setMovable(true)
                                    .build());
                            }
                        }
                        break;
                case 4:
                case 5:
                case 6:
                case 7:
                    for (int x = MINE_BL.x() + additionalX; x <= MINE_BL.x() + additionalX + 18; x++) {
                        if (x == 0 || x == 1 || x == 17 || x == 18) {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.FENCE)
                                .setMovable(false)
                                .build());
                        } else {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.MINE)
                                .setMovable(true)
                                .build());
                        }
                    }
                    break;
                case 8:
                    for (int x = MINE_BL.x() + additionalX + 2; x <= MINE_BL.x() + additionalX + 17; x++) {
                        if (x >= 0 && x < 4 || x >= 15 && x <= 18) {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.FENCE)
                                .setMovable(false)
                                .build());
                        } else {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.MINE)
                                .setMovable(true)
                                .build());
                        }
                    }
                    break;
                default:
                    for (int x = 0; x < 19; x++) {
                        if (x != 5) {
                            tiles.get(y).set(x, new Tile.Builder()
                                .setPosition(new Position(x, y))
                                .setType(TileType.FENCE)
                                .setMovable(false)
                                .build());
                        }
                    }

            }
        }
    }


    private static void lakeInitializer(int lakeId) {
        int additionalX = getAdditionalX(lakeId);
        int additionalY = getAdditionalY(lakeId);

        for (int y = RIVER_BL.y() + additionalY; y <= RIVER_TR.y() + additionalY; y++) {
            switch (y) {
                case 0:
                case 7:
                    for (int x = RIVER_BL.x() + additionalX; x <= RIVER_TR.x() + additionalX; x++) {
                        tiles.get(y).set(x, new Tile.Builder()
                            .setPosition(new Position(x, y))
                            .setType(TileType.FENCE)
                            .setMovable(false)
                            .build());
                    }
                    break;
                default:
                    tiles.get(y).set(RIVER_BL.x() + additionalX, new Tile.Builder()
                        .setPosition(new Position(RIVER_BL.x() + additionalX, y))
                        .setType(TileType.FENCE)
                        .setMovable(false)
                        .build());
                    for (int x = RIVER_BL.x() + additionalX + 1; x <= RIVER_TR.x() + additionalX - 1; x++) {
                        tiles.get(y).set(x, new Tile.Builder()
                            .setPosition(new Position(x, y))
                            .setType(TileType.RIVER)
                            .setMovable(false)
                            .build());
                    }
                    tiles.get(y).set(RIVER_TR.x() + additionalX, new Tile.Builder()
                        .setPosition(new Position(RIVER_TR.x() + additionalX, y))
                        .setType(TileType.FENCE)
                        .setMovable(false)
                        .build());
            }
        }
    }



    private static boolean canBePlanted(Position position) {
        return tiles.get(position.x()).get(position.y()).getType() == TileType.GROUND
                && tiles.get(position.x()).get(position.y()).getObject() == null;
    }

    private static Position randomPosition() {
        return new Position(Random.rand(GROUND_TP.x(), GROUND_BR.x()), Random.rand(GROUND_TP.y(), GROUND_BR.y()));
    }

    private static Position randomMinePosition() {
        return new Position(Random.rand(MINE_TP.x(), MINE_BR.x()), Random.rand(MINE_TP.y(), MINE_BR.y()));
    }

    public static int getAdditionalX(int id) {
        switch (id) {
            case 1:
                return 0;
            case 2:
                return 150;
            default:
                return 75;
        }
    }

    public static int getAdditionalY(int id) {
        switch (id) {
            case 0:
                return 0;
            case 3:
                return 150;
            default:
                return 75;
        }
    }



    public static Farm initializeFarm() {
        initializeTiles();
        return new Farm(tiles, lake, cottage, quarry, greenhouse);
    }
}
