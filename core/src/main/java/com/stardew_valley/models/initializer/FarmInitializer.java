package com.stardew_valley.models.initializer;

import com.google.gson.*;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.*;
import com.stardew_valley.models.character.NPC.NPC;
import com.stardew_valley.models.character.NPC.NPCQuest;
import com.stardew_valley.models.character.NPC.NPCQuestType;
import com.stardew_valley.models.character.NPC.NPCType;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.farming.Seed;
import com.stardew_valley.models.farming.SeedInfo;
import com.stardew_valley.models.foraging.*;

import java.util.ArrayList;
import java.util.List;

public class FarmInitializer {
    private final static Position FARM_BL = new Position(0, 0);
    private final static Position FARM_TR = new Position(225, 225);
    private final static Position RIVER_BL = new Position(45, 5);
    private final static Position RIVER_TR = new Position(53, 12);
    private final static Position GREENHOUSE_BL = new Position(6, 5);
    private final static Position GREENHOUSE_TR = new Position(20, 22);
    private final static Position MINE_BL = new Position(3, 57);
    private final static Position MINE_TR = new Position(22 - 7, 70);
    private final static Position COTTAGE_BL = new Position(50, 35);
    private final static Position COTTAGE_TR = new Position(59, 44);

    private static NPC sebastian;
    private static NPC abigail;
    private static NPC harvey;
    private static NPC leah;

    private final static Position SEBASTIAN_COTTAGE_BL = new Position(80 + 10, 130);
    private final static Position SEBASTIAN_COTTAGE_TR = new Position(84 + 10, 136);

    private final static Position SEBASTIAN_STARTING_POSITION = new Position(82, 129);


    private final static Position ABIGAIL_COTTAGE_BL = new Position(120 + 10, 130);
    private final static Position ABIGAIL_COTTAGE_TR = new Position(124 + 10, 136);

    private final static Position ABIGAIL_STARTING_POSITION = new Position(122, 129);


    private final static Position LEAH_COTTAGE_BL = new Position(120 + 10, 90);
    private final static Position LEAH_COTTAGE_TR = new Position(124 + 10, 96);

    private final static Position LEAH_STARTING_POSITION = new Position(122, 89);


    private final static Position HARVEY_COTTAGE_BL = new Position(80 + 10, 90);
    private final static Position HARVEY_COTTAGE_TR = new Position(84 + 10, 96);

    private final static Position HARVEY_STARTING_POSITION = new Position(82, 89);

    private final static Position BLACKSMITH_BL = new Position(5, 5);
    private final static Position BLACKSMITH_TR = new Position(25, 25);

    private final static List<List<Tile>> tiles = new ArrayList<>();
    private static Cottage cottage;
    private static Greenhouse greenhouse;
    private static Lake lake;
    private static Quarry quarry;

    private static void initializeTiles() {
        firstInitializer();
        surroundWithFence();
        divideMapWithFence();
        npcsHouseInitializer();
        npcsInitializer();
        for (int i = 0; i < 4; i++) {
            houseInitializer(i);
            minerInitializer(i);
            lakeInitializer(i);
            greenhouseInitializer(i);
        }

        shippingBinInitializer(1);
        shippingBinInitializer(3);
        shippingBinInitializer(5);
        shippingBinInitializer(7);
    }

