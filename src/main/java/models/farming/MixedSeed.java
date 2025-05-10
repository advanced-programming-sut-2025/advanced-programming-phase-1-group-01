package models.farming;

import java.util.List;
import java.util.Random;

public enum MixedSeed implements FarmingEnum {
    SPRING_SEEDS(List.of(CropType.CAULIFLOWER, CropType.PARSNIP, CropType.POTATO, CropType.BLUE_JAZZ, CropType.TULIP)),
    SUMMER_SEEDS(List.of(CropType.CORN, CropType.HOT_PEPPER, CropType.RADISH, CropType.WHEAT, CropType.POPPY, CropType.SUNFLOWER, CropType.SUMMER_SPANGLE)),
    FALL_SEEDS(List.of(CropType.ARTICHOKE, CropType.CORN, CropType.EGGPLANT, CropType.PUMPKIN, CropType.SUNFLOWER, CropType.FAIRY_ROSE)),
    WINTER_SEEDS(List.of(CropType.POWDERMELON));

    private final List<CropType> crops;

    MixedSeed(List<CropType> crops) {
        this.crops = crops;
    }

    public List<CropType> getCrops() {
        return crops;
    }

    private static final Random random = new Random();

    public CropType getRandomCrop() {
        int index = random.nextInt(crops.size());
        return crops.get(index);
    }
}
