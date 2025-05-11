package models.initializer;

import models.Game;
import models.Position;
import models.building.*;
import models.character.NPC.NPC;
import models.character.NPC.NPCQuest;
import models.character.NPC.NPCType;
import models.character.player.Player;
import models.enums.Direction;
import models.relations.FriendshipNetwork;

import java.util.ArrayList;
import java.util.Arrays;
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

    private static Room1 room1;
    private static Room2 room2;
    private static Room3 room3;
    private static Room4 room4;
    private static Room5 room5;

    private static NPC sebastian;
    private static NPC abigail;
    private static NPC harvey;
    private static NPC leah;
    private static NPC robin;

    private final static List<List<Tile>> tiles = new ArrayList<>();

    public static void initializeTiles() {
        for (int i = VILLAGE_TP.getX(); i < VILLAGE_BR.getX(); i++) {
            tiles.add(new ArrayList<>());
        }

        roomMaker(VILLAGE_TP, VILLAGE_BR, TileType.GROUND, true);
        roomMaker(FIRST_ROOM_TP, FIRST_ROOM_BR, TileType.COTTAGE, true);
        roomMaker(SECOND_ROOM_TP, SECOND_ROOM_BR, TileType.COTTAGE, true);
        roomMaker(THIRD_ROOM_TP, THIRD_ROOM_BR, TileType.COTTAGE, true);
        roomMaker(FOURTH_ROOM_TP, FOURTH_ROOM_BR, TileType.COTTAGE, true);
        roomMaker(FIFTH_ROOM_TP, FIFTH_ROOM_BR, TileType.COTTAGE, true);


        wallMaker(FIRST_ROOM_TP.getX() - 1, FIRST_ROOM_BR.getX() + 1, FIRST_ROOM_TP.getY() - 1,
                TileType.WALL, false, false);
        wallMaker(FIRST_ROOM_TP.getX() - 1, FIRST_ROOM_BR.getX() + 1, FIRST_ROOM_BR.getY() + 1,
                TileType.WALL, false, true);
        wallMaker(FIRST_ROOM_TP.getY() - 1, FIRST_ROOM_BR.getY() + 1, FIRST_ROOM_TP.getX() - 1,
                TileType.WALL, false, false);
        wallMaker(FIRST_ROOM_TP.getY() - 1, FIRST_ROOM_BR.getY() + 1, FIRST_ROOM_BR.getX() + 1,
                TileType.WALL, false, false);

        wallMaker(SECOND_ROOM_TP.getX() - 1, SECOND_ROOM_BR.getX() + 1, SECOND_ROOM_TP.getY() - 1,
                TileType.WALL, false, false);
        wallMaker(SECOND_ROOM_TP.getX() - 1, SECOND_ROOM_BR.getX() + 1, SECOND_ROOM_BR.getY() + 1,
                TileType.WALL, false, true);
        wallMaker(SECOND_ROOM_TP.getY() - 1, SECOND_ROOM_BR.getY() + 1, SECOND_ROOM_TP.getX() - 1,
                TileType.WALL, false, false);
        wallMaker(SECOND_ROOM_TP.getY() - 1, SECOND_ROOM_BR.getY() + 1, SECOND_ROOM_BR.getX() + 1,
                TileType.WALL, false, false);

        wallMaker(THIRD_ROOM_TP.getX() - 1, THIRD_ROOM_BR.getX() + 1, THIRD_ROOM_TP.getY() - 1,
                TileType.WALL, false, false);
        wallMaker(THIRD_ROOM_TP.getX() - 1, THIRD_ROOM_BR.getX() + 1, THIRD_ROOM_BR.getY() + 1,
                TileType.WALL, false, true);
        wallMaker(THIRD_ROOM_TP.getY() - 1, THIRD_ROOM_BR.getY() + 1, THIRD_ROOM_TP.getX() - 1,
                TileType.WALL, false, false);
        wallMaker(THIRD_ROOM_TP.getY() - 1, THIRD_ROOM_BR.getY() + 1, THIRD_ROOM_BR.getX() + 1,
                TileType.WALL, false, false);

        wallMaker(FOURTH_ROOM_TP.getX() - 1, FOURTH_ROOM_BR.getX() + 1, FOURTH_ROOM_TP.getY() - 1,
                TileType.WALL, false, false);
        wallMaker(FOURTH_ROOM_TP.getX() - 1, FOURTH_ROOM_BR.getX() + 1, FOURTH_ROOM_BR.getY() + 1,
                TileType.WALL, false, true);
        wallMaker(FOURTH_ROOM_TP.getY() - 1, FOURTH_ROOM_BR.getY() + 1, FOURTH_ROOM_TP.getX() - 1,
                TileType.WALL, false, false);
        wallMaker(FOURTH_ROOM_TP.getY() - 1, FOURTH_ROOM_BR.getY() + 1, FOURTH_ROOM_BR.getX() + 1,
                TileType.WALL, false, false);

        wallMaker(FIFTH_ROOM_TP.getX() - 1, FIFTH_ROOM_BR.getX() + 1, FIFTH_ROOM_TP.getY() - 1,
                TileType.WALL, false, false);
        wallMaker(FIFTH_ROOM_TP.getX() - 1, FIFTH_ROOM_BR.getX() + 1, FIFTH_ROOM_BR.getY() + 1,
                TileType.WALL, false, true);
        wallMaker(FIFTH_ROOM_TP.getY() - 1, FIFTH_ROOM_BR.getY() + 1, FIFTH_ROOM_TP.getX() - 1,
                TileType.WALL, false, false);
        wallMaker(FIFTH_ROOM_TP.getY() - 1, FIFTH_ROOM_BR.getY() + 1, FIFTH_ROOM_BR.getX() + 1,
                TileType.WALL, false, false);

    }

    public static void roomMaker(Position start, Position end, TileType type, boolean isMovable) {
        for (int i = start.getX(); i < end.getX(); i++) {
            for (int j = start.getY(); j < end.getY(); j++) {
                Tile tile = new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(type)
                        .setMovable(isMovable)
                        .setBuilding(null)
                        .setObject(null)
                        .build();
                tiles.get(i).set(j, tile);
            }
        }
    }

    public static void wallMaker(int start, int end, int constantVar, TileType type, boolean isMovable, boolean hasDoor) {
        for (int i = start; i < end; i++) {
            Tile tile = new Tile.Builder()
                    .setPosition(new Position(i, constantVar))
                    .setType(type)
                    .setMovable(isMovable)
                    .setBuilding(null)
                    .setObject(null)
                    .build();
            if (!hasDoor || (i != (start + end) / 2)) tiles.get(i).set(constantVar, tile);
        }
    }

    private static void InitializeBuilding() {
        Room1 room1 = new Room1(new Position(FIRST_ROOM_TP.getX(), FIRST_ROOM_BR.getY()));
        Room2 room2 = new Room2(new Position(SECOND_ROOM_TP.getX(), SECOND_ROOM_BR.getY()));
        Room3 room3 = new Room3(new Position(THIRD_ROOM_TP.getX(), THIRD_ROOM_BR.getY()));
        Room4 room4 = new Room4(new Position(FOURTH_ROOM_TP.getX(), FOURTH_ROOM_BR.getY()));
        Room5 room5 = new Room5(new Position(FIFTH_ROOM_TP.getX(), FIFTH_ROOM_BR.getY()));
    }

    private static void InitializeSebastian() {
        NPC sebastian = new NPC(NPCType.SEBASTIAN, room1,
                new Position((FIRST_ROOM_TP.getX() + FIRST_ROOM_BR.getX()) / 2, FIRST_ROOM_BR.getY() + 2),
                Direction.DOWN);
    }
    private static void InitializeAbigail() {
        NPC abigail = new NPC(NPCType.ABIGAIL, room2,
                new Position(SECOND_ROOM_BR.getX() + 2, (SECOND_ROOM_TP.getY() + SECOND_ROOM_BR.getY()) / 2),
                Direction.RIGHT);
    }
    private static void InitializeHarvey() {
        NPC harvey = new NPC(NPCType.HARVEY, room3,
                new Position((THIRD_ROOM_TP.getX() + THIRD_ROOM_BR.getX()) / 2, THIRD_ROOM_BR.getX() - 2),
                Direction.UP);
    }
    private static void InitializeLeah() {
        NPC leah = new NPC(NPCType.LEAH, room4,
                new Position((FOURTH_ROOM_TP.getX() + FOURTH_ROOM_BR.getX()) / 2, FOURTH_ROOM_BR.getY() - 2),
                Direction.UP);
    }
    private static void InitializeRobin() {
        NPC robin = new NPC(NPCType.ROBIN, room5,
                new Position(FIFTH_ROOM_TP.getX() - 2, (FIFTH_ROOM_TP.getY() + FIRST_ROOM_BR.getY()) / 2),
                Direction.LEFT);
    }

    private static void InitializeFriendship(List<Player> players) {
        for (Player player : players) {
            FriendshipNetwork.establishFriendship(player, robin, 0);
            FriendshipNetwork.establishFriendship(player, leah, 0);
            FriendshipNetwork.establishFriendship(player, sebastian, 0);
            FriendshipNetwork.establishFriendship(player, abigail, 0);
            FriendshipNetwork.establishFriendship(player, harvey, 0);

            robin.addPlayerToTalk(player);
            leah.addPlayerToTalk(player);
            sebastian.addPlayerToTalk(player);
            abigail.addPlayerToTalk(player);
            harvey.addPlayerToTalk(player);
        }
    }

    private static void initializeQuests() {
        List<NPCQuest> quests = Arrays.asList(NPCQuest.values());
        int count = 1;
        for (NPC npc : List.of(sebastian, abigail, harvey, leah, robin)) {
            for (int i = count; i < count + 3; i++) {
                npc.addQuest(i % 3, quests.get(i));
            }
            count += 3;
        }
    }
}
