package models.animal;

import models.building.Building;
import models.character.player.Player;

public class Dinosaur extends Animal {
    public Dinosaur(AnimalType animalType, Player owner, AnimalHouse shelter) {
        super(animalType, owner, shelter);
    }

    @Override
    public int getDayModulus() {
        return 7;
    }
}
