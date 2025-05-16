package models.farming;

import models.Item;

import java.util.List;

public class Tree extends Plant {
    private final TreeInfo info;
    private TreeState state;
    private TreeSource source;
    private Fruit fruit;
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

        if (!isFullyGrown()) {
            int currentLevelDays = growthStages[growthLevel - 1];

            if (daysInCurrentLevel >= currentLevelDays) {
                growthLevel++;
            }

            daysInCurrentLevel++;
            return;
        }

        if (hasProduct) return;

        if (daysInCurrentLevel < fruitHarvestCycle) {
            daysInCurrentLevel++;
        } else {
            hasProduct = true;
            daysInCurrentLevel = 0;
        }
    }

    public boolean isFullyGrown() {
        return growthLevel >= info.getStages().length;
    }

    @Override
    public boolean hasProduct() {
        return hasProduct;
    }

    @Override
    public Item getProduct() {
        if (state == TreeState.BURNT) {
//            return new Coal();
        }

        if (!hasProduct || isAttackedByCrow) return null;
        hasProduct = false;
        return fruit;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public int getPrice() {
        return 0;
    }

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
}
