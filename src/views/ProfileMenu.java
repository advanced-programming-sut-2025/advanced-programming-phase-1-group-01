package views;

import controllers.ProfileController;
import models.Result;
import models.enums.commands.Menu;

public class ProfileMenu extends AppMenu {
    private final ProfileController controller;

    public ProfileMenu(ProfileController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

        while (controller.getRepo().getCurrentMenu().equals(Menu.PROFILE)) {

            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success() && result.message().equals("main")) {
                controller.getRepo().setCurrentMenu(Menu.MAIN);
            }
        }
    }
}
