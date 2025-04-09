package views;

import controllers.LoginMenuController;

public class LoginMenu extends AppMenu {
    private final LoginMenuController controller;

    public LoginMenu(LoginMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

    }
}
