package views;

import controllers.GameMenuController;
import models.Result;
import models.enums.commands.Menu;

public class GameMenu extends AppMenu {
    private final GameMenuController controller;

    public GameMenu(GameMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

        while (controller.getRepo().getCurrentMenu().equals(Menu.GAME)){
            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success() && result.message().contains("")){
                controller.getRepo().setCurrentMenu(Menu.GAME);
            }
        }
    }
}
