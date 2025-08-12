package com.stardew_valley.models;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.ReactionType;
import com.stardew_valley.network.GameClient;

public class ReactionUI {
    private ReactionType type;
    private boolean isStarted = false;
    private float stateTime = 0f;
    public static final float TIME = 5f;


    public boolean isShowed() {
        if (stateTime > TIME) {
            isStarted = false;
        }
        //System.out.println(isStarted + "nah?");
        //System.out.println((type != null) + "yep?");
        return isStarted && type != null;
    }

    public void setStarted(ReactionType type) {
        this.type = type;
        stateTime = 0f;
        isStarted = true;
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public Texture getTexture() {
        if (type != null) {
            return type.getTexture();
        }

        return AssetManager.getAssetManager().getHappyFaceTex();
    }

    public void setType(ReactionType type) {
        this.type = type;
    }
}
