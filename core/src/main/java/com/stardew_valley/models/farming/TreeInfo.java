package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.foraging.ForagingTreeInfo;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public enum TreeInfo implements FarmingEnum {
    APRICOT_TREE("Apricot Tree", null/*"Apricot Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.APRICOT, 1, Season.SPRING, Map.of(
        1, AssetManager.getAssetManager().getApricotStage1(),
        2, AssetManager.getAssetManager().getApricotStage2(),
        3, AssetManager.getAssetManager().getApricotStage3(),
        4, AssetManager.getAssetManager().getApricotStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getApricotStage5Spring(),
        "Summer", AssetManager.getAssetManager().getApricotStage5Summer(),
        "Fall", AssetManager.getAssetManager().getApricotStage5Fall(),
        "Winter", AssetManager.getAssetManager().getApricotStage5Winter(),
        "Fruit", AssetManager.getAssetManager().getApricotStage5Fruit()
    ), AssetManager.getAssetManager().getApricotTreeLightning(), null),
    CHERRY_TREE("Cherry Tree", null/*"Cherry Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.CHERRY, 1, Season.SPRING, Map.of(
        1, AssetManager.getAssetManager().getCherryStage1(),
        2, AssetManager.getAssetManager().getCherryStage2(),
        3, AssetManager.getAssetManager().getCherryStage3(),
        4, AssetManager.getAssetManager().getCherryStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getCherryStage5Spring(),
        "Summer", AssetManager.getAssetManager().getCherryStage5Summer(),
        "Fall", AssetManager.getAssetManager().getCherryStage5Fall(),
        "Winter", AssetManager.getAssetManager().getCherryStage5Winter(),
        "Fruit", AssetManager.getAssetManager().getCherryStage5Fruit()
    ), AssetManager.getAssetManager().getCherryTreeLightning(), null),

    BANANA_TREE("Banana Tree", null/*"Banana Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.BANANA, 1, Season.SUMMER, Map.of(
        1, AssetManager.getAssetManager().getBananaStage1(),
        2, AssetManager.getAssetManager().getBananaStage2(),
        3, AssetManager.getAssetManager().getBananaStage3(),
        4, AssetManager.getAssetManager().getBananaStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getBananaStage5Spring(),
        "Summer", AssetManager.getAssetManager().getBananaStage5Summer(),
        "Fall", AssetManager.getAssetManager().getBananaStage5Fall(),
        "Winter", AssetManager.getAssetManager().getBananaStage5Winter(),
        "Fruit", AssetManager.getAssetManager().getBananaStage5Fruit()
    ), AssetManager.getAssetManager().getBananaTreeLightning(), null),

    MANGO_TREE("Mango Tree", null/*"Mango Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.MANGO, 1, Season.SUMMER, Map.of(
        1, AssetManager.getAssetManager().getMangoStage1(),
        2, AssetManager.getAssetManager().getMangoStage2(),
        3, AssetManager.getAssetManager().getMangoStage3(),
        4, AssetManager.getAssetManager().getMangoStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getMangoStage5Spring(),
        "Summer", AssetManager.getAssetManager().getMangoStage5Summer(),
        "Fall", AssetManager.getAssetManager().getMangoStage5Fall(),
        "Winter", AssetManager.getAssetManager().getMangoStage5Winter(),
        "Fruit", AssetManager.getAssetManager().getMangoStage5Fruit()
    ), AssetManager.getAssetManager().getMangoTreeLightning(), null),

    ORANGE_TREE("Orange Tree", null/*"Orange Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.ORANGE, 1, Season.SUMMER, Map.of(
        1, AssetManager.getAssetManager().getOrangeStage1(),
        2, AssetManager.getAssetManager().getOrangeStage2(),
        3, AssetManager.getAssetManager().getOrangeStage3(),
        4, AssetManager.getAssetManager().getOrangeStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getOrangeStage5Spring(),
        "Summer", AssetManager.getAssetManager().getOrangeStage5Summer(),
        "Fall", AssetManager.getAssetManager().getOrangeStage5Fall(),
        "Winter", AssetManager.getAssetManager().getOrangeStage5Winter(),
        "Fruit", AssetManager.getAssetManager().getOrangeStage5Fruit()
    ), AssetManager.getAssetManager().getOrangeTreeLightning(), null),

    PEACH_TREE("Peach Tree", null/*"Peach Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.PEACH, 1, Season.SUMMER, Map.of(
        1, AssetManager.getAssetManager().getPeachStage1(),
        2, AssetManager.getAssetManager().getPeachStage2(),
        3, AssetManager.getAssetManager().getPeachStage3(),
        4, AssetManager.getAssetManager().getPeachStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getPeachStage5Spring(),
        "Summer", AssetManager.getAssetManager().getPeachStage5Summer(),
        "Fall", AssetManager.getAssetManager().getPeachStage5Fall(),
        "Winter", AssetManager.getAssetManager().getPeachStage5Winter(),
        "Fruit", AssetManager.getAssetManager().getPeachStage5Fruit()
    ), AssetManager.getAssetManager().getPeachTreeLightning(), null),

    APPLE_TREE("Apple Tree", null/*"Apple Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.APPLE, 1, Season.FALL, Map.of(
        1, AssetManager.getAssetManager().getAppleStage1(),
        2, AssetManager.getAssetManager().getAppleStage2(),
        3, AssetManager.getAssetManager().getAppleStage3(),
        4, AssetManager.getAssetManager().getAppleStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getAppleStage5Spring(),
        "Summer", AssetManager.getAssetManager().getAppleStage5Summer(),
        "Fall", AssetManager.getAssetManager().getAppleStage5Fall(),
        "Winter", AssetManager.getAssetManager().getAppleStage5Winter(),
        "Fruit", AssetManager.getAssetManager().getAppleStage5Fruit()
    ), AssetManager.getAssetManager().getAppleTreeLightning(), null),

    POMEGRANATE_TREE("Pomegranate Tree", null/*"Pomegranate Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.POMEGRANATE, 1, Season.FALL, Map.of(
        1, AssetManager.getAssetManager().getPomegranateStage1(),
        2, AssetManager.getAssetManager().getPomegranateStage2(),
        3, AssetManager.getAssetManager().getPomegranateStage3(),
        4, AssetManager.getAssetManager().getPomegranateStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getPomegranateStage5Spring(),
        "Summer", AssetManager.getAssetManager().getPomegranateStage5Summer(),
        "Fall", AssetManager.getAssetManager().getPomegranateStage5Fall(),
        "Winter", AssetManager.getAssetManager().getPomegranateStage5Winter(),
        "Fruit", AssetManager.getAssetManager().getPomegranateStage5Fruit()
    ), AssetManager.getAssetManager().getPomegranateTreeLightning(), null),

    OAK_TREE("Oak Tree", ForagingTreeInfo.ACORNS, new int[]{7, 7, 7, 7}, 28, FruitInfo.OAK_RESIN, 7, Season.SPECIAL, Map.of(
        1, AssetManager.getAssetManager().getOakStage1(),
        2, AssetManager.getAssetManager().getOakStage2(),
        3, AssetManager.getAssetManager().getOakStage3(),
        4, AssetManager.getAssetManager().getOakStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getOakStage5Spring(),
        "Summer", AssetManager.getAssetManager().getOakStage5Summer(),
        "Fall", AssetManager.getAssetManager().getOakStage5Fall(),
        "Winter", AssetManager.getAssetManager().getOakStage5Winter()
    ), null,
        Map.of(
            "Spring", AssetManager.getAssetManager().getOakStumpSpring(),
            "Winter", AssetManager.getAssetManager().getOakStumpWinter()
        )),

    MAPLE_TREE("Maple Tree", ForagingTreeInfo.MAPLE_SEEDS, new int[]{7, 7, 7, 7}, 28, FruitInfo.MAPLE_SYRUP, 9, Season.SPECIAL, Map.of(
        1, AssetManager.getAssetManager().getMapleStage1(),
        2, AssetManager.getAssetManager().getMapleStage2(),
        3, AssetManager.getAssetManager().getMapleStage3(),
        4, AssetManager.getAssetManager().getMapleStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getMapleStage5Spring(),
        "Summer", AssetManager.getAssetManager().getMapleStage5Summer(),
        "Fall", AssetManager.getAssetManager().getMapleStage5Fall(),
        "Winter", AssetManager.getAssetManager().getMapleStage5Winter()
    ), null,
        Map.of(
            "Spring", AssetManager.getAssetManager().getMapleStumpSpring(),
            "Summer", AssetManager.getAssetManager().getMapleStumpSummer(),
            "Fall", AssetManager.getAssetManager().getMapleStumpFall(),
            "Winter", AssetManager.getAssetManager().getMapleStumpWinter()
        )),

    PINE_TREE("Pine Tree", ForagingTreeInfo.PINE_CONES, new int[]{7, 7, 7, 7}, 28, FruitInfo.PINE_TAR, 5, Season.SPECIAL, Map.of(
        1, AssetManager.getAssetManager().getPineStage1(),
        2, AssetManager.getAssetManager().getPineStage2(),
        3, AssetManager.getAssetManager().getPineStage3(),
        4, AssetManager.getAssetManager().getPineStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getPineStage5Spring(),
        "Summer", AssetManager.getAssetManager().getPineStage5Summer(),
        "Fall", AssetManager.getAssetManager().getPineStage5Fall(),
        "Winter", AssetManager.getAssetManager().getPineStage5Winter()
    ), null,
        Map.of(
            "Spring", AssetManager.getAssetManager().getPineStumpSpring(),
            "Fall", AssetManager.getAssetManager().getPineStumpFall(),
            "Winter", AssetManager.getAssetManager().getPineStumpWinter()
        )),

    MAHOGANY_TREE("Mahogany Tree", ForagingTreeInfo.MAHOGANY_SEEDS, new int[]{7, 7, 7, 7}, 28, FruitInfo.SAP, 1, Season.SPECIAL, Map.of(
        1, AssetManager.getAssetManager().getMahoganyStage1(),
        2, AssetManager.getAssetManager().getMahoganyStage2(),
        3, AssetManager.getAssetManager().getMahoganyStage3(),
        4, AssetManager.getAssetManager().getMahoganyStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getMahoganyStage5Spring(),
        "Summer", AssetManager.getAssetManager().getMahoganyStage5Summer(),
        "Fall", AssetManager.getAssetManager().getMahoganyStage5Fall(),
        "Winter", AssetManager.getAssetManager().getMahoganyStage5Winter()
    ), null,
        Map.of(
            "Spring", AssetManager.getAssetManager().getMahoganyStumpSpring(),
            "Fall", AssetManager.getAssetManager().getMahoganyStumpFall(),
            "Winter", AssetManager.getAssetManager().getMahoganyStumpWinter()
        )
    ),

    MUSHROOM_TREE("Mushroom Tree", ForagingTreeInfo.MUSHROOMS_TREE_SEEDS, new int[]{7, 7, 7, 7}, 28, FruitInfo.COMMON_MUSHROOM, 1, Season.SPECIAL, Map.of(
        1, AssetManager.getAssetManager().getMushroomTreeStage1(),
        2, AssetManager.getAssetManager().getMushroomTreeStage2(),
        3, AssetManager.getAssetManager().getMushroomTreeStage3(),
        4, AssetManager.getAssetManager().getMushroomTreeStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getMushroomTreeStage5(),
        "Summer", AssetManager.getAssetManager().getMushroomTreeStage5(),
        "Fall", AssetManager.getAssetManager().getMushroomTreeStage5(),
        "Winter", AssetManager.getAssetManager().getMushroomTreeStage5()
    ), null,
        Map.of(
            "Spring", AssetManager.getAssetManager().getMushroomTreeStump(),
            "Summer", AssetManager.getAssetManager().getMushroomTreeStump(),
            "Fall", AssetManager.getAssetManager().getMushroomTreeStump(),
            "Winter", AssetManager.getAssetManager().getMushroomTreeStump()
        )),

    MYSTIC_TREE("Mystic Tree", null/*"Mystic Tree Seeds"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.MYSTIC_SYRUP, 7, Season.SPECIAL, Map.of(
        1, AssetManager.getAssetManager().getMysticTreeStage1(),
        2, AssetManager.getAssetManager().getMysticTreeStage2(),
        3, AssetManager.getAssetManager().getMysticTreeStage3(),
        4, AssetManager.getAssetManager().getMysticTreeStage4()
    ), Map.of(
        "Spring", AssetManager.getAssetManager().getMysticTreeStage5(),
        "Summer", AssetManager.getAssetManager().getMysticTreeStage5(),
        "Fall", AssetManager.getAssetManager().getMysticTreeStage5(),
        "Winter", AssetManager.getAssetManager().getMysticTreeStage5()
    ), null,
        Map.of(
            "Spring", AssetManager.getAssetManager().getMysticTreeStump(),
            "Summer", AssetManager.getAssetManager().getMysticTreeStump(),
            "Fall", AssetManager.getAssetManager().getMysticTreeStump(),
            "Winter", AssetManager.getAssetManager().getMysticTreeStump()
        ));

    private final String name;
    private final FarmingEnum source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final FruitInfo fruitInfo;
    private final int fruitHarvestCycle;
    private final Season season;
    private final Map<Integer, Texture> stagesTextures;
    private final Map<String, Texture> lastStageTextures;
    private final Texture lightningTexture;
    private final Map<String, Texture> stumpTextures;

    TreeInfo(String name, FarmingEnum source, int[] stages, int totalHarvestTime, FruitInfo fruitInfo, int fruitHarvestCycle, Season season, Map<Integer, Texture> stagesTextures, Map<String, Texture> lastStageTextures, Texture lightningTexture, Map<String, Texture> stumpTextures) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruitInfo = fruitInfo;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.season = season;
        this.stagesTextures = stagesTextures;
        this.lastStageTextures = lastStageTextures;
        this.lightningTexture = lightningTexture;
        this.stumpTextures = stumpTextures;
    }

    private static final Random RANDOM = new Random();

    public static TreeInfo randomTree() {
        TreeInfo[] values = TreeInfo.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getName() {
        return name;
    }

    public FarmingEnum getSource() {
        return source;
    }

    public int[] getStages() {
        return stages;
    }

    public FruitInfo getFruitInfo() {
        return fruitInfo;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public int getFruitHarvestCycle() {
        return fruitHarvestCycle;
    }

    public Season getSeason() {
        return season;
    }

    public static TreeInfo fromString(String name) {
        for (TreeInfo treeInfo : values()) {
            if (treeInfo.name.equalsIgnoreCase(name)) {
                return treeInfo;
            }
        }
        return null;
    }

    public static TreeInfo fromTreeSource(TreeSource source) {
        for (TreeInfo treeInfo : values()) {
            if (treeInfo.source == source.getInfo()) {
                return treeInfo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return """
            Name: %s
            Source: %s
            Stages: %s
            Total Harvest Time: %d
            Fruit: %s
            Fruit Harvest Cycle: %d
            Fruit Base Sell Price: %d
            Is Fruit Edible: %B
            Fruit Energy: %d
            Season: %s""".formatted(name, source.getName(), Arrays.toString(stages), totalHarvestTime, fruitInfo.getName(), fruitHarvestCycle, fruitInfo.getBaseSellPrice(), fruitInfo.isEdible(), fruitInfo.getEnergy(), season);
    }

    public Texture getTextureByStage(int stage) {
        return stagesTextures.get(stage);
    }

    public Map<String, Texture> getLastStageTextures() {
        return lastStageTextures;
    }

    public Texture getLightningTexture() {
        return lightningTexture;
    }

    public Map<String, Texture> getStumpTextures() {
        return stumpTextures;
    }
}
