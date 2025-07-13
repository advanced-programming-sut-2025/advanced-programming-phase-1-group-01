package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.GameMenuController;
import com.stardew_valley.models.Result;

public class GameMenuView extends View {
    private Stage stage;

    private final GameMenuController controller;

    public GameMenuView(GameMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {
        while (controller.getRepo().getCurrentView().equals(com.stardew_valley.models.enums.commands.View.GAME_MENU)){
            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success()) {
                if (result.message().contains("main menu")) {
                    controller.getRepo().setCurrentMenu(com.stardew_valley.models.enums.commands.View.MAIN_MENU);
                }
                if (result.message().contains("Game starting")) {
                    controller.getRepo().setCurrentMenu(com.stardew_valley.models.enums.commands.View.GAME);
                }
            }
        }
    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
