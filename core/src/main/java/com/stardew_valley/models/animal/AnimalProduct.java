package com.stardew_valley.models.animal;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;

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

    @Override
    public Texture getTexture() {
        return switch (animalProductType) {
            case EGG -> AssetManager.getAssetManager().getEgg();
            case BIG_EGG -> AssetManager.getAssetManager().getBigEgg();
            case DUCK_EGG -> AssetManager.getAssetManager().getDuckEgg();
            case DUCK_FEATHER -> AssetManager.getAssetManager().getDuckFeather();
            case RABBIT_WOOL -> AssetManager.getAssetManager().getRabbitWool();
            case RABBIT_LEG -> AssetManager.getAssetManager().getRabbitLeg();
            case DINOSAUR_EGG -> AssetManager.getAssetManager().getDinosaurEgg();
            case MILK -> AssetManager.getAssetManager().getMilk();
            case BIG_MILK -> AssetManager.getAssetManager().getBigMilk();
            case GOAT_MILK -> AssetManager.getAssetManager().getGoatMilk();
            case BIG_GOAT_MILK -> AssetManager.getAssetManager().getBigGoatMilk();
            case SHEEP_WOOL -> AssetManager.getAssetManager().getSheepWool();
            case TRUFFLE -> AssetManager.getAssetManager().getTruffle();
        };
    }
}
