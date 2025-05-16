package models.initializer;

import models.Position;
import models.building.*;
import models.character.NPC.*;
import models.character.player.Player;
import models.enums.Direction;

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
        for (int i = VILLAGE_TP.x(); i < VILLAGE_BR.x(); i++) {
            tiles.add(new ArrayList<>());
        }

        roomMaker(VILLAGE_TP, VILLAGE_BR, TileType.GROUND, true);
        roomMaker(FIRST_ROOM_TP, FIRST_ROOM_BR, TileType.COTTAGE, true);
        roomMaker(SECOND_ROOM_TP, SECOND_ROOM_BR, TileType.COTTAGE, true);
        roomMaker(THIRD_ROOM_TP, THIRD_ROOM_BR, TileType.COTTAGE, true);
        roomMaker(FOURTH_ROOM_TP, FOURTH_ROOM_BR, TileType.COTTAGE, true);
        roomMaker(FIFTH_ROOM_TP, FIFTH_ROOM_BR, TileType.COTTAGE, true);


        wallMaker(FIRST_ROOM_TP.x() - 1, FIRST_ROOM_BR.x() + 1, FIRST_ROOM_TP.y() - 1,
                TileType.WALL, false, false);
        wallMaker(FIRST_ROOM_TP.x() - 1, FIRST_ROOM_BR.x() + 1, FIRST_ROOM_BR.y() + 1,
                TileType.WALL, false, true);
        wallMaker(FIRST_ROOM_TP.y() - 1, FIRST_ROOM_BR.y() + 1, FIRST_ROOM_TP.x() - 1,
                TileType.WALL, false, false);
        wallMaker(FIRST_ROOM_TP.y() - 1, FIRST_ROOM_BR.y() + 1, FIRST_ROOM_BR.x() + 1,
                TileType.WALL, false, false);

        wallMaker(SECOND_ROOM_TP.x() - 1, SECOND_ROOM_BR.x() + 1, SECOND_ROOM_TP.y() - 1,
                TileType.WALL, false, false);
        wallMaker(SECOND_ROOM_TP.x() - 1, SECOND_ROOM_BR.x() + 1, SECOND_ROOM_BR.y() + 1,
                TileType.WALL, false, true);
        wallMaker(SECOND_ROOM_TP.y() - 1, SECOND_ROOM_BR.y() + 1, SECOND_ROOM_TP.x() - 1,
                TileType.WALL, false, false);
        wallMaker(SECOND_ROOM_TP.y() - 1, SECOND_ROOM_BR.y() + 1, SECOND_ROOM_BR.x() + 1,
                TileType.WALL, false, false);

        wallMaker(THIRD_ROOM_TP.x() - 1, THIRD_ROOM_BR.x() + 1, THIRD_ROOM_TP.y() - 1,
                TileType.WALL, false, false);
        wallMaker(THIRD_ROOM_TP.x() - 1, THIRD_ROOM_BR.x() + 1, THIRD_ROOM_BR.y() + 1,
                TileType.WALL, false, true);
        wallMaker(THIRD_ROOM_TP.y() - 1, THIRD_ROOM_BR.y() + 1, THIRD_ROOM_TP.x() - 1,
                TileType.WALL, false, false);
        wallMaker(THIRD_ROOM_TP.y() - 1, THIRD_ROOM_BR.y() + 1, THIRD_ROOM_BR.x() + 1,
                TileType.WALL, false, false);

        wallMaker(FOURTH_ROOM_TP.x() - 1, FOURTH_ROOM_BR.x() + 1, FOURTH_ROOM_TP.y() - 1,
                TileType.WALL, false, false);
        wallMaker(FOURTH_ROOM_TP.x() - 1, FOURTH_ROOM_BR.x() + 1, FOURTH_ROOM_BR.y() + 1,
                TileType.WALL, false, true);
        wallMaker(FOURTH_ROOM_TP.y() - 1, FOURTH_ROOM_BR.y() + 1, FOURTH_ROOM_TP.x() - 1,
                TileType.WALL, false, false);
        wallMaker(FOURTH_ROOM_TP.y() - 1, FOURTH_ROOM_BR.y() + 1, FOURTH_ROOM_BR.x() + 1,
                TileType.WALL, false, false);

        wallMaker(FIFTH_ROOM_TP.x() - 1, FIFTH_ROOM_BR.x() + 1, FIFTH_ROOM_TP.y() - 1,
                TileType.WALL, false, false);
        wallMaker(FIFTH_ROOM_TP.x() - 1, FIFTH_ROOM_BR.x() + 1, FIFTH_ROOM_BR.y() + 1,
                TileType.WALL, false, true);
        wallMaker(FIFTH_ROOM_TP.y() - 1, FIFTH_ROOM_BR.y() + 1, FIFTH_ROOM_TP.x() - 1,
                TileType.WALL, false, false);
        wallMaker(FIFTH_ROOM_TP.y() - 1, FIFTH_ROOM_BR.y() + 1, FIFTH_ROOM_BR.x() + 1,
                TileType.WALL, false, false);

    }


    public static void roomMaker(Position start, Position end, TileType type, boolean isMovable) {
        for (int i = start.x(); i < end.x(); i++) {
            for (int j = start.y(); j < end.y(); j++) {
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
        Room1 room1 = new Room1(new Position(FIRST_ROOM_TP.x(), FIRST_ROOM_BR.y()));
        Room2 room2 = new Room2(new Position(SECOND_ROOM_TP.x(), SECOND_ROOM_BR.y()));
        Room3 room3 = new Room3(new Position(THIRD_ROOM_TP.x(), THIRD_ROOM_BR.y()));
        Room4 room4 = new Room4(new Position(FOURTH_ROOM_TP.x(), FOURTH_ROOM_BR.y()));
        Room5 room5 = new Room5(new Position(FIFTH_ROOM_TP.x(), FIFTH_ROOM_BR.y()));
    }

    private static void InitializeSebastian(List<Player> players) {
        NPC sebastian = new NPC(NPCType.SEBASTIAN, room1,
                new Position((FIRST_ROOM_TP.x() + FIRST_ROOM_BR.x()) / 2, FIRST_ROOM_BR.y() + 2),
                Direction.DOWN, List.of(new NPCQuest(NPCQuestType.SEBASTIAN_1),
                new NPCQuest(NPCQuestType.SEBASTIAN_2), new NPCQuest(NPCQuestType.SEBASTIAN_3)));
    }
    private static void InitializeAbigail(List<Player> players) {
        NPC abigail = new NPC(NPCType.ABIGAIL, room2,
                new Position(SECOND_ROOM_BR.x() + 2, (SECOND_ROOM_TP.y() + SECOND_ROOM_BR.y()) / 2),
                Direction.RIGHT, List.of(new NPCQuest(NPCQuestType.ABIGAIL_1),
                new NPCQuest(NPCQuestType.ABIGAIL_2), new NPCQuest(NPCQuestType.ABIGAIL_3)));
    }
    private static void InitializeHarvey(List<Player> players) {
        NPC harvey = new NPC(NPCType.HARVEY, room3,
                new Position((THIRD_ROOM_TP.x() + THIRD_ROOM_BR.x()) / 2, THIRD_ROOM_BR.x() - 2),
                Direction.UP, List.of(new NPCQuest(NPCQuestType.HARVEY_1),
                new NPCQuest(NPCQuestType.HARVEY_2), new NPCQuest(NPCQuestType.HARVEY_3)));
    }
    private static void InitializeLeah(List<Player> players) {
        NPC leah = new NPC(NPCType.LEAH, room4,
                new Position((FOURTH_ROOM_TP.x() + FOURTH_ROOM_BR.x()) / 2, FOURTH_ROOM_BR.y() - 2),
                Direction.UP, List.of(new NPCQuest(NPCQuestType.LEAH_1),
                new NPCQuest(NPCQuestType.LEAH_2), new NPCQuest(NPCQuestType.LEAH_3)));
    }
    private static void InitializeRobin(List<Player> players) {
        NPC robin = new NPC(NPCType.ROBIN, room5,
                new Position(FIFTH_ROOM_TP.x() - 2, (FIFTH_ROOM_TP.y() + FIRST_ROOM_BR.y()) / 2),
                Direction.LEFT, List.of(new NPCQuest(NPCQuestType.ROBIN_1),
                new NPCQuest(NPCQuestType.ROBIN_2), new NPCQuest(NPCQuestType.ROBIN_3)));
    }

    private static void InitializeFriendship(List<Player> players) {
        for (Player player : players) {
            robin.addPlayerToTalk(player);
            leah.addPlayerToTalk(player);
            sebastian.addPlayerToTalk(player);
            abigail.addPlayerToTalk(player);
            harvey.addPlayerToTalk(player);

            robin.addGiftDailyStatus(player);
            leah.addGiftDailyStatus(player);
            sebastian.addGiftDailyStatus(player);
            abigail.addGiftDailyStatus(player);
            harvey.addGiftDailyStatus(player);
            robin.addGiftDailyStatus(player);

            robin.addFriendshipAndLevel(player);
            leah.addFriendshipAndLevel(player);
            sebastian.addFriendshipAndLevel(player);
            abigail.addFriendshipAndLevel(player);
            harvey.addFriendshipAndLevel(player);
        }
    }

//    private static void initializeQuests() {
//        List<NPCQuestType> quests = Arrays.asList(NPCQuestType.values());
//        int count = 1;
//        for (NPC npc : List.of(sebastian, abigail, harvey, leah, robin)) {
//            for (int i = count; i < count + 3; i++) {
//                npc.addQuest(i % 3, quests.get(i));
//            }
//            count += 3;
//        }
//    }

    public static NPCVillage initializeVillage(List<Player> players) {
        initializeTiles();
        InitializeBuilding();
        InitializeSebastian(players);
        InitializeHarvey(players);
        InitializeLeah(players);
        InitializeAbigail(players);
        InitializeRobin(players);
//        initializeQuests();
        InitializeFriendship(players);
        return new NPCVillage(tiles, List.of(sebastian, abigail, harvey, leah, robin), room1, room2, room3, room4, room5);
    }
}
