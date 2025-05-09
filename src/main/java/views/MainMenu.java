package views;

import controllers.MainMenuController;
import models.Result;
import models.enums.commands.Menu;

public class MainMenu extends AppMenu {
    private final MainMenuController controller;

    public MainMenu(MainMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

        while (controller.getRepo().getCurrentMenu().equals(Menu.MAIN)) {

            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success()) {

                if (result.message().contains("profile")) {
                    controller.getRepo().setCurrentMenu(Menu.MAIN);
                }

                if (result.message().contains("game")) {
                    controller.getRepo().setCurrentMenu(Menu.GAME);
                }

                if (result.message().contains("login")) {
                    controller.getRepo().setCurrentMenu(Menu.LOGIN);
                }

                if (result.message().contains("logged out")) {
                    controller.getRepo().setCurrentMenu(Menu.LOGIN);
                }
            }
        }
    }
}
