package models.animal;

import models.building.Building;
import models.character.player.Player;

public class Dinosaur extends Animal {
    public Dinosaur(AnimalInfo animalInfo, Player owner, AnimalHouse shelter) {
        super(animalInfo, owner, shelter);
    }

    @Override
    public int getDayModulus() {
        return 7;
    }
}
