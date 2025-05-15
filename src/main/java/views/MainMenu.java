package views;

import controllers.MainMenuController;
import models.Result;

public class MainMenu extends View {
    private final MainMenuController controller;

    public MainMenu(MainMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

        while (controller.getRepo().getCurrentView().equals(models.enums.commands.View.MAIN_MENU)) {

            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success()) {

                if (result.message().contains("profile")) {
                    controller.getRepo().setCurrentMenu(models.enums.commands.View.PROFILE_MENU);
                }

                if (result.message().contains("game")) {
                    controller.getRepo().setCurrentMenu(models.enums.commands.View.GAME_MENU);
                }

                if (result.message().contains("login")) {
                    controller.getRepo().setCurrentMenu(models.enums.commands.View.LOGIN_MENU);
                }

                if (result.message().contains("logged out")) {
                    controller.getRepo().setCurrentMenu(models.enums.commands.View.LOGIN_MENU);
                }
            }
        }
    }
}
