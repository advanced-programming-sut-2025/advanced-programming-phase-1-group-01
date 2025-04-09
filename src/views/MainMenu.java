package views;

import controllers.MainMenuController;

public class MainMenu extends AppMenu {
    private final MainMenuController controller;

    public MainMenu(MainMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

    }
}
