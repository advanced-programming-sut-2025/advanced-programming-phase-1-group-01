package com.stardew_valley.models.character.NPC;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.stardew_valley.models.AI.AIChat;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.Building;
import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.weather.Weather;

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
    private boolean isMoving = false;
    private float globalStateTime = 0f;
    private final Texture chat_tex = AssetManager.getAssetManager().getChat();
    private final Texture plus_tex = AssetManager.getAssetManager().getPlus();
    private String message = "";

    private boolean isBeingGifted = false;
    private float giftingStateTime = 0f;
    private float giftingOffset = 0f;

    private float x;
    private float y;


    public NPC(NPCType type, Building home, Position position, Direction direction, List<NPCQuest> quests) {
        this.type = type;
        this.home = home;
        this.position = position;
        this.direction = direction;
        this.quests = quests;
        quests.get(0).activateQuest();
    }

    public NPC(NPCType type, Position position, Direction direction, List<NPCQuest> quests) {
        this(type, null, position, direction, quests);
        this.x = position.x() * 16;
        this.y = position.y() * 16;
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
        return AIChat.messageGenerator(message, season, weather, hour, getFriendshipLevel());
    }

    public void update(float delta) {
        if (isBeingGifted) {
            giftingStateTime += delta;

            float duration = 0.5f;
            float progress = giftingStateTime / duration;
            giftingOffset = (float)(Math.sin(progress * Math.PI) * 10);

            if (giftingStateTime >= duration) {
                isBeingGifted = false;
                giftingOffset = 0f;
            }
        }
    }


    public void handleGifting(Item giftItem) {
        if (isBeingGifted) return;

        isBeingGifted = true;
        giftingStateTime = 0f;
        Player player = Repository.getRepo().getCurrentGame().getCurrentPlayer();
        friendshipLevels.put(player, friendshipLevels.getOrDefault(player, 0));
        giftingOffset = 0f;
    }

    public void handleFinishingQuest() {
        if (isBeingGifted) return;

        isBeingGifted = true;
        giftingStateTime = 0f;
        giftingOffset = 0f;
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

    private float plusX() {
        return getX() + 3;
    }

    private float plusY() {
        return getY() + 30;
    }

    private float chatX() {
        return getX() - 10;
    }

    private float chatY() {
        return getY() + 30;
    }

    public void draw(Batch batch) {
        batch.draw(getTexture(), getX(), getY() + giftingOffset);

        batch.draw(plus_tex, plusX(), plusY(), 9, 9);

        if (!message.isEmpty()) {
            batch.draw(chat_tex, chatX(), chatY(), 9, 9);
        }

    }

    public Texture getTexture() {
        float frameTime = isMoving ? globalStateTime : 0f;

        switch (type) {
            case SEBASTIAN:
                return AssetManager.getAssetManager()
                    .getSebastianLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();
            case ABIGAIL:
                return AssetManager.getAssetManager()
                    .getAbigailLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();
            case HARVEY:
                return AssetManager.getAssetManager()
                    .getHarveyLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();
            default:
                return AssetManager.getAssetManager()
                    .getLeahLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isInsidePlusIcon(float x, float y) {
        float px = plusX();
        float py = plusY();
        return x >= px && x < px + 9 && y >= py && y < py + 9;
    }

    public boolean isInsideChatIcon(float x, float y) {
        float px = chatX();
        float py = chatY();
        return x >= px && x < px + 9 && y >= py && y < py + 9;
    }

    public int getFriendshipLevel() {
        return 0;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
