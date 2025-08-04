package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.enums.Emoji;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// this enum contains all kinds of crops
public enum CropInfo implements FarmingEnum {
    BLUE_JAZZ("Blue Jazz", SeedInfo.JAZZ_SEEDS, new int[]{1, 2, 2, 2}, 7, true, -1, 50, true, 45, List.of(Season.SPRING), false, AssetManager.getAssetManager().getBlueJazz(), Map.of(
        1, AssetManager.getAssetManager().getBlueJazzStage1(),
        2, AssetManager.getAssetManager().getBlueJazzStage2(),
        3, AssetManager.getAssetManager().getBlueJazzStage3(),
        4, AssetManager.getAssetManager().getBlueJazzStage4(),
        5, AssetManager.getAssetManager().getBlueJazzStage5()
    )),
    CARROT("Carrot", SeedInfo.CARROT_SEEDS, new int[]{1, 1, 1}, 3, true, -1, 35, true, 75, List.of(Season.SPRING), false, AssetManager.getAssetManager().getCarrot(), Map.of(
        1, AssetManager.getAssetManager().getCarrotStage1(),
        2, AssetManager.getAssetManager().getCarrotStage2(),
        3, AssetManager.getAssetManager().getCarrotStage3(),
        4, AssetManager.getAssetManager().getCarrotStage4()
    )),
    CAULIFLOWER("Cauliflower", SeedInfo.CAULIFLOWER_SEEDS, new int[]{1, 2, 4, 4, 1}, 12, true, -1, 175, true, 75, List.of(Season.SPRING), true, AssetManager.getAssetManager().getCauliflower(), Map.of(
        1, AssetManager.getAssetManager().getCauliflowerStage1(),
        2, AssetManager.getAssetManager().getCauliflowerStage2(),
        3, AssetManager.getAssetManager().getCauliflowerStage3(),
        4, AssetManager.getAssetManager().getCauliflowerStage4(),
        5, AssetManager.getAssetManager().getCauliflowerStage5(),
        6, AssetManager.getAssetManager().getCauliflowerStage6()
    )),
    COFFEE_BEAN("Coffee Bean", SeedInfo.COFFEE_BEAN, new int[]{1, 2, 2, 3, 2}, 10, false, 2, 15, false, 0, List.of(Season.SPRING, Season.SUMMER), false, AssetManager.getAssetManager().getCoffeeBean(), Map.of(
        1, AssetManager.getAssetManager().getCoffeeBeanStage1(),
        2, AssetManager.getAssetManager().getCoffeeBeanStage2(),
        3, AssetManager.getAssetManager().getCoffeeBeanStage3(),
        4, AssetManager.getAssetManager().getCoffeeBeanStage4(),
        5, AssetManager.getAssetManager().getCoffeeBeanStage5(),
        6, AssetManager.getAssetManager().getCoffeeBeanStage6(),
        7, AssetManager.getAssetManager().getCoffeeBeanStage7()
    )),
    GARLIC("Garlic", SeedInfo.GARLIC_SEEDS, new int[]{1, 1, 1, 1}, 4, true, -1, 60, true, 20, List.of(Season.SPRING), false, AssetManager.getAssetManager().getGarlic(), Map.of(
        1, AssetManager.getAssetManager().getGarlicStage1(),
        2, AssetManager.getAssetManager().getGarlicStage2(),
        3, AssetManager.getAssetManager().getGarlicStage3(),
        4, AssetManager.getAssetManager().getGarlicStage4(),
        5, AssetManager.getAssetManager().getGarlicStage5()
    )),
    GREEN_BEAN("Green Bean", SeedInfo.BEAN_STARTER, new int[]{1, 1, 1, 3, 4}, 10, false, 3, 40, true, 25, List.of(Season.SPRING), false, AssetManager.getAssetManager().getGreenBean(), Map.of(
        1, AssetManager.getAssetManager().getGreenBeanStage2(),
        2, AssetManager.getAssetManager().getGreenBeanStage3(),
        3, AssetManager.getAssetManager().getGreenBeanStage4(),
        4, AssetManager.getAssetManager().getGreenBeanStage5(),
        5, AssetManager.getAssetManager().getGreenBeanStage6(),
        6, AssetManager.getAssetManager().getGreenBeanStage7(),
        7, AssetManager.getAssetManager().getGreenBeanStage8()
    )),
    KALE("Kale", SeedInfo.KALE_SEEDS, new int[]{1, 2, 2, 1}, 6, true, -1, 110, true, 50, List.of(Season.SPRING), false, AssetManager.getAssetManager().getKale(), Map.of(
        1, AssetManager.getAssetManager().getKaleStage1(),
        2, AssetManager.getAssetManager().getKaleStage2(),
        3, AssetManager.getAssetManager().getKaleStage3(),
        4, AssetManager.getAssetManager().getKaleStage4(),
        5, AssetManager.getAssetManager().getKaleStage5()
    )),
    PARSNIP("Parsnip", SeedInfo.PARSNIP_SEEDS, new int[]{1, 1, 1, 1}, 4, true, -1, 35, true, 25, List.of(Season.SPRING), false, AssetManager.getAssetManager().getParsnip(), Map.of(
        1, AssetManager.getAssetManager().getParsnipStage1(),
        2, AssetManager.getAssetManager().getParsnipStage2(),
        3, AssetManager.getAssetManager().getParsnipStage3(),
        4, AssetManager.getAssetManager().getParsnipStage4(),
        5, AssetManager.getAssetManager().getParsnipStage5()
    )),
    POTATO("Potato", SeedInfo.POTATO_SEEDS, new int[]{1, 1, 1, 2, 1}, 6, true, -1, 80, true, 25, List.of(Season.SPRING), false, AssetManager.getAssetManager().getPotato(), Map.of(
        1, AssetManager.getAssetManager().getPotatoStage1(),
        2, AssetManager.getAssetManager().getPotatoStage2(),
        3, AssetManager.getAssetManager().getPotatoStage3(),
        4, AssetManager.getAssetManager().getPotatoStage4(),
        5, AssetManager.getAssetManager().getPotatoStage5(),
        6, AssetManager.getAssetManager().getPotatoStage6()
    )),
    RHUBARB("Rhubarb", SeedInfo.RHUBARB_SEEDS, new int[]{2, 2, 2, 3, 4}, 13, true, -1, 220, false, 0, List.of(Season.SPRING), false, AssetManager.getAssetManager().getRhubarb(), Map.of(
        1, AssetManager.getAssetManager().getRhubarbStage1(),
        2, AssetManager.getAssetManager().getRhubarbStage2(),
        3, AssetManager.getAssetManager().getRhubarbStage3(),
        4, AssetManager.getAssetManager().getRhubarbStage4(),
        5, AssetManager.getAssetManager().getRhubarbStage5(),
        6, AssetManager.getAssetManager().getRhubarbStage6()
    )),
    STRAWBERRY("Strawberry", SeedInfo.STRAWBERRY_SEEDS, new int[]{1, 1, 2, 2, 2}, 8, false, 4, 120, true, 50, List.of(Season.SPRING), false, AssetManager.getAssetManager().getStrawberry(), Map.of(
        1, AssetManager.getAssetManager().getStrawberryStage1(),
        2, AssetManager.getAssetManager().getStrawberryStage2(),
        3, AssetManager.getAssetManager().getStrawberryStage3(),
        4, AssetManager.getAssetManager().getStrawberryStage4(),
        5, AssetManager.getAssetManager().getStrawberryStage5(),
        6, AssetManager.getAssetManager().getStrawberryStage6(),
        7, AssetManager.getAssetManager().getStrawberryStage7()
    )),
    TULIP("Tulip", SeedInfo.TULIP_BULB, new int[]{1, 1, 2, 2}, 6, true, -1, 30, true, 45, List.of(Season.SPRING), false, AssetManager.getAssetManager().getTulip(), Map.of(
        1, AssetManager.getAssetManager().getTulipStage1(),
        2, AssetManager.getAssetManager().getTulipStage2(),
        3, AssetManager.getAssetManager().getTulipStage3(),
        4, AssetManager.getAssetManager().getTulipStage4(),
        5, AssetManager.getAssetManager().getTulipStage6()
    )),
    UNMILLED_RICE("Unmilled Rice", SeedInfo.RICE_SHOOT, new int[]{1, 2, 2, 3}, 8, true, -1, 30, true, 3, List.of(Season.SPRING), false, AssetManager.getAssetManager().getUnmilledRice(), Map.of(
        1, AssetManager.getAssetManager().getUnmilledRiceStage1(),
        2, AssetManager.getAssetManager().getUnmilledRiceStage2(),
        3, AssetManager.getAssetManager().getUnmilledRiceStage3(),
        4, AssetManager.getAssetManager().getUnmilledRiceStage4(),
        5, AssetManager.getAssetManager().getUnmilledRiceStage5()
    )),
    BLUEBERRY("Blueberry", SeedInfo.BLUEBERRY_SEEDS, new int[]{1, 3, 3, 4, 2}, 13, false, 4, 50, true, 25, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getBlueberry(), Map.of(
        1, AssetManager.getAssetManager().getBlueberryStage1(),
        2, AssetManager.getAssetManager().getBlueberryStage2(),
        3, AssetManager.getAssetManager().getBlueberryStage3(),
        4, AssetManager.getAssetManager().getBlueberryStage4(),
        5, AssetManager.getAssetManager().getBlueberryStage5(),
        6, AssetManager.getAssetManager().getBlueberryStage6(),
        7, AssetManager.getAssetManager().getBlueberryStage7()
    )),
    CORN("Corn", SeedInfo.CORN_SEEDS, new int[]{2, 3, 3, 3, 3}, 14, false, 4, 50, true, 25, List.of(Season.SUMMER, Season.FALL), false, AssetManager.getAssetManager().getCorn(), Map.of(
        1, AssetManager.getAssetManager().getCornStage1(),
        2, AssetManager.getAssetManager().getCornStage2(),
        3, AssetManager.getAssetManager().getCornStage3(),
        4, AssetManager.getAssetManager().getCornStage4(),
        5, AssetManager.getAssetManager().getCornStage5(),
        6, AssetManager.getAssetManager().getCornStage6(),
        7, AssetManager.getAssetManager().getCornStage7()
    )),
    HOPS("Hops", SeedInfo.HOPS_STARTER, new int[]{1, 1, 2, 3, 4}, 11, false, 1, 25, true, 45, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getHops(), Map.of(
        1, AssetManager.getAssetManager().getHopsStage1(),
        2, AssetManager.getAssetManager().getHopsStage3(),
        3, AssetManager.getAssetManager().getHopsStage4(),
        4, AssetManager.getAssetManager().getHopsStage5(),
        5, AssetManager.getAssetManager().getHopsStage6(),
        6, AssetManager.getAssetManager().getHopsStage7(),
        7, AssetManager.getAssetManager().getHopsStage8()
    )),
    HOT_PEPPER("Hot Pepper", SeedInfo.PEPPER_SEEDS, new int[]{1, 1, 1, 1, 1}, 5, false, 3, 40, true, 13, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getHotPepper(), Map.of(
        1, AssetManager.getAssetManager().getHotPepperStage1(),
        2, AssetManager.getAssetManager().getHotPepperStage2(),
        3, AssetManager.getAssetManager().getHotPepperStage3(),
        4, AssetManager.getAssetManager().getHotPepperStage4(),
        5, AssetManager.getAssetManager().getHotPepperStage5(),
        6, AssetManager.getAssetManager().getHotPepperStage6(),
        7, AssetManager.getAssetManager().getHotPepperStage7()
    )),
    MELON("Melon", SeedInfo.MELON_SEEDS, new int[]{1, 2, 3, 3, 3}, 12, true, -1, 250, true, 113, List.of(Season.SUMMER), true, AssetManager.getAssetManager().getMelon(), Map.of(
        1, AssetManager.getAssetManager().getMelonStage1(),
        2, AssetManager.getAssetManager().getMelonStage2(),
        3, AssetManager.getAssetManager().getMelonStage3(),
        4, AssetManager.getAssetManager().getMelonStage4(),
        5, AssetManager.getAssetManager().getMelonStage5(),
        6, AssetManager.getAssetManager().getMelonStage6()
    )),
    POPPY("Poppy", SeedInfo.POPPY_SEEDS, new int[]{1, 2, 2, 2}, 7, true, -1, 140, true, 45, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getPoppy(), Map.of(
        1, AssetManager.getAssetManager().getPoppyStage1(),
        2, AssetManager.getAssetManager().getPoppyStage2(),
        3, AssetManager.getAssetManager().getPoppyStage3(),
        4, AssetManager.getAssetManager().getPoppyStage4(),
        5, AssetManager.getAssetManager().getPoppyStage6()
    )),
    RADISH("Radish", SeedInfo.RADISH_SEEDS, new int[]{2, 1, 2, 1}, 6, true, -1, 90, true, 45, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getRadish(), Map.of(
        1, AssetManager.getAssetManager().getRadishStage1(),
        2, AssetManager.getAssetManager().getRadishStage2(),
        3, AssetManager.getAssetManager().getRadishStage3(),
        4, AssetManager.getAssetManager().getRadishStage4(),
        5, AssetManager.getAssetManager().getRadishStage5()
    )),
    RED_CABBAGE("Red Cabbage", SeedInfo.RED_CABBAGE_SEEDS, new int[]{2, 1, 2, 2, 2}, 9, true, -1, 260, true, 75, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getRedCabbage(), Map.of(
        1, AssetManager.getAssetManager().getRedCabbageStage1(),
        2, AssetManager.getAssetManager().getRedCabbageStage2(),
        3, AssetManager.getAssetManager().getRedCabbageStage3(),
        4, AssetManager.getAssetManager().getRedCabbageStage4(),
        5, AssetManager.getAssetManager().getRedCabbageStage5(),
        6, AssetManager.getAssetManager().getRedCabbageStage6()
    )),
    STARFRUIT("Starfruit", SeedInfo.STARFRUIT_SEEDS, new int[]{2, 3, 2, 3, 3}, 13, true, -1, 750, true, 125, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getStarfruit(), Map.of(
        1, AssetManager.getAssetManager().getStarfruitStage1(),
        2, AssetManager.getAssetManager().getStarfruitStage2(),
        3, AssetManager.getAssetManager().getStarfruitStage3(),
        4, AssetManager.getAssetManager().getStarfruitStage4(),
        5, AssetManager.getAssetManager().getStarfruitStage5(),
        6, AssetManager.getAssetManager().getStarfruitStage6()
    )),
    SUMMER_SPANGLE("Summer Spangle", SeedInfo.SPANGLE_SEEDS, new int[]{1, 2, 3, 1}, 8, true, -1, 90, true, 45, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getSummerSpangle(), Map.of(
        1, AssetManager.getAssetManager().getSummerSpangleStage1(),
        2, AssetManager.getAssetManager().getSummerSpangleStage2(),
        3, AssetManager.getAssetManager().getSummerSpangleStage3(),
        4, AssetManager.getAssetManager().getSummerSpangleStage4(),
        5, AssetManager.getAssetManager().getSummerSpangleStage5()
    )),
    SUMMER_SQUASH("Summer Squash", SeedInfo.SUMMER_SQUASH_SEEDS, new int[]{1, 1, 1, 2, 1}, 6, false, 3, 45, true, 63, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getSummerSquash(), Map.of(
        1, AssetManager.getAssetManager().getSummerSquashStage1(),
        2, AssetManager.getAssetManager().getSummerSquashStage2(),
        3, AssetManager.getAssetManager().getSummerSquashStage3(),
        4, AssetManager.getAssetManager().getSummerSquashStage4(),
        5, AssetManager.getAssetManager().getSummerSquashStage5(),
        6, AssetManager.getAssetManager().getSummerSquashStage6(),
        7, AssetManager.getAssetManager().getSummerSquashStage7()
    )),
    SUNFLOWER("Sunflower", SeedInfo.SUNFLOWER_SEEDS, new int[]{1, 2, 3, 2}, 8, true, -1, 80, true, 45, List.of(Season.SUMMER, Season.FALL), false, AssetManager.getAssetManager().getSunflower(), Map.of(
        1, AssetManager.getAssetManager().getSunflowerStage1(),
        2, AssetManager.getAssetManager().getSunflowerStage2(),
        3, AssetManager.getAssetManager().getSunflowerStage3(),
        4, AssetManager.getAssetManager().getSunflowerStage4(),
        5, AssetManager.getAssetManager().getSunflowerStage5()
    )),
    TOMATO("Tomato", SeedInfo.TOMATO_SEEDS, new int[]{2, 2, 2, 2, 3}, 11, false, 4, 60, true, 20, List.of(Season.SUMMER), false, AssetManager.getAssetManager().getTomato(), Map.of(
        1, AssetManager.getAssetManager().getTomatoStage1(),
        2, AssetManager.getAssetManager().getTomatoStage2(),
        3, AssetManager.getAssetManager().getTomatoStage3(),
        4, AssetManager.getAssetManager().getTomatoStage4(),
        5, AssetManager.getAssetManager().getTomatoStage5(),
        6, AssetManager.getAssetManager().getTomatoStage6(),
        7, AssetManager.getAssetManager().getTomatoStage7()
    )),
    WHEAT("Wheat", SeedInfo.WHEAT_SEEDS, new int[]{1, 1, 1, 1}, 4, true, -1, 25, false, 0, List.of(Season.SUMMER, Season.FALL), false, AssetManager.getAssetManager().getWheat(), Map.of(
        1, AssetManager.getAssetManager().getWheatStage1(),
        2, AssetManager.getAssetManager().getWheatStage2(),
        3, AssetManager.getAssetManager().getWheatStage3(),
        4, AssetManager.getAssetManager().getWheatStage4(),
        5, AssetManager.getAssetManager().getWheatStage5()
    )),
    AMARANTH("Amaranth", SeedInfo.AMARANTH_SEEDS, new int[]{1, 2, 2, 2}, 7, true, -1, 150, true, 50, List.of(Season.FALL), false, AssetManager.getAssetManager().getAmaranth(), Map.of(
        1, AssetManager.getAssetManager().getAmaranthStage1(),
        2, AssetManager.getAssetManager().getAmaranthStage2(),
        3, AssetManager.getAssetManager().getAmaranthStage3(),
        4, AssetManager.getAssetManager().getAmaranthStage4(),
        5, AssetManager.getAssetManager().getAmaranthStage5()
    )),
    ARTICHOKE("Artichoke", SeedInfo.ARTICHOKE_SEEDS, new int[]{2, 2, 1, 2, 1}, 8, true, -1, 160, true, 30, List.of(Season.FALL), false, AssetManager.getAssetManager().getArtichoke(), Map.of(
        1, AssetManager.getAssetManager().getArtichokeStage1(),
        2, AssetManager.getAssetManager().getArtichokeStage2(),
        3, AssetManager.getAssetManager().getArtichokeStage3(),
        4, AssetManager.getAssetManager().getArtichokeStage4(),
        5, AssetManager.getAssetManager().getArtichokeStage5(),
        6, AssetManager.getAssetManager().getArtichokeStage6()
    )),
    BEET("Beet", SeedInfo.BEET_SEEDS, new int[]{1, 1, 2, 2}, 6, true, -1, 100, true, 30, List.of(Season.FALL), false, AssetManager.getAssetManager().getBeet(), Map.of(
        1, AssetManager.getAssetManager().getBeetStage1(),
        2, AssetManager.getAssetManager().getBeetStage2(),
        3, AssetManager.getAssetManager().getBeetStage3(),
        4, AssetManager.getAssetManager().getBeetStage4(),
        5, AssetManager.getAssetManager().getBeetStage5()
    )),
    BOK_CHOY("Bok Choy", SeedInfo.BOKCHOY_SEEDS, new int[]{1, 1, 1, 1}, 4, true, -1, 80, true, 25, List.of(Season.FALL), false, AssetManager.getAssetManager().getBokChoy(), Map.of(
        1, AssetManager.getAssetManager().getBokChoyStage1(),
        2, AssetManager.getAssetManager().getBokChoyStage2(),
        3, AssetManager.getAssetManager().getBokChoyStage3(),
        4, AssetManager.getAssetManager().getBokChoyStage4(),
        5, AssetManager.getAssetManager().getBokChoyStage5()
    )),
    BROCCOLI("Broccoli", SeedInfo.BROCCOLI_SEEDS, new int[]{2, 2, 2, 2}, 8, false, 4, 70, true, 63, List.of(Season.FALL), false, AssetManager.getAssetManager().getBroccoli(), Map.of(
        1, AssetManager.getAssetManager().getBroccoliStage1(),
        2, AssetManager.getAssetManager().getBroccoliStage2(),
        3, AssetManager.getAssetManager().getBroccoliStage3(),
        4, AssetManager.getAssetManager().getBroccoliStage4(),
        5, AssetManager.getAssetManager().getBroccoliStage5(),
        6, AssetManager.getAssetManager().getBroccoliStage4()
    )),
    CRANBERRIES("Cranberries", SeedInfo.CRANBERRY_SEEDS, new int[]{1, 2, 1, 1, 2}, 7, false, 5, 75, true, 38, List.of(Season.FALL), false, AssetManager.getAssetManager().getCranberries(), Map.of(
        1, AssetManager.getAssetManager().getCranberryStage1(),
        2, AssetManager.getAssetManager().getCranberryStage2(),
        3, AssetManager.getAssetManager().getCranberryStage3(),
        4, AssetManager.getAssetManager().getCranberryStage4(),
        5, AssetManager.getAssetManager().getCranberryStage5(),
        6, AssetManager.getAssetManager().getCranberryStage6(),
        7, AssetManager.getAssetManager().getCranberryStage7()
    )),
    EGGPLANT("Eggplant", SeedInfo.EGGPLANT_SEEDS, new int[]{1, 1, 1, 1}, 5, false, 5, 60, true, 20, List.of(Season.FALL), false, AssetManager.getAssetManager().getEggplant(), Map.of(
        1, AssetManager.getAssetManager().getEggplantStage1(),
        2, AssetManager.getAssetManager().getEggplantStage2(),
        3, AssetManager.getAssetManager().getEggplantStage3(),
        4, AssetManager.getAssetManager().getEggplantStage4(),
        5, AssetManager.getAssetManager().getEggplantStage5(),
        6, AssetManager.getAssetManager().getEggplantStage6()
    )),
    FAIRY_ROSE("Fairy Rose", SeedInfo.FAIRY_SEEDS, new int[]{1, 4, 4, 3}, 12, true, -1, 290, true, 45, List.of(Season.FALL), false, AssetManager.getAssetManager().getFairyRose(), Map.of(
        1, AssetManager.getAssetManager().getFairyRoseStage1(),
        2, AssetManager.getAssetManager().getFairyRoseStage2(),
        3, AssetManager.getAssetManager().getFairyRoseStage3(),
        4, AssetManager.getAssetManager().getFairyRoseStage4(),
        5, AssetManager.getAssetManager().getFairyRoseStage5()
    )),
    GRAPE("Grape", SeedInfo.GRAPE_STARTER, new int[]{1, 1, 2, 3, 3}, 10, false, 3, 80, true, 38, List.of(Season.FALL), false, AssetManager.getAssetManager().getGrape(), Map.of(
        1, AssetManager.getAssetManager().getGrapeStage1(),
        2, AssetManager.getAssetManager().getGrapeStage2(),
        3, AssetManager.getAssetManager().getGrapeStage3(),
        4, AssetManager.getAssetManager().getGrapeStage4(),
        5, AssetManager.getAssetManager().getGrapeStage5(),
        6, AssetManager.getAssetManager().getGrapeStage6(),
        7, AssetManager.getAssetManager().getGrapeStage7()
    )),
    PUMPKIN("Pumpkin", SeedInfo.PUMPKIN_SEEDS, new int[]{1, 2, 3, 4, 3}, 13, true, -1, 320, false, 0, List.of(Season.FALL), true, AssetManager.getAssetManager().getPumpkin(), Map.of(
        1, AssetManager.getAssetManager().getPumpkinStage1(),
        2, AssetManager.getAssetManager().getPumpkinStage2(),
        3, AssetManager.getAssetManager().getPumpkinStage3(),
        4, AssetManager.getAssetManager().getPumpkinStage4(),
        5, AssetManager.getAssetManager().getPumpkinStage5(),
        6, AssetManager.getAssetManager().getPumpkinStage6()
    )),
    YAM("Yam", SeedInfo.YAM_SEEDS, new int[]{1, 3, 3, 3}, 10, true, -1, 160, true, 45, List.of(Season.FALL), false, AssetManager.getAssetManager().getYam(), Map.of(
        1, AssetManager.getAssetManager().getYamStage1(),
        2, AssetManager.getAssetManager().getYamStage2(),
        3, AssetManager.getAssetManager().getYamStage3(),
        4, AssetManager.getAssetManager().getYamStage4(),
        5, AssetManager.getAssetManager().getYamStage5()
    )),
    SWEET_GEM_BERRY("Sweet Gem Berry", SeedInfo.RARE_SEEDS, new int[]{2, 4, 6, 6, 6}, 24, true, -1, 3000, false, 0, List.of(Season.FALL), false, AssetManager.getAssetManager().getSweetGemBerry(), Map.of(
        1, AssetManager.getAssetManager().getSweetGemBerryStage1(),
        2, AssetManager.getAssetManager().getSweetGemBerryStage2(),
        3, AssetManager.getAssetManager().getSweetGemBerryStage3(),
        4, AssetManager.getAssetManager().getSweetGemBerryStage4(),
        5, AssetManager.getAssetManager().getSweetGemBerryStage5(),
        6, AssetManager.getAssetManager().getSweetGemBerryStage6()
    )),
    POWDERMELON("Powdermelon", SeedInfo.POWDERMELON_SEEDS, new int[]{1, 2, 1, 2, 1}, 7, true, -1, 60, true, 63, List.of(Season.WINTER), true, AssetManager.getAssetManager().getPowdermelon(), Map.of(
        1, AssetManager.getAssetManager().getPowdermelonStage1(),
        2, AssetManager.getAssetManager().getPowdermelonStage2(),
        3, AssetManager.getAssetManager().getPowdermelonStage3(),
        4, AssetManager.getAssetManager().getPowdermelonStage4(),
        5, AssetManager.getAssetManager().getPowdermelonStage5(),
        6, AssetManager.getAssetManager().getPowdermelonStage6()
    )),
    ANCIENT_FRUIT("Ancient Fruit", SeedInfo.ANCIENT_SEEDS, new int[]{2, 7, 7, 7, 5}, 28, false, 7, 550, false, 0, List.of(Season.SPRING, Season.SUMMER, Season.FALL), false, AssetManager.getAssetManager().getAncientFruit(), Map.of(
        1, AssetManager.getAssetManager().getAncientFruitStage1(),
        2, AssetManager.getAssetManager().getAncientFruitStage2(),
        3, AssetManager.getAssetManager().getAncientFruitStage3(),
        4, AssetManager.getAssetManager().getAncientFruitStage4(),
        5, AssetManager.getAssetManager().getAncientFruitStage5(),
        6, AssetManager.getAssetManager().getAncientFruitStage6(),
        7, AssetManager.getAssetManager().getAncientFruitStage7()
    ));

