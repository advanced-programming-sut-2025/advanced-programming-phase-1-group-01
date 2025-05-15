package models.fish;

import models.dateTime.Season;

public enum FishInfo {
    SALMON("Salmon", 75, Season.FALL),
    SARDINE("Sardine", 40, Season.FALL),
    SHAD("Shad", 60, Season.FALL),
    BLUE_DISCUS("Blue Discus", 120, Season.FALL),
    MIDNIGHT_CARP("Midnight Carp", 150, Season.WINTER),
    SQUID("Squid", 80, Season.WINTER),
    TUNA("Tuna", 100, Season.WINTER),
    PERCH("Perch", 55, Season.WINTER),
    FLOUNDER("Flounder", 100, Season.SPRING),
    LIONFISH("Lionfish", 100, Season.SPRING),
    HERRING("Herring", 30, Season.SPRING),
    GHOSTFISH("Ghostfish", 45, Season.SPRING),
    TILAPIA("Tilapia", 75, Season.SUMMER),
    DORADO("Dorado", 100, Season.SUMMER),
    SUNFISH("Sunfish", 30, Season.SUMMER),
    RAINBOW_TROUT("Rainbow Trout", 65, Season.SUMMER),
    LEGEND("Legend", 5000, Season.SPRING),
    GLACIERFISH("Glacierfish", 1000, Season.WINTER),
    ANGLER("Angler", 900, Season.FALL),
    CRIMSONFISH("Crimsonfish", 1500, Season.SUMMER);

    private final String name;
    private final int basePrice;
    private final Season season;

    FishInfo(String name, int basePrice, Season season) {
        this.name = name;
        this.basePrice = basePrice;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public Season getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return name;
    }
}