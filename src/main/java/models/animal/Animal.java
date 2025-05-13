package models.animal;

import models.Position;
import models.character.Character;
import models.character.player.Player;
import models.enums.Direction;
import models.Random;

public class Animal extends Character {
    protected final AnimalInfo animalInfo;
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


    public Animal(AnimalInfo animalInfo, Player owner, AnimalHouse shelter) {
        this.animalInfo = animalInfo;
        this.owner = owner;
        this.shelter = shelter;
        this.position = findAPlace(shelter);
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

    public boolean isAValidIncrement() {
        return true;
    }

    public AnimalProductQuality getAnimalProductQuality() {
//        double QualityNumber = getRelationshipLevel(owner) * (0.5 + 0.5 * random.rand(0.0, 1.0)) / 1000;
//        if (QualityNumber <= 0.5) return AnimalProductQuality.NORMAL;
//        if (QualityNumber <= 0.7) return AnimalProductQuality.SILVER;
//        if (QualityNumber <= 0.9) return AnimalProductQuality.GOLD;
        return AnimalProductQuality.IRIDIUM;
    }

}
