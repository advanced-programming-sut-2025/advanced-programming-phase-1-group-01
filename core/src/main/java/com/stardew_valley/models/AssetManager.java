package com.stardew_valley.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {

    private final Skin skin = new Skin(Gdx.files.internal("skin/NzSkin.json"));
//    private final Skin skin = new Skin(Gdx.files.internal("skin3/craftacular-ui.json"));
//    private final Skin skin = new Skin(Gdx.files.internal("skin_temp/terra-mother-ui.json"));

    private static AssetManager assetManager;
    public static final float SCALE = 4f;

    private final String spring_background = "images/all_dirt.png";

    private final String wood_fence = "images/wood_fence.png";

    private final String house = "images/house.png";

    private final String mine = "images/resized_mine.png";

    private final String lake = "images/r_lake.png";

    private final String greenhouse = "images/map_1.png";

    private final String clock = "images/clock.png";
    private final String arrow = "images/arrow.png";

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

    private final TextureRegion wood_fence_tex = new TextureRegion(new Texture(wood_fence));

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


    public TextureRegion getHouse() {
        return house_tex;
    }

    public TextureRegion getWoodFence() {
        return wood_fence_tex;
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

    //    crops:

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
        return Green_Bean_Stages[1];
    }

    public Texture getGreenBeanStage3() {
        return Green_Bean_Stages[2];
    }

    public Texture getGreenBeanStage4() {
        return Green_Bean_Stages[3];
    }

    public Texture getGreenBeanStage5() {
        return Green_Bean_Stages[4];
    }

    public Texture getGreenBeanStage6() {
        return Green_Bean_Stages[5];
    }

    public Texture getGreenBeanStage7() {
        return Green_Bean_Stages[6];
    }

    public Texture getGreenBeanStage8() {
        return Green_Bean_Stages[7];
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

    private final Texture Corn = new Texture("farming/crops/Corn.png");
    private final Texture[] Corn_Stages = new Texture[]{
        new Texture("farming/crops/Corn_Stage_1.png"),
        new Texture("farming/crops/Corn_Stage_2.png"),
        new Texture("farming/crops/Corn_Stage_3.png"),
        new Texture("farming/crops/Corn_Stage_4.png"),
        new Texture("farming/crops/Corn_Stage_5.png"),
        new Texture("farming/crops/Corn_Stage_6.png"),
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
