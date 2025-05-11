package models.animal;

import models.character.player.Player;

public class Hen extends Animal {
    public Hen(AnimalType animalType, Player owner, AnimalHouse shelter) {
        super(animalType, owner, shelter);
    }

    @Override
    public int getDayModulus() {
        return 1;
    }
}
