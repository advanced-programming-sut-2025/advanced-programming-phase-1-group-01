package models.foraging;

import models.dateTime.Season;

public enum ForagingTrees {
    ACORNS(Season.SPECIAL),
    MAPLE_SEEDS(Season.SPECIAL),
    PINE_CONS(Season.SPECIAL),
    MAHOGANY_SEEDS(Season.SPECIAL),
    MUSHROOMS_TREE_SEEDS(Season.SPECIAL);

    private Season season;

    ForagingTrees(Season season) {
        this.season = season;
    }
}
