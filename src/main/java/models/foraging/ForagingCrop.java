package models.foraging;

import models.Item;

public class ForagingCrop extends Foraging implements Item {
    private final ForagingCropInfo info;

    public ForagingCrop(ForagingCropInfo info) {
        this.info = info;
    }

    public ForagingCropInfo getInfo() {
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
