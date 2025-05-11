package models.character.NPC;

import models.Position;
import models.building.Building;
import models.character.Character;
import models.character.player.Player;
import models.enums.Direction;

import java.util.HashMap;
import java.util.Map;

public class NPC extends Character {
    private NPCType type;
    private Building home;
    private Position position;
    private Direction direction;
    private final Map<Player, Boolean> hasTalkedToday = new HashMap<>();
    private final Map<Integer, NPCQuest> quests;

    public NPC(NPCType type, Building home, Position position, Direction direction) {
        this.type = type;
        this.home = home;
        this.position = position;
        this.direction = direction;
        quests = new HashMap<>();
    }

    public void addPlayerToTalk(Player player) {
        hasTalkedToday.put(player, false);
    }

    public void addQuest(int numberOfQuest, NPCQuest quest) {
        quests.put(numberOfQuest, quest);
    }

    public void advanceFriendship(Player player, int amount) {
        relationships.put(player, relationships.get(player) + amount);
        if (relationships.get(player) > 799) relationships.put(player, 799);
        if (relationships.get(player) >= 200) quests.get(2).activateQuest();
    }
}