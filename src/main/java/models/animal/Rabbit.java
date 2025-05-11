package models.animal;

import models.building.Building;
import models.character.player.Player;

public class Rabbit extends Animal {
    public Rabbit(AnimalType animalType, Player owner, AnimalHouse shelter) {
        super(animalType, owner, shelter);
    }

    @Override
    public int getDayModulus() {
        return 4;
    }
}
