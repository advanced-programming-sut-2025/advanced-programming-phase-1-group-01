package models.animal;

import models.building.Building;
import models.character.player.Player;

public class Pig extends Animal {
    public Pig(AnimalInfo animalInfo, Player owner, AnimalHouse shelter) {
        super(animalInfo, owner, shelter);
    }

    @Override
    public int getDayModulus() {
        return 1;
    }
}
