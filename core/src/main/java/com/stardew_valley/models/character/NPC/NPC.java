package com.stardew_valley.models.character.NPC;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew_valley.models.AI.AIChat;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.ReactionUI;
import com.stardew_valley.models.building.Building;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.weather.Weather;
import com.stardew_valley.network.GameClient;

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
    private float globalDelta = 0f;
    private final Texture chat_tex = AssetManager.getAssetManager().getChat();
    private final Texture plus_tex = AssetManager.getAssetManager().getPlus();
    private String message = "";
    private boolean hasWalk = false;
    private float targetPositionX;
    private float targetPositionY;

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
        targetPositionX = this.x;
        targetPositionY = this.y;
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


    public void handleGifting(Player player) {
        if (isBeingGifted) return;

        isBeingGifted = true;
        giftingStateTime = 0f;
        //@ add gift
        giftingOffset = 0f;
    }

    public void handleFinishingQuest() {
        if (isBeingGifted) return;
        GameClient.getInstance().sendAddAmountRequest(1f, "QUEST");

        isBeingGifted = true;
        giftingStateTime = 0f;
        giftingOffset = 0f;
        Repository.getRepo().getCurrentUser().getPlayer().advanceQuestDone();
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

    public void draw(Batch batch, float delta) {
        globalStateTime += delta;
        globalDelta = delta;
        batch.draw(getTextureRegion(), getX(), getY() + giftingOffset);
        if (hasWalk) {
            moveTo(targetPositionX, targetPositionY);
        }

        batch.draw(plus_tex, plusX(), plusY(), 9, 9);

        if (!message.isEmpty()) {
            batch.draw(chat_tex, chatX(), chatY(), 9, 9);
        }

    }

    public TextureRegion getTextureRegion() {
        float frameTime = isMoving ? globalStateTime : 0f;

        switch (type) {
            case SEBASTIAN:
                return getDirectionFrame(
                    AssetManager.getAssetManager().getSebastianUpAnimation(),
                    AssetManager.getAssetManager().getSebastianDownAnimation(),
                    AssetManager.getAssetManager().getSebastianLeftAnimation(),
                    AssetManager.getAssetManager().getSebastianRightAnimation(),
                    frameTime
                );
            case ABIGAIL:
                return getDirectionFrame(
                    AssetManager.getAssetManager().getAbigailUpAnimation(),
                    AssetManager.getAssetManager().getAbigailDownAnimation(),
                    AssetManager.getAssetManager().getAbigailLeftAnimation(),
                    AssetManager.getAssetManager().getAbigailRightAnimation(),
                    frameTime
                );
            case HARVEY:
                return getDirectionFrame(
                    AssetManager.getAssetManager().getHarveyUpAnimation(),
                    AssetManager.getAssetManager().getHarveyDownAnimation(),
                    AssetManager.getAssetManager().getHarveyLeftAnimation(),
                    AssetManager.getAssetManager().getHarveyRightAnimation(),
                    frameTime
                );
            default:
                return getDirectionFrame(
                    AssetManager.getAssetManager().getLeahUpAnimation(),
                    AssetManager.getAssetManager().getLeahDownAnimation(),
                    AssetManager.getAssetManager().getLeahLeftAnimation(),
                    AssetManager.getAssetManager().getLeahRightAnimation(),
                    frameTime
                );
        }
    }

    private TextureRegion getDirectionFrame(
        com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> upAnim,
        com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> downAnim,
        com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> leftAnim,
        com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> rightAnim,
        float frameTime
    ) {
        switch (direction) {
            case UP:
                return upAnim.getKeyFrame(frameTime, true);
            case DOWN:
                return downAnim.getKeyFrame(frameTime, true);
            case LEFT:
                return leftAnim.getKeyFrame(frameTime, true);
            case RIGHT:
                return rightAnim.getKeyFrame(frameTime, true);
            default:
                return downAnim.getKeyFrame(frameTime, true);
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

    public void moveTo(float targetX, float targetY) {
        float dx = targetX - this.x;
        float dy = targetY - this.y;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                direction = Direction.RIGHT;
            } else if (dx < 0) {
                direction = Direction.LEFT;
            }
        } else {
            if (dy > 0) {
                direction = Direction.UP;
            } else if (dy < 0) {
                direction = Direction.DOWN;
            }
        }


        List<List<Tile>> tiles = Repository.getRepo().getCurrentGame().getFarm().getTiles();
        int startX = (int) (this.x / 16);
        int startY = (int) (this.y / 16);
        int endX = (int) (targetX / 16);
        int endY = (int) (targetY / 16);

        boolean canMove = true;
        int steps = Math.max(Math.abs(endX - startX), Math.abs(endY - startY));
        for (int i = 1; i <= steps; i++) {
            int checkX = startX + i * Integer.signum(endX - startX);
            int checkY = startY + i * Integer.signum(endY - startY);
            if (checkX < 0 || checkY < 0 || checkX >= tiles.size() || checkY >= tiles.get(0).size()) {
                canMove = false;
                break;
            }
            Tile tile = tiles.get(checkX).get(checkY);
            if (tile.getType() != TileType.GROUND || !tile.isMovable()) {
                canMove = false;
                break;
            }
        }

        if (canMove) {
            float speed = 50f;
            float distance = (float) Math.sqrt(dx*dx + dy*dy);
            if (distance > 1f) {
                float moveX = (dx / distance) * speed * globalDelta;
                float moveY = (dy / distance) * speed * globalDelta;
                this.x += moveX;
                this.y += moveY;
                isMoving = true;
            } else {
                this.x = targetX;
                this.y = targetY;
                isMoving = false;
            }
        } else {
            isMoving = false;
            hasWalk = false;
            //System.out.println("555");
        }
    }

    public void setHasWalk(float x, float y) {
        hasWalk = true;
        targetPositionX = x;
        targetPositionY = y;
        moveTo(x, y);
    }

    public boolean isWalking() {
        return isMoving;
    }

}
