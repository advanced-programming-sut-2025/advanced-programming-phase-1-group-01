package models.farming;

import models.building.TileObject;

public class Plant implements TileObject {
    protected boolean isWatered;

    public void water() {
        isWatered = true;
    }

    public boolean isWatered() {
        return isWatered;
    }

    public void resetIsWatered() {
        isWatered = false;
    }
}