    private final String name;
    private final SeedInfo source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final boolean oneTime;
    private final int regrowthTime;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final List<Season> seasons;
    private final boolean canBecomeGiant;
    private final Texture cropTexture;
    private final Map<Integer, Texture> stagesTextures;

    CropInfo(String name, SeedInfo source, int[] stages, int totalHarvestTime, boolean oneTime, int regrowthTime, int baseSellPrice, boolean isEdible, int energy, List<Season> seasons, boolean canBecomeGiant, Texture cropTexture, Map<Integer, Texture> stagesTextures) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
        this.cropTexture = cropTexture;
        this.stagesTextures = stagesTextures;
    }

    public String getName() {
        return name;
    }

    public SeedInfo getSource() {
        return source;
    }

    public int[] getStages() {
        return stages;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public String getSeasonsStr() {
        return seasons.stream().map(Season::toString).collect(Collectors.joining(" & "));
    }

    public boolean canBecomeGiant() {
        return canBecomeGiant;
    }

    public static CropInfo fromSeed(Seed seed) {
        for (CropInfo info : CropInfo.values()) {
            if (info.source == seed.getInfo()) {
                return info;
            }
        }
        return null;
    }

    public static CropInfo fromString(String name) {
        for (CropInfo cropInfo : values()) {
            if (cropInfo.getName().equalsIgnoreCase(name)) {
                return cropInfo;
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
                One Time: %B
                Regrowth Time: %d
                Base Sell Price: %d
                Is Edible: %B
                Base Energy: %d
                Season: %s
                Can Become Giant: %B""".formatted(name,
                source.getName(),
                Arrays.toString(stages),
                totalHarvestTime,
                oneTime,
                regrowthTime,
                baseSellPrice,
                isEdible,
                energy,
                getSeasonsStr(),
                canBecomeGiant);
    }

    public String getSymbol() {
        return null;
    }

    public Crop toItem() {
        return new Crop(source);
    }

    public Texture getCropTexture() {
        return cropTexture;
    }

    public Texture getTextureByStage(int stage) {
        return stagesTextures.get(stage);
    }
}
