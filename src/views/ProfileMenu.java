package views;

import controllers.ProfileMenuController;

public class ProfileMenu extends AppMenu {
    private final ProfileMenuController controller;

    public ProfileMenu(ProfileMenuController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

    }
}
