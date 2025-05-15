package models.foraging;

import models.Item;

public class ForagingMineral extends Foraging implements Item {
    private final ForagingMineralInfo info;

    public ForagingMineral(ForagingMineralInfo info) {
        this.info = info;
    }

    public ForagingMineralInfo getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public String getSymbol() {
        return info.getSymbol();
    }
}