    private static void npcsHouseInitializer() {
        npcHouseInitializer(LEAH_COTTAGE_BL, LEAH_COTTAGE_TR, TileType.COTTAGE);
        npcHouseInitializer(SEBASTIAN_COTTAGE_BL, SEBASTIAN_COTTAGE_TR, TileType.COTTAGE);
        npcHouseInitializer(ABIGAIL_COTTAGE_BL, ABIGAIL_COTTAGE_TR, TileType.COTTAGE);
        npcHouseInitializer(HARVEY_COTTAGE_BL, HARVEY_COTTAGE_TR, TileType.COTTAGE);
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

        int xStart = GREENHOUSE_BL.x() + additionalX - 1;
        int xEnd = GREENHOUSE_TR.x() + additionalX + 1;

        int yStart = GREENHOUSE_BL.y() + additionalY;
        int yEnd = GREENHOUSE_TR.y() + additionalY - 3;

        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                System.out.println((i - additionalX) + " " +  (j - additionalY));
                if ((i == 5 + additionalX || i == 6 + additionalX || i == 7 + additionalX) &&
                    (j == 12 + additionalY)) {
                    continue;
                }

                tiles.get(i).set(j, new Tile.Builder()
                    .setPosition(new Position(i, j))
                    .setType(TileType.FENCE)
                    .setMovable(false)
                    .build());
            }
        }

        for (int i = GREENHOUSE_BL.x() + additionalX + 2; i <= GREENHOUSE_TR.x() + additionalX - 5 - 1; i++) {
            for (int j = GREENHOUSE_BL.y() + additionalY + 3 + 1; j <= GREENHOUSE_TR.y() + additionalY - 7; j++) {
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
                    .setType(TileType.FENCE) //*
                    .setMovable(true)
                    .build());
            }
        }

        for (int xOffset = 1; xOffset <= 2; xOffset++) {
            int x = xOffset * thirdWidth;
            for (int y = 0; y < height; y++) {
                tiles.get(y).set(x, new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(TileType.FENCE) //*
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
                .setType(TileType.FENCE) //*
                .setMovable(false)
                .build());

            tiles.get(height - 1).set(x, new Tile.Builder()
                .setPosition(new Position(height - 1, x))
                .setType(TileType.FENCE) //*
                .setMovable(false)
                .build());
        }

        for (int y = 0; y < height; y++) {
            tiles.get(y).set(0, new Tile.Builder()
                .setPosition(new Position(y, 0))
                .setType(TileType.FENCE) //*
                .setMovable(false)
                .build());

            tiles.get(y).set(width - 1, new Tile.Builder()
                .setPosition(new Position(y, width - 1))
                .setType(TileType.FENCE) //*
                .setMovable(false)
                .build());
        }
    }

    private static void houseInitializer(int houseId) {
        int additionalX = getAdditionalX(houseId);
        int additionalY = getAdditionalY(houseId);


        for (int x = COTTAGE_BL.x() + additionalX - 1; x < COTTAGE_TR.x() + additionalX; x++) {
            for (int y = COTTAGE_BL.y() + additionalY; y < COTTAGE_TR.y() + additionalY - 2; y++) {
                Tile tile = new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(TileType.COTTAGE)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
                tiles.get(y).set(x, tile);
            }
        }
    }

    private static void npcHouseInitializer(Position bottomLeft, Position topRight, TileType houseType) {
        for (int x = bottomLeft.x() - 1; x <= topRight.x(); x++) {
            for (int y = bottomLeft.y(); y <= topRight.y() - 2; y++) {
                Tile tile = new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(houseType)
                    .setMovable(false)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
                tiles.get(y).set(x, tile);
            }
        }
    }

    private static void initializeSebastian() {
        sebastian = new NPC(NPCType.SEBASTIAN,
            SEBASTIAN_STARTING_POSITION,
            Direction.DOWN, List.of(new NPCQuest(NPCQuestType.SEBASTIAN_1),
            new NPCQuest(NPCQuestType.SEBASTIAN_2), new NPCQuest(NPCQuestType.SEBASTIAN_3)));
    }
    private static void initializeAbigail() {
        abigail = new NPC(NPCType.ABIGAIL,
            ABIGAIL_STARTING_POSITION,
            Direction.RIGHT, List.of(new NPCQuest(NPCQuestType.ABIGAIL_1),
            new NPCQuest(NPCQuestType.ABIGAIL_2), new NPCQuest(NPCQuestType.ABIGAIL_3)));
    }
    private static void initializeHarvey() {
        harvey = new NPC(NPCType.HARVEY,
            HARVEY_STARTING_POSITION,
            Direction.UP, List.of(new NPCQuest(NPCQuestType.HARVEY_1),
            new NPCQuest(NPCQuestType.HARVEY_2), new NPCQuest(NPCQuestType.HARVEY_3)));
    }
    private static void initializeLeah() {
        leah = new NPC(NPCType.LEAH,
            LEAH_STARTING_POSITION,
            Direction.UP, List.of(new NPCQuest(NPCQuestType.LEAH_1),
            new NPCQuest(NPCQuestType.LEAH_2), new NPCQuest(NPCQuestType.LEAH_3)));
    }

    private static void npcsInitializer() {
        initializeLeah();
        initializeSebastian();
        initializeAbigail();
        initializeHarvey();
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


    public static int getSebastianCottageStartingPointX() {
        return SEBASTIAN_COTTAGE_BL.x();
    }

    public static int getSebastianCottageStartingPointY() {
        return SEBASTIAN_COTTAGE_BL.y();
    }

    public static int getAbigailCottageStartingPointX() {
        return ABIGAIL_COTTAGE_BL.x();
    }

    public static int getAbigailCottageStartingPointY() {
        return ABIGAIL_COTTAGE_BL.y();
    }

    public static int getLeahCottageStartingPointX() {
        return LEAH_COTTAGE_BL.x();
    }

    public static int getLeahCottageStartingPointY() {
        return LEAH_COTTAGE_BL.y();
    }

    public static int getHarveyCottageStartingPointX() {
        return HARVEY_COTTAGE_BL.x();
    }

    public static int getHarveyCottageStartingPointY() {
        return HARVEY_COTTAGE_BL.y();
    }


    public static int getHarveyStartingPointX() {
        return HARVEY_STARTING_POSITION.x();
    }

    public static int getHarveyStartingPointY() {
        return HARVEY_STARTING_POSITION.y();
    }

    public static int getLeahStartingPointX() {
        return LEAH_STARTING_POSITION.x();
    }

    public static int getLeahStartingPointY() {
        return LEAH_STARTING_POSITION.y();
    }

    public static int getSebastianStartingPointX() {
        return SEBASTIAN_STARTING_POSITION.x();
    }

    public static int getSebastianStartingPointY() {
        return SEBASTIAN_STARTING_POSITION.y();
    }

    public static int getAbigailStartingPointX() {
        return ABIGAIL_STARTING_POSITION.x();
    }

    public static int getAbigailStartingPointY() {
        return ABIGAIL_STARTING_POSITION.y();
    }



    private static void minerInitializer(int mineId) {
        int additionalX = getAdditionalX(mineId);
        int additionalY = getAdditionalY(mineId);

        int baseX = MINE_BL.x() + additionalX;
        int baseY = MINE_BL.y() + additionalY;

        for (int y = baseY; y < MINE_TR.y() + additionalY; y++) {
            int localY = y - baseY;

            for (int localX = 0; localX <= 18; localX++) {
                TileType type;
                boolean movable;

                switch (localY) {
                    case 0:
                        if (localX >= 5 && localX <= 13) {
                            type = TileType.FENCE;
                            movable = false;
                        } else continue;
                        break;

                    case 1:
                        if (localX >= 2 && localX <= 16) {
                            if (localX > 5 && localX < 14) {
                                type = TileType.MINE;
                                movable = true;
                            } else {
                                type = TileType.FENCE;
                                movable = false;
                            }
                        } else continue;
                        break;

                    case 2:
                        if (localX >= 2 && localX <= 17) {
                            boolean isFence = localX == 2 || localX > 14;
                            type = isFence ? TileType.FENCE : TileType.MINE;
                            movable = !isFence;
                        } else continue;
                        break;

                    case 3:
                        if (localX >= 1 && localX <= 18) {
                            boolean isFence = (localX == 1 || localX == 2 || localX == 17 || localX == 18);
                            type = isFence ? TileType.FENCE : TileType.MINE;
                            movable = !isFence;
                        } else continue;
                        break;

                    case 4: case 5: case 6: case 7:
                        boolean isFence = (localX == 0 || localX == 1 || localX == 17 || localX == 18);
                        type = isFence ? TileType.FENCE : TileType.MINE;
                        movable = !isFence;
                        break;

                    case 8:
                        if (localX >= 2 && localX <= 17) {
                            boolean isRiver = localX < 4 || localX >= 15;
                            type = isRiver ? TileType.RIVER : TileType.MINE;
                            movable = !isRiver;
                        } else continue;
                        break;

                    default:
                        if (localX != 5) {
                            type = TileType.FENCE;
                            movable = false;
                        } else continue;
                }

                setTile(y, baseX + localX, type, movable);
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

                TileType type = (isBorderX || isBorderY) ? /*TileType.FENCE*/TileType.RIVER : TileType.RIVER;

                tiles.get(y).set(x, new Tile.Builder()
                    .setPosition(new Position(x, y))
                    .setType(type)
                    .setMovable(/*type != TileType.FENCE*/false) //*
                    .build());
            }
        }
    }

    private static void shippingBinInitializer(int landId) {

        int row = landId / 3;
        int col = landId % 3;

        int centerX = col * 75 + 37;
        int centerY = row * 75 + 37;

        addShippingBinsAround(centerX, centerY);
    }

    private static void addShippingBinsAround(int x, int y) {
        setShippingBinTile(x, y - 36);
        setShippingBinTile(x, y + 37);
        setShippingBinTile(x - 36, y);
        setShippingBinTile(x + 36, y);
    }

    private static void setShippingBinTile(int x, int y) {
        tiles.get(y).set(x, new Tile.Builder()
            .setPosition(new Position(x, y))
            .setType(TileType.SHIPPING_BIN)
            .setMovable(false)
            .build());
    }



    private static boolean canBePlanted(Position position) {
        return tiles.get(position.x()).get(position.y()).getType() == TileType.GROUND
                && tiles.get(position.x()).get(position.y()).getObject() == null;
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

        tiles.get(75).get(105).setMovable(true);
        tiles.get(105).get(75).setMovable(true);
        return new Farm(tiles, lake, cottage, quarry, greenhouse, List.of(sebastian, abigail, harvey, leah));
    }

    public static JsonArray buildTilesJson(List<List<Tile>> tiles) {
        JsonArray result = new JsonArray();

        for (List<Tile> row : tiles) {
            for (Tile tile : row) {
                JsonObject tileJson = new JsonObject();

                tileJson.addProperty("x", tile.getPosition().x());
                tileJson.addProperty("y", tile.getPosition().y());
                tileJson.addProperty("t", TileType.getNumberFromTileType(tile.getType()));
                tileJson.addProperty("p", tile.isPlowed() ? 1 : 0);
                tileJson.addProperty("m", tile.isMovable() ? 1 : 0);
                tileJson.addProperty("o", Foraging.getNumberFromTileObject(tile.getObject()));

                result.add(tileJson);
            }
        }
        return result;
    }

    public static List<List<Tile>> fromJson(String json) {
        Gson gson = new Gson();
        JsonArray rows = gson.fromJson(json, JsonArray.class);

        List<List<Tile>> tileMap = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            JsonArray row = rows.get(i).getAsJsonArray();
            List<Tile> tileRow = new ArrayList<>();

            for (int j = 0; j < row.size(); j++) {
                JsonObject tileJson = row.get(j).getAsJsonObject();

                int x = tileJson.get("x").getAsInt();
                int y = tileJson.get("y").getAsInt();
                int typeNum = tileJson.get("t").getAsInt();
                boolean plowed = tileJson.get("p").getAsBoolean();
                boolean movable = tileJson.get("m").getAsBoolean();
                int objectNum = tileJson.get("o").getAsInt();

                Tile tile;

                if (validateTileData(x, y, typeNum, objectNum)) {
                    tile = new Tile.Builder()
                        .setPosition(new Position(x, y))
                        .setType(TileType.values()[typeNum - 1])
                        .setMovable(movable)
                        .setObject(getTileObjectFromNumber(objectNum))
                        .build();
                } else {
                    tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.GROUND)
                        .setMovable(true)
                        .setObject(null)
                        .build();
                }


                if (plowed) {
                    tile.plow();
                }

                tileRow.add(tile);
            }

            tileMap.add(tileRow);
        }

        return tileMap;
    }

    public static TileObject getTileObjectFromNumber(int num) {
        if (num == 0) {
            return null;
        } else if (num >= 1 && num <= 42) {
            return new Seed(SeedInfo.values()[num - 1]);
        } else if (num >= 43 && num <= 65) {
            return new ForagingCrop(ForagingCropInfo.values()[num - 43]);
        } else if (num >= 66) {
            return new ForagingMineral(ForagingMineralInfo.values()[num - 66]);
        }
        return null;
    }

    private static boolean validateTileData(int x, int y, int typeNum, int objectNum) {
        if (x < 0 || y < 0 || x >= 225 || y >= 225) {
            return false;
        }

        if (typeNum < 1 || typeNum > TileType.values().length) {
            return false;
        }

        if (objectNum < 1 || objectNum > 82) {
            return false;
        }

        return true;
    }
}
