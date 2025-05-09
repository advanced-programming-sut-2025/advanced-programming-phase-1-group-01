package models.initializer;

import models.Position;
import models.building.Tile;
import models.building.TileType;

import java.util.ArrayList;
import java.util.List;

public class VillageInitializer {
    private final static Position VILLAGE_TP = new Position(0, 0);
    private final static Position VILLAGE_BR = new Position(75, 75);
    private final static Position FIRST_ROOM_TP = new Position(37, 17);
    private final static Position FIRST_ROOM_BR = new Position(42, 22);
    private final static Position SECOND_ROOM_TP = new Position(12, 47);
    private final static Position SECOND_ROOM_BR = new Position(15, 52);
    private final static Position THIRD_ROOM_TP = new Position(24, 68);
    private final static Position THIRD_ROOM_BR = new Position(29, 73);
    private final static Position FOURTH_ROOM_TP = new Position(49, 68);
    private final static Position FOURTH_ROOM_BR = new Position(55, 73);
    private final static Position FIFTH_ROOM_TP = new Position(61, 68);
    private final static Position FIFTH_ROOM_BR = new Position(66, 73);

    private final static List<List<Tile>> tiles = new ArrayList<>();

    public static void initializeTiles() {
        for (int i = VILLAGE_TP.getX(); i < VILLAGE_BR.getX(); i++) {
            tiles.add(new ArrayList<>());
        }

        for (int i = VILLAGE_TP.getX(); i < VILLAGE_BR.getX(); i++) {
            for (int j = VILLAGE_TP.getY(); j < VILLAGE_BR.getY(); j++) {
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

        for (int j = FIRST_ROOM_TP.getY(); j < FIRST_ROOM_BR.getY(); j++) {
            for (int i = FIRST_ROOM_TP.getX(); i < FIRST_ROOM_BR.getX(); i++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.MINE)
                        .setMovable(true)
                        .setBuilding(null)
                        .setObject(null)
                        .build();
                tiles.get(i).set(j, tile);
            }
        }
    }

}
