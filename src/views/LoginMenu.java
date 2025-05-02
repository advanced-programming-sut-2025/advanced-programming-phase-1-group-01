package views;

import controllers.LoginMenuController;
import models.Result;
import models.enums.commands.Menu;

public class LoginMenu extends AppMenu {
    private final LoginMenuController controller;

    public LoginMenu(LoginMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

        while (controller.getRepo().getCurrentMenu().equals(Menu.LOGIN)) {
            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success() && result.message().contains("logged in")) {
                controller.getRepo().setCurrentMenu(Menu.MAIN);
            }
        }
    }
}
