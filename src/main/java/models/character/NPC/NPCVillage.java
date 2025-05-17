package models.character.NPC;

import models.Item;
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
        NPCQuestType questType = quest.getQuestType();
        switch (questType) {
            case SEBASTIAN_1 -> {
                if (!player.hasEnoughItem("iron bar", 50)) {
                    return "You don't have enough iron bar";
                }
                player.getInventory().getSlot("iron bar").removeQuantity(50);
                player.getInventory().addItem("diamond", 2);
            }
            case SEBASTIAN_2 -> {
                if (!player.hasEnoughItem("pumpkin", 1)) {
                    return "You don't have enough pumpkin";
                }
                player.getInventory().getSlot("pumpkin").removeQuantity(1);
                player.addCoin(5000);
            }
            case SEBASTIAN_3 -> {
                if (!player.hasEnoughItem("stone", 150)) {
                    return "You don't have enough stone";
                }
                player.getInventory().getSlot("stone").removeQuantity(150);
                player.getInventory().addItem("quarts", 50);
            }
            case ABIGAIL_1 -> {
                if (!player.hasEnoughItem("gold bar", 1)) {
                    return "You don't have gold bar";
                }
                player.getInventory().getSlot("gold bar").removeQuantity(1);
                npc.advanceFriendshipLevel(player, 200);
            }
            case ABIGAIL_2 -> {
                if (!player.hasEnoughItem("pumpkin", 1)) {
                    return "You don't have pumpkin";
                }
                player.getInventory().getSlot("pumpkin").removeQuantity(1);
                player.addCoin(500);
            }
            case ABIGAIL_3 -> {
                if (!player.hasEnoughItem("wheat", 50)) {
                    return "You don't have wheat";
                }
                player.getInventory().getSlot("wheat").removeQuantity(50);
                player.getInventory().addItem("iridium sprinkler", 1);

            }
            case LEAH_1 -> {
                if (!player.hasEnoughItem("hard wood", 10)) {
                    return "You don't have hard wood";
                }
                player.getInventory().getSlot("hard wood").removeQuantity(10);
                player.addCoin(500);
            }
            case LEAH_2 -> {
                if (!player.hasEnoughItem("salmon", 1)) {
                    return "You don't have salmon";
                }
                player.getInventory().getSlot("salmon").removeQuantity(1);
                player.getInventory().addItem("dinner salmon", 1);
            }
            case LEAH_3 -> {
                if (!player.hasEnoughItem("normal wood", 200)) {
                    return "You don't have wood";
                }
                player.getInventory().getSlot("normal wood").removeQuantity(200);
                player.getInventory().addItem("deluxe scarecrow", 1);
            }
            case ROBIN_1 -> {
                if (!player.hasEnoughItem("normal wood", 80)) {
                    return "You don't have wood";
                }
                player.getInventory().getSlot("normal wood").removeQuantity(80);
                player.addCoin(1000);
            }
            case ROBIN_2 -> {
                if (!player.hasEnoughItem("iron bar", 10)) {
                    return "You don't have iron bar";
                }
                player.getInventory().getSlot("iron bar").removeQuantity(10);
                player.getInventory().addItem("bee house", 3);
            }
            case ROBIN_3 -> {
                if (!player.hasEnoughItem("normal wood", 1000)) {
                    return "You don't have normal wood";
                }
                player.getInventory().getSlot("normal wood").removeQuantity(1000);
                player.addCoin(25000);
            }
            case HARVEY_1 -> {
                if (player.hasEnoughItem("corn", 12)) {
                    player.getInventory().getSlot("corn").removeQuantity(12);
                }
                else if (player.hasEnoughItem("potato", 12)) {
                    player.getInventory().getSlot("potato").removeQuantity(12);
                }
                else if (player.hasEnoughItem("garlic", 12)) {
                    player.getInventory().getSlot("garlic").removeQuantity(12);
                } else return "You don't have vegetables";
                player.addCoin(750);
            }


            case HARVEY_2 -> {
                if (!player.hasEnoughItem("salmon", 1)) {
                    return "You don't have salmon";
                }
                player.getInventory().getSlot("salmon").removeQuantity(1);
                npc.advanceFriendshipLevel(player, 200);
            }

            case HARVEY_3 -> {
                if (!player.hasEnoughItem("wine", 1)) {
                    return "You don't have wine";
                }
                player.getInventory().getSlot("wine").removeQuantity(1);
                player.getInventory().addItem("salad", 5);
            }
        }

        quest.completeQuest();
        return "Quest completed successfully";
    }
}