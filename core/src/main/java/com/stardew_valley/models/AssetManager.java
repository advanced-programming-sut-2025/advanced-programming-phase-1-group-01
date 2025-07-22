package com.stardew_valley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {

    private final Skin skin = new Skin(Gdx.files.internal("skin/NzSkin.json"));
//    private final Skin skin = new Skin(Gdx.files.internal("skin_temp/pixthulhu-ui.json"));
//    private final Skin skin = new Skin(Gdx.files.internal("skin_temp/terra-mother-ui.json"));

    private static AssetManager assetManager;
    public static final float SCALE = 4f;

    private final String spring_background = "images/all_dirt.png";

    private final String alex_0_walking_right_0 = "images/a_alex/tile_1_0.png";
    private final String alex_0_walking_right_1 = "images/a_alex/tile_1_1.png";
    private final String alex_0_walking_right_2 = "images/a_alex/tile_1_2.png";
    private final String alex_0_walking_right_3 = "images/a_alex/tile_1_3.png";

    private final String alex_0_walking_left_0 = "images/a_alex/tile_3_0.png";
    private final String alex_0_walking_left_1 = "images/a_alex/tile_3_1.png";
    private final String alex_0_walking_left_2 = "images/a_alex/tile_3_2.png";
    private final String alex_0_walking_left_3 = "images/a_alex/tile_3_3.png";

    private final String alex_0_walking_up_0 = "images/a_alex/tile_2_0.png";
    private final String alex_0_walking_up_1 = "images/a_alex/tile_2_1.png";
    private final String alex_0_walking_up_2 = "images/a_alex/tile_2_2.png";
    private final String alex_0_walking_up_3 = "images/a_alex/tile_2_3.png";

    private final String alex_0_walking_down_0 = "images/a_alex/tile_0_0.png";
    private final String alex_0_walking_down_1 = "images/a_alex/tile_0_1.png";
    private final String alex_0_walking_down_2 = "images/a_alex/tile_0_2.png";
    private final String alex_0_walking_down_3 = "images/a_alex/tile_0_3.png";


    private final TextureRegion alex_0_walking_right_0_tex = new TextureRegion(new Texture(alex_0_walking_right_0));
    private final TextureRegion alex_0_walking_right_1_tex = new TextureRegion(new Texture(alex_0_walking_right_1));
    private final TextureRegion alex_0_walking_right_2_tex = new TextureRegion(new Texture(alex_0_walking_right_2));
    private final TextureRegion alex_0_walking_right_3_tex = new TextureRegion(new Texture(alex_0_walking_right_3));

    private final TextureRegion alex_0_walking_left_0_tex = new TextureRegion(new Texture(alex_0_walking_left_0));
    private final TextureRegion alex_0_walking_left_1_tex = new TextureRegion(new Texture(alex_0_walking_left_1));
    private final TextureRegion alex_0_walking_left_2_tex = new TextureRegion(new Texture(alex_0_walking_left_2));
    private final TextureRegion alex_0_walking_left_3_tex = new TextureRegion(new Texture(alex_0_walking_left_3));

    private final TextureRegion alex_0_walking_up_0_tex = new TextureRegion(new Texture(alex_0_walking_up_0));
    private final TextureRegion alex_0_walking_up_1_tex = new TextureRegion(new Texture(alex_0_walking_up_1));
    private final TextureRegion alex_0_walking_up_2_tex = new TextureRegion(new Texture(alex_0_walking_up_2));
    private final TextureRegion alex_0_walking_up_3_tex = new TextureRegion(new Texture(alex_0_walking_up_3));

    private final TextureRegion alex_0_walking_down_0_tex = new TextureRegion(new Texture(alex_0_walking_down_0));
    private final TextureRegion alex_0_walking_down_1_tex = new TextureRegion(new Texture(alex_0_walking_down_1));
    private final TextureRegion alex_0_walking_down_2_tex = new TextureRegion(new Texture(alex_0_walking_down_2));
    private final TextureRegion alex_0_walking_down_3_tex = new TextureRegion(new Texture(alex_0_walking_down_3));


    private final Animation<TextureRegion> alex_0_walking_right_animation = new Animation<>(0.1f,
        alex_0_walking_right_0_tex,
        alex_0_walking_right_1_tex,
        alex_0_walking_right_2_tex,
        alex_0_walking_right_3_tex
        );

    private final Animation<TextureRegion> alex_0_walking_left_animation = new Animation<>(0.1f,
        alex_0_walking_left_0_tex,
        alex_0_walking_left_1_tex,
        alex_0_walking_left_2_tex,
        alex_0_walking_left_3_tex
    );

    private final Animation<TextureRegion> alex_0_walking_up_animation = new Animation<>(0.1f,
        alex_0_walking_up_0_tex,
        alex_0_walking_up_1_tex,
        alex_0_walking_up_2_tex,
        alex_0_walking_up_3_tex
    );

    private final Animation<TextureRegion> alex_0_walking_down_animation = new Animation<>(0.1f,
        alex_0_walking_down_0_tex,
        alex_0_walking_down_1_tex,
        alex_0_walking_down_2_tex,
        alex_0_walking_down_3_tex
    );


    public Animation<TextureRegion> get_Alex_0_walking_right_animation() {
        return alex_0_walking_right_animation;
    }

    public Animation<TextureRegion> get_Alex_0_walking_left_animation() {
        return alex_0_walking_left_animation;
    }

    public Animation<TextureRegion> get_Alex_0_walking_up_animation() {
        return alex_0_walking_up_animation;
    }

    public Animation<TextureRegion> get_Alex_0_walking_down_animation() {
        return alex_0_walking_down_animation;
    }


    private final TextureRegion spring_background_tex = new TextureRegion(new Texture(spring_background));

    public TextureRegion getSpringBackground() {
        return spring_background_tex;
    }


    public static AssetManager getAssetManager() {
        if (assetManager == null) {
            assetManager = new AssetManager();
        }
        return assetManager;
    }

    public Skin getSkin() {
        return skin;
    }
}
