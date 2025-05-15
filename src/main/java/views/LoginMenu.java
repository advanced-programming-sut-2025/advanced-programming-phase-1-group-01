package views;

import controllers.LoginMenuController;
import models.Result;
import models.data.FileManager;

import java.util.Properties;

public class LoginMenu extends View {
    private final LoginMenuController controller;

    public LoginMenu(LoginMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {
        Properties loginData = FileManager.loadLoginInfo();

        if (loginData != null) {
            String username = loginData.getProperty("username");
            controller.getRepo().setCurrentUser(controller.getRepo().getUserByUsername(username));
            FileManager.clearLoginInfo();
            appView.showMessage("Logged in successfully");
            controller.getRepo().setCurrentMenu(models.enums.commands.View.MAIN_MENU);
        }

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
