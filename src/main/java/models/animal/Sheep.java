package models.animal;

import models.building.Building;
import models.character.player.Player;

public class Sheep extends Animal {
    public Sheep(AnimalType animalType, Player owner, AnimalHouse shelter) {
        super(animalType, owner, shelter);
    }

    @Override
    public int getDayModulus() {
        return 3;
    }

    @Override
    public boolean isAValidIncrement() {
//        return getRelationshipLevel(owner) >= 70;
        return true;
    }
}
