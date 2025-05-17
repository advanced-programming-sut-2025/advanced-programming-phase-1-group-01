package models.animal;

import models.Item;
import models.ingredients.Ingredient;

public class AnimalProduct implements Item {
    private final AnimalProductType animalProductType;
    private final ProductQuality productQuality;

    public AnimalProduct(AnimalProductType animalProductType, ProductQuality productQuality) {
        this.animalProductType = animalProductType;
        this.productQuality = productQuality;
    }

    public AnimalProductType getAnimalProductType() {
        return animalProductType;
    }

    public ProductQuality getAnimalProductQuality() {
        return productQuality;
    }


    @Override
    public String getName() {
        return animalProductType.toString();
    }

    @Override
    public int getPrice() {
        return animalProductType.getBasePrice();
    }
}
