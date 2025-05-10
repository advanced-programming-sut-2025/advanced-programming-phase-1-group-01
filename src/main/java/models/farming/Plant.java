package models.farming;

import models.building.TileObject;
import models.character.NPC.TradeItem;

public class Plant implements TileObject, TradeItem {
    protected boolean isWatered;
    protected int growthLevel;

    protected int amount;

    public Plant(int amount) {
        this.amount = amount;
    }

    public void water() {
        isWatered = true;
    }

    public boolean isWatered() {
        return isWatered;
    }

    public void resetIsWatered() {
        isWatered = false;
    }

    public int getGrowthLevel() {
        return growthLevel;
    }

    public void incrementGrowthLevel() {
        growthLevel++;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
