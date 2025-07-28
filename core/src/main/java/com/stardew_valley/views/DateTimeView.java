package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.DateTimeController;
import com.stardew_valley.models.AssetManager;

public class DateTimeView {
    private final DateTimeController controller;
    private final Batch batch;
    private Stage uiStage;
    private final Skin skin;
    private final BitmapFont font;
    private final TextureRegion arrow = AssetManager.getAssetManager().getArrow();
    private final TextureRegion clock = AssetManager.getAssetManager().getClock();

    public DateTimeView(DateTimeController controller) {
        this.controller = controller;
        this.batch = Main.getBatch();
        this.uiStage = new Stage(new ScreenViewport());
        this.skin = AssetManager.getAssetManager().getSkin();
        this.font = skin.getFont("Impact");
        font.getData().setScale(1.5f);
    }

    private void update() {
        uiStage.act(Gdx.graphics.getDeltaTime());
        render();
    }

    public void render() {
        float startX = 15;
        float startY = Gdx.graphics.getHeight() - 15;
        String clock = String.valueOf(controller.getRepo().getCurrentGame().getTimeManager().getNow().getDay())
            + controller.getRepo().getCurrentGame().getTimeManager().getNow().getWeekDay()
            + controller.getRepo().getCurrentGame().getTimeManager().getNow().getHour()
            + controller.getRepo().getCurrentGame().getTimeManager().getNow().getSeason()
            + controller.getRepo().getCurrentGame().getTimeManager().getNow().getYear();

        font.draw(batch, clock, startX, startY);
    }
}
