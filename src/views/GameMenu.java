package views;

import controllers.GameMenuController;

public class GameMenu extends AppMenu {
    private final GameMenuController controller;

    public GameMenu(GameMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

    }
}
