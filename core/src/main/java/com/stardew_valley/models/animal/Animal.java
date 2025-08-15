package com.stardew_valley.models.animal;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.TileObject;
import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.enums.Emoji;
import com.stardew_valley.models.Random;

public class Animal extends Character implements TileObject {
    protected final AnimalInfo animalInfo;
    protected String name;
    protected Player owner;
    protected Position position;
    protected Direction direction;
    protected boolean isHungry = true;
    protected boolean isOut = false;
    protected boolean hasProduct = true;
    protected boolean hasBeenPetted = false;
    protected AnimalHouse shelter;
    protected AnimalProductType animalProductType;
    protected int periodicDay = 0;
    protected int friendshipLevel = 0;
    protected float x;
    protected float y;

    private boolean isBeingFed = false;
    private float stateTime = 0f;
    private Texture hayTexture = null;

    private boolean isBeingPetted = false;
    private float pettingStateTime = 0f;
    private float pettingOffsetY = 0f;

    private float globalStateTime = 0f;

    private boolean isMovingToOwner = false;

    private float moveStateTime = 0f;
    private final float moveDuration = 2f;

    private float moveStartX, moveStartY;
    private float moveTargetX, moveTargetY;






    public Animal(AnimalInfo animalInfo,String name, Player owner, AnimalHouse shelter) {
        this.animalInfo = animalInfo;
        this.owner = owner;
        this.shelter = shelter;
        this.position = findAPlace(shelter);
        this.name = name;
    }

    public Animal(AnimalInfo animalInfo, Player owner, Position position, float x, float y) {
        this.animalInfo = animalInfo;
        this.owner = owner;
        this.direction = Direction.LEFT;
        this.position = position;
        this.x = x;
        this.y = y;
        setProduct(animalInfo.getProducts().getFirst());
    }

    public AnimalProductType getAnimalProductType() {
        return animalProductType;
    }

    public void setProduct(AnimalProductType product) {
        if ((getFriendshipLevel() + 150 * Random.rand(0.0, 1.0)) / 1500 >= 1) {
            this.animalProductType = animalInfo.getWealthyProduct();
        } else this.animalProductType = product;
    }

    private Position findAPlace(AnimalHouse shelter) {
        int counter = 0;
        Position position = null;
        while (position == null && counter < 1000) {
            int randomX = Random.rand(shelter.getTopLeftCorner().x(), shelter.getBottomRightCorner().x());
            int randomY = Random.rand(shelter.getTopLeftCorner().y(), shelter.getBottomRightCorner().y());
            if (shelter.isThatTileEmpty(new Position(randomY, randomX))) {
                position = new Position(randomY, randomX);
            }
            counter++;
        }
        return position;
    }

    public AnimalInfo getAnimalInfo() {
        return animalInfo;
    }

    public AnimalHouse getShelter() {
        return shelter;
    }

    public void petting() {
        hasBeenPetted = true;
        advanceFriendshipLevel(15);
//        FriendshipNetwork.increaseFriendshipLevel(this, owner, 15);
    }

