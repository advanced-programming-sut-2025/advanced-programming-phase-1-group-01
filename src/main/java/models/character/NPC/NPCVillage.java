package models.character.NPC;

import models.building.*;
import models.character.player.Player;

import java.util.ArrayList;
import java.util.List;

public class NPCVillage extends Maps {
    private final Room1 room1;
    private final Room2 room2;
    private final Room3 room3;
    private final Room4 room4;
    private final Room5 room5;
    private final List<NPC> NPCs;

    public NPCVillage(List<List<Tile>> tiles, List<NPC> NPCs, Room1 room1, Room2 room2, Room3 room3, Room4 room4, Room5 room5) {
        super(tiles);
        this.room1 = room1;
        this.room2 = room2;
        this.room3 = room3;
        this.room4 = room4;
        this.room5 = room5;
        this.NPCs = NPCs;
    }

    public Room1 getRoom1() {
        return room1;
    }

    public Room2 getRoom2() {
        return room2;
    }

    public Room3 getRoom3() {
        return room3;
    }

    public Room4 getRoom4() {
        return room4;
    }

    public Room5 getRoom5() {
        return room5;
    }

    public List<NPC> getNPCs() {
        return NPCs;
    }

    public NPC getNPCIdByName(String name) {
        return switch (name.toLowerCase()) {
            case "sebastian" -> NPCs.get(0);
            case "abigail" -> NPCs.get(1);
            case "harvey" -> NPCs.get(2);
            case "leah" -> NPCs.get(3);
            case "robin" -> NPCs.get(4);
            default -> null;
        };
    }

    public String getListedNPCFriendships(Player player) {
        StringBuilder listedNPCFriendships = new StringBuilder();
        listedNPCFriendships.append("Friendships levels:\n");
        for (NPC npc : NPCs) {
            String formattedLine = String.format("%-" + 10 + "s", npc.getType().getName());
            listedNPCFriendships.append(formattedLine).append(" : ").append(npc.getFriendshipLevel(player)).append("\n");
        }
        return listedNPCFriendships.toString();
    }

    public String getListedQuests(Player player) {
        StringBuilder listedQuests = new StringBuilder();
        listedQuests.append("Activated for you quests:\n");
        for (NPC npc : NPCs) {
            for (NPCQuest npcQuest : npc.getQuests()) {
                if (npcQuest.isActive() && (npcQuest.getOwner() == null || npcQuest.getOwner().equals(player))) {
                    String formattedLine = String.format("%-" + 13 + "s", npcQuest.getQuestType().name().toLowerCase().
                            replace("_", " "));
                    listedQuests.append(formattedLine).append(" : ").append(npcQuest.getQuestType()
                            .getMissionNumber()).append("\n");
                }
            }
        }
        return listedQuests.toString();
    }

    public String finishQuest(Player player, int index) {
        NPCQuest quest = null;
        NPC npc = null;
        outer:
        for (NPC Npc : NPCs) {
            for (NPCQuest npcQuest : Npc.getQuests()) {
                if (npcQuest.getQuestType().getMissionNumber() == index) {
                    quest = npcQuest;
                    npc = Npc;
                    break outer;
                }
            }
        }
        if (quest == null) return "quest not found";
        if (!quest.isActive()) return "The Quest is not active";
        if (!quest.getOwner().equals(player)) return "You are not the owner of this quest";
        if (!player.isNearTo(npc.getPosition())) return "You are not near the npc";
        //@ check if the player has not enough items
        //@ add rewards to inventory
        quest.completeQuest();
        return "Quest completed successfully";
    }
}