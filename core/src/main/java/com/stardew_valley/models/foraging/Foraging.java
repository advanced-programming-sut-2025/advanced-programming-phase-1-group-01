package com.stardew_valley.models.foraging;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.building.TileObject;
import com.stardew_valley.models.farming.Seed;

public abstract class Foraging implements TileObject {
    public static int getNumberFromTileObject(TileObject tileObject) {
        if (tileObject instanceof Seed seed) {
            return seed.getInfo().ordinal() + 1;
        } else if (tileObject instanceof ForagingCrop crop) {
            return crop.getInfo().ordinal() + 42 + 1;
        } else if (tileObject instanceof ForagingMineral mineral) {
            return mineral.getInfo().ordinal() + 65 + 1;
        } else {
            return 0;
        }
    }
}
