package views;

import controllers.GameMenuController;
import models.Result;

public class GameMenu extends View {
    private final GameMenuController controller;

    public GameMenu(GameMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

        while (controller.getRepo().getCurrentView().equals(models.enums.commands.View.GAME_MENU)){
            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success() && result.message().contains("main menu")){
                controller.getRepo().setCurrentMenu(models.enums.commands.View.MAIN_MENU);
            }
        }
    }
}
