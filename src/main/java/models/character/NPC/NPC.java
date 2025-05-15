package models.character.NPC;

import models.AI.AIChat;
import models.Position;
import models.building.Building;
import models.character.Character;
import models.character.player.Player;
import models.character.player.Slot;
import models.dateTime.Season;
import models.enums.Direction;
import models.weather.Weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPC extends Character {
    private NPCType type;
    private Building home;
    private Position position;
    private Direction direction;
    private final Map<Player, Boolean> hasTalkedToday = new HashMap<>();
    private final Map<Player, Boolean> hasReceivedToday = new HashMap<>();
    private final List<NPCQuest> quests;
    private final Map<Player, Integer> friendshipLevels = new HashMap<>();
    private boolean isDayCounterForThirdQuestStarted = false;
    private int dayCounter = 0;

    public NPC(NPCType type, Building home, Position position, Direction direction, List<NPCQuest> quests) {
        this.type = type;
        this.home = home;
        this.position = position;
        this.direction = direction;
        this.quests = quests;
        quests.get(0).activateQuest();
    }

    public void addFriendshipAndLevel(Player player) {
        friendshipLevels.put(player, 0);
    }

    public void addGiftDailyStatus(Player player) {
        hasReceivedToday.put(player, false);
    }

    public void addPlayerToTalk(Player player) {
        hasTalkedToday.put(player, false);
    }

    private void acceptGift(Player player, Slot slot) {
        if (!hasReceivedToday.get(player)) advanceFriendshipLevel(player, 50);
        if (type.isFavorite(slot.getItem())) advanceFriendshipLevel(player, 200);
        slot.removeQuantity(1);
    }

    public void advanceFriendshipLevel(Player player, int amount) {
        if (friendshipLevels.get(player) + amount <= 799) {
            friendshipLevels.put(player, friendshipLevels.get(player) + amount);
            if (friendshipLevels.get(player) >= 200) {
                quests.get(1).activateQuest();
                quests.get(1).setOwner(player);
                isDayCounterForThirdQuestStarted = true;
                quests.get(2).setOwner(player);
            }
        }
    }


    public Position getPosition() {
        return position;
    }

    public int getFriendshipLevel(Player player) {
        return friendshipLevels.get(player);
    }
    

    public String talkWithPlayer(Player player, String message, Season season, Weather weather, int hour) {
        if (!hasTalkedToday.get(player)) {
            advanceFriendshipLevel(player, 20);
        }
        if (player.isNearTo(position)) {
            return AIChat.messageGenerator(message, season, weather, hour, friendshipLevels.get(player));
        } else return "You are not near to NPC";
    }

    public String giftNPC(Player player, String gift) {
        Slot slot = player.getInventory().getSlot(gift);
        if (slot == null) return "invalid gift";
        if (slot.getQuantity() >= 0) {
            acceptGift(player, slot);
        }
        return "You gifted successfully";
    }

    public NPCType getType() {
        return type;
    }

    public void advanceDayCounter() {
        if (isDayCounterForThirdQuestStarted) {
            dayCounter++;
            if (dayCounter >= 28) {
                quests.get(2).activateQuest();
            }
        }
    }

    public List<NPCQuest> getQuests() {
        return quests;
    }

    public void resetForNewDay() {
        hasTalkedToday.replaceAll((p, v) -> false);
        hasReceivedToday.replaceAll((p, v) -> false);
    }
}