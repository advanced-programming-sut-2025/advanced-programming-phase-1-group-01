package com.stardew_valley.models.building;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Drawable;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Size;

public interface TileObject extends Item, Drawable {
    String getSymbol();
}
