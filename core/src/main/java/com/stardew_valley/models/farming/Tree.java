package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.foraging.ForagingMineral;
import com.stardew_valley.models.foraging.ForagingMineralInfo;

public class Tree extends Plant {
    private final TreeInfo info;
    private TreeState state;
    private TreeSource source;
    private boolean isAttackedByCrow;

    public Tree(TreeSource source) {
        this.source = source;
        this.info = TreeInfo.fromTreeSource(source);
        state = TreeState.HEALTHY;
    }

    public TreeState getState() {
        return state;
    }

    public void broke() {
        state = TreeState.BROKEN;
    }

    public void burn() {
        state = TreeState.BURNT;
    }

    public void repair() {
        state = TreeState.HEALTHY;
    }

    @Override
    public TreeInfo getInfo() {
        return info;
    }

    @Override
    public void grow() { // this method should be called every day
        int[] growthStages = info.getStages();
        int fruitHarvestCycle = info.getFruitHarvestCycle();
        Season currSeason = Repository.getRepo().getCurrentGame().getTimeManager().getNow().getSeason();

        if (!isFullyGrown()) {
            int currentLevelDays = growthStages[growthLevel - 1];

            if (daysInCurrentLevel >= currentLevelDays) {
                incrementGrowthIfWatered();
                if (isFullyGrown() && currSeason == info.getSeason()) hasProduct = true;
                daysInCurrentLevel = 1;
            } else {
                daysInCurrentLevel++;
            }
            return;
        }

        if (!hasProduct && currSeason == info.getSeason()) {
            if (daysInCurrentLevel < fruitHarvestCycle) {
                daysInCurrentLevel++;
            } else {
                hasProduct = true;
                daysInCurrentLevel = 1;
            }
        }
    }

    public boolean isFullyGrown() {
        return growthLevel > info.getStages().length;
    }

    @Override
    public boolean hasProduct() {
        return hasProduct;
    }

    @Override
    public Item getProduct() {
        if (state == TreeState.BURNT) {
            return new ForagingMineral(ForagingMineralInfo.COAL);
        }

        if (!hasProduct || isAttackedByCrow) return null;
        hasProduct = false;
        return new Fruit(info.getFruitInfo());
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public void growFull() {
        for (int i = 0; i < info.getTotalHarvestTime(); i++) {
            grow();
        }
    }

    @Override
    public int getPrice() {
        return 0;
    } // not sellable

    public TreeSource getSource() {
        return source;
    }

    public void setSource(TreeSource source) {
        this.source = source;
    }

    public boolean isAttackedByCrow() {
        return isAttackedByCrow;
    }

    public void applyCrowAttack() {
        isAttackedByCrow = true;
    }

    @Override
    public Texture getTexture() {
        if (growthLevel < 5) {
            return info.getTextureByStage(growthLevel);
        }
        Season currSeason = Repository.getRepo().getCurrentGame().getTimeManager().getNow().getSeason();
        if (hasProduct && info.getSeason() != Season.SPECIAL) {
            return info.getFruitedTexture();
        }
        return info.getLastStageTextureBySeason(currSeason);
    }

}
