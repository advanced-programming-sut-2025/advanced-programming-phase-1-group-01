package models.animal;

import models.character.player.Player;

public class Hen extends Animal {
    public Hen(AnimalInfo animalInfo, Player owner, AnimalHouse shelter) {
        super(animalInfo, owner, shelter);
    }

    @Override
    public int getDayModulus() {
        return 1;
    }
}
