package com.stardew_valley.models.initializer;

import com.stardew_valley.models.Position;
import com.stardew_valley.models.Random;
import com.stardew_valley.models.building.*;
import com.stardew_valley.models.crafting.Bomb;
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
    private final static Position RIVER_BL = new Position(45, 5);
    private final static Position RIVER_TR = new Position(53, 12);
    private final static Position MINE_TP = new Position(1, 1);
    private final static Position MINE_BR = new Position(13, 10);
    private final static Position GREENHOUSE_BL = new Position(6, 5);
    private final static Position GREENHOUSE_TR = new Position(20, 22);
    private final static Position MINE_BL = new Position(3, 57);
    private final static Position MINE_TR = new Position(22 - 7, 70);
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
        divideMapWithFence();
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

    private static void divideMapWithFence() {
        int height = tiles.size();
        int width = tiles.get(0).size();

        int thirdHeight = height / 3;
        int thirdWidth = width / 3;

        for (int yOffset = 1; yOffset <= 2; yOffset++) {
            int y = yOffset * thirdHeight;
            for (int x = 0; x < width; x++) {
                tiles.get(y).set(x, new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(TileType.FENCE)
                    .setMovable(false)
                    .build());
            }
        }

        for (int xOffset = 1; xOffset <= 2; xOffset++) {
            int x = xOffset * thirdWidth;
            for (int y = 0; y < height; y++) {
                tiles.get(y).set(x, new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(TileType.FENCE)
                    .setMovable(false)
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
            for (int y = COTTAGE_BL.y() + additionalY; y < COTTAGE_TR.y() + additionalY; y++) {
                Tile tile = new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(TileType.COTTAGE)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
                tiles.get(x).set(y, tile);
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
        return RIVER_BL.x();
    }

    public static int getLakeStartingPointY() {
        return RIVER_BL.y();
    }

    public static int getMineStartingPointX() {
        return MINE_BL.x();
    }

    public static int getMineStartingPointY() {
        return MINE_BL.y();
    }

    public static int getGreenhouseStartingPointX() {
        return GREENHOUSE_BL.x();
    }

    public static int getGreenhouseStartingPointY() {
        return GREENHOUSE_BL.y();
    }


    private static void minerInitializer(int mineId) {
        int additionalX = getAdditionalX(mineId);
        int additionalY = getAdditionalY(mineId);

        int baseX = MINE_BL.x() + additionalX;
        int baseY = MINE_BL.y() + additionalY;

        for (int y = baseY; y < MINE_TR.y() + additionalY; y++) {
            int localY = y - baseY;

            switch (localY) {
                case 0:
                    for (int localX = 5; localX <= 13; localX++) {
                        setTile(y, baseX + localX, TileType.FENCE, false);
                    }
                    break;

                case 1:
                    for (int localX = 2; localX <= 16; localX++) {
                        TileType type = (localX > 5 && localX < 14) ? TileType.MINE : TileType.FENCE;
                        boolean movable = type == TileType.MINE;
                        setTile(y, baseX + localX, type, movable);
                    }
                    break;

                case 2:
                    for (int localX = 2; localX <= 17; localX++) {
                        boolean isFence = localX == 2 || localX > 14;
                        TileType type = isFence ?
                            TileType.FENCE //*
                            :
                            TileType.MINE;
                        boolean movable = !isFence;
                        setTile(y, baseX + localX, type, movable);
                    }
                    break;

                case 3:
                    for (int localX = 1; localX <= 18; localX++) {
                        boolean isFence = (localX == 1 || localX == 2 || localX == 17 || localX == 18);
                        TileType type = isFence ?
                            TileType.FENCE //*
                            :
                            TileType.MINE;
                        boolean movable = !isFence;
                        setTile(y, baseX + localX, type, movable);
                    }
                    break;

                case 4: case 5: case 6: case 7:
                    for (int localX = 0; localX <= 18; localX++) {
                        boolean isFence = (localX == 0 || localX == 1 || localX == 17 || localX == 18);
                        TileType type = isFence ?
                            TileType.FENCE //*
                            :
                            TileType.MINE;
                        boolean movable = !isFence;
                        setTile(y, baseX + localX, type, movable);
                    }
                    break;

                case 8:
                    for (int localX = 2; localX <= 17; localX++) {
                        boolean isFence = localX < 4 || localX >= 15;
                        TileType type = isFence ?
                            TileType.FENCE //*
                            :
                            TileType.MINE;
                        boolean movable = !isFence;
                        setTile(y, baseX + localX, type, movable);
                    }
                    break;

                default:
                    for (int localX = 0; localX < 19; localX++) {
                        if (localX != 5) {
                            setTile(y, localX,
                                TileType.FENCE,//*
                                false);
                        }
                    }
            }
        }
    }

    private static void setTile(int y, int x, TileType type, boolean movable) {
        tiles.get(y).set(x, new Tile.Builder()
            .setPosition(new Position(x, y))
            .setType(type)
            .setMovable(movable)
            .build());
        debugPrint(x, y, type, "setTile");
    }

    private static void debugPrint(int x, int y, TileType type, String source) {
        System.out.println("[" + source + "] Setting tile at (" + x + ", " + y + ") to " + type);
    }




    private static void lakeInitializer(int lakeId) {
        int additionalX = getAdditionalX(lakeId);
        int additionalY = getAdditionalY(lakeId);

        int xStart = RIVER_BL.x() + additionalX;
        int xEnd = RIVER_TR.x() + additionalX;
        int yStart = RIVER_BL.y() + additionalY;
        int yEnd = RIVER_TR.y() + additionalY;

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                boolean isBorderX = (x == xStart || x == xEnd);
                boolean isBorderY = (y == yStart || y == yEnd);

                TileType type = (isBorderX || isBorderY) ? TileType.FENCE : TileType.RIVER;

                tiles.get(y).set(x, new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(type)
                    .setMovable(type != TileType.FENCE)
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
        if (tiles.isEmpty()) {
            initializeTiles();
        }
        return new Farm(tiles, lake, cottage, quarry, greenhouse);
    }
}
