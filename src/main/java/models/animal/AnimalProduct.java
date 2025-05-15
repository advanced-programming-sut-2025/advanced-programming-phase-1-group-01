package models.animal;

import models.ingredients.Ingredient;

public class AnimalProduct extends Ingredient {
    private final AnimalProductType animalProductType;
    private final ProductQuality productQuality;

    public AnimalProduct(AnimalProductType animalProductType, ProductQuality productQuality, int amount) {
        super(amount);
        this.animalProductType = animalProductType;
        this.productQuality = productQuality;
    }

    public AnimalProductType getAnimalProductType() {
        return animalProductType;
    }

    public ProductQuality getAnimalProductQuality() {
        return productQuality;
    }

    public void increaseAmount(int increment) {
        amount += increment;
    }
}
