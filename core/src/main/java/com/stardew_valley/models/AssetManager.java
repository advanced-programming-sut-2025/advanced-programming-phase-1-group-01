package com.stardew_valley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {

    private final Skin skin = new Skin(Gdx.files.internal("skin/NzSkin.json"));

    private static AssetManager assetManager;

    public static final float SCALE = 4f;

    private final Texture tempTex = new Texture("farming/crops/Broccoli.png");

    private final Texture PlowedTile = new Texture("farming/Plowed_Tile.png");

    private final String black = "images/black.png";
    private final String white = "images/white.png";

    private final String spring_background = "images/all_dirt.png";

    private final String wood_fence = "images/wood_fence_16x16.png";
    private final String barn_fence = "images/barn_fence_16x16.png";
    private final String cage_fence = "images/cage_fence_16x16.png";

    private final String house = "images/house.png";

    private final String mine = "images/resized_mine.png";

    private final String lake = "images/r_lake.png";

    private final String greenhouse = "images/map_1.png";

    private final String clock = "images/clock.png";
    private final String arrow = "images/arrow.png";

    private final String circleSign = "images/circle.png";

    private final String lake_water = "images/lake_water.png";

    private final String house_top = "images/house_top.png";

    private final String npc_house_1_full = "images/cabins/1_full.png";
    private final String npc_house_1_top = "images/cabins/1_top.png";

    private final String npc_house_2_full = "images/cabins/2_full.png";
    private final String npc_house_2_top = "images/cabins/2_top.png";

    private final String npc_house_3_full = "images/cabins/3_full.png";
    private final String npc_house_3_top = "images/cabins/3_top.png";

    private final String npc_house_4_full = "images/cabins/4_full.png";
    private final String npc_house_4_top = "images/cabins/4_top.png";


    private final String fainting_1 = "images/fainting/resized_1-Photoroom.png";
    private final String fainting_2 = "images/fainting/resized_2-Photoroom.png";
    private final String fainting_3 = "images/fainting/resized_3-Photoroom.png";
    private final String fainting_4 = "images/fainting/resized_4-Photoroom.png";
    private final String fainting_5 = "images/fainting/resized_5-Photoroom.png";


    private final String sheep_down_0 = "images/a_Sheep/tile_0_0.png";
    private final String sheep_down_1 = "images/a_Sheep/tile_0_1.png";
    private final String sheep_down_2 = "images/a_Sheep/tile_0_2.png";
    private final String sheep_down_3 = "images/a_Sheep/tile_0_3.png";

    private final String sheep_right_0 = "images/a_Sheep/tile_1_0.png";
    private final String sheep_right_1 = "images/a_Sheep/tile_1_1.png";
    private final String sheep_right_2 = "images/a_Sheep/tile_1_2.png";
    private final String sheep_right_3 = "images/a_Sheep/tile_1_3.png";

    private final String sheep_left_0 = "images/a_Sheep/tile_1_0_flipped.png";
    private final String sheep_left_1 = "images/a_Sheep/tile_1_1_flipped.png";
    private final String sheep_left_2 = "images/a_Sheep/tile_1_2_flipped.png";
    private final String sheep_left_3 = "images/a_Sheep/tile_1_3_flipped.png";

    private final String sheep_up_0 = "images/a_Sheep/tile_2_0.png";
    private final String sheep_up_1 = "images/a_Sheep/tile_2_1.png";
    private final String sheep_up_2 = "images/a_Sheep/tile_2_2.png";
    private final String sheep_up_3 = "images/a_Sheep/tile_2_3.png";


    private final String rabbit_down_0 = "images/a_Rabbit/tile_0_0.png";
    private final String rabbit_down_1 = "images/a_Rabbit/tile_0_1.png";
    private final String rabbit_down_2 = "images/a_Rabbit/tile_0_2.png";
    private final String rabbit_down_3 = "images/a_Rabbit/tile_0_3.png";

    private final String rabbit_right_0 = "images/a_Rabbit/tile_1_0.png";
    private final String rabbit_right_1 = "images/a_Rabbit/tile_1_1.png";
    private final String rabbit_right_2 = "images/a_Rabbit/tile_1_2.png";
    private final String rabbit_right_3 = "images/a_Rabbit/tile_1_3.png";

    private final String rabbit_left_0 = "images/a_Rabbit/tile_3_0.png";
    private final String rabbit_left_1 = "images/a_Rabbit/tile_3_1.png";
    private final String rabbit_left_2 = "images/a_Rabbit/tile_3_2.png";
    private final String rabbit_left_3 = "images/a_Rabbit/tile_3_3.png";

    private final String rabbit_up_0 = "images/a_Rabbit/tile_2_0.png";
    private final String rabbit_up_1 = "images/a_Rabbit/tile_2_1.png";
    private final String rabbit_up_2 = "images/a_Rabbit/tile_2_2.png";
    private final String rabbit_up_3 = "images/a_Rabbit/tile_2_3.png";


    private final String abigail_down_0 = "images/a_Abigail/tile_0_0.png";
    private final String abigail_down_1 = "images/a_Abigail/tile_0_1.png";
    private final String abigail_down_2 = "images/a_Abigail/tile_0_2.png";
    private final String abigail_down_3 = "images/a_Abigail/tile_0_3.png";

    private final String abigail_right_0 = "images/a_Abigail/tile_1_0.png";
    private final String abigail_right_1 = "images/a_Abigail/tile_1_1.png";
    private final String abigail_right_2 = "images/a_Abigail/tile_1_2.png";
    private final String abigail_right_3 = "images/a_Abigail/tile_1_3.png";

    private final String abigail_left_0 = "images/a_Abigail/tile_3_0.png";
    private final String abigail_left_1 = "images/a_Abigail/tile_3_1.png";
    private final String abigail_left_2 = "images/a_Abigail/tile_3_2.png";
    private final String abigail_left_3 = "images/a_Abigail/tile_3_3.png";

    private final String abigail_up_0 = "images/a_Abigail/tile_2_0.png";
    private final String abigail_up_1 = "images/a_Abigail/tile_2_1.png";
    private final String abigail_up_2 = "images/a_Abigail/tile_2_2.png";
    private final String abigail_up_3 = "images/a_Abigail/tile_2_3.png";


    private final String harvey_down_0 = "images/a_harvey/tile_0_0.png";
    private final String harvey_down_1 = "images/a_harvey/tile_0_1.png";
    private final String harvey_down_2 = "images/a_harvey/tile_0_2.png";
    private final String harvey_down_3 = "images/a_harvey/tile_0_3.png";

    private final String harvey_right_0 = "images/a_harvey/tile_1_0.png";
    private final String harvey_right_1 = "images/a_harvey/tile_1_1.png";
    private final String harvey_right_2 = "images/a_harvey/tile_1_2.png";
    private final String harvey_right_3 = "images/a_harvey/tile_1_3.png";

    private final String harvey_left_0 = "images/a_harvey/tile_3_0.png";
    private final String harvey_left_1 = "images/a_harvey/tile_3_1.png";
    private final String harvey_left_2 = "images/a_harvey/tile_3_2.png";
    private final String harvey_left_3 = "images/a_harvey/tile_3_3.png";

    private final String harvey_up_0 = "images/a_harvey/tile_2_0.png";
    private final String harvey_up_1 = "images/a_harvey/tile_2_1.png";
    private final String harvey_up_2 = "images/a_harvey/tile_2_2.png";
    private final String harvey_up_3 = "images/a_harvey/tile_2_3.png";


    private final String sebastian_down_0 = "images/a_sebastian/tile_0_0.png";
    private final String sebastian_down_1 = "images/a_sebastian/tile_0_1.png";
    private final String sebastian_down_2 = "images/a_sebastian/tile_0_2.png";
    private final String sebastian_down_3 = "images/a_sebastian/tile_0_3.png";

    private final String sebastian_right_0 = "images/a_sebastian/tile_1_0.png";
    private final String sebastian_right_1 = "images/a_sebastian/tile_1_1.png";
    private final String sebastian_right_2 = "images/a_sebastian/tile_1_2.png";
    private final String sebastian_right_3 = "images/a_sebastian/tile_1_3.png";

    private final String sebastian_left_0 = "images/a_sebastian/tile_3_0.png";
    private final String sebastian_left_1 = "images/a_sebastian/tile_3_1.png";
    private final String sebastian_left_2 = "images/a_sebastian/tile_3_2.png";
    private final String sebastian_left_3 = "images/a_sebastian/tile_3_3.png";

    private final String sebastian_up_0 = "images/a_sebastian/tile_2_0.png";
    private final String sebastian_up_1 = "images/a_sebastian/tile_2_1.png";
    private final String sebastian_up_2 = "images/a_sebastian/tile_2_2.png";
    private final String sebastian_up_3 = "images/a_sebastian/tile_2_3.png";


    private final String leah_down_0 = "images/a_leah/tile_0_0.png";
    private final String leah_down_1 = "images/a_leah/tile_0_1.png";
    private final String leah_down_2 = "images/a_leah/tile_0_2.png";
    private final String leah_down_3 = "images/a_leah/tile_0_3.png";

    private final String leah_right_0 = "images/a_leah/tile_1_0.png";
    private final String leah_right_1 = "images/a_leah/tile_1_1.png";
    private final String leah_right_2 = "images/a_leah/tile_1_2.png";
    private final String leah_right_3 = "images/a_leah/tile_1_3.png";

    private final String leah_left_0 = "images/a_leah/tile_3_0.png";
    private final String leah_left_1 = "images/a_leah/tile_3_1.png";
    private final String leah_left_2 = "images/a_leah/tile_3_2.png";
    private final String leah_left_3 = "images/a_leah/tile_3_3.png";

    private final String leah_up_0 = "images/a_leah/tile_2_0.png";
    private final String leah_up_1 = "images/a_leah/tile_2_1.png";
    private final String leah_up_2 = "images/a_leah/tile_2_2.png";
    private final String leah_up_3 = "images/a_leah/tile_2_3.png";


    private final String pig_down_0 = "images/a_Pig/tile_0_0.png";
    private final String pig_down_1 = "images/a_Pig/tile_0_1.png";
    private final String pig_down_2 = "images/a_Pig/tile_0_2.png";
    private final String pig_down_3 = "images/a_Pig/tile_0_3.png";

    private final String pig_right_0 = "images/a_Pig/tile_1_0.png";
    private final String pig_right_1 = "images/a_Pig/tile_1_1.png";
    private final String pig_right_2 = "images/a_Pig/tile_1_2.png";
    private final String pig_right_3 = "images/a_Pig/tile_1_3.png";

    private final String pig_left_0 = "images/a_Pig/tile_1_0_flipped.png";
    private final String pig_left_1 = "images/a_Pig/tile_1_1_flipped.png";
    private final String pig_left_2 = "images/a_Pig/tile_1_2_flipped.png";
    private final String pig_left_3 = "images/a_Pig/tile_1_3_flipped.png";

    private final String pig_up_0 = "images/a_Pig/tile_2_0.png";
    private final String pig_up_1 = "images/a_Pig/tile_2_1.png";
    private final String pig_up_2 = "images/a_Pig/tile_2_2.png";
    private final String pig_up_3 = "images/a_Pig/tile_2_3.png";


    private final String hen_down_0 = "images/a_hen/tile_0_0.png";
    private final String hen_down_1 = "images/a_hen/tile_0_1.png";
    private final String hen_down_2 = "images/a_dino/tile_0_2.png";
    private final String hen_down_3 = "images/a_dino/tile_0_3.png";

    private final String hen_right_0 = "images/a_hen/tile_1_0.png";
    private final String hen_right_1 = "images/a_hen/tile_1_1.png";
    private final String hen_right_2 = "images/a_hen/tile_1_2.png";
    private final String hen_right_3 = "images/a_hen/tile_1_3.png";

    private final String hen_left_0 = "images/a_hen/tile_3_0.png";
    private final String hen_left_1 = "images/a_dino/tile_3_1.png";
    private final String hen_left_2 = "images/a_hen/tile_3_2.png";
    private final String hen_left_3 = "images/a_hen/tile_3_3.png";

    private final String hen_up_0 = "images/a_dino/tile_2_0.png";
    private final String hen_up_1 = "images/a_hen/tile_2_1.png";
    private final String hen_up_2 = "images/a_hen/tile_2_2.png";
    private final String hen_up_3 = "images/a_hen/tile_2_3.png";


    private final String duck_down_0 = "images/a_Duck/tile_0_0.png";
    private final String duck_down_1 = "images/a_Duck/tile_0_1.png";
    private final String duck_down_2 = "images/a_Duck/tile_0_2.png";
    private final String duck_down_3 = "images/a_Duck/tile_0_3.png";

    private final String duck_right_0 = "images/a_Duck/tile_1_0.png";
    private final String duck_right_1 = "images/a_Duck/tile_1_1.png";
    private final String duck_right_2 = "images/a_Duck/tile_1_2.png";
    private final String duck_right_3 = "images/a_Duck/tile_1_3.png";

    private final String duck_left_0 = "images/a_Duck/tile_1_0_flipped.png";
    private final String duck_left_1 = "images/a_Duck/tile_1_1_flipped.png";
    private final String duck_left_2 = "images/a_Duck/tile_1_2_flipped.png";
    private final String duck_left_3 = "images/a_Duck/tile_1_3_flipped.png";

    private final String duck_up_0 = "images/a_Duck/tile_0_0.png";
    private final String duck_up_1 = "images/a_Duck/tile_0_1.png";
    private final String duck_up_2 = "images/a_Duck/tile_0_2.png";
    private final String duck_up_3 = "images/a_Duck/tile_0_3.png";


    private final String dino_down_0 = "images/a_dino/tile_0_0.png";
    private final String dino_down_1 = "images/a_dino/tile_0_1.png";
    private final String dino_down_2 = "images/a_dino/tile_0_2.png";
    private final String dino_down_3 = "images/a_dino/tile_0_3.png";

    private final String dino_right_0 = "images/a_dino/tile_1_0.png";
    private final String dino_right_1 = "images/a_dino/tile_1_1.png";
    private final String dino_right_2 = "images/a_dino/tile_1_2.png";
    private final String dino_right_3 = "images/a_dino/tile_1_3.png";

    private final String dino_left_0 = "images/a_dino/tile_3_0.png";
    private final String dino_left_1 = "images/a_dino/tile_3_1.png";
    private final String dino_left_2 = "images/a_dino/tile_3_2.png";
    private final String dino_left_3 = "images/a_dino/tile_3_3.png";

    private final String dino_up_0 = "images/a_dino/tile_2_0.png";
    private final String dino_up_1 = "images/a_dino/tile_2_1.png";
    private final String dino_up_2 = "images/a_dino/tile_2_2.png";
    private final String dino_up_3 = "images/a_dino/tile_2_3.png";


    private final String goat_down_0 = "images/a_Goat/tile_0_0.png";
    private final String goat_down_1 = "images/a_Goat/tile_0_1.png";
    private final String goat_down_2 = "images/a_Goat/tile_0_2.png";
    private final String goat_down_3 = "images/a_Goat/tile_0_3.png";

    private final String goat_right_0 = "images/a_Goat/tile_1_0.png";
    private final String goat_right_1 = "images/a_Goat/tile_1_1.png";
    private final String goat_right_2 = "images/a_Goat/tile_1_2.png";
    private final String goat_right_3 = "images/a_Goat/tile_1_3.png";

    private final String goat_left_0 = "images/a_Goat/tile_1_0_flipped.png";
    private final String goat_left_1 = "images/a_Goat/tile_1_1_flipped.png";
    private final String goat_left_2 = "images/a_Goat/tile_1_2_flipped.png";
    private final String goat_left_3 = "images/a_Goat/tile_1_3_flipped.png";

    private final String goat_up_0 = "images/a_Goat/tile_2_0.png";
    private final String goat_up_1 = "images/a_Goat/tile_2_1.png";
    private final String goat_up_2 = "images/a_Goat/tile_2_2.png";
    private final String goat_up_3 = "images/a_Goat/tile_2_3.png";


    private final String cow_down_0 = "images/a_cow/tile_0_0.png";
    private final String cow_down_1 = "images/a_cow/tile_0_0.png";
    private final String cow_down_2 = "images/a_cow/tile_0_0.png";
    private final String cow_down_3 = "images/a_cow/tile_0_0.png";

    private final String cow_right_0 = "images/a_cow/tile_1_0.png";
    private final String cow_right_1 = "images/a_cow/tile_1_1.png";
    private final String cow_right_2 = "images/a_cow/tile_1_2.png";
    private final String cow_right_3 = "images/a_cow/tile_1_3.png";

    private final String cow_left_0 = "images/a_cow/tile_1_0_flipped.png";
    private final String cow_left_1 = "images/a_cow/tile_1_1_flipped.png";
    private final String cow_left_2 = "images/a_cow/tile_1_2_flipped.png";
    private final String cow_left_3 = "images/a_cow/tile_1_3_flipped.png";

    private final String cow_up_0 = "images/a_cow/tile_2_0.png";
    private final String cow_up_1 = "images/a_cow/tile_2_1.png";
    private final String cow_up_2 = "images/a_cow/tile_2_2.png";
    private final String cow_up_3 = "images/a_cow/tile_2_3.png";


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


    private final TextureRegion house_tex = new TextureRegion(new Texture(house));


    private final TextureRegion mine_tex = new TextureRegion(new Texture(mine));

    private final TextureRegion lake_tex = new TextureRegion(new Texture(lake));

    private final TextureRegion greenhouse_tex = new TextureRegion(new Texture(greenhouse));

    private final TextureRegion clock_tex = new TextureRegion(new Texture(clock));
    private final TextureRegion arrow_tex = new TextureRegion(new Texture(arrow));

    private final TextureRegion circle_tex = new TextureRegion(new Texture(circleSign));

    private final TextureRegion lake_water_tex = new TextureRegion(new Texture(lake_water));

    private final TextureRegion house_top_tex = new TextureRegion(new Texture(house_top));

    private final TextureRegion npc_house_1_full_tex = new TextureRegion(new Texture(npc_house_1_full));
    private final TextureRegion npc_house_1_top_tex = new TextureRegion(new Texture(npc_house_1_top));

    private final TextureRegion npc_house_2_full_tex = new TextureRegion(new Texture(npc_house_2_full));
    private final TextureRegion npc_house_2_top_tex = new TextureRegion(new Texture(npc_house_2_top));

    private final TextureRegion npc_house_3_full_tex = new TextureRegion(new Texture(npc_house_3_full));
    private final TextureRegion npc_house_3_top_tex = new TextureRegion(new Texture(npc_house_3_top));

    private final TextureRegion npc_house_4_full_tex = new TextureRegion(new Texture(npc_house_4_full));
    private final TextureRegion npc_house_4_top_tex = new TextureRegion(new Texture(npc_house_4_top));

    private final TextureRegion wood_fence_tex = new TextureRegion(new Texture(wood_fence));
    private final Texture cage_fence_tex = new Texture(cage_fence);
    private final Texture barn_fence_tex = new Texture(barn_fence);

    private final TextureRegion fainting_tex1 = new TextureRegion(new Texture(fainting_1));
    private final TextureRegion fainting_tex2 = new TextureRegion(new Texture(fainting_2));
    private final TextureRegion fainting_tex3 = new TextureRegion(new Texture(fainting_3));
    private final TextureRegion fainting_tex4 = new TextureRegion(new Texture(fainting_4));
    private final TextureRegion fainting_tex5 = new TextureRegion(new Texture(fainting_5));


    private final TextureRegion sheep_down_0_tex = new TextureRegion(new Texture(sheep_down_0));
    private final TextureRegion sheep_down_1_tex = new TextureRegion(new Texture(sheep_down_1));
    private final TextureRegion sheep_down_2_tex = new TextureRegion(new Texture(sheep_down_2));
    private final TextureRegion sheep_down_3_tex = new TextureRegion(new Texture(sheep_down_3));

    private final TextureRegion sheep_right_0_tex = new TextureRegion(new Texture(sheep_right_0));
    private final TextureRegion sheep_right_1_tex = new TextureRegion(new Texture(sheep_right_1));
    private final TextureRegion sheep_right_2_tex = new TextureRegion(new Texture(sheep_right_2));
    private final TextureRegion sheep_right_3_tex = new TextureRegion(new Texture(sheep_right_3));

    private final TextureRegion sheep_up_0_tex = new TextureRegion(new Texture(sheep_up_0));
    private final TextureRegion sheep_up_1_tex = new TextureRegion(new Texture(sheep_up_1));
    private final TextureRegion sheep_up_2_tex = new TextureRegion(new Texture(sheep_up_2));
    private final TextureRegion sheep_up_3_tex = new TextureRegion(new Texture(sheep_up_3));

    private final TextureRegion sheep_left_0_tex = new TextureRegion(new Texture(sheep_left_0));
    private final TextureRegion sheep_left_1_tex = new TextureRegion(new Texture(sheep_left_1));
    private final TextureRegion sheep_left_2_tex = new TextureRegion(new Texture(sheep_left_2));
    private final TextureRegion sheep_left_3_tex = new TextureRegion(new Texture(sheep_left_3));


    private final TextureRegion rabbit_down_0_tex = new TextureRegion(new Texture(rabbit_down_0));
    private final TextureRegion rabbit_down_1_tex = new TextureRegion(new Texture(rabbit_down_1));
    private final TextureRegion rabbit_down_2_tex = new TextureRegion(new Texture(rabbit_down_2));
    private final TextureRegion rabbit_down_3_tex = new TextureRegion(new Texture(rabbit_down_3));

    private final TextureRegion rabbit_right_0_tex = new TextureRegion(new Texture(rabbit_right_0));
    private final TextureRegion rabbit_right_1_tex = new TextureRegion(new Texture(rabbit_right_1));
    private final TextureRegion rabbit_right_2_tex = new TextureRegion(new Texture(rabbit_right_2));
    private final TextureRegion rabbit_right_3_tex = new TextureRegion(new Texture(rabbit_right_3));

    private final TextureRegion rabbit_up_0_tex = new TextureRegion(new Texture(rabbit_up_0));
    private final TextureRegion rabbit_up_1_tex = new TextureRegion(new Texture(rabbit_up_1));
    private final TextureRegion rabbit_up_2_tex = new TextureRegion(new Texture(rabbit_up_2));
    private final TextureRegion rabbit_up_3_tex = new TextureRegion(new Texture(rabbit_up_3));

    private final TextureRegion rabbit_left_0_tex = new TextureRegion(new Texture(rabbit_left_0));
    private final TextureRegion rabbit_left_1_tex = new TextureRegion(new Texture(rabbit_left_1));
    private final TextureRegion rabbit_left_2_tex = new TextureRegion(new Texture(rabbit_left_2));
    private final TextureRegion rabbit_left_3_tex = new TextureRegion(new Texture(rabbit_left_3));


    private final TextureRegion leah_down_0_tex = new TextureRegion(new Texture(leah_down_0));
    private final TextureRegion leah_down_1_tex = new TextureRegion(new Texture(leah_down_1));
    private final TextureRegion leah_down_2_tex = new TextureRegion(new Texture(leah_down_2));
    private final TextureRegion leah_down_3_tex = new TextureRegion(new Texture(leah_down_3));

    private final TextureRegion leah_right_0_tex = new TextureRegion(new Texture(leah_right_0));
    private final TextureRegion leah_right_1_tex = new TextureRegion(new Texture(leah_right_1));
    private final TextureRegion leah_right_2_tex = new TextureRegion(new Texture(leah_right_2));
    private final TextureRegion leah_right_3_tex = new TextureRegion(new Texture(leah_right_3));

    private final TextureRegion leah_up_0_tex = new TextureRegion(new Texture(leah_up_0));
    private final TextureRegion leah_up_1_tex = new TextureRegion(new Texture(leah_up_1));
    private final TextureRegion leah_up_2_tex = new TextureRegion(new Texture(leah_up_2));
    private final TextureRegion leah_up_3_tex = new TextureRegion(new Texture(leah_up_3));

    private final TextureRegion leah_left_0_tex = new TextureRegion(new Texture(leah_left_0));
    private final TextureRegion leah_left_1_tex = new TextureRegion(new Texture(leah_left_1));
    private final TextureRegion leah_left_2_tex = new TextureRegion(new Texture(leah_left_2));
    private final TextureRegion leah_left_3_tex = new TextureRegion(new Texture(leah_left_3));


    private final TextureRegion sebastian_down_0_tex = new TextureRegion(new Texture(sebastian_down_0));
    private final TextureRegion sebastian_down_1_tex = new TextureRegion(new Texture(sebastian_down_1));
    private final TextureRegion sebastian_down_2_tex = new TextureRegion(new Texture(sebastian_down_2));
    private final TextureRegion sebastian_down_3_tex = new TextureRegion(new Texture(sebastian_down_3));

    private final TextureRegion sebastian_right_0_tex = new TextureRegion(new Texture(sebastian_right_0));
    private final TextureRegion sebastian_right_1_tex = new TextureRegion(new Texture(sebastian_right_1));
    private final TextureRegion sebastian_right_2_tex = new TextureRegion(new Texture(sebastian_right_2));
    private final TextureRegion sebastian_right_3_tex = new TextureRegion(new Texture(sebastian_right_3));

    private final TextureRegion sebastian_up_0_tex = new TextureRegion(new Texture(sebastian_up_0));
    private final TextureRegion sebastian_up_1_tex = new TextureRegion(new Texture(sebastian_up_1));
    private final TextureRegion sebastian_up_2_tex = new TextureRegion(new Texture(sebastian_up_2));
    private final TextureRegion sebastian_up_3_tex = new TextureRegion(new Texture(sebastian_up_3));

    private final TextureRegion sebastian_left_0_tex = new TextureRegion(new Texture(sebastian_left_0));
    private final TextureRegion sebastian_left_1_tex = new TextureRegion(new Texture(sebastian_left_1));
    private final TextureRegion sebastian_left_2_tex = new TextureRegion(new Texture(sebastian_left_2));
    private final TextureRegion sebastian_left_3_tex = new TextureRegion(new Texture(sebastian_left_3));


    private final TextureRegion harvey_down_0_tex = new TextureRegion(new Texture(harvey_down_0));
    private final TextureRegion harvey_down_1_tex = new TextureRegion(new Texture(harvey_down_1));
    private final TextureRegion harvey_down_2_tex = new TextureRegion(new Texture(harvey_down_2));
    private final TextureRegion harvey_down_3_tex = new TextureRegion(new Texture(harvey_down_3));

    private final TextureRegion harvey_right_0_tex = new TextureRegion(new Texture(harvey_right_0));
    private final TextureRegion harvey_right_1_tex = new TextureRegion(new Texture(harvey_right_1));
    private final TextureRegion harvey_right_2_tex = new TextureRegion(new Texture(harvey_right_2));
    private final TextureRegion harvey_right_3_tex = new TextureRegion(new Texture(harvey_right_3));

    private final TextureRegion harvey_up_0_tex = new TextureRegion(new Texture(harvey_up_0));
    private final TextureRegion harvey_up_1_tex = new TextureRegion(new Texture(harvey_up_1));
    private final TextureRegion harvey_up_2_tex = new TextureRegion(new Texture(harvey_up_2));
    private final TextureRegion harvey_up_3_tex = new TextureRegion(new Texture(harvey_up_3));

    private final TextureRegion harvey_left_0_tex = new TextureRegion(new Texture(harvey_left_0));
    private final TextureRegion harvey_left_1_tex = new TextureRegion(new Texture(harvey_left_1));
    private final TextureRegion harvey_left_2_tex = new TextureRegion(new Texture(harvey_left_2));
    private final TextureRegion harvey_left_3_tex = new TextureRegion(new Texture(harvey_left_3));


    private final TextureRegion abigail_down_0_tex = new TextureRegion(new Texture(abigail_down_0));
    private final TextureRegion abigail_down_1_tex = new TextureRegion(new Texture(abigail_down_1));
    private final TextureRegion abigail_down_2_tex = new TextureRegion(new Texture(abigail_down_2));
    private final TextureRegion abigail_down_3_tex = new TextureRegion(new Texture(abigail_down_3));

    private final TextureRegion abigail_right_0_tex = new TextureRegion(new Texture(abigail_right_0));
    private final TextureRegion abigail_right_1_tex = new TextureRegion(new Texture(abigail_right_1));
    private final TextureRegion abigail_right_2_tex = new TextureRegion(new Texture(abigail_right_2));
    private final TextureRegion abigail_right_3_tex = new TextureRegion(new Texture(abigail_right_3));

    private final TextureRegion abigail_up_0_tex = new TextureRegion(new Texture(abigail_up_0));
    private final TextureRegion abigail_up_1_tex = new TextureRegion(new Texture(abigail_up_1));
    private final TextureRegion abigail_up_2_tex = new TextureRegion(new Texture(abigail_up_2));
    private final TextureRegion abigail_up_3_tex = new TextureRegion(new Texture(abigail_up_3));

    private final TextureRegion abigail_left_0_tex = new TextureRegion(new Texture(abigail_left_0));
    private final TextureRegion abigail_left_1_tex = new TextureRegion(new Texture(abigail_left_1));
    private final TextureRegion abigail_left_2_tex = new TextureRegion(new Texture(abigail_left_2));
    private final TextureRegion abigail_left_3_tex = new TextureRegion(new Texture(abigail_left_3));


    private final TextureRegion pig_down_0_tex = new TextureRegion(new Texture(pig_down_0));
    private final TextureRegion pig_down_1_tex = new TextureRegion(new Texture(pig_down_1));
    private final TextureRegion pig_down_2_tex = new TextureRegion(new Texture(pig_down_2));
    private final TextureRegion pig_down_3_tex = new TextureRegion(new Texture(pig_down_3));

    private final TextureRegion pig_right_0_tex = new TextureRegion(new Texture(pig_right_0));
    private final TextureRegion pig_right_1_tex = new TextureRegion(new Texture(pig_right_1));
    private final TextureRegion pig_right_2_tex = new TextureRegion(new Texture(pig_right_2));
    private final TextureRegion pig_right_3_tex = new TextureRegion(new Texture(pig_right_3));

    private final TextureRegion pig_up_0_tex = new TextureRegion(new Texture(pig_up_0));
    private final TextureRegion pig_up_1_tex = new TextureRegion(new Texture(pig_up_1));
    private final TextureRegion pig_up_2_tex = new TextureRegion(new Texture(pig_up_2));
    private final TextureRegion pig_up_3_tex = new TextureRegion(new Texture(pig_up_3));

    private final TextureRegion pig_left_0_tex = new TextureRegion(new Texture(pig_left_0));
    private final TextureRegion pig_left_1_tex = new TextureRegion(new Texture(pig_left_1));
    private final TextureRegion pig_left_2_tex = new TextureRegion(new Texture(pig_left_2));
    private final TextureRegion pig_left_3_tex = new TextureRegion(new Texture(pig_left_3));


    private final TextureRegion hen_down_0_tex = new TextureRegion(new Texture(hen_down_0));
    private final TextureRegion hen_down_1_tex = new TextureRegion(new Texture(hen_down_1));
    private final TextureRegion hen_down_2_tex = new TextureRegion(new Texture(hen_down_2));
    private final TextureRegion hen_down_3_tex = new TextureRegion(new Texture(hen_down_3));

    private final TextureRegion hen_right_0_tex = new TextureRegion(new Texture(hen_right_0));
    private final TextureRegion hen_right_1_tex = new TextureRegion(new Texture(hen_right_1));
    private final TextureRegion hen_right_2_tex = new TextureRegion(new Texture(hen_right_2));
    private final TextureRegion hen_right_3_tex = new TextureRegion(new Texture(hen_right_3));

    private final TextureRegion hen_up_0_tex = new TextureRegion(new Texture(hen_up_0));
    private final TextureRegion hen_up_1_tex = new TextureRegion(new Texture(hen_up_1));
    private final TextureRegion hen_up_2_tex = new TextureRegion(new Texture(hen_up_2));
    private final TextureRegion hen_up_3_tex = new TextureRegion(new Texture(hen_up_3));

    private final TextureRegion hen_left_0_tex = new TextureRegion(new Texture(hen_left_0));
    private final TextureRegion hen_left_1_tex = new TextureRegion(new Texture(hen_left_1));
    private final TextureRegion hen_left_2_tex = new TextureRegion(new Texture(hen_left_2));
    private final TextureRegion hen_left_3_tex = new TextureRegion(new Texture(hen_left_3));


    private final TextureRegion duck_down_0_tex = new TextureRegion(new Texture(duck_down_0));
    private final TextureRegion duck_down_1_tex = new TextureRegion(new Texture(duck_down_1));
    private final TextureRegion duck_down_2_tex = new TextureRegion(new Texture(duck_down_2));
    private final TextureRegion duck_down_3_tex = new TextureRegion(new Texture(duck_down_3));

    private final TextureRegion duck_right_0_tex = new TextureRegion(new Texture(duck_right_0));
    private final TextureRegion duck_right_1_tex = new TextureRegion(new Texture(duck_right_1));
    private final TextureRegion duck_right_2_tex = new TextureRegion(new Texture(duck_right_2));
    private final TextureRegion duck_right_3_tex = new TextureRegion(new Texture(duck_right_3));

    private final TextureRegion duck_up_0_tex = new TextureRegion(new Texture(duck_up_0));
    private final TextureRegion duck_up_1_tex = new TextureRegion(new Texture(duck_up_1));
    private final TextureRegion duck_up_2_tex = new TextureRegion(new Texture(duck_up_2));
    private final TextureRegion duck_up_3_tex = new TextureRegion(new Texture(duck_up_3));

    private final TextureRegion duck_left_0_tex = new TextureRegion(new Texture(duck_left_0));
    private final TextureRegion duck_left_1_tex = new TextureRegion(new Texture(duck_left_1));
    private final TextureRegion duck_left_2_tex = new TextureRegion(new Texture(duck_left_2));
    private final TextureRegion duck_left_3_tex = new TextureRegion(new Texture(duck_left_3));


    private final TextureRegion goat_down_0_tex = new TextureRegion(new Texture(goat_down_0));
    private final TextureRegion goat_down_1_tex = new TextureRegion(new Texture(goat_down_1));
    private final TextureRegion goat_down_2_tex = new TextureRegion(new Texture(goat_down_2));
    private final TextureRegion goat_down_3_tex = new TextureRegion(new Texture(goat_down_3));

    private final TextureRegion goat_right_0_tex = new TextureRegion(new Texture(goat_right_0));
    private final TextureRegion goat_right_1_tex = new TextureRegion(new Texture(goat_right_1));
    private final TextureRegion goat_right_2_tex = new TextureRegion(new Texture(goat_right_2));
    private final TextureRegion goat_right_3_tex = new TextureRegion(new Texture(goat_right_3));

    private final TextureRegion goat_up_0_tex = new TextureRegion(new Texture(goat_up_0));
    private final TextureRegion goat_up_1_tex = new TextureRegion(new Texture(goat_up_1));
    private final TextureRegion goat_up_2_tex = new TextureRegion(new Texture(goat_up_2));
    private final TextureRegion goat_up_3_tex = new TextureRegion(new Texture(goat_up_3));

    private final TextureRegion goat_left_0_tex = new TextureRegion(new Texture(goat_left_0));
    private final TextureRegion goat_left_1_tex = new TextureRegion(new Texture(goat_left_1));
    private final TextureRegion goat_left_2_tex = new TextureRegion(new Texture(goat_left_2));
    private final TextureRegion goat_left_3_tex = new TextureRegion(new Texture(goat_left_3));


    private final TextureRegion dino_down_0_tex = new TextureRegion(new Texture(dino_down_0));
    private final TextureRegion dino_down_1_tex = new TextureRegion(new Texture(dino_down_1));
    private final TextureRegion dino_down_2_tex = new TextureRegion(new Texture(dino_down_2));
    private final TextureRegion dino_down_3_tex = new TextureRegion(new Texture(dino_down_3));

    private final TextureRegion dino_right_0_tex = new TextureRegion(new Texture(dino_right_0));
    private final TextureRegion dino_right_1_tex = new TextureRegion(new Texture(dino_right_1));
    private final TextureRegion dino_right_2_tex = new TextureRegion(new Texture(dino_right_2));
    private final TextureRegion dino_right_3_tex = new TextureRegion(new Texture(dino_right_3));

    private final TextureRegion dino_up_0_tex = new TextureRegion(new Texture(dino_up_0));
    private final TextureRegion dino_up_1_tex = new TextureRegion(new Texture(dino_up_1));
    private final TextureRegion dino_up_2_tex = new TextureRegion(new Texture(dino_up_2));
    private final TextureRegion dino_up_3_tex = new TextureRegion(new Texture(dino_up_3));

    private final TextureRegion dino_left_0_tex = new TextureRegion(new Texture(dino_left_0));
    private final TextureRegion dino_left_1_tex = new TextureRegion(new Texture(dino_left_1));
    private final TextureRegion dino_left_2_tex = new TextureRegion(new Texture(dino_left_2));
    private final TextureRegion dino_left_3_tex = new TextureRegion(new Texture(dino_left_3));


    private final TextureRegion cow_down_0_tex = new TextureRegion(new Texture(cow_down_0));
    private final TextureRegion cow_down_1_tex = new TextureRegion(new Texture(cow_down_1));
    private final TextureRegion cow_down_2_tex = new TextureRegion(new Texture(cow_down_2));
    private final TextureRegion cow_down_3_tex = new TextureRegion(new Texture(cow_down_3));

    private final TextureRegion cow_right_0_tex = new TextureRegion(new Texture(cow_right_0));
    private final TextureRegion cow_right_1_tex = new TextureRegion(new Texture(cow_right_1));
    private final TextureRegion cow_right_2_tex = new TextureRegion(new Texture(cow_right_2));
    private final TextureRegion cow_right_3_tex = new TextureRegion(new Texture(cow_right_3));

    private final TextureRegion cow_up_0_tex = new TextureRegion(new Texture(cow_up_0));
    private final TextureRegion cow_up_1_tex = new TextureRegion(new Texture(cow_up_1));
    private final TextureRegion cow_up_2_tex = new TextureRegion(new Texture(cow_up_2));
    private final TextureRegion cow_up_3_tex = new TextureRegion(new Texture(cow_up_3));

    private final TextureRegion cow_left_0_tex = new TextureRegion(new Texture(cow_left_0));
    private final TextureRegion cow_left_1_tex = new TextureRegion(new Texture(cow_left_1));
    private final TextureRegion cow_left_2_tex = new TextureRegion(new Texture(cow_left_2));
    private final TextureRegion cow_left_3_tex = new TextureRegion(new Texture(cow_left_3));


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


    private final Animation<TextureRegion> sheep_up_animation = new Animation<>(0.1f,
        sheep_up_0_tex,
        sheep_up_1_tex,
        sheep_up_2_tex,
        sheep_up_3_tex
    );

    private final Animation<TextureRegion> sheep_right_animation = new Animation<>(0.1f,
        sheep_right_0_tex,
        sheep_right_1_tex,
        sheep_right_2_tex,
        sheep_right_3_tex
    );

    private final Animation<TextureRegion> sheep_down_animation = new Animation<>(0.1f,
        sheep_down_0_tex,
        sheep_down_1_tex,
        sheep_down_2_tex,
        sheep_down_3_tex
    );

    private final Animation<TextureRegion> sheep_left_animation = new Animation<>(0.1f,
        sheep_left_0_tex,
        sheep_left_1_tex,
        sheep_left_2_tex,
        sheep_left_3_tex
    );


    private final Animation<TextureRegion> rabbit_up_animation = new Animation<>(0.1f,
        rabbit_up_0_tex,
        rabbit_up_1_tex,
        rabbit_up_2_tex,
        rabbit_up_3_tex
    );

    private final Animation<TextureRegion> rabbit_right_animation = new Animation<>(0.1f,
        rabbit_right_0_tex,
        rabbit_right_1_tex,
        rabbit_right_2_tex,
        rabbit_right_3_tex
    );

    private final Animation<TextureRegion> rabbit_down_animation = new Animation<>(0.1f,
        rabbit_down_0_tex,
        rabbit_down_1_tex,
        rabbit_down_2_tex,
        rabbit_down_3_tex
    );

    private final Animation<TextureRegion> rabbit_left_animation = new Animation<>(0.1f,
        rabbit_left_0_tex,
        rabbit_left_1_tex,
        rabbit_left_2_tex,
        rabbit_left_3_tex
    );


    private final Animation<TextureRegion> leah_up_animation = new Animation<>(0.1f,
        leah_up_0_tex,
        leah_up_1_tex,
        leah_up_2_tex,
        leah_up_3_tex
    );

    private final Animation<TextureRegion> leah_right_animation = new Animation<>(0.1f,
        leah_right_0_tex,
        leah_right_1_tex,
        leah_right_2_tex,
        leah_right_3_tex
    );

    private final Animation<TextureRegion> leah_down_animation = new Animation<>(0.1f,
        leah_down_0_tex,
        leah_down_1_tex,
        leah_down_2_tex,
        leah_down_3_tex
    );

    private final Animation<TextureRegion> leah_left_animation = new Animation<>(0.1f,
        leah_left_0_tex,
        leah_left_1_tex,
        leah_left_2_tex,
        leah_left_3_tex
    );


    private final Animation<TextureRegion> sebastian_up_animation = new Animation<>(0.1f,
        sebastian_up_0_tex,
        sebastian_up_1_tex,
        sebastian_up_2_tex,
        sebastian_up_3_tex
    );

    private final Animation<TextureRegion> sebastian_right_animation = new Animation<>(0.1f,
        sebastian_right_0_tex,
        sebastian_right_1_tex,
        sebastian_right_2_tex,
        sebastian_right_3_tex
    );

    private final Animation<TextureRegion> sebastian_down_animation = new Animation<>(0.1f,
        sebastian_down_0_tex,
        sebastian_down_1_tex,
        sebastian_down_2_tex,
        sebastian_down_3_tex
    );

    private final Animation<TextureRegion> sebastian_left_animation = new Animation<>(0.1f,
        sebastian_left_0_tex,
        sebastian_left_1_tex,
        sebastian_left_2_tex,
        sebastian_left_3_tex
    );


    private final Animation<TextureRegion> harvey_up_animation = new Animation<>(0.1f,
        harvey_up_0_tex,
        harvey_up_1_tex,
        harvey_up_2_tex,
        harvey_up_3_tex
    );

    private final Animation<TextureRegion> harvey_right_animation = new Animation<>(0.1f,
        harvey_right_0_tex,
        harvey_right_1_tex,
        harvey_right_2_tex,
        harvey_right_3_tex
    );

    private final Animation<TextureRegion> harvey_down_animation = new Animation<>(0.1f,
        harvey_down_0_tex,
        harvey_down_1_tex,
        harvey_down_2_tex,
        harvey_down_3_tex
    );

    private final Animation<TextureRegion> harvey_left_animation = new Animation<>(0.1f,
        harvey_left_0_tex,
        harvey_left_1_tex,
        harvey_left_2_tex,
        harvey_left_3_tex
    );


    private final Animation<TextureRegion> abigail_up_animation = new Animation<>(0.1f,
        abigail_up_0_tex,
        abigail_up_1_tex,
        abigail_up_2_tex,
        abigail_up_3_tex
    );

    private final Animation<TextureRegion> abigail_right_animation = new Animation<>(0.1f,
        abigail_right_0_tex,
        abigail_right_1_tex,
        abigail_right_2_tex,
        abigail_right_3_tex
    );

    private final Animation<TextureRegion> abigail_down_animation = new Animation<>(0.1f,
        abigail_down_0_tex,
        abigail_down_1_tex,
        abigail_down_2_tex,
        abigail_down_3_tex
    );

    private final Animation<TextureRegion> abigail_left_animation = new Animation<>(0.1f,
        abigail_left_0_tex,
        abigail_left_1_tex,
        abigail_left_2_tex,
        abigail_left_3_tex
    );


    private final Animation<TextureRegion> pig_up_animation = new Animation<>(0.1f,
        pig_up_0_tex,
        pig_up_1_tex,
        pig_up_2_tex,
        pig_up_3_tex
    );

    private final Animation<TextureRegion> pig_right_animation = new Animation<>(0.1f,
        pig_right_0_tex,
        pig_right_1_tex,
        pig_right_2_tex,
        pig_right_3_tex
    );

    private final Animation<TextureRegion> pig_down_animation = new Animation<>(0.1f,
        pig_down_0_tex,
        pig_down_1_tex,
        pig_down_2_tex,
        pig_down_3_tex
    );

    private final Animation<TextureRegion> pig_left_animation = new Animation<>(0.1f,
        pig_left_0_tex,
        pig_left_1_tex,
        pig_left_2_tex,
        pig_left_3_tex
    );


    private final Animation<TextureRegion> hen_up_animation = new Animation<>(0.1f,
        hen_up_0_tex,
        hen_up_1_tex,
        hen_up_2_tex,
        hen_up_3_tex
    );

    private final Animation<TextureRegion> hen_right_animation = new Animation<>(0.1f,
        hen_right_0_tex,
        hen_right_1_tex,
        hen_right_2_tex,
        hen_right_3_tex
    );

    private final Animation<TextureRegion> hen_down_animation = new Animation<>(0.1f,
        hen_down_0_tex,
        hen_down_1_tex,
        hen_down_2_tex,
        hen_down_3_tex
    );

    private final Animation<TextureRegion> hen_left_animation = new Animation<>(0.1f,
        hen_left_0_tex,
        hen_left_1_tex,
        hen_left_2_tex,
        hen_left_3_tex
    );


    private final Animation<TextureRegion> duck_up_animation = new Animation<>(0.1f,
        duck_up_0_tex,
        duck_up_1_tex,
        duck_up_2_tex,
        duck_up_3_tex
    );

    private final Animation<TextureRegion> duck_right_animation = new Animation<>(0.1f,
        duck_right_0_tex,
        duck_right_1_tex,
        duck_right_2_tex,
        duck_right_3_tex
    );

    private final Animation<TextureRegion> duck_down_animation = new Animation<>(0.1f,
        duck_down_0_tex,
        duck_down_1_tex,
        duck_down_2_tex,
        duck_down_3_tex
    );

    private final Animation<TextureRegion> duck_left_animation = new Animation<>(0.1f,
        duck_left_0_tex,
        duck_left_1_tex,
        duck_left_2_tex,
        duck_left_3_tex
    );


    private final Animation<TextureRegion> goat_up_animation = new Animation<>(0.1f,
        goat_up_0_tex,
        goat_up_1_tex,
        goat_up_2_tex,
        goat_up_3_tex
    );

    private final Animation<TextureRegion> goat_right_animation = new Animation<>(0.1f,
        goat_right_0_tex,
        goat_right_1_tex,
        goat_right_2_tex,
        goat_right_3_tex
    );

    private final Animation<TextureRegion> goat_down_animation = new Animation<>(0.1f,
        goat_down_0_tex,
        goat_down_1_tex,
        goat_down_2_tex,
        goat_down_3_tex
    );

    private final Animation<TextureRegion> goat_left_animation = new Animation<>(0.1f,
        goat_left_0_tex,
        goat_left_1_tex,
        goat_left_2_tex,
        goat_left_3_tex
    );


    private final Animation<TextureRegion> dino_up_animation = new Animation<>(0.1f,
        dino_up_0_tex,
        dino_up_1_tex,
        dino_up_2_tex,
        dino_up_3_tex
    );

    private final Animation<TextureRegion> dino_right_animation = new Animation<>(0.1f,
        dino_right_0_tex,
        dino_right_1_tex,
        dino_right_2_tex,
        dino_right_3_tex
    );

    private final Animation<TextureRegion> dino_down_animation = new Animation<>(0.1f,
        dino_down_0_tex,
        dino_down_1_tex,
        dino_down_2_tex,
        dino_down_3_tex
    );

    private final Animation<TextureRegion> dino_left_animation = new Animation<>(0.1f,
        dino_left_0_tex,
        dino_left_1_tex,
        dino_left_2_tex,
        dino_left_3_tex
    );


    private final Animation<TextureRegion> cow_up_animation = new Animation<>(0.1f,
        cow_up_0_tex,
        cow_up_1_tex,
        cow_up_2_tex,
        cow_up_3_tex
    );

    private final Animation<TextureRegion> cow_right_animation = new Animation<>(0.1f,
        cow_right_0_tex,
        cow_right_1_tex,
        cow_right_2_tex,
        cow_right_3_tex
    );

    private final Animation<TextureRegion> cow_down_animation = new Animation<>(0.1f,
        cow_down_0_tex,
        cow_down_1_tex,
        cow_down_2_tex,
        cow_down_3_tex
    );

    private final Animation<TextureRegion> fainting_animation = new Animation<>(
        0.25f,
        fainting_tex1,
        fainting_tex2,
        fainting_tex3,
        fainting_tex4,
        fainting_tex5
    );


    private final Animation<TextureRegion> cow_left_animation = new Animation<>(0.1f,
        cow_left_0_tex,
        cow_left_1_tex,
        cow_left_2_tex,
        cow_left_3_tex
    );


    public TextureRegion getHouse() {
        return house_tex;
    }

    public TextureRegion getWoodFence() {
        return wood_fence_tex;
    }

    public Texture getBarnFence() {
        return barn_fence_tex;
    }

    public Texture getCageFence() {
        return cage_fence_tex;
    }

    public TextureRegion getMine() {
        return mine_tex;
    }

    public TextureRegion getLake() {
        return lake_tex;
    }

    public TextureRegion getGreenhouse() {
        return greenhouse_tex;
    }

    public TextureRegion getClock() {
        return clock_tex;
    }

    public TextureRegion getArrow() {
        return arrow_tex;
    }

    public TextureRegion getCircleSign() {
        return circle_tex;
    }

    public TextureRegion getLakeWater() {
        return lake_water_tex;
    }

    public TextureRegion getHouseTop() {
        return house_top_tex;
    }


    public TextureRegion getNpcHouse1Full() {
        return npc_house_1_full_tex;
    }

    public TextureRegion getNpcHouse1Top() {
        return npc_house_1_top_tex;
    }

    public TextureRegion getNpcHouse2Full() {
        return npc_house_2_full_tex;
    }

    public TextureRegion getNpcHouse2Top() {
        return npc_house_2_top_tex;
    }

    public TextureRegion getNpcHouse3Full() {
        return npc_house_3_full_tex;
    }

    public TextureRegion getNpcHouse3Top() {
        return npc_house_3_top_tex;
    }

    public TextureRegion getNpcHouse4Full() {
        return npc_house_4_full_tex;
    }

    public TextureRegion getNpcHouse4Top() {
        return npc_house_4_top_tex;
    }


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

    public Animation<TextureRegion> getSheepUpAnimation() {
        return sheep_up_animation;
    }

    public Animation<TextureRegion> getSheepRightAnimation() {
        return sheep_right_animation;
    }

    public Animation<TextureRegion> getSheepDownAnimation() {
        return sheep_down_animation;
    }

    public Animation<TextureRegion> getSheepLeftAnimation() {
        return sheep_left_animation;
    }


    public Animation<TextureRegion> getRabbitUpAnimation() {
        return rabbit_up_animation;
    }

    public Animation<TextureRegion> getRabbitRightAnimation() {
        return rabbit_right_animation;
    }

    public Animation<TextureRegion> getRabbitDownAnimation() {
        return rabbit_down_animation;
    }

    public Animation<TextureRegion> getRabbitLeftAnimation() {
        return rabbit_left_animation;
    }


    public TextureRegion getAbigailRight() {
        return abigail_right_0_tex;
    }

    public TextureRegion getLeahUp() {
        return leah_up_0_tex;
    }

    public TextureRegion getHarveyDown() {
        return harvey_down_0_tex;
    }

    public TextureRegion getSebastianLeft() {
        return sebastian_left_0_tex;
    }


    public Animation<TextureRegion> getAbigailUpAnimation() {
        return abigail_up_animation;
    }

    public Animation<TextureRegion> getAbigailRightAnimation() {
        return abigail_right_animation;
    }

    public Animation<TextureRegion> getAbigailDownAnimation() {
        return abigail_down_animation;
    }

    public Animation<TextureRegion> getAbigailLeftAnimation() {
        return abigail_left_animation;
    }


    public Animation<TextureRegion> getHarveyUpAnimation() {
        return harvey_up_animation;
    }

    public Animation<TextureRegion> getHarveyRightAnimation() {
        return harvey_right_animation;
    }

    public Animation<TextureRegion> getHarveyDownAnimation() {
        return harvey_down_animation;
    }

    public Animation<TextureRegion> getHarveyLeftAnimation() {
        return harvey_left_animation;
    }


    public Animation<TextureRegion> getSebastianUpAnimation() {
        return sebastian_up_animation;
    }

    public Animation<TextureRegion> getSebastianRightAnimation() {
        return sebastian_right_animation;
    }

    public Animation<TextureRegion> getSebastianDownAnimation() {
        return sebastian_down_animation;
    }

    public Animation<TextureRegion> getSebastianLeftAnimation() {
        return sebastian_left_animation;
    }


    public Animation<TextureRegion> getLeahUpAnimation() {
        return leah_up_animation;
    }

    public Animation<TextureRegion> getLeahRightAnimation() {
        return leah_right_animation;
    }

    public Animation<TextureRegion> getLeahDownAnimation() {
        return leah_down_animation;
    }

    public Animation<TextureRegion> getLeahLeftAnimation() {
        return leah_left_animation;
    }


    public Animation<TextureRegion> getPigUpAnimation() {
        return pig_up_animation;
    }

    public Animation<TextureRegion> getPigRightAnimation() {
        return pig_right_animation;
    }

    public Animation<TextureRegion> getPigDownAnimation() {
        return pig_down_animation;
    }

    public Animation<TextureRegion> getPigLeftAnimation() {
        return pig_left_animation;
    }


    public Animation<TextureRegion> getDuckUpAnimation() {
        return duck_up_animation;
    }

    public Animation<TextureRegion> getDuckRightAnimation() {
        return duck_right_animation;
    }

    public Animation<TextureRegion> getDuckDownAnimation() {
        return duck_down_animation;
    }

    public Animation<TextureRegion> getDuckLeftAnimation() {
        return duck_left_animation;
    }


    public Animation<TextureRegion> getHenUpAnimation() {
        return hen_up_animation;
    }

    public Animation<TextureRegion> getHenRightAnimation() {
        return hen_right_animation;
    }

    public Animation<TextureRegion> getHenDownAnimation() {
        return hen_down_animation;
    }

    public Animation<TextureRegion> getHenLeftAnimation() {
        return hen_left_animation;
    }


    public Animation<TextureRegion> getgoatUpAnimation() {
        return goat_up_animation;
    }

    public Animation<TextureRegion> getgoatRightAnimation() {
        return goat_right_animation;
    }

    public Animation<TextureRegion> getgoatDownAnimation() {
        return goat_down_animation;
    }

    public Animation<TextureRegion> getgoatLeftAnimation() {
        return goat_left_animation;
    }


    public Animation<TextureRegion> getDinoUpAnimation() {
        return dino_up_animation;
    }

    public Animation<TextureRegion> getDinoRightAnimation() {
        return dino_right_animation;
    }

    public Animation<TextureRegion> getDinoDownAnimation() {
        return dino_down_animation;
    }

    public Animation<TextureRegion> getDinoLeftAnimation() {
        return dino_left_animation;
    }


    public Animation<TextureRegion> getCowUpAnimation() {
        return cow_up_animation;
    }

    public Animation<TextureRegion> getCowRightAnimation() {
        return cow_right_animation;
    }

    public Animation<TextureRegion> getCowDownAnimation() {
        return cow_down_animation;
    }

    public Animation<TextureRegion> getCowLeftAnimation() {
        return cow_left_animation;
    }

    public Animation<TextureRegion> getFaintingAnimation() {
        return fainting_animation;
    }

    private final TextureRegion spring_background_tex = new TextureRegion(new Texture(spring_background));

    private final TextureRegion black_tex = new TextureRegion(new Texture(black));
    private final TextureRegion white_tex = new TextureRegion(new Texture(white));

    public TextureRegion getSpringBackground() {
        return spring_background_tex;
    }

    public TextureRegion getBlackTexture() {
        return black_tex;
    }

    public TextureRegion getWhiteTexture() {
        return white_tex;
    }

    // crops:
    private final Texture Blue_Jazz = new Texture("farming/crops/Blue_Jazz.png");
    private final Texture[] Blue_Jazz_Stages = new Texture[]{
        new Texture("farming/crops/Blue_Jazz_Stage_1.png"),
        new Texture("farming/crops/Blue_Jazz_Stage_2.png"),
        new Texture("farming/crops/Blue_Jazz_Stage_3.png"),
        new Texture("farming/crops/Blue_Jazz_Stage_4.png"),
        new Texture("farming/crops/Blue_Jazz_Stage_5.png"),
    };

    public Texture getBlueJazz() {
        return Blue_Jazz;
    }

    public Texture getBlueJazzStage1() {
        return Blue_Jazz_Stages[0];
    }

    public Texture getBlueJazzStage2() {
        return Blue_Jazz_Stages[1];
    }

    public Texture getBlueJazzStage3() {
        return Blue_Jazz_Stages[2];
    }

    public Texture getBlueJazzStage4() {
        return Blue_Jazz_Stages[3];
    }

    public Texture getBlueJazzStage5() {
        return Blue_Jazz_Stages[4];
    }

    private final Texture Carrot = new Texture("farming/crops/Carrot.png");
    private final Texture[] Carrot_Stages = new Texture[]{
        new Texture("farming/crops/Carrot_Stage_1.png"),
        new Texture("farming/crops/Carrot_Stage_2.png"),
        new Texture("farming/crops/Carrot_Stage_3.png"),
        new Texture("farming/crops/Carrot_Stage_4.png"),
    };

    public Texture getCarrot() {
        return Carrot;
    }

    public Texture getCarrotStage1() {
        return Carrot_Stages[0];
    }

    public Texture getCarrotStage2() {
        return Carrot_Stages[1];
    }

    public Texture getCarrotStage3() {
        return Carrot_Stages[2];
    }

    public Texture getCarrotStage4() {
        return Carrot_Stages[3];
    }

    private final Texture Cauliflower = new Texture("farming/crops/Cauliflower.png");
    private final Texture[] Cauliflower_Stages = new Texture[]{
        new Texture("farming/crops/Cauliflower_Stage_1.png"),
        new Texture("farming/crops/Cauliflower_Stage_2.png"),
        new Texture("farming/crops/Cauliflower_Stage_3.png"),
        new Texture("farming/crops/Cauliflower_Stage_4.png"),
        new Texture("farming/crops/Cauliflower_Stage_5.png"),
        new Texture("farming/crops/Cauliflower_Stage_6.png"),
    };

    public Texture getCauliflower() {
        return Cauliflower;
    }

    public Texture getCauliflowerStage1() {
        return Cauliflower_Stages[0];
    }

    public Texture getCauliflowerStage2() {
        return Cauliflower_Stages[1];
    }

    public Texture getCauliflowerStage3() {
        return Cauliflower_Stages[2];
    }

    public Texture getCauliflowerStage4() {
        return Cauliflower_Stages[3];
    }

    public Texture getCauliflowerStage5() {
        return Cauliflower_Stages[4];
    }

    public Texture getCauliflowerStage6() {
        return Cauliflower_Stages[5];
    }

    private final Texture Coffee_Bean = new Texture("farming/crops/Coffee_Bean.png");
    private final Texture[] Coffee_Bean_Stages = new Texture[]{
        new Texture("farming/crops/Coffee_Stage_1.png"),
        new Texture("farming/crops/Coffee_Stage_2.png"),
        new Texture("farming/crops/Coffee_Stage_3.png"),
        new Texture("farming/crops/Coffee_Stage_4.png"),
        new Texture("farming/crops/Coffee_Stage_5.png"),
        new Texture("farming/crops/Coffee_Stage_6.png"),
        new Texture("farming/crops/Coffee_Stage_7.png"),
    };

    public Texture getCoffeeBean() {
        return Coffee_Bean;
    }

    public Texture getCoffeeBeanStage1() {
        return Coffee_Bean_Stages[0];
    }

    public Texture getCoffeeBeanStage2() {
        return Coffee_Bean_Stages[1];
    }

    public Texture getCoffeeBeanStage3() {
        return Coffee_Bean_Stages[2];
    }

    public Texture getCoffeeBeanStage4() {
        return Coffee_Bean_Stages[3];
    }

    public Texture getCoffeeBeanStage5() {
        return Coffee_Bean_Stages[4];
    }

    public Texture getCoffeeBeanStage6() {
        return Coffee_Bean_Stages[5];
    }

    public Texture getCoffeeBeanStage7() {
        return Coffee_Bean_Stages[6];
    }

    private final Texture Garlic = new Texture("farming/crops/Garlic.png");
    private final Texture[] Garlic_Stages = new Texture[]{
        new Texture("farming/crops/Garlic_Stage_1.png"),
        new Texture("farming/crops/Garlic_Stage_2.png"),
        new Texture("farming/crops/Garlic_Stage_3.png"),
        new Texture("farming/crops/Garlic_Stage_4.png"),
        new Texture("farming/crops/Garlic_Stage_5.png"),
    };

    public Texture getGarlic() {
        return Garlic;
    }

    public Texture getGarlicStage1() {
        return Garlic_Stages[0];
    }

    public Texture getGarlicStage2() {
        return Garlic_Stages[1];
    }

    public Texture getGarlicStage3() {
        return Garlic_Stages[2];
    }

    public Texture getGarlicStage4() {
        return Garlic_Stages[3];
    }

    public Texture getGarlicStage5() {
        return Garlic_Stages[4];
    }

    private final Texture Green_Bean = new Texture("farming/crops/Green_Bean.png");
    private final Texture[] Green_Bean_Stages = new Texture[]{
//        new Texture("farming/crops/Green_Bean_Stage_1.png"),
        new Texture("farming/crops/Green_Bean_Stage_2.png"),
        new Texture("farming/crops/Green_Bean_Stage_3.png"),
        new Texture("farming/crops/Green_Bean_Stage_4.png"),
        new Texture("farming/crops/Green_Bean_Stage_5.png"),
        new Texture("farming/crops/Green_Bean_Stage_6.png"),
        new Texture("farming/crops/Green_Bean_Stage_7.png"),
        new Texture("farming/crops/Green_Bean_Stage_8.png"),
    };

    public Texture getGreenBean() {
        return Green_Bean;
    }

    public Texture getGreenBeanStage2() {
        return Green_Bean_Stages[0];
    }

    public Texture getGreenBeanStage3() {
        return Green_Bean_Stages[1];
    }

    public Texture getGreenBeanStage4() {
        return Green_Bean_Stages[2];
    }

    public Texture getGreenBeanStage5() {
        return Green_Bean_Stages[3];
    }

    public Texture getGreenBeanStage6() {
        return Green_Bean_Stages[4];
    }

    public Texture getGreenBeanStage7() {
        return Green_Bean_Stages[5];
    }

    public Texture getGreenBeanStage8() {
        return Green_Bean_Stages[6];
    }

    private final Texture Kale = new Texture("farming/crops/Kale.png");
    private final Texture[] Kale_Stages = new Texture[]{
        new Texture("farming/crops/Kale_Stage_1.png"),
        new Texture("farming/crops/Kale_Stage_2.png"),
        new Texture("farming/crops/Kale_Stage_3.png"),
        new Texture("farming/crops/Kale_Stage_4.png"),
        new Texture("farming/crops/Kale_Stage_5.png"),
    };

    public Texture getKale() {
        return Kale;
    }

    public Texture getKaleStage1() {
        return Kale_Stages[0];
    }

    public Texture getKaleStage2() {
        return Kale_Stages[1];
    }

    public Texture getKaleStage3() {
        return Kale_Stages[2];
    }

    public Texture getKaleStage4() {
        return Kale_Stages[3];
    }

    public Texture getKaleStage5() {
        return Kale_Stages[4];
    }

    private final Texture Parsnip = new Texture("farming/crops/Parsnip.png");
    private final Texture[] Parsnip_Stages = new Texture[]{
        new Texture("farming/crops/Parsnip_Stage_1.png"),
        new Texture("farming/crops/Parsnip_Stage_2.png"),
        new Texture("farming/crops/Parsnip_Stage_3.png"),
        new Texture("farming/crops/Parsnip_Stage_4.png"),
        new Texture("farming/crops/Parsnip_Stage_5.png"),
    };

    public Texture getParsnip() {
        return Parsnip;
    }

    public Texture getParsnipStage1() {
        return Parsnip_Stages[0];
    }

    public Texture getParsnipStage2() {
        return Parsnip_Stages[1];
    }

    public Texture getParsnipStage3() {
        return Parsnip_Stages[2];
    }

    public Texture getParsnipStage4() {
        return Parsnip_Stages[3];
    }

    public Texture getParsnipStage5() {
        return Parsnip_Stages[4];
    }

    private final Texture Potato = new Texture("farming/crops/Potato.png");
    private final Texture[] Potato_Stages = new Texture[]{
        new Texture("farming/crops/Potato_Stage_1.png"),
        new Texture("farming/crops/Potato_Stage_2.png"),
        new Texture("farming/crops/Potato_Stage_3.png"),
        new Texture("farming/crops/Potato_Stage_4.png"),
        new Texture("farming/crops/Potato_Stage_5.png"),
        new Texture("farming/crops/Potato_Stage_6.png"),
    };

    public Texture getPotato() {
        return Potato;
    }

    public Texture getPotatoStage1() {
        return Potato_Stages[0];
    }

    public Texture getPotatoStage2() {
        return Potato_Stages[1];
    }

    public Texture getPotatoStage3() {
        return Potato_Stages[2];
    }

    public Texture getPotatoStage4() {
        return Potato_Stages[3];
    }

    public Texture getPotatoStage5() {
        return Potato_Stages[4];
    }

    public Texture getPotatoStage6() {
        return Potato_Stages[5];
    }

    private final Texture Rhubarb = new Texture("farming/crops/Rhubarb.png");
    private final Texture[] Rhubarb_Stages = new Texture[]{
        new Texture("farming/crops/Rhubarb_Stage_1.png"),
        new Texture("farming/crops/Rhubarb_Stage_2.png"),
        new Texture("farming/crops/Rhubarb_Stage_3.png"),
        new Texture("farming/crops/Rhubarb_Stage_4.png"),
        new Texture("farming/crops/Rhubarb_Stage_5.png"),
        new Texture("farming/crops/Rhubarb_Stage_6.png"),
    };

    public Texture getRhubarb() {
        return Rhubarb;
    }

    public Texture getRhubarbStage1() {
        return Rhubarb_Stages[0];
    }

    public Texture getRhubarbStage2() {
        return Rhubarb_Stages[1];
    }

    public Texture getRhubarbStage3() {
        return Rhubarb_Stages[2];
    }

    public Texture getRhubarbStage4() {
        return Rhubarb_Stages[3];
    }

    public Texture getRhubarbStage5() {
        return Rhubarb_Stages[4];
    }

    public Texture getRhubarbStage6() {
        return Rhubarb_Stages[5];
    }

    private final Texture Strawberry = new Texture("farming/crops/Strawberry.png");
    private final Texture[] Strawberry_Stages = new Texture[]{
        new Texture("farming/crops/Strawberry_Stage_1.png"),
        new Texture("farming/crops/Strawberry_Stage_2.png"),
        new Texture("farming/crops/Strawberry_Stage_3.png"),
        new Texture("farming/crops/Strawberry_Stage_4.png"),
        new Texture("farming/crops/Strawberry_Stage_5.png"),
        new Texture("farming/crops/Strawberry_Stage_6.png"),
        new Texture("farming/crops/Strawberry_Stage_7.png")
    };

    public Texture getStrawberry() {
        return Strawberry;
    }

    public Texture getStrawberryStage1() {
        return Strawberry_Stages[0];
    }

    public Texture getStrawberryStage2() {
        return Strawberry_Stages[1];
    }

    public Texture getStrawberryStage3() {
        return Strawberry_Stages[2];
    }

    public Texture getStrawberryStage4() {
        return Strawberry_Stages[3];
    }

    public Texture getStrawberryStage5() {
        return Strawberry_Stages[4];
    }

    public Texture getStrawberryStage6() {
        return Strawberry_Stages[5];
    }

    public Texture getStrawberryStage7() {
        return Strawberry_Stages[6];
    }

    private final Texture Tulip = new Texture("farming/crops/Tulip.png");
    private final Texture[] Tulip_Stages = new Texture[]{
        new Texture("farming/crops/Tulip_Stage_1.png"),
        new Texture("farming/crops/Tulip_Stage_2.png"),
        new Texture("farming/crops/Tulip_Stage_3.png"),
        new Texture("farming/crops/Tulip_Stage_4.png"),
//        new Texture("farming/crops/Tulip_Stage_5.png"),
        new Texture("farming/crops/Tulip_Stage_6.png"),
    };

    public Texture getTulip() {
        return Tulip;
    }

    public Texture getTulipStage1() {
        return Tulip_Stages[0];
    }

    public Texture getTulipStage2() {
        return Tulip_Stages[1];
    }

    public Texture getTulipStage3() {
        return Tulip_Stages[2];
    }

    public Texture getTulipStage4() {
        return Tulip_Stages[3];
    }

    public Texture getTulipStage6() {
        return Tulip_Stages[4];
    }

    private final Texture Unmilled_Rice = new Texture("farming/crops/Unmilled_Rice.png");
    private final Texture[] Unmilled_Rice_Stages = new Texture[]{
        new Texture("farming/crops/Unmilled_Rice_Stage_1.png"),
        new Texture("farming/crops/Unmilled_Rice_Stage_2.png"),
        new Texture("farming/crops/Unmilled_Rice_Stage_3.png"),
        new Texture("farming/crops/Unmilled_Rice_Stage_4.png"),
        new Texture("farming/crops/Unmilled_Rice_Stage_5.png"),
    };

    public Texture getUnmilledRice() {
        return Unmilled_Rice;
    }

    public Texture getUnmilledRiceStage1() {
        return Unmilled_Rice_Stages[0];
    }

    public Texture getUnmilledRiceStage2() {
        return Unmilled_Rice_Stages[1];
    }

    public Texture getUnmilledRiceStage3() {
        return Unmilled_Rice_Stages[2];
    }

    public Texture getUnmilledRiceStage4() {
        return Unmilled_Rice_Stages[3];
    }

    public Texture getUnmilledRiceStage5() {
        return Unmilled_Rice_Stages[4];
    }

    private final Texture Blueberry = new Texture("farming/crops/Blueberry.png");
    private final Texture[] Blueberry_Stages = new Texture[]{
        new Texture("farming/crops/Blueberry_Stage_1.png"),
        new Texture("farming/crops/Blueberry_Stage_2.png"),
        new Texture("farming/crops/Blueberry_Stage_3.png"),
        new Texture("farming/crops/Blueberry_Stage_4.png"),
        new Texture("farming/crops/Blueberry_Stage_5.png"),
        new Texture("farming/crops/Blueberry_Stage_6.png"),
        new Texture("farming/crops/Blueberry_Stage_7.png"),
    };

    public Texture getBlueberry() {
        return Blueberry;
    }

    public Texture getBlueberryStage1() {
        return Blueberry_Stages[0];
    }

    public Texture getBlueberryStage2() {
        return Blueberry_Stages[1];
    }

    public Texture getBlueberryStage3() {
        return Blueberry_Stages[2];
    }

    public Texture getBlueberryStage4() {
        return Blueberry_Stages[3];
    }

    public Texture getBlueberryStage5() {
        return Blueberry_Stages[4];
    }

    public Texture getBlueberryStage6() {
        return Blueberry_Stages[5];
    }

    public Texture getBlueberryStage7() {
        return Blueberry_Stages[6];
    }

    private final Texture Corn = new Texture("farming/crops/Corn.png");
    private final Texture[] Corn_Stages = new Texture[]{
        new Texture("farming/crops/Corn_Stage_1.png"),
        new Texture("farming/crops/Corn_Stage_2.png"),
        new Texture("farming/crops/Corn_Stage_3.png"),
        new Texture("farming/crops/Corn_Stage_4.png"),
        new Texture("farming/crops/Corn_Stage_5.png"),
        new Texture("farming/crops/Corn_Stage_6.png"),
        new Texture("farming/crops/Corn_Stage_7.png"),
    };

    public Texture getCorn() {
        return Corn;
    }

    public Texture getCornStage1() {
        return Corn_Stages[0];
    }

    public Texture getCornStage2() {
        return Corn_Stages[1];
    }

    public Texture getCornStage3() {
        return Corn_Stages[2];
    }

    public Texture getCornStage4() {
        return Corn_Stages[3];
    }

    public Texture getCornStage5() {
        return Corn_Stages[4];
    }

    public Texture getCornStage6() {
        return Corn_Stages[5];
    }

    public Texture getCornStage7() {
        return Corn_Stages[6];
    }

    private final Texture Hops = new Texture("farming/crops/Hops.png");
    private final Texture[] Hops_Stages = new Texture[]{
        new Texture("farming/crops/Hops_Stage_1.png"),
//        new Texture("farming/crops/Hops_Stage_2.png"),
        new Texture("farming/crops/Hops_Stage_3.png"),
        new Texture("farming/crops/Hops_Stage_4.png"),
        new Texture("farming/crops/Hops_Stage_5.png"),
        new Texture("farming/crops/Hops_Stage_6.png"),
        new Texture("farming/crops/Hops_Stage_7.png"),
        new Texture("farming/crops/Hops_Stage_8.png"),
    };

    public Texture getHops() {
        return Hops;
    }

    public Texture getHopsStage1() {
        return Hops_Stages[0];
    }

    public Texture getHopsStage3() {
        return Hops_Stages[1];
    }

    public Texture getHopsStage4() {
        return Hops_Stages[2];
    }

    public Texture getHopsStage5() {
        return Hops_Stages[3];
    }

    public Texture getHopsStage6() {
        return Hops_Stages[4];
    }

    public Texture getHopsStage7() {
        return Hops_Stages[5];
    }

    public Texture getHopsStage8() {
        return Hops_Stages[6];
    }

    private final Texture Hot_Pepper = new Texture("farming/crops/Hot_Pepper.png");
    private final Texture[] Hot_Pepper_Stages = new Texture[]{
        new Texture("farming/crops/Hot_Pepper_Stage_1.png"),
        new Texture("farming/crops/Hot_Pepper_Stage_2.png"),
        new Texture("farming/crops/Hot_Pepper_Stage_3.png"),
        new Texture("farming/crops/Hot_Pepper_Stage_4.png"),
        new Texture("farming/crops/Hot_Pepper_Stage_4b.png"),
        new Texture("farming/crops/Hot_Pepper_Stage_5.png"),
        new Texture("farming/crops/Hot_Pepper_Stage_6.png"),
    };

    public Texture getHotPepper() {
        return Hot_Pepper;
    }

    public Texture getHotPepperStage1() {
        return Hot_Pepper_Stages[0];
    }

    public Texture getHotPepperStage2() {
        return Hot_Pepper_Stages[1];
    }

    public Texture getHotPepperStage3() {
        return Hot_Pepper_Stages[2];
    }

    public Texture getHotPepperStage4() {
        return Hot_Pepper_Stages[3];
    }

    public Texture getHotPepperStage5() {
        return Hot_Pepper_Stages[4];
    }

    public Texture getHotPepperStage6() {
        return Hot_Pepper_Stages[5];
    }

    public Texture getHotPepperStage7() {
        return Hot_Pepper_Stages[6];
    }

    private final Texture Melon = new Texture("farming/crops/Melon.png");
    private final Texture[] Melon_Stages = new Texture[]{
        new Texture("farming/crops/Melon_Stage_1.png"),
        new Texture("farming/crops/Melon_Stage_2.png"),
        new Texture("farming/crops/Melon_Stage_3.png"),
        new Texture("farming/crops/Melon_Stage_4.png"),
        new Texture("farming/crops/Melon_Stage_5.png"),
        new Texture("farming/crops/Melon_Stage_6.png"),
    };

    public Texture getMelon() {
        return Melon;
    }

    public Texture getMelonStage1() {
        return Melon_Stages[0];
    }

    public Texture getMelonStage2() {
        return Melon_Stages[1];
    }

    public Texture getMelonStage3() {
        return Melon_Stages[2];
    }

    public Texture getMelonStage4() {
        return Melon_Stages[3];
    }

    public Texture getMelonStage5() {
        return Melon_Stages[4];
    }

    public Texture getMelonStage6() {
        return Melon_Stages[5];
    }

    private final Texture Poppy = new Texture("farming/crops/Poppy.png");
    private final Texture[] Poppy_Stages = new Texture[]{
        new Texture("farming/crops/Poppy_Stage_1.png"),
        new Texture("farming/crops/Poppy_Stage_2.png"),
        new Texture("farming/crops/Poppy_Stage_3.png"),
        new Texture("farming/crops/Poppy_Stage_4.png"),
//        new Texture("farming/crops/Poppy_Stage_5.png"),
        new Texture("farming/crops/Poppy_Stage_6.png"),
    };

    public Texture getPoppy() {
        return Poppy;
    }

    public Texture getPoppyStage1() {
        return Poppy_Stages[0];
    }

    public Texture getPoppyStage2() {
        return Poppy_Stages[1];
    }

    public Texture getPoppyStage3() {
        return Poppy_Stages[2];
    }

    public Texture getPoppyStage4() {
        return Poppy_Stages[3];
    }

    public Texture getPoppyStage6() {
        return Poppy_Stages[4];
    }

    private final Texture Radish = new Texture("farming/crops/Radish.png");
    private final Texture[] Radish_Stages = new Texture[]{
        new Texture("farming/crops/Radish_Stage_1.png"),
        new Texture("farming/crops/Radish_Stage_2.png"),
        new Texture("farming/crops/Radish_Stage_3.png"),
        new Texture("farming/crops/Radish_Stage_4.png"),
        new Texture("farming/crops/Radish_Stage_5.png"),
    };

    public Texture getRadish() {
        return Radish;
    }

    public Texture getRadishStage1() {
        return Radish_Stages[0];
    }

    public Texture getRadishStage2() {
        return Radish_Stages[1];
    }

    public Texture getRadishStage3() {
        return Radish_Stages[2];
    }

    public Texture getRadishStage4() {
        return Radish_Stages[3];
    }

    public Texture getRadishStage5() {
        return Radish_Stages[4];
    }

    private final Texture Red_Cabbage = new Texture("farming/crops/Red_Cabbage.png");
    private final Texture[] Red_Cabbage_Stages = new Texture[]{
        new Texture("farming/crops/Red_Cabbage_Stage_1.png"),
        new Texture("farming/crops/Red_Cabbage_Stage_2.png"),
        new Texture("farming/crops/Red_Cabbage_Stage_3.png"),
        new Texture("farming/crops/Red_Cabbage_Stage_4.png"),
        new Texture("farming/crops/Red_Cabbage_Stage_5.png"),
        new Texture("farming/crops/Red_Cabbage_Stage_6.png"),
    };

    public Texture getRedCabbage() {
        return Red_Cabbage;
    }

    public Texture getRedCabbageStage1() {
        return Red_Cabbage_Stages[0];
    }

    public Texture getRedCabbageStage2() {
        return Red_Cabbage_Stages[1];
    }

    public Texture getRedCabbageStage3() {
        return Red_Cabbage_Stages[2];
    }

    public Texture getRedCabbageStage4() {
        return Red_Cabbage_Stages[3];
    }

    public Texture getRedCabbageStage5() {
        return Red_Cabbage_Stages[4];
    }

    public Texture getRedCabbageStage6() {
        return Red_Cabbage_Stages[5];
    }

    private final Texture Starfruit = new Texture("farming/crops/Starfruit.png");
    private final Texture[] Starfruit_Stages = new Texture[]{
        new Texture("farming/crops/Starfruit_Stage_1.png"),
        new Texture("farming/crops/Starfruit_Stage_2.png"),
        new Texture("farming/crops/Starfruit_Stage_3.png"),
        new Texture("farming/crops/Starfruit_Stage_4.png"),
        new Texture("farming/crops/Starfruit_Stage_5.png"),
        new Texture("farming/crops/Starfruit_Stage_6.png"),
    };

    public Texture getStarfruit() {
        return Starfruit;
    }

    public Texture getStarfruitStage1() {
        return Starfruit_Stages[0];
    }

    public Texture getStarfruitStage2() {
        return Starfruit_Stages[1];
    }

    public Texture getStarfruitStage3() {
        return Starfruit_Stages[2];
    }

    public Texture getStarfruitStage4() {
        return Starfruit_Stages[3];
    }

    public Texture getStarfruitStage5() {
        return Starfruit_Stages[4];
    }

    public Texture getStarfruitStage6() {
        return Starfruit_Stages[5];
    }

    private final Texture Summer_Spangle = new Texture("farming/crops/Summer_Spangle.png");
    private final Texture[] Summer_Spangle_Stages = new Texture[]{
        new Texture("farming/crops/Summer_Spangle_Stage_1.png"),
        new Texture("farming/crops/Summer_Spangle_Stage_2.png"),
        new Texture("farming/crops/Summer_Spangle_Stage_3.png"),
        new Texture("farming/crops/Summer_Spangle_Stage_4.png"),
        new Texture("farming/crops/Summer_Spangle_Stage_5.png"),
    };

    public Texture getSummerSpangle() {
        return Summer_Spangle;
    }

    public Texture getSummerSpangleStage1() {
        return Summer_Spangle_Stages[0];
    }

    public Texture getSummerSpangleStage2() {
        return Summer_Spangle_Stages[1];
    }

    public Texture getSummerSpangleStage3() {
        return Summer_Spangle_Stages[2];
    }

    public Texture getSummerSpangleStage4() {
        return Summer_Spangle_Stages[3];
    }

    public Texture getSummerSpangleStage5() {
        return Summer_Spangle_Stages[4];
    }

    private final Texture Summer_Squash = new Texture("farming/crops/Summer_Squash.png");
    private final Texture[] Summer_Squash_Stages = new Texture[]{
        new Texture("farming/crops/Summer_Squash_Stage_1.png"),
        new Texture("farming/crops/Summer_Squash_Stage_2.png"),
        new Texture("farming/crops/Summer_Squash_Stage_3.png"),
        new Texture("farming/crops/Summer_Squash_Stage_4.png"),
        new Texture("farming/crops/Summer_Squash_Stage_5.png"),
        new Texture("farming/crops/Summer_Squash_Stage_6.png"),
        new Texture("farming/crops/Summer_Squash_Stage_7.png"),
    };

    public Texture getSummerSquash() {
        return Summer_Squash;
    }

    public Texture getSummerSquashStage1() {
        return Summer_Squash_Stages[0];
    }

    public Texture getSummerSquashStage2() {
        return Summer_Squash_Stages[1];
    }

    public Texture getSummerSquashStage3() {
        return Summer_Squash_Stages[2];
    }

    public Texture getSummerSquashStage4() {
        return Summer_Squash_Stages[3];
    }

    public Texture getSummerSquashStage5() {
        return Summer_Squash_Stages[4];
    }

    public Texture getSummerSquashStage6() {
        return Summer_Squash_Stages[5];
    }

    public Texture getSummerSquashStage7() {
        return Summer_Squash_Stages[6];
    }

    private final Texture Sunflower = new Texture("farming/crops/Sunflower.png");
    private final Texture[] Sunflower_Stages = new Texture[]{
        new Texture("farming/crops/Sunflower_Stage_1.png"),
        new Texture("farming/crops/Sunflower_Stage_2.png"),
        new Texture("farming/crops/Sunflower_Stage_3.png"),
        new Texture("farming/crops/Sunflower_Stage_4.png"),
        new Texture("farming/crops/Sunflower_Stage_5.png"),
    };

    public Texture getSunflower() {
        return Sunflower;
    }

    public Texture getSunflowerStage1() {
        return Sunflower_Stages[0];
    }

    public Texture getSunflowerStage2() {
        return Sunflower_Stages[1];
    }

    public Texture getSunflowerStage3() {
        return Sunflower_Stages[2];
    }

    public Texture getSunflowerStage4() {
        return Sunflower_Stages[3];
    }

    public Texture getSunflowerStage5() {
        return Sunflower_Stages[4];
    }

    private final Texture Tomato = new Texture("farming/crops/Tomato.png");
    private final Texture[] Tomato_Stages = new Texture[]{
        new Texture("farming/crops/Tomato_Stage_1.png"),
        new Texture("farming/crops/Tomato_Stage_2.png"),
        new Texture("farming/crops/Tomato_Stage_3.png"),
        new Texture("farming/crops/Tomato_Stage_4.png"),
        new Texture("farming/crops/Tomato_Stage_5.png"),
        new Texture("farming/crops/Tomato_Stage_6.png"),
        new Texture("farming/crops/Tomato_Stage_7.png"),
    };

    public Texture getTomato() {
        return Tomato;
    }

    public Texture getTomatoStage1() {
        return Tomato_Stages[0];
    }

    public Texture getTomatoStage2() {
        return Tomato_Stages[1];
    }

    public Texture getTomatoStage3() {
        return Tomato_Stages[2];
    }

    public Texture getTomatoStage4() {
        return Tomato_Stages[3];
    }

    public Texture getTomatoStage5() {
        return Tomato_Stages[4];
    }

    public Texture getTomatoStage6() {
        return Tomato_Stages[5];
    }

    public Texture getTomatoStage7() {
        return Tomato_Stages[6];
    }

    private final Texture Wheat = new Texture("farming/crops/Wheat.png");
    private final Texture[] Wheat_Stages = new Texture[]{
        new Texture("farming/crops/Wheat_Stage_1.png"),
        new Texture("farming/crops/Wheat_Stage_2.png"),
        new Texture("farming/crops/Wheat_Stage_3.png"),
        new Texture("farming/crops/Wheat_Stage_4.png"),
        new Texture("farming/crops/Wheat_Stage_5.png"),
    };

    public Texture getWheat() {
        return Wheat;
    }

    public Texture getWheatStage1() {
        return Wheat_Stages[0];
    }

    public Texture getWheatStage2() {
        return Wheat_Stages[1];
    }

    public Texture getWheatStage3() {
        return Wheat_Stages[2];
    }

    public Texture getWheatStage4() {
        return Wheat_Stages[3];
    }

    public Texture getWheatStage5() {
        return Wheat_Stages[4];
    }

    private final Texture Amaranth = new Texture("farming/crops/Amaranth.png");
    private final Texture[] Amaranth_Stages = new Texture[]{
        new Texture("farming/crops/Amaranth_Stage_1.png"),
        new Texture("farming/crops/Amaranth_Stage_2.png"),
        new Texture("farming/crops/Amaranth_Stage_3.png"),
        new Texture("farming/crops/Amaranth_Stage_4.png"),
        new Texture("farming/crops/Amaranth_Stage_5.png"),
    };

    public Texture getAmaranth() {
        return Amaranth;
    }

    public Texture getAmaranthStage1() {
        return Amaranth_Stages[0];
    }

    public Texture getAmaranthStage2() {
        return Amaranth_Stages[1];
    }

    public Texture getAmaranthStage3() {
        return Amaranth_Stages[2];
    }

    public Texture getAmaranthStage4() {
        return Amaranth_Stages[3];
    }

    public Texture getAmaranthStage5() {
        return Amaranth_Stages[4];
    }

    private final Texture Artichoke = new Texture("farming/crops/Artichoke.png");
    private final Texture[] Artichoke_Stages = new Texture[]{
        new Texture("farming/crops/Artichoke_Stage_1.png"),
        new Texture("farming/crops/Artichoke_Stage_2.png"),
        new Texture("farming/crops/Artichoke_Stage_3.png"),
        new Texture("farming/crops/Artichoke_Stage_4.png"),
        new Texture("farming/crops/Artichoke_Stage_5.png"),
        new Texture("farming/crops/Artichoke_Stage_6.png"),
    };

    public Texture getArtichoke() {
        return Artichoke;
    }

    public Texture getArtichokeStage1() {
        return Artichoke_Stages[0];
    }

    public Texture getArtichokeStage2() {
        return Artichoke_Stages[1];
    }

    public Texture getArtichokeStage3() {
        return Artichoke_Stages[2];
    }

    public Texture getArtichokeStage4() {
        return Artichoke_Stages[3];
    }

    public Texture getArtichokeStage5() {
        return Artichoke_Stages[4];
    }

    public Texture getArtichokeStage6() {
        return Artichoke_Stages[5];
    }

    private final Texture Beet = new Texture("farming/crops/Beet.png");
    private final Texture[] Beet_Stages = new Texture[]{
        new Texture("farming/crops/Beet_Stage_1.png"),
        new Texture("farming/crops/Beet_Stage_2.png"),
        new Texture("farming/crops/Beet_Stage_3.png"),
        new Texture("farming/crops/Beet_Stage_4.png"),
        new Texture("farming/crops/Beet_Stage_5.png"),
    };

    public Texture getBeet() {
        return Beet;
    }

    public Texture getBeetStage1() {
        return Beet_Stages[0];
    }

    public Texture getBeetStage2() {
        return Beet_Stages[1];
    }

    public Texture getBeetStage3() {
        return Beet_Stages[2];
    }

    public Texture getBeetStage4() {
        return Beet_Stages[3];
    }

    public Texture getBeetStage5() {
        return Beet_Stages[4];
    }

    private final Texture Bok_Choy = new Texture("farming/crops/Bok_Choy.png");
    private final Texture[] Bok_Choy_Stages = new Texture[]{
        new Texture("farming/crops/Bok_Choy_Stage_1.png"),
        new Texture("farming/crops/Bok_Choy_Stage_2.png"),
        new Texture("farming/crops/Bok_Choy_Stage_3.png"),
        new Texture("farming/crops/Bok_Choy_Stage_4.png"),
        new Texture("farming/crops/Bok_Choy_Stage_5.png"),
    };

    public Texture getBokChoy() {
        return Bok_Choy;
    }

    public Texture getBokChoyStage1() {
        return Bok_Choy_Stages[0];
    }

    public Texture getBokChoyStage2() {
        return Bok_Choy_Stages[1];
    }

    public Texture getBokChoyStage3() {
        return Bok_Choy_Stages[2];
    }

    public Texture getBokChoyStage4() {
        return Bok_Choy_Stages[3];
    }

    public Texture getBokChoyStage5() {
        return Bok_Choy_Stages[4];
    }

    private final Texture Broccoli = new Texture("farming/crops/Broccoli.png");
    private final Texture[] Broccoli_Stages = new Texture[]{
        new Texture("farming/crops/Broccoli_Stage_1.png"),
        new Texture("farming/crops/Broccoli_Stage_2.png"),
        new Texture("farming/crops/Broccoli_Stage_3.png"),
        new Texture("farming/crops/Broccoli_Stage_4.png"),
        new Texture("farming/crops/Broccoli_Stage_5.png"),
    };

    public Texture getBroccoli() {
        return Broccoli;
    }

    public Texture getBroccoliStage1() {
        return Broccoli_Stages[0];
    }

    public Texture getBroccoliStage2() {
        return Broccoli_Stages[1];
    }

    public Texture getBroccoliStage3() {
        return Broccoli_Stages[2];
    }

    public Texture getBroccoliStage4() {
        return Broccoli_Stages[3];
    }

    public Texture getBroccoliStage5() {
        return Broccoli_Stages[4];
    }

    private final Texture Cranberries = new Texture("farming/crops/Cranberries.png");
    private final Texture[] Cranberries_Stages = new Texture[]{
        new Texture("farming/crops/Cranberry_Stage_1.png"),
        new Texture("farming/crops/Cranberry_Stage_2.png"),
        new Texture("farming/crops/Cranberry_Stage_3.png"),
        new Texture("farming/crops/Cranberry_Stage_4.png"),
        new Texture("farming/crops/Cranberry_Stage_5.png"),
        new Texture("farming/crops/Cranberry_Stage_6.png"),
        new Texture("farming/crops/Cranberry_Stage_7.png"),
    };

    public Texture getCranberries() {
        return Cranberries;
    }

    public Texture getCranberryStage1() {
        return Cranberries_Stages[0];
    }

    public Texture getCranberryStage2() {
        return Cranberries_Stages[1];
    }

    public Texture getCranberryStage3() {
        return Cranberries_Stages[2];
    }

    public Texture getCranberryStage4() {
        return Cranberries_Stages[3];
    }

    public Texture getCranberryStage5() {
        return Cranberries_Stages[4];
    }

    public Texture getCranberryStage6() {
        return Cranberries_Stages[5];
    }

    public Texture getCranberryStage7() {
        return Cranberries_Stages[6];
    }

    private final Texture Eggplant = new Texture("farming/crops/Eggplant.png");
    private final Texture[] Eggplant_Stages = new Texture[]{
        new Texture("farming/crops/Eggplant_Stage_1.png"),
        new Texture("farming/crops/Eggplant_Stage_2.png"),
        new Texture("farming/crops/Eggplant_Stage_3.png"),
        new Texture("farming/crops/Eggplant_Stage_4.png"),
        new Texture("farming/crops/Eggplant_Stage_5.png"),
        new Texture("farming/crops/Eggplant_Stage_6.png"),
        new Texture("farming/crops/Eggplant_Stage_7.png")
    };

    public Texture getEggplant() {
        return Eggplant;
    }

    public Texture getEggplantStage1() {
        return Eggplant_Stages[0];
    }

    public Texture getEggplantStage2() {
        return Eggplant_Stages[1];
    }

    public Texture getEggplantStage3() {
        return Eggplant_Stages[2];
    }

    public Texture getEggplantStage4() {
        return Eggplant_Stages[3];
    }

    public Texture getEggplantStage5() {
        return Eggplant_Stages[4];
    }

    public Texture getEggplantStage6() {
        return Eggplant_Stages[5];
    }

    public Texture getEggplantStage7() {
        return Eggplant_Stages[6];
    }

    private final Texture Fairy_Rose = new Texture("farming/crops/Fairy_Rose.png");
    private final Texture[] Fairy_Rose_Stages = new Texture[]{
        new Texture("farming/crops/Fairy_Rose_Stage_1.png"),
        new Texture("farming/crops/Fairy_Rose_Stage_2.png"),
        new Texture("farming/crops/Fairy_Rose_Stage_3.png"),
        new Texture("farming/crops/Fairy_Rose_Stage_4.png"),
        new Texture("farming/crops/Fairy_Rose_Stage_5.png"),
    };

    public Texture getFairyRose() {
        return Fairy_Rose;
    }

    public Texture getFairyRoseStage1() {
        return Fairy_Rose_Stages[0];
    }

    public Texture getFairyRoseStage2() {
        return Fairy_Rose_Stages[1];
    }

    public Texture getFairyRoseStage3() {
        return Fairy_Rose_Stages[2];
    }

    public Texture getFairyRoseStage4() {
        return Fairy_Rose_Stages[3];
    }

    public Texture getFairyRoseStage5() {
        return Fairy_Rose_Stages[4];
    }

    private final Texture Grape = new Texture("farming/crops/Grape.png");
    private final Texture[] Grape_Stages = new Texture[]{
        new Texture("farming/crops/Grape_Stage_1.png"),
        new Texture("farming/crops/Grape_Stage_2.png"),
        new Texture("farming/crops/Grape_Stage_3.png"),
        new Texture("farming/crops/Grape_Stage_4.png"),
        new Texture("farming/crops/Grape_Stage_5.png"),
        new Texture("farming/crops/Grape_Stage_6.png"),
        new Texture("farming/crops/Grape_Stage_7.png"),
    };

    public Texture getGrape() {
        return Grape;
    }

    public Texture getGrapeStage1() {
        return Grape_Stages[0];
    }

    public Texture getGrapeStage2() {
        return Grape_Stages[1];
    }

    public Texture getGrapeStage3() {
        return Grape_Stages[2];
    }

    public Texture getGrapeStage4() {
        return Grape_Stages[3];
    }

    public Texture getGrapeStage5() {
        return Grape_Stages[4];
    }

    public Texture getGrapeStage6() {
        return Grape_Stages[5];
    }

    public Texture getGrapeStage7() {
        return Grape_Stages[6];
    }

    private final Texture Pumpkin = new Texture("farming/crops/Pumpkin.png");
    private final Texture[] Pumpkin_Stages = new Texture[]{
        new Texture("farming/crops/Pumpkin_Stage_1.png"),
        new Texture("farming/crops/Pumpkin_Stage_2.png"),
        new Texture("farming/crops/Pumpkin_Stage_3.png"),
        new Texture("farming/crops/Pumpkin_Stage_4.png"),
        new Texture("farming/crops/Pumpkin_Stage_5.png"),
        new Texture("farming/crops/Pumpkin_Stage_6.png"),
    };

    public Texture getPumpkin() {
        return Pumpkin;
    }

    public Texture getPumpkinStage1() {
        return Pumpkin_Stages[0];
    }

    public Texture getPumpkinStage2() {
        return Pumpkin_Stages[1];
    }

    public Texture getPumpkinStage3() {
        return Pumpkin_Stages[2];
    }

    public Texture getPumpkinStage4() {
        return Pumpkin_Stages[3];
    }

    public Texture getPumpkinStage5() {
        return Pumpkin_Stages[4];
    }

    public Texture getPumpkinStage6() {
        return Pumpkin_Stages[5];
    }

    private final Texture Yam = new Texture("farming/crops/Yam.png");
    private final Texture[] Yam_Stages = new Texture[]{
        new Texture("farming/crops/Yam_Stage_1.png"),
        new Texture("farming/crops/Yam_Stage_2.png"),
        new Texture("farming/crops/Yam_Stage_3.png"),
        new Texture("farming/crops/Yam_Stage_4.png"),
        new Texture("farming/crops/Yam_Stage_5.png"),
    };

    public Texture getYam() {
        return Yam;
    }

    public Texture getYamStage1() {
        return Yam_Stages[0];
    }

    public Texture getYamStage2() {
        return Yam_Stages[1];
    }

    public Texture getYamStage3() {
        return Yam_Stages[2];
    }

    public Texture getYamStage4() {
        return Yam_Stages[3];
    }

    public Texture getYamStage5() {
        return Yam_Stages[4];
    }

    private final Texture Sweet_Gem_Berry = new Texture("farming/crops/Sweet_Gem_Berry.png");
    private final Texture[] Sweet_Gem_Berry_Stages = new Texture[]{
        new Texture("farming/crops/Sweet_Gem_Berry_Stage_1.png"),
        new Texture("farming/crops/Sweet_Gem_Berry_Stage_2.png"),
        new Texture("farming/crops/Sweet_Gem_Berry_Stage_3.png"),
        new Texture("farming/crops/Sweet_Gem_Berry_Stage_4.png"),
        new Texture("farming/crops/Sweet_Gem_Berry_Stage_5.png"),
        new Texture("farming/crops/Sweet_Gem_Berry_Stage_6.png"),
    };

    public Texture getSweetGemBerry() {
        return Sweet_Gem_Berry;
    }

    public Texture getSweetGemBerryStage1() {
        return Sweet_Gem_Berry_Stages[0];
    }

    public Texture getSweetGemBerryStage2() {
        return Sweet_Gem_Berry_Stages[1];
    }

    public Texture getSweetGemBerryStage3() {
        return Sweet_Gem_Berry_Stages[2];
    }

    public Texture getSweetGemBerryStage4() {
        return Sweet_Gem_Berry_Stages[3];
    }

    public Texture getSweetGemBerryStage5() {
        return Sweet_Gem_Berry_Stages[4];
    }

    public Texture getSweetGemBerryStage6() {
        return Sweet_Gem_Berry_Stages[5];
    }

    private final Texture Powdermelon = new Texture("farming/crops/Powdermelon.png");
    private final Texture[] Powdermelon_Stages = new Texture[]{
        new Texture("farming/crops/Powdermelon_Stage_1.png"),
        new Texture("farming/crops/Powdermelon_Stage_2.png"),
        new Texture("farming/crops/Powdermelon_Stage_3.png"),
        new Texture("farming/crops/Powdermelon_Stage_4.png"),
        new Texture("farming/crops/Powdermelon_Stage_5.png"),
        new Texture("farming/crops/Powdermelon_Stage_6.png"),
    };

    public Texture getPowdermelon() {
        return Powdermelon;
    }

    public Texture getPowdermelonStage1() {
        return Powdermelon_Stages[0];
    }

    public Texture getPowdermelonStage2() {
        return Powdermelon_Stages[1];
    }

    public Texture getPowdermelonStage3() {
        return Powdermelon_Stages[2];
    }

    public Texture getPowdermelonStage4() {
        return Powdermelon_Stages[3];
    }

    public Texture getPowdermelonStage5() {
        return Powdermelon_Stages[4];
    }

    public Texture getPowdermelonStage6() {
        return Powdermelon_Stages[5];
    }

    private final Texture Ancient_Fruit = new Texture("farming/crops/Ancient_Fruit.png");
    private final Texture[] Ancient_Fruit_Stages = new Texture[]{
        new Texture("farming/crops/Ancient_Fruit_Stage_1.png"),
        new Texture("farming/crops/Ancient_Fruit_Stage_2.png"),
        new Texture("farming/crops/Ancient_Fruit_Stage_3.png"),
        new Texture("farming/crops/Ancient_Fruit_Stage_4.png"),
        new Texture("farming/crops/Ancient_Fruit_Stage_5.png"),
        new Texture("farming/crops/Ancient_Fruit_Stage_6.png"),
        new Texture("farming/crops/Ancient_Fruit_Stage_7.png"),
    };

    public Texture getAncientFruit() {
        return Ancient_Fruit;
    }

    public Texture getAncientFruitStage1() {
        return Ancient_Fruit_Stages[0];
    }

    public Texture getAncientFruitStage2() {
        return Ancient_Fruit_Stages[1];
    }

    public Texture getAncientFruitStage3() {
        return Ancient_Fruit_Stages[2];
    }

    public Texture getAncientFruitStage4() {
        return Ancient_Fruit_Stages[3];
    }

    public Texture getAncientFruitStage5() {
        return Ancient_Fruit_Stages[4];
    }

    public Texture getAncientFruitStage6() {
        return Ancient_Fruit_Stages[5];
    }

    public Texture getAncientFruitStage7() {
        return Ancient_Fruit_Stages[6];
    }

    // trees:
    private final Texture Apricot = new Texture("farming/trees/Apricot.png");
    private final Texture ApricotSapling = new Texture("farming/trees/Apricot_Sapling.png");
    private final Texture ApricotStage1 = new Texture("farming/trees/Apricot_Stage_1.png");
    private final Texture ApricotStage2 = new Texture("farming/trees/Apricot_Stage_2.png");
    private final Texture ApricotStage3 = new Texture("farming/trees/Apricot_Stage_3.png");
    private final Texture ApricotStage4 = new Texture("farming/trees/Apricot_Stage_4.png");
    private final Texture ApricotStage5Spring = new Texture("farming/trees/Apricot_Stage_5_Spring.png");
    private final Texture ApricotStage5Summer = new Texture("farming/trees/Apricot_Stage_5_Summer.png");
    private final Texture ApricotStage5Fall = new Texture("farming/trees/Apricot_Stage_5_Fall.png");
    private final Texture ApricotStage5Winter = new Texture("farming/trees/Apricot_Stage_5_Winter.png");
    private final Texture ApricotStage5Fruit = new Texture("farming/trees/Apricot_Stage_5_Fruit.png");
    private final Texture ApricotTreeLightning = new Texture("farming/trees/ApricotTreeLightning.png");

    public Texture getApricot() {
        return Apricot;
    }

    public Texture getApricotSapling() {
        return ApricotSapling;
    }

    public Texture getApricotStage1() {
        return ApricotStage1;
    }

    public Texture getApricotStage2() {
        return ApricotStage2;
    }

    public Texture getApricotStage3() {
        return ApricotStage3;
    }

    public Texture getApricotStage4() {
        return ApricotStage4;
    }

    public Texture getApricotStage5Spring() {
        return ApricotStage5Spring;
    }

    public Texture getApricotStage5Summer() {
        return ApricotStage5Summer;
    }

    public Texture getApricotStage5Fall() {
        return ApricotStage5Fall;
    }

    public Texture getApricotStage5Winter() {
        return ApricotStage5Winter;
    }

    public Texture getApricotStage5Fruit() {
        return ApricotStage5Fruit;
    }

    public Texture getApricotTreeLightning() {
        return ApricotTreeLightning;
    }

    private final Texture Banana = new Texture("farming/trees/Banana.png");
    private final Texture BananaSapling = new Texture("farming/trees/Banana_Sapling.png");
    private final Texture BananaPudding = new Texture("farming/trees/Banana_Pudding.png");
    private final Texture BananaStage1 = new Texture("farming/trees/Banana_Stage_1.png");
    private final Texture BananaStage2 = new Texture("farming/trees/Banana_Stage_2.png");
    private final Texture BananaStage3 = new Texture("farming/trees/Banana_Stage_3.png");
    private final Texture BananaStage4 = new Texture("farming/trees/Banana_Stage_4.png");
    private final Texture BananaStage5Spring = new Texture("farming/trees/Banana_Stage_5_Spring.png");
    private final Texture BananaStage5Summer = new Texture("farming/trees/Banana_Stage_5_Summer.png");
    private final Texture BananaStage5Fall = new Texture("farming/trees/Banana_Stage_5_Fall.png");
    private final Texture BananaStage5Winter = new Texture("farming/trees/Banana_Stage_5_Winter.png");
    private final Texture BananaStage5Fruit = new Texture("farming/trees/Banana_Stage_5_Fruit.png");
    private final Texture BananaTreeLightning = new Texture("farming/trees/BananaTreeLightning.png");

    public Texture getBanana() {
        return Banana;
    }

    public Texture getBananaSapling() {
        return BananaSapling;
    }

    public Texture getBananaPudding() {
        return BananaPudding;
    }

    public Texture getBananaStage1() {
        return BananaStage1;
    }

    public Texture getBananaStage2() {
        return BananaStage2;
    }

    public Texture getBananaStage3() {
        return BananaStage3;
    }

    public Texture getBananaStage4() {
        return BananaStage4;
    }

    public Texture getBananaStage5Spring() {
        return BananaStage5Spring;
    }

    public Texture getBananaStage5Summer() {
        return BananaStage5Summer;
    }

    public Texture getBananaStage5Fall() {
        return BananaStage5Fall;
    }

    public Texture getBananaStage5Winter() {
        return BananaStage5Winter;
    }

    public Texture getBananaStage5Fruit() {
        return BananaStage5Fruit;
    }

    public Texture getBananaTreeLightning() {
        return BananaTreeLightning;
    }

    private final Texture Cherry = new Texture("farming/trees/Cherry.png");
    private final Texture CherrySapling = new Texture("farming/trees/Cherry_Sapling.png");
    private final Texture CherryStage1 = new Texture("farming/trees/Cherry_Stage_1.png");
    private final Texture CherryStage2 = new Texture("farming/trees/Cherry_Stage_2.png");
    private final Texture CherryStage3 = new Texture("farming/trees/Cherry_Stage_3.png");
    private final Texture CherryStage4 = new Texture("farming/trees/Cherry_Stage_4.png");
    private final Texture CherryStage5Spring = new Texture("farming/trees/Cherry_Stage_5_Spring.png");
    private final Texture CherryStage5Summer = new Texture("farming/trees/Cherry_Stage_5_Summer.png");
    private final Texture CherryStage5Fall = new Texture("farming/trees/Cherry_Stage_5_Fall.png");
    private final Texture CherryStage5Winter = new Texture("farming/trees/Cherry_Stage_5_Winter.png");
    private final Texture CherryStage5Fruit = new Texture("farming/trees/Cherry_Stage_5_Fruit.png");
    private final Texture CherryTreeLightning = new Texture("farming/trees/CherryTreeLightning.png");

    public Texture getCherry() {
        return Cherry;
    }

    public Texture getCherrySapling() {
        return CherrySapling;
    }

    public Texture getCherryStage1() {
        return CherryStage1;
    }

    public Texture getCherryStage2() {
        return CherryStage2;
    }

    public Texture getCherryStage3() {
        return CherryStage3;
    }

    public Texture getCherryStage4() {
        return CherryStage4;
    }

    public Texture getCherryStage5Spring() {
        return CherryStage5Spring;
    }

    public Texture getCherryStage5Summer() {
        return CherryStage5Summer;
    }

    public Texture getCherryStage5Fall() {
        return CherryStage5Fall;
    }

    public Texture getCherryStage5Winter() {
        return CherryStage5Winter;
    }

    public Texture getCherryStage5Fruit() {
        return CherryStage5Fruit;
    }

    public Texture getCherryTreeLightning() {
        return CherryTreeLightning;
    }

    private final Texture Mango = new Texture("farming/trees/Mango.png");
    private final Texture MangoSapling = new Texture("farming/trees/Mango_Sapling.png");
    private final Texture MangoStage1 = new Texture("farming/trees/Mango_Stage_1.png");
    private final Texture MangoStage2 = new Texture("farming/trees/Mango_Stage_2.png");
    private final Texture MangoStage3 = new Texture("farming/trees/Mango_Stage_3.png");
    private final Texture MangoStage4 = new Texture("farming/trees/Mango_Stage_4.png");
    private final Texture MangoStage5Spring = new Texture("farming/trees/Mango_Stage_5_Spring.png");
    private final Texture MangoStage5Summer = new Texture("farming/trees/Mango_Stage_5_Summer.png");
    private final Texture MangoStage5Fall = new Texture("farming/trees/Mango_Stage_5_Fall.png");
    private final Texture MangoStage5Winter = new Texture("farming/trees/Mango_Stage_5_Winter.png");
    private final Texture MangoStage5Fruit = new Texture("farming/trees/Mango_Stage_5_Fruit.png");
    private final Texture MangoStickyRice = new Texture("farming/trees/Mango_Sticky_Rice.png");
    private final Texture MangoTreeLightning = new Texture("farming/trees/MangoTreeLightning.png");

    public Texture getMango() {
        return Mango;
    }

    public Texture getMangoSapling() {
        return MangoSapling;
    }

    public Texture getMangoStage1() {
        return MangoStage1;
    }

    public Texture getMangoStage2() {
        return MangoStage2;
    }

    public Texture getMangoStage3() {
        return MangoStage3;
    }

    public Texture getMangoStage4() {
        return MangoStage4;
    }

    public Texture getMangoStage5Spring() {
        return MangoStage5Spring;
    }

    public Texture getMangoStage5Summer() {
        return MangoStage5Summer;
    }

    public Texture getMangoStage5Fall() {
        return MangoStage5Fall;
    }

    public Texture getMangoStage5Winter() {
        return MangoStage5Winter;
    }

    public Texture getMangoStage5Fruit() {
        return MangoStage5Fruit;
    }

    public Texture getMangoStickyRice() {
        return MangoStickyRice;
    }

    public Texture getMangoTreeLightning() {
        return MangoTreeLightning;
    }

    private final Texture Orange = new Texture("farming/trees/Orange.png");
    private final Texture OrangeSapling = new Texture("farming/trees/Orange_Sapling.png");
    private final Texture OrangeStage1 = new Texture("farming/trees/Orange_Stage_1.png");
    private final Texture OrangeStage2 = new Texture("farming/trees/Orange_Stage_2.png");
    private final Texture OrangeStage3 = new Texture("farming/trees/Orange_Stage_3.png");
    private final Texture OrangeStage4 = new Texture("farming/trees/Orange_Stage_4.png");
    private final Texture OrangeStage5Spring = new Texture("farming/trees/Orange_Stage_5_Spring.png");
    private final Texture OrangeStage5Summer = new Texture("farming/trees/Orange_Stage_5_Summer.png");
    private final Texture OrangeStage5Fall = new Texture("farming/trees/Orange_Stage_5_Fall.png");
    private final Texture OrangeStage5Winter = new Texture("farming/trees/Orange_Stage_5_Winter.png");
    private final Texture OrangeStage5Fruit = new Texture("farming/trees/Orange_Stage_5_Fruit.png");
    private final Texture OrangeTreeLightning = new Texture("farming/trees/OrangeTreeLightning.png");

    public Texture getOrange() {
        return Orange;
    }

    public Texture getOrangeSapling() {
        return OrangeSapling;
    }

    public Texture getOrangeStage1() {
        return OrangeStage1;
    }

    public Texture getOrangeStage2() {
        return OrangeStage2;
    }

    public Texture getOrangeStage3() {
        return OrangeStage3;
    }

    public Texture getOrangeStage4() {
        return OrangeStage4;
    }

    public Texture getOrangeStage5Spring() {
        return OrangeStage5Spring;
    }

    public Texture getOrangeStage5Summer() {
        return OrangeStage5Summer;
    }

    public Texture getOrangeStage5Fall() {
        return OrangeStage5Fall;
    }

    public Texture getOrangeStage5Winter() {
        return OrangeStage5Winter;
    }

    public Texture getOrangeStage5Fruit() {
        return OrangeStage5Fruit;
    }

    public Texture getOrangeTreeLightning() {
        return OrangeTreeLightning;
    }

    private final Texture Peach = new Texture("farming/trees/Peach.png");
    private final Texture PeachSapling = new Texture("farming/trees/Peach_Sapling.png");
    private final Texture PeachStage1 = new Texture("farming/trees/Peach_Stage_1.png");
    private final Texture PeachStage2 = new Texture("farming/trees/Peach_Stage_2.png");
    private final Texture PeachStage3 = new Texture("farming/trees/Peach_Stage_3.png");
    private final Texture PeachStage4 = new Texture("farming/trees/Peach_Stage_4.png");
    private final Texture PeachStage5Spring = new Texture("farming/trees/Peach_Stage_5_Spring.png");
    private final Texture PeachStage5Summer = new Texture("farming/trees/Peach_Stage_5_Summer.png");
    private final Texture PeachStage5Fall = new Texture("farming/trees/Peach_Stage_5_Fall.png");
    private final Texture PeachStage5Winter = new Texture("farming/trees/Peach_Stage_5_Winter.png");
    private final Texture PeachStage5Fruit = new Texture("farming/trees/Peach_Stage_5_Fruit.png");
    private final Texture PeachTreeLightning = new Texture("farming/trees/PeachTreeLightning.png");

    public Texture getPeach() {
        return Peach;
    }

    public Texture getPeachSapling() {
        return PeachSapling;
    }

    public Texture getPeachStage1() {
        return PeachStage1;
    }

    public Texture getPeachStage2() {
        return PeachStage2;
    }

    public Texture getPeachStage3() {
        return PeachStage3;
    }

    public Texture getPeachStage4() {
        return PeachStage4;
    }

    public Texture getPeachStage5Spring() {
        return PeachStage5Spring;
    }

    public Texture getPeachStage5Summer() {
        return PeachStage5Summer;
    }

    public Texture getPeachStage5Fall() {
        return PeachStage5Fall;
    }

    public Texture getPeachStage5Winter() {
        return PeachStage5Winter;
    }

    public Texture getPeachStage5Fruit() {
        return PeachStage5Fruit;
    }

    public Texture getPeachTreeLightning() {
        return PeachTreeLightning;
    }

    private final Texture Apple = new Texture("farming/trees/Apple.png");
    private final Texture AppleSapling = new Texture("farming/trees/Apple_Sapling.png");
    private final Texture AppleStage1 = new Texture("farming/trees/Apple_Stage_1.png");
    private final Texture AppleStage2 = new Texture("farming/trees/Apple_Stage_2.png");
    private final Texture AppleStage3 = new Texture("farming/trees/Apple_Stage_3.png");
    private final Texture AppleStage4 = new Texture("farming/trees/Apple_Stage_4.png");
    private final Texture AppleStage5Spring = new Texture("farming/trees/Apple_Stage_5_Spring.png");
    private final Texture AppleStage5Summer = new Texture("farming/trees/Apple_Stage_5_Summer.png");
    private final Texture AppleStage5Fall = new Texture("farming/trees/Apple_Stage_5_Fall.png");
    private final Texture AppleStage5Winter = new Texture("farming/trees/Apple_Stage_5_Winter.png");
    private final Texture AppleStage5Fruit = new Texture("farming/trees/Apple_Stage_5_Fruit.png");
    private final Texture AppleTreeLightning = new Texture("farming/trees/AppleTreeLightning.png");

    public Texture getApple() {
        return Apple;
    }

    public Texture getAppleSapling() {
        return AppleSapling;
    }

    public Texture getAppleStage1() {
        return AppleStage1;
    }

    public Texture getAppleStage2() {
        return AppleStage2;
    }

    public Texture getAppleStage3() {
        return AppleStage3;
    }

    public Texture getAppleStage4() {
        return AppleStage4;
    }

    public Texture getAppleStage5Spring() {
        return AppleStage5Spring;
    }

    public Texture getAppleStage5Summer() {
        return AppleStage5Summer;
    }

    public Texture getAppleStage5Fall() {
        return AppleStage5Fall;
    }

    public Texture getAppleStage5Winter() {
        return AppleStage5Winter;
    }

    public Texture getAppleStage5Fruit() {
        return AppleStage5Fruit;
    }

    public Texture getAppleTreeLightning() {
        return AppleTreeLightning;
    }

    private final Texture Pomegranate = new Texture("farming/trees/Pomegranate.png");
    private final Texture PomegranateSapling = new Texture("farming/trees/Pomegranate_Sapling.png");
    private final Texture PomegranateStage1 = new Texture("farming/trees/Pomegranate_Stage_1.png");
    private final Texture PomegranateStage2 = new Texture("farming/trees/Pomegranate_Stage_2.png");
    private final Texture PomegranateStage3 = new Texture("farming/trees/Pomegranate_Stage_3.png");
    private final Texture PomegranateStage4 = new Texture("farming/trees/Pomegranate_Stage_4.png");
    private final Texture PomegranateStage5Spring = new Texture("farming/trees/Pomegranate_Stage_5_Spring.png");
    private final Texture PomegranateStage5Summer = new Texture("farming/trees/Pomegranate_Stage_5_Summer.png");
    private final Texture PomegranateStage5Fall = new Texture("farming/trees/Pomegranate_Stage_5_Fall.png");
    private final Texture PomegranateStage5Winter = new Texture("farming/trees/Pomegranate_Stage_5_Winter.png");
    private final Texture PomegranateStage5Fruit = new Texture("farming/trees/Pomegranate_Stage_5_Fruit.png");
    private final Texture PomegranateTreeLightning = new Texture("farming/trees/PomegranateTreeLightning.png");

    public Texture getPomegranate() {
        return Pomegranate;
    }

    public Texture getPomegranateSapling() {
        return PomegranateSapling;
    }

    public Texture getPomegranateStage1() {
        return PomegranateStage1;
    }

    public Texture getPomegranateStage2() {
        return PomegranateStage2;
    }

    public Texture getPomegranateStage3() {
        return PomegranateStage3;
    }

    public Texture getPomegranateStage4() {
        return PomegranateStage4;
    }

    public Texture getPomegranateStage5Spring() {
        return PomegranateStage5Spring;
    }

    public Texture getPomegranateStage5Summer() {
        return PomegranateStage5Summer;
    }

    public Texture getPomegranateStage5Fall() {
        return PomegranateStage5Fall;
    }

    public Texture getPomegranateStage5Winter() {
        return PomegranateStage5Winter;
    }

    public Texture getPomegranateStage5Fruit() {
        return PomegranateStage5Fruit;
    }

    public Texture getPomegranateTreeLightning() {
        return PomegranateTreeLightning;
    }

    private final Texture OakResin = new Texture("farming/trees/Oak_Resin.png");
    private final Texture OakStage1 = new Texture("farming/trees/Oak_Stage_1.png");
    private final Texture OakStage2 = new Texture("farming/trees/Oak_Stage_2.png");
    private final Texture OakStage3 = new Texture("farming/trees/Oak_Stage_3.png");
    private final Texture OakStage4 = new Texture("farming/trees/Oak_Stage_4.png");
    private final Texture OakStage5Spring = new Texture("farming/trees/Oak_Stage_5_Spring.png");
    private final Texture OakStage5Summer = new Texture("farming/trees/Oak_Stage_5_Summer.png");
    private final Texture OakStage5Fall = new Texture("farming/trees/Oak_Stage_5_Fall.png");
    private final Texture OakStage5Winter = new Texture("farming/trees/Oak_Stage_5_Winter.png");
    private final Texture OakStumpSpring = new Texture("farming/trees/Oak_stump_Spring.png");
    private final Texture OakStumpWinter = new Texture("farming/trees/Oak_stump_Winter.png");

    public Texture getOakResin() {
        return OakResin;
    }

    public Texture getOakStage1() {
        return OakStage1;
    }

    public Texture getOakStage2() {
        return OakStage2;
    }

    public Texture getOakStage3() {
        return OakStage3;
    }

    public Texture getOakStage4() {
        return OakStage4;
    }

    public Texture getOakStage5Spring() {
        return OakStage5Spring;
    }

    public Texture getOakStage5Summer() {
        return OakStage5Summer;
    }

    public Texture getOakStage5Fall() {
        return OakStage5Fall;
    }

    public Texture getOakStage5Winter() {
        return OakStage5Winter;
    }

    public Texture getOakStumpSpring() {
        return OakStumpSpring;
    }

    public Texture getOakStumpWinter() {
        return OakStumpWinter;
    }

    private final Texture MapleSyrup = new Texture("farming/trees/Maple_Syrup.png");
    private final Texture MapleSeed = new Texture("farming/trees/Maple_Seed.png");
    private final Texture MapleStage1 = new Texture("farming/trees/Maple_Stage_1.png");
    private final Texture MapleStage2 = new Texture("farming/trees/Maple_Stage_2.png");
    private final Texture MapleStage3 = new Texture("farming/trees/Maple_Stage_3.png");
    private final Texture MapleStage4 = new Texture("farming/trees/Maple_Stage_4.png");
    private final Texture MapleStage5Spring = new Texture("farming/trees/Maple_Stage_5_Spring.png");
    private final Texture MapleStage5Summer = new Texture("farming/trees/Maple_Stage_5_Summer.png");
    private final Texture MapleStage5Fall = new Texture("farming/trees/Maple_Stage_5_Fall.png");
    private final Texture MapleStage5Winter = new Texture("farming/trees/Maple_Stage_5_Winter.png");
    private final Texture MapleStumpSpring = new Texture("farming/trees/Maple_stump_Spring.png");
    private final Texture MapleStumpSummer = new Texture("farming/trees/Maple_stump_Summer.png");
    private final Texture MapleStumpFall = new Texture("farming/trees/Maple_stump_Fall.png");
    private final Texture MapleStumpWinter = new Texture("farming/trees/Maple_stump_Winter.png");

    public Texture getMapleSyrup() {
        return MapleSyrup;
    }

    public Texture getMapleSeed() {
        return MapleSeed;
    }

    public Texture getMapleStage1() {
        return MapleStage1;
    }

    public Texture getMapleStage2() {
        return MapleStage2;
    }

    public Texture getMapleStage3() {
        return MapleStage3;
    }

    public Texture getMapleStage4() {
        return MapleStage4;
    }

    public Texture getMapleStage5Spring() {
        return MapleStage5Spring;
    }

    public Texture getMapleStage5Summer() {
        return MapleStage5Summer;
    }

    public Texture getMapleStage5Fall() {
        return MapleStage5Fall;
    }

    public Texture getMapleStage5Winter() {
        return MapleStage5Winter;
    }

    public Texture getMapleStumpSpring() {
        return MapleStumpSpring;
    }

    public Texture getMapleStumpSummer() {
        return MapleStumpSummer;
    }

    public Texture getMapleStumpFall() {
        return MapleStumpFall;
    }

    public Texture getMapleStumpWinter() {
        return MapleStumpWinter;
    }

    private final Texture PineTar = new Texture("farming/trees/Pine_Tar.png");
    private final Texture PineCone = new Texture("farming/trees/Pine_Cone.png");
    private final Texture PineStage1 = new Texture("farming/trees/Pine_Stage_1.png");
    private final Texture PineStage2 = new Texture("farming/trees/Pine_Stage_2.png");
    private final Texture PineStage3 = new Texture("farming/trees/Pine_Stage_3.png");
    private final Texture PineStage4 = new Texture("farming/trees/Pine_Stage_4.png");
    private final Texture PineStage5Spring = new Texture("farming/trees/Pine_Stage_5_Spring.png");
    private final Texture PineStage5Summer = new Texture("farming/trees/Pine_Stage_5_Summer.png");
    private final Texture PineStage5Fall = new Texture("farming/trees/Pine_Stage_5_Fall.png");
    private final Texture PineStage5Winter = new Texture("farming/trees/Pine_Stage_5_Winter.png");
    private final Texture PineStumpSpring = new Texture("farming/trees/Pine_stump_Spring.png");
    private final Texture PineStumpFall = new Texture("farming/trees/Pine_stump_Fall.png");
    private final Texture PineStumpWinter = new Texture("farming/trees/Pine_stump_Winter.png");

    public Texture getPineTar() {
        return PineTar;
    }

    public Texture getPineCone() {
        return PineCone;
    }

    public Texture getPineStage1() {
        return PineStage1;
    }

    public Texture getPineStage2() {
        return PineStage2;
    }

    public Texture getPineStage3() {
        return PineStage3;
    }

    public Texture getPineStage4() {
        return PineStage4;
    }

    public Texture getPineStage5Spring() {
        return PineStage5Spring;
    }

    public Texture getPineStage5Summer() {
        return PineStage5Summer;
    }

    public Texture getPineStage5Fall() {
        return PineStage5Fall;
    }

    public Texture getPineStage5Winter() {
        return PineStage5Winter;
    }

    public Texture getPineStumpSpring() {
        return PineStumpSpring;
    }

    public Texture getPineStumpFall() {
        return PineStumpFall;
    }

    public Texture getPineStumpWinter() {
        return PineStumpWinter;
    }

    private final Texture Sap = new Texture("farming/trees/Sap.png");
    private final Texture MahoganySeed = new Texture("farming/trees/Mahogany_Seed.png");
    private final Texture MahoganyStage1 = new Texture("farming/trees/Mahogany_Stage_1.png");
    private final Texture MahoganyStage2 = new Texture("farming/trees/Mahogany_Stage_2.png");
    private final Texture MahoganyStage3 = new Texture("farming/trees/Mahogany_Stage_3.png");
    private final Texture MahoganyStage4 = new Texture("farming/trees/Mahogany_Stage_4.png");
    private final Texture MahoganyStage5Spring = new Texture("farming/trees/Mahogany_Stage_5_Spring.png");
    private final Texture MahoganyStage5Summer = new Texture("farming/trees/Mahogany_Stage_5_Summer.png");
    private final Texture MahoganyStage5Fall = new Texture("farming/trees/Mahogany_Stage_5_Fall.png");
    private final Texture MahoganyStage5Winter = new Texture("farming/trees/Mahogany_Stage_5_Winter.png");
    private final Texture MahoganyStumpSpring = new Texture("farming/trees/Mahogany_stump_Spring.png");
    private final Texture MahoganyStumpFall = new Texture("farming/trees/Mahogany_stump_Fall.png");
    private final Texture MahoganyStumpWinter = new Texture("farming/trees/Mahogany_stump_Winter.png");

    public Texture getSap() {
        return Sap;
    }

    public Texture getMahoganySeed() {
        return MahoganySeed;
    }

    public Texture getMahoganyStage1() {
        return MahoganyStage1;
    }

    public Texture getMahoganyStage2() {
        return MahoganyStage2;
    }

    public Texture getMahoganyStage3() {
        return MahoganyStage3;
    }

    public Texture getMahoganyStage4() {
        return MahoganyStage4;
    }

    public Texture getMahoganyStage5Spring() {
        return MahoganyStage5Spring;
    }

    public Texture getMahoganyStage5Summer() {
        return MahoganyStage5Summer;
    }

    public Texture getMahoganyStage5Fall() {
        return MahoganyStage5Fall;
    }

    public Texture getMahoganyStage5Winter() {
        return MahoganyStage5Winter;
    }

    public Texture getMahoganyStumpSpring() {
        return MahoganyStumpSpring;
    }

    public Texture getMahoganyStumpFall() {
        return MahoganyStumpFall;
    }

    public Texture getMahoganyStumpWinter() {
        return MahoganyStumpWinter;
    }

    private final Texture CommonMushroom = new Texture("farming/trees/Common_Mushroom.png");
    private final Texture MushroomTreeSeed = new Texture("farming/trees/Mushroom_Tree_Seed.png");
    private final Texture MushroomTreeStage1 = new Texture("farming/trees/MushroomTree_Stage_1.png");
    private final Texture MushroomTreeStage2 = new Texture("farming/trees/MushroomTree_Stage_2.png");
    private final Texture MushroomTreeStage3 = new Texture("farming/trees/MushroomTree_Stage_3.png");
    private final Texture MushroomTreeStage4 = new Texture("farming/trees/MushroomTree_Stage_4.png");
    private final Texture MushroomTreeStage5 = new Texture("farming/trees/MushroomTree_Stage_5.png");
    private final Texture MushroomTreeStump = new Texture("farming/trees/Mushroom_stump.png");

    public Texture getCommonMushroom() {
        return CommonMushroom;
    }

    public Texture getMushroomTreeSeed() {
        return MushroomTreeSeed;
    }

    public Texture getMushroomTreeStage1() {
        return MushroomTreeStage1;
    }

    public Texture getMushroomTreeStage2() {
        return MushroomTreeStage2;
    }

    public Texture getMushroomTreeStage3() {
        return MushroomTreeStage3;
    }

    public Texture getMushroomTreeStage4() {
        return MushroomTreeStage4;
    }

    public Texture getMushroomTreeStage5() {
        return MushroomTreeStage5;
    }

    public Texture getMushroomTreeStump() {
        return MushroomTreeStump;
    }

    private final Texture MysticSyrup = new Texture("farming/trees/Mystic_Syrup.png");
    private final Texture MysticTreeSeed = new Texture("farming/trees/Mystic_Tree_Seed.png");
    private final Texture MysticTreeStage1 = new Texture("farming/trees/Mystic_Tree_Stage_1.png");
    private final Texture MysticTreeStage2 = new Texture("farming/trees/Mystic_Tree_Stage_2.png");
    private final Texture MysticTreeStage3 = new Texture("farming/trees/Mystic_Tree_Stage_3.png");
    private final Texture MysticTreeStage4 = new Texture("farming/trees/Mystic_Tree_Stage_4.png");
    private final Texture MysticTreeStage5 = new Texture("farming/trees/Mystic_Tree_Stage_5.png");
    private final Texture MysticTreeStump = new Texture("farming/trees/Mystic_Tree_Stump.png");

    public Texture getMysticSyrup() {
        return MysticSyrup;
    }

    public Texture getMysticTreeSeed() {
        return MysticTreeSeed;
    }

    public Texture getMysticTreeStage1() {
        return MysticTreeStage1;
    }

    public Texture getMysticTreeStage2() {
        return MysticTreeStage2;
    }

    public Texture getMysticTreeStage3() {
        return MysticTreeStage3;
    }

    public Texture getMysticTreeStage4() {
        return MysticTreeStage4;
    }

    public Texture getMysticTreeStage5() {
        return MysticTreeStage5;
    }

    public Texture getMysticTreeStump() {
        return MysticTreeStump;
    }

    // foraging crops:
    private final Texture Daffodil = new Texture("farming/foraging/Daffodil.png");
    private final Texture Dandelion = new Texture("farming/foraging/Dandelion.png");
    private final Texture Leek = new Texture("farming/foraging/Leek.png");
    private final Texture Morel = new Texture("farming/foraging/Morel.png");
    private final Texture Salmonberry = new Texture("farming/foraging/Salmonberry.png");
    private final Texture SpringOnion = new Texture("farming/foraging/Spring_Onion.png");
    private final Texture WildHorseradish = new Texture("farming/foraging/Wild_Horseradish.png");
    private final Texture FiddleheadFern = new Texture("farming/foraging/Fiddlehead_Fern.png");
    private final Texture RedMushroom = new Texture("farming/foraging/Red_Mushroom.png");
    private final Texture SpiceBerry = new Texture("farming/foraging/Spice_Berry.png");
    private final Texture SweetPea = new Texture("farming/foraging/Sweet_Pea.png");
    private final Texture Blackberry = new Texture("farming/foraging/Blackberry.png");
    private final Texture Chanterelle = new Texture("farming/foraging/Chanterelle.png");
    private final Texture Hazelnut = new Texture("farming/foraging/Hazelnut.png");
    private final Texture PurpleMushroom = new Texture("farming/foraging/Purple_Mushroom.png");
    private final Texture WildPlum = new Texture("farming/foraging/Wild_Plum.png");
    private final Texture Crocus = new Texture("farming/foraging/Crocus.png");
    private final Texture CrystalFruit = new Texture("farming/foraging/Crystal_Fruit.png");
    private final Texture Holly = new Texture("farming/foraging/Holly.png");
    private final Texture SnowYam = new Texture("farming/foraging/Snow_Yam.png");
    private final Texture WinterRoot = new Texture("farming/foraging/Winter_Root.png");
    // Common Mushroom and Grape have already been made

    public Texture getDaffodil() {
        return Daffodil;
    }

    public Texture getDandelion() {
        return Dandelion;
    }

    public Texture getLeek() {
        return Leek;
    }

    public Texture getMorel() {
        return Morel;
    }

    public Texture getSalmonberry() {
        return Salmonberry;
    }

    public Texture getSpringOnion() {
        return SpringOnion;
    }

    public Texture getWildHorseradish() {
        return WildHorseradish;
    }

    public Texture getFiddleheadFern() {
        return FiddleheadFern;
    }

    public Texture getRedMushroom() {
        return RedMushroom;
    }

    public Texture getSpiceBerry() {
        return SpiceBerry;
    }

    public Texture getSweetPea() {
        return SweetPea;
    }

    public Texture getBlackberry() {
        return Blackberry;
    }

    public Texture getChanterelle() {
        return Chanterelle;
    }

    public Texture getHazelnut() {
        return Hazelnut;
    }

    public Texture getPurpleMushroom() {
        return PurpleMushroom;
    }

    public Texture getWildPlum() {
        return WildPlum;
    }

    public Texture getCrocus() {
        return Crocus;
    }

    public Texture getCrystalFruit() {
        return CrystalFruit;
    }

    public Texture getHolly() {
        return Holly;
    }

    public Texture getSnowYam() {
        return SnowYam;
    }

    public Texture getWinterRoot() {
        return WinterRoot;
    }

    // foraging trees(including: Acorn, Maple Seed, Pine Cone, Mahogany Seed, Mushroom Tree Seed) have already been made

    // foraging seeds:
    private final Texture JazzSeeds = new Texture("farming/crops/Jazz_Seeds.png");
    private final Texture CarrotSeeds = new Texture("farming/crops/Carrot_Seeds.png");
    private final Texture CauliflowerSeeds = new Texture("farming/crops/Cauliflower_Seeds.png");
    private final Texture CoffeeBean = new Texture("farming/crops/Coffee_Bean.png");
    private final Texture GarlicSeeds = new Texture("farming/crops/Garlic_Seeds.png");
    private final Texture BeanStarter = new Texture("farming/crops/Bean_Starter.png");
    private final Texture KaleSeeds = new Texture("farming/crops/Kale_Seeds.png");
    private final Texture ParsnipSeeds = new Texture("farming/crops/Parsnip_Seeds.png");
    private final Texture PotatoSeeds = new Texture("farming/crops/Potato_Seeds.png");
    private final Texture RhubarbSeeds = new Texture("farming/crops/Rhubarb_Seeds.png");
    private final Texture StrawberrySeeds = new Texture("farming/crops/Strawberry_Seeds.png");
    private final Texture TulipBulb = new Texture("farming/crops/Tulip_Bulb.png");
    private final Texture RiceShoot = new Texture("farming/crops/Rice_Shoot.png");
    private final Texture BlueberrySeeds = new Texture("farming/crops/Blueberry_Seeds.png");
    private final Texture CornSeeds = new Texture("farming/crops/Corn_Seeds.png");
    private final Texture HopsStarter = new Texture("farming/crops/Hops_Starter.png");
    private final Texture PepperSeeds = new Texture("farming/crops/Pepper_Seeds.png");
    private final Texture MelonSeeds = new Texture("farming/crops/Melon_Seeds.png");
    private final Texture PoppySeeds = new Texture("farming/crops/Poppy_Seeds.png");
    private final Texture RadishSeeds = new Texture("farming/crops/Radish_Seeds.png");
    private final Texture RedCabbageSeeds = new Texture("farming/crops/Red_Cabbage_Seeds.png");
    private final Texture StarfruitSeeds = new Texture("farming/crops/Starfruit_Seeds.png");
    private final Texture SpangleSeeds = new Texture("farming/crops/Spangle_Seeds.png");
    private final Texture SummerSquashSeeds = new Texture("farming/crops/Summer_Squash_Seeds.png");
    private final Texture SunflowerSeeds = new Texture("farming/crops/Sunflower_Seeds.png");
    private final Texture TomatoSeeds = new Texture("farming/crops/Tomato_Seeds.png");
    private final Texture WheatSeeds = new Texture("farming/crops/Wheat_Seeds.png");
    private final Texture AmaranthSeeds = new Texture("farming/crops/Amaranth_Seeds.png");
    private final Texture ArtichokeSeeds = new Texture("farming/crops/Artichoke_Seeds.png");
    private final Texture BeetSeeds = new Texture("farming/crops/Beet_Seeds.png");
    private final Texture BokChoySeeds = new Texture("farming/crops/Bok_Choy_Seeds.png");
    private final Texture BroccoliSeeds = new Texture("farming/crops/Broccoli_Seeds.png");
    private final Texture CranberrySeeds = new Texture("farming/crops/Cranberry_Seeds.png");
    private final Texture EggplantSeeds = new Texture("farming/crops/Eggplant_Seeds.png");
    private final Texture FairySeeds = new Texture("farming/crops/Fairy_Seeds.png");
    private final Texture GrapeStarter = new Texture("farming/crops/Grape_Starter.png");
    private final Texture PumpkinSeeds = new Texture("farming/crops/Pumpkin_Seeds.png");
    private final Texture YamSeeds = new Texture("farming/crops/Yam_Seeds.png");
    private final Texture RareSeeds = new Texture("farming/crops/Rare_Seed.png");
    private final Texture PowdermelonSeeds = new Texture("farming/crops/Powdermelon_Seeds.png");
    private final Texture AncientSeeds = new Texture("farming/crops/Ancient_Seeds.png");
    private final Texture MixedSeeds = new Texture("farming/crops/Mixed_Seeds.png");

    public Texture getJazzSeeds() {
        return JazzSeeds;
    }

    public Texture getCarrotSeeds() {
        return CarrotSeeds;
    }

    public Texture getCauliflowerSeeds() {
        return CauliflowerSeeds;
    }

    public Texture getGarlicSeeds() {
        return GarlicSeeds;
    }

    public Texture getBeanStarter() {
        return BeanStarter;
    }

    public Texture getKaleSeeds() {
        return KaleSeeds;
    }

    public Texture getParsnipSeeds() {
        return ParsnipSeeds;
    }

    public Texture getPotatoSeeds() {
        return PotatoSeeds;
    }

    public Texture getRhubarbSeeds() {
        return RhubarbSeeds;
    }

    public Texture getStrawberrySeeds() {
        return StrawberrySeeds;
    }

    public Texture getTulipBulb() {
        return TulipBulb;
    }

    public Texture getRiceShoot() {
        return RiceShoot;
    }

    public Texture getBlueberrySeeds() {
        return BlueberrySeeds;
    }

    public Texture getCornSeeds() {
        return CornSeeds;
    }

    public Texture getHopsStarter() {
        return HopsStarter;
    }

    public Texture getPepperSeeds() {
        return PepperSeeds;
    }

    public Texture getMelonSeeds() {
        return MelonSeeds;
    }

    public Texture getPoppySeeds() {
        return PoppySeeds;
    }

    public Texture getRadishSeeds() {
        return RadishSeeds;
    }

    public Texture getRedCabbageSeeds() {
        return RedCabbageSeeds;
    }

    public Texture getStarfruitSeeds() {
        return StarfruitSeeds;
    }

    public Texture getSpangleSeeds() {
        return SpangleSeeds;
    }

    public Texture getSummerSquashSeeds() {
        return SummerSquashSeeds;
    }

    public Texture getSunflowerSeeds() {
        return SunflowerSeeds;
    }

    public Texture getTomatoSeeds() {
        return TomatoSeeds;
    }

    public Texture getWheatSeeds() {
        return WheatSeeds;
    }

    public Texture getAmaranthSeeds() {
        return AmaranthSeeds;
    }

    public Texture getArtichokeSeeds() {
        return ArtichokeSeeds;
    }

    public Texture getBeetSeeds() {
        return BeetSeeds;
    }

    public Texture getBokChoySeeds() {
        return BokChoySeeds;
    }

    public Texture getBroccoliSeeds() {
        return BroccoliSeeds;
    }

    public Texture getCranberrySeeds() {
        return CranberrySeeds;
    }

    public Texture getEggplantSeeds() {
        return EggplantSeeds;
    }

    public Texture getFairySeeds() {
        return FairySeeds;
    }

    public Texture getGrapeStarter() {
        return GrapeStarter;
    }

    public Texture getPumpkinSeeds() {
        return PumpkinSeeds;
    }

    public Texture getYamSeeds() {
        return YamSeeds;
    }

    public Texture getRareSeeds() {
        return RareSeeds;
    }

    public Texture getPowdermelonSeeds() {
        return PowdermelonSeeds;
    }

    public Texture getAncientSeeds() {
        return AncientSeeds;
    }

    public Texture getMixedSeeds() {
        return MixedSeeds;
    }

    // foraging minerals:
    private final Texture Quartz = new Texture("mineral/Quartz.png");
    private final Texture EarthCrystal = new Texture("mineral/Earth_Crystal.png");
    private final Texture FrozenTear = new Texture("mineral/Frozen_Tear.png");
    private final Texture FireQuartz = new Texture("mineral/Fire_Quartz.png");
    private final Texture Emerald = new Texture("mineral/Emerald.png");
    private final Texture Aquamarine = new Texture("mineral/Aquamarine.png");
    private final Texture Ruby = new Texture("mineral/Ruby.png");
    private final Texture Amethyst = new Texture("mineral/Amethyst.png");
    private final Texture Topaz = new Texture("mineral/Topaz.png");
    private final Texture Jade = new Texture("mineral/Jade.png");
    private final Texture Diamond = new Texture("mineral/Diamond.png");
    private final Texture PrismaticShard = new Texture("mineral/Prismatic_Shard.png");
    private final Texture Copper = new Texture("mineral/Copper.png");
    private final Texture Iron = new Texture("mineral/Iron.png");
    private final Texture Gold = new Texture("mineral/Gold.png");
    private final Texture Iridium = new Texture("mineral/Iridium.png");
    private final Texture Coal = new Texture("mineral/Coal.png");

    public Texture getQuartz() {
        return Quartz;
    }

    public Texture getEarthCrystal() {
        return EarthCrystal;
    }

    public Texture getFrozenTear() {
        return FrozenTear;
    }

    public Texture getFireQuartz() {
        return FireQuartz;
    }

    public Texture getEmerald() {
        return Emerald;
    }

    public Texture getAquamarine() {
        return Aquamarine;
    }

    public Texture getRuby() {
        return Ruby;
    }

    public Texture getAmethyst() {
        return Amethyst;
    }

    public Texture getTopaz() {
        return Topaz;
    }

    public Texture getJade() {
        return Jade;
    }

    public Texture getDiamond() {
        return Diamond;
    }

    public Texture getPrismaticShard() {
        return PrismaticShard;
    }

    public Texture getCopper() {
        return Copper;
    }

    public Texture getIron() {
        return Iron;
    }

    public Texture getGold() {
        return Gold;
    }

    public Texture getIridium() {
        return Iridium;
    }

    public Texture getCoal() {
        return Coal;
    }

    // tools:
    private final Texture Hoe = new Texture("tools/Hoe.png");
    private final Texture CopperHoe = new Texture("tools/Copper_Hoe.png");
    private final Texture SteelHoe = new Texture("tools/Steel_Hoe.png");
    private final Texture GoldHoe = new Texture("tools/Gold_Hoe.png");
    private final Texture IridiumHoe = new Texture("tools/Iridium_Hoe.png");

    private final Texture Pickaxe = new Texture("tools/Pickaxe.png");
    private final Texture CopperPickaxe = new Texture("tools/Copper_Pickaxe.png");
    private final Texture SteelPickaxe = new Texture("tools/Steel_Pickaxe.png");
    private final Texture GoldPickaxe = new Texture("tools/Gold_Pickaxe.png");
    private final Texture IridiumPickaxe = new Texture("tools/Iridium_Pickaxe.png");

    private final Texture Axe = new Texture("tools/Axe.png");
    private final Texture CopperAxe = new Texture("tools/Copper_Axe.png");
    private final Texture SteelAxe = new Texture("tools/Steel_Axe.png");
    private final Texture GoldAxe = new Texture("tools/Gold_Axe.png");
    private final Texture IridiumAxe = new Texture("tools/Iridium_Axe.png");

    private final Texture WateringCan = new Texture("tools/Watering_Can.png");
    private final Texture CopperWateringCan = new Texture("tools/Copper_Watering_Can.png");
    private final Texture SteelWateringCan = new Texture("tools/Steel_Watering_Can.png");
    private final Texture GoldWateringCan = new Texture("tools/Gold_Watering_Can.png");
    private final Texture IridiumWateringCan = new Texture("tools/Iridium_Watering_Can.png");

    private final Texture TrainingRod = new Texture("tools/Training_Rod.png");
    private final Texture IridiumRod = new Texture("tools/Iridium_Rod.png");
    private final Texture FiberglassRod = new Texture("tools/Fiberglass_Rod.png");
    private final Texture BambooPole = new Texture("tools/Bamboo_Pole.png");
    private final Texture AdvancedIridiumRod = new Texture("tools/Advanced_Iridium_Rod.png");

    private final Texture TrashCan = new Texture("tools/Trash_Can.png");
    private final Texture TrashCanCopper = new Texture("tools/Trash_Can_Copper.png");
    private final Texture TrashCanSteel = new Texture("tools/Trash_Can_Steel.png");
    private final Texture TrashCanGold = new Texture("tools/Trash_Can_Gold.png");
    private final Texture TrashCanIridium = new Texture("tools/Trash_Can_Iridium.png");

    private final Texture Scythe = new Texture("tools/Scythe.png");

    private final Texture MilkPail = new Texture("tools/Milk_Pail.png");

    private final Texture Shear = new Texture("tools/Shears.png");

    public Texture getHoe() {
        return Hoe;
    }

    public Texture getCopperHoe() {
        return CopperHoe;
    }

    public Texture getSteelHoe() {
        return SteelHoe;
    }

    public Texture getGoldHoe() {
        return GoldHoe;
    }

    public Texture getIridiumHoe() {
        return IridiumHoe;
    }

    public Texture getPickaxe() {
        return Pickaxe;
    }

    public Texture getCopperPickaxe() {
        return CopperPickaxe;
    }

    public Texture getSteelPickaxe() {
        return SteelPickaxe;
    }

    public Texture getGoldPickaxe() {
        return GoldPickaxe;
    }

    public Texture getIridiumPickaxe() {
        return IridiumPickaxe;
    }

    public Texture getAxe() {
        return Axe;
    }

    public Texture getCopperAxe() {
        return CopperAxe;
    }

    public Texture getSteelAxe() {
        return SteelAxe;
    }

    public Texture getGoldAxe() {
        return GoldAxe;
    }

    public Texture getIridiumAxe() {
        return IridiumAxe;
    }

    public Texture getWateringCan() {
        return WateringCan;
    }

    public Texture getCopperWateringCan() {
        return CopperWateringCan;
    }

    public Texture getSteelWateringCan() {
        return SteelWateringCan;
    }

    public Texture getGoldWateringCan() {
        return GoldWateringCan;
    }

    public Texture getIridiumWateringCan() {
        return IridiumWateringCan;
    }

    public Texture getTrainingRod() {
        return TrainingRod;
    }

    public Texture getIridiumRod() {
        return IridiumRod;
    }

    public Texture getFiberglassRod() {
        return FiberglassRod;
    }

    public Texture getBambooPole() {
        return BambooPole;
    }

    public Texture getAdvancedIridiumRod() {
        return AdvancedIridiumRod;
    }

    public Texture getTrashCan() {
        return TrashCan;
    }

    public Texture getTrashCanCopper() {
        return TrashCanCopper;
    }

    public Texture getTrashCanSteel() {
        return TrashCanSteel;
    }

    public Texture getTrashCanGold() {
        return TrashCanGold;
    }

    public Texture getTrashCanIridium() {
        return TrashCanIridium;
    }

    public Texture getScythe() {
        return Scythe;
    }

    public Texture getMilkPail() {
        return MilkPail;
    }

    public Texture getShear() {
        return Shear;
    }

    public Texture defaultTexture() {
        return Hoe;
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

    public Texture getTempTex() {
        return tempTex;
    }

    public Texture getPlowedTile() {
        return PlowedTile;
    }
}
