package models.animal;

import models.Position;
import models.character.Character;
import models.character.player.Player;
import models.enums.Direction;
import models.enums.Emoji;
//import models.random;
import models.relations.Friendship;
import models.relations.FriendshipLevel;
import models.Random;

public class Animal extends Character {
    protected final AnimalInfo animalInfo;
    protected final String name;
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


    public Animal(AnimalInfo animalInfo,String name, Player owner, AnimalHouse shelter) {
        this.animalInfo = animalInfo;
        this.owner = owner;
        this.shelter = shelter;
        this.position = findAPlace(shelter);
        this.name = name;
    }

    public AnimalProductType getAnimalProductType() {
        return animalProductType;
    }

    public void setProduct(AnimalProductType product) {
//        if ((getRelationshipLevel(owner) + 150 * random.rand(0.0, 1.0)) / 1500 >= 1) {
//            this.animalProductType = animalType.getWealthyProduct();
//        } else this.animalProductType = product;
    }

    private Position findAPlace(AnimalHouse shelter) {
        int counter = 0;
        Position position = null;
        while (position == null && counter < 1000) {
            int randomX = Random.rand(shelter.getTopLeftCorner().x(), shelter.getBottomRightCorner().x());
            int randomY = Random.rand(shelter.getTopLeftCorner().y(), shelter.getBottomRightCorner().y());
            if (shelter.isThatTileEmpty(new Position(randomX, randomY))) {
                position = new Position(randomX, randomY);
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
        isHungry = false;
    }

    public void feedByHay() {
        isHungry = false;
    }

    public boolean hasAnyProduct() {
        return hasProduct;
    }

    public AnimalProductType collectProduct() {
        hasProduct = false;
        return getAnimalProductType();
    }

    protected int getDayModulus() {
        return 4;
    }

    public final void updateDay() {
        periodicDay = (periodicDay + 1) % getDayModulus();
    }

    public void DailyResetAndStart() {

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
//        double QualityNumber = getRelationshipLevel(owner) * (0.5 + 0.5 * random.rand(0.0, 1.0)) / 1000;
//        if (QualityNumber <= 0.5) return AnimalProductQuality.NORMAL;
//        if (QualityNumber <= 0.7) return AnimalProductQuality.SILVER;
//        if (QualityNumber <= 0.9) return AnimalProductQuality.GOLD;
        return ProductQuality.IRIDIUM;
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
}
