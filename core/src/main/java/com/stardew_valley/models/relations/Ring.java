package com.stardew_valley.models.relations;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.Item;

public class Ring implements Item {
    @Override
    public String getName() {
        return "ring";
    }

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public Texture getTexture() {
        return new Texture("relationship/ring.png");
    }
}
