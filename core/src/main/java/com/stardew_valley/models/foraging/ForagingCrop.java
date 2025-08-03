package com.stardew_valley.models.foraging;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;

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
    public int getPrice() {
        return 0;
    }

    @Override
    public Texture getTexture() {
        return info.getTexture();
    }
}
