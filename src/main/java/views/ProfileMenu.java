package views;

import controllers.ProfileMenuController;
import models.Result;

public class ProfileMenu extends View {
    private final ProfileMenuController controller;

    public ProfileMenu(ProfileMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

        while (controller.getRepo().getCurrentView().equals(models.enums.commands.View.PROFILE_MENU)) {

            String input = appView.readLine();
            Result result = controller.handleCommand(input);
            appView.showMessage(result.message());

            if (result.success() && result.message().contains("main")) {
                controller.getRepo().setCurrentMenu(models.enums.commands.View.MAIN_MENU);
            }
        }
    }
}
