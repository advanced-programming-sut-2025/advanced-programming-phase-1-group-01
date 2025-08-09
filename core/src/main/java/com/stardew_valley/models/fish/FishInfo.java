package com.stardew_valley.models.fish;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.dateTime.Season;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum FishInfo {
    SALMON("Salmon", 75, Season.FALL, false, AssetManager.getAssetManager().getSalmon()),
    SARDINE("Sardine", 40, Season.FALL, false, AssetManager.getAssetManager().getSardine()),
    SHAD("Shad", 60, Season.FALL, false, AssetManager.getAssetManager().getShad()),
    BLUE_DISCUS("Blue Discus", 120, Season.FALL, false, AssetManager.getAssetManager().getBlueDiscus()),
    MIDNIGHT_CARP("Midnight Carp", 150, Season.WINTER, false, AssetManager.getAssetManager().getMidnightCarp()),
    SQUID("Squid", 80, Season.WINTER, false, AssetManager.getAssetManager().getSquid()),
    TUNA("Tuna", 100, Season.WINTER, false, AssetManager.getAssetManager().getTuna()),
    PERCH("Perch", 55, Season.WINTER, false, AssetManager.getAssetManager().getPerch()),
    FLOUNDER("Flounder", 100, Season.SPRING, false, AssetManager.getAssetManager().getFlounder()),
    LIONFISH("Lionfish", 100, Season.SPRING, false, AssetManager.getAssetManager().getLionfish()),
    HERRING("Herring", 30, Season.SPRING, false, AssetManager.getAssetManager().getHerring()),
    GHOSTFISH("Ghostfish", 45, Season.SPRING, false, AssetManager.getAssetManager().getGhostfish()),
    TILAPIA("Tilapia", 75, Season.SUMMER, false, AssetManager.getAssetManager().getTilapia()),
    DORADO("Dorado", 100, Season.SUMMER, false, AssetManager.getAssetManager().getDorado()),
    SUNFISH("Sunfish", 30, Season.SUMMER, false, AssetManager.getAssetManager().getSunfish()),
    RAINBOW_TROUT("Rainbow Trout", 65, Season.SUMMER, false, AssetManager.getAssetManager().getRainbowTrout()),
    LEGEND("Legend", 5000, Season.SPRING, true, AssetManager.getAssetManager().getLegend()),
    GLACIERFISH("Glacierfish", 1000, Season.WINTER, true, AssetManager.getAssetManager().getGlacierfish()),
    ANGLER("Angler", 900, Season.FALL, true, AssetManager.getAssetManager().getAngler()),
    CRIMSONFISH("Crimsonfish", 1500, Season.SUMMER, true, AssetManager.getAssetManager().getCrimsonfish());

    private final String name;
    private final int basePrice;
    private final Season season;
    private final boolean isLegendary;
    private final Texture texture;

    FishInfo(String name, int basePrice, Season season, boolean isLegendary, Texture texture) {
        this.name = name;
        this.basePrice = basePrice;
        this.season = season;
        this.isLegendary = isLegendary;
        this.texture = texture;
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

    private static final Random RANDOM = new Random();

    public static FishInfo getRandomFish(Season season, boolean hasLegendary) {
        List<FishInfo> fishInfos = new ArrayList<>();
        for (FishInfo fishInfo : FishInfo.values()) {
            if (fishInfo.getSeason() == season && (!fishInfo.isLegendary || hasLegendary)) {
                fishInfos.add(fishInfo);
            }
        }

        int index = RANDOM.nextInt(fishInfos.size());
        return fishInfos.get(index);
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public Texture getTexture() {
        return texture;
    }

    public Item toItem() {
        return new Fish(this);
    }
}
