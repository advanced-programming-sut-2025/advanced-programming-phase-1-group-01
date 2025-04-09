package views;

import controllers.ProfileController;

public class ProfileMenu extends AppMenu {
    private final ProfileController controller;

    public ProfileMenu(ProfileController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

    }
}
