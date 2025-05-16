package views;

import controllers.GameController;
import models.Result;

public class GameView extends View {
    private GameController controller;

    public GameView(GameController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {
        while (controller.getRepo().getCurrentView().equals(models.enums.commands.View.GAME)) {
            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());
        }
    }
}
