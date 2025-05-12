package models.farming;

import models.building.TileObject;
import models.character.NPC.TradeItem;

public class Plant implements TileObject {
    protected boolean isWatered;
    protected int growthLevel;
    protected int daysInCurrentLevel;

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

    public int getDaysInCurrentLevel() {
        return daysInCurrentLevel;
    }

    public void incrementDaysInCurrentLevel() {
        daysInCurrentLevel++;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
