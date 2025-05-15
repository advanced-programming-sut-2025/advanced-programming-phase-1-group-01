package views;

import controllers.GameController;

public class GameView extends View {
    private GameController controller;

    public GameView(GameController controller, AppView appView) {
        this.controller = controller;
        this.appView = appView;
    }

    @Override
    public void handleInput() {

    }
}
