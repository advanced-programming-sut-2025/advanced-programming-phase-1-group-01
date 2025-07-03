package com.stardew_valley.views;

import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.Result;

public class GameView extends View {
    private GameController controller;

    public GameView(GameController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {
        while (controller.getRepo().getCurrentView().equals(com.stardew_valley.models.enums.commands.View.GAME)) {
            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());
        }
    }
}
