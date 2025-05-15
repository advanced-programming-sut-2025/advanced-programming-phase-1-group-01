package models.farming;

import java.util.List;
import java.util.Random;

public enum MixedSeed implements FarmingEnum {
    SPRING_SEEDS("Spring Seeds", List.of(CropInfo.CAULIFLOWER, CropInfo.PARSNIP, CropInfo.POTATO, CropInfo.BLUE_JAZZ, CropInfo.TULIP)),
    SUMMER_SEEDS("Summer Seeds", List.of(CropInfo.CORN, CropInfo.HOT_PEPPER, CropInfo.RADISH, CropInfo.WHEAT, CropInfo.POPPY, CropInfo.SUNFLOWER, CropInfo.SUMMER_SPANGLE)),
    FALL_SEEDS("Fall Seeds", List.of(CropInfo.ARTICHOKE, CropInfo.CORN, CropInfo.EGGPLANT, CropInfo.PUMPKIN, CropInfo.SUNFLOWER, CropInfo.FAIRY_ROSE)),
    WINTER_SEEDS("Winter Seeds", List.of(CropInfo.POWDERMELON));

    private final String name;
    private final List<CropInfo> crops;

    MixedSeed(String name, List<CropInfo> crops) {
        this.name = name;
        this.crops = crops;
    }

    public List<CropInfo> getCrops() {
        return crops;
    }

    private static final Random random = new Random();

    public CropInfo getRandomCrop() {
        int index = random.nextInt(crops.size());
        return crops.get(index);
    }

    @Override
    public String getName() {
        return name;
    }
}
