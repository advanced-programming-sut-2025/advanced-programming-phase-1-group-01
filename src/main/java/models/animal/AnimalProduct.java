package models.animal;

import models.ingredients.Ingredient;

public class AnimalProduct extends Ingredient {
    private final AnimalProductType animalProductType;
    private final AnimalProductQuality animalProductQuality;

    public AnimalProduct(AnimalProductType animalProductType, AnimalProductQuality animalProductQuality, int amount) {
        super(amount);
        this.animalProductType = animalProductType;
        this.animalProductQuality = animalProductQuality;
    }

    public AnimalProductType getAnimalProductType() {
        return animalProductType;
    }

    public AnimalProductQuality getAnimalProductQuality() {
        return animalProductQuality;
    }

    public void increaseAmount(int increment) {
        amount += increment;
    }
}
