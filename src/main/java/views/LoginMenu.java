package views;

import controllers.LoginMenuController;
import models.Result;

public class LoginMenu extends View {
    private final LoginMenuController controller;

    public LoginMenu(LoginMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {
        while (controller.getRepo().getCurrentView().equals(models.enums.commands.View.LOGIN_MENU)) {
            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success() && result.message().contains("logged in")) {
                controller.getRepo().setCurrentMenu(models.enums.commands.View.MAIN_MENU);
            }
        }
    }
}