    public void checkAnimalStatus() {
//        if (isHungry) FriendshipNetwork.decreaseFriendshipLevel(this, owner, 20);
//        if (isOut) FriendshipNetwork.increaseFriendshipLevel(this, owner, 20);
//        if (!hasBeenPetted) FriendshipNetwork.increaseFriendshipLevel(this, owner, 10);

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void moveAnimal(Position newPosition) {
        setPosition(newPosition);
        if (isInShelter(newPosition)) {
            isOut = false;
        } else {
            isOut = true;
            isHungry = false;
        }
    }



    public void feedByHay() {
        if (isBeingFed) return;

        hayTexture = AssetManager.getAssetManager().getHay().getTexture();
        stateTime = 0f;
        isBeingFed = true;
    }




    public boolean hasAnyProduct() {
        return hasProduct;
    }

    public AnimalProductType collectProduct() {
        hasProduct = false;
        setProduct(animalInfo.getProducts().get(0));
        return getAnimalProductType();
    }

    protected int getDayModulus() {
        return 4;
    }

    public final void updateDay() {
        periodicDay = (periodicDay + 1) % getDayModulus();
    }

    public void DailyResetAndStart() {
        isHungry = true;
        hasBeenPetted = false;
    }

    public void advanceFriendshipLevel(int amount) {
        if (friendshipLevel + amount <= 1000) friendshipLevel += amount;
    }

    private void decreaseFriendshipLevel(int amount) {
        if (friendshipLevel - amount >= 0) friendshipLevel -= amount;
    }

    public boolean isAValidIncrement() {
        return true;
    }

    public ProductQuality getAnimalProductQuality() {
//        double QualityNumber = getRelationshipLevel(owner) * (0.5 + 0.5 * Random.rand(0.0, 1.0)) / 1000;
//        if (QualityNumber <= 0.5) return ProductQuality.REGULAR;
//        if (QualityNumber <= 0.7) return ProductQuality.SILVER;
//        if (QualityNumber <= 0.9) return ProductQuality.GOLD;
        return ProductQuality.IRIDIUM;
    }

    public static ProductQuality getProductQuality(double num) {
        if (num < 0) return null;

        if (num <= 0.5) return ProductQuality.REGULAR;
        else if (num < 0.7) return ProductQuality.SILVER;
        else if (num < 0.9) return ProductQuality.GOLD;
        else return ProductQuality.REGULAR;
    }

    public String getAnimalName() {
        return name;
    }

    public String hasBeenPetted() {
        if (hasBeenPetted) return Emoji.TRUE.getSymbol();
        else return Emoji.FALSE.getSymbol();
    }

    public String isHungry() {
        if (isHungry) return Emoji.TRUE.getSymbol();
        else return Emoji.FALSE.getSymbol();
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public boolean getIsHungry() {
        return isHungry;
    }

    public int calculateSellPrice() {
        return (int)(animalInfo.getPrice() * (friendshipLevel / 1000.0 + 0.3));
    }

    private boolean isInShelter(Position position) {
        return position.x() >= shelter.getTopLeftCorner().x()
                && position.x() <= shelter.getBottomRightCorner().x()
                && position.y() >= shelter.getTopLeftCorner().y()
                && position.y() <= shelter.getBottomRightCorner().y();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public Texture getTexture() {
        float frameTime = isMovingToOwner ? globalStateTime : 0f;

        switch (animalInfo) {
            case COW:
                return AssetManager.getAssetManager()
                    .getCowLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();

            case RABBIT:
                return AssetManager.getAssetManager()
                    .getRabbitLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();

            case HEN:
                return AssetManager.getAssetManager()
                    .getHenLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();

            case DUCK:
                return AssetManager.getAssetManager()
                    .getDuckLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();

            case SHEEP:
                return AssetManager.getAssetManager()
                    .getSheepLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();

            case PIG:
                return AssetManager.getAssetManager()
                    .getPigLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();

            case DINOSAUR:
                return AssetManager.getAssetManager()
                    .getDinoLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();

            default:
                return AssetManager.getAssetManager()
                    .getgoatLeftAnimation()
                    .getKeyFrame(frameTime)
                    .getTexture();
        }
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void update(float delta) {
        globalStateTime += delta;
        if (isBeingFed) {
            stateTime += delta;

            if (stateTime >= 2f) {
                isBeingFed = false;
                isHungry = false;
            }
        }

        if (isBeingPetted) {
            pettingStateTime += delta;

            float duration = 0.5f;
            float progress = pettingStateTime / duration;
            pettingOffsetY = (float)(Math.sin(progress * Math.PI) * 10);

            if (pettingStateTime >= duration) {
                isBeingPetted = false;
                hasBeenPetted = true;
                pettingOffsetY = 0f;
            }
        }

        if (isMovingToOwner) {
            moveStateTime += delta;

            float progress = Math.min(moveStateTime / moveDuration, 1f);
            float newX = moveStartX + (moveTargetX - moveStartX) * progress;
            float newY = moveStartY + (moveTargetY - moveStartY) * progress;

            setX(newX);
            setY(newY);

            if (moveStateTime >= moveDuration) {
                isMovingToOwner = false;
            }
        }
    }

    public void draw(Batch batch) {
        float drawX = getY();
        float drawY = getX() + pettingOffsetY;

        batch.draw(getTexture(), drawX, drawY);

        if (isBeingFed && hayTexture != null) {
            float alpha = Math.max(0, 1 - (stateTime / 2f));

            Color oldColor = new Color(batch.getColor());
            batch.setColor(1f, 1f, 1f, alpha);

            float hayX = getX();
            float hayY = getY() - 16;
            batch.draw(hayTexture, hayY, hayX, 10, 10);

            batch.setColor(oldColor);
        }
    }

    public void handlePetting() {
        if (isBeingPetted || hasBeenPetted) return;

        isBeingPetted = true;
        pettingStateTime = 0f;
        pettingOffsetY = 0f;
    }



    public boolean isNearPlayer() {
        float distance = (owner.getX() - getY())*((owner.getX() - getY())) + (owner.getY() - getX())*((owner.getY() - getX()));
        return distance < 16 * 16 * 25 * 2;
    }

    public void sellAnimal() {
        owner.increaseCoins(calculateSellPrice());
        owner.getAnimals().remove(this);
    }

    public void moveToOwner() {
        if (isMovingToOwner) return;

        isMovingToOwner = true;
        moveStateTime = 0f;

        moveStartX = getX();
        moveStartY = getY();

        moveTargetX = owner.getY();
        moveTargetY = owner.getX();
    }

    public void setX(float x) {
        position = new Position((int)(x / 16), (int)(y / 16));
        this.x = x;
    }


    public void setY(float y) {
        position = new Position((int)(x / 16), (int)(y / 16));
        this.y = y;
    }

    public boolean isPetted() {
        return hasBeenPetted;
    }


}
