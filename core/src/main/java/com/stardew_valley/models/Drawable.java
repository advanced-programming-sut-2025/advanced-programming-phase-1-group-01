package com.stardew_valley.models;

import com.badlogic.gdx.graphics.Texture;
import org.w3c.dom.Text;

public interface Drawable {
    default Texture getTexture() {
        return AssetManager.getAssetManager().getTempTex();
    }
}
