package com.stardew_valley.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Timer;
import com.stardew_valley.Main;
import com.stardew_valley.models.Result;
//import com.stardew_valley.models.character.player.User;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.commands.GameMenuCommands;
import com.stardew_valley.network.GameClient;
import com.stardew_valley.network.Network;
import com.stardew_valley.views.LobbyView;
import com.stardew_valley.views.MainMenuView;

import java.io.IOException;
import java.util.List;

public class GameMenuController extends Controller {


    public GameMenuController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        GameMenuCommands matchedCommand = null;

        for (GameMenuCommands cmd : GameMenuCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command!");
        }

//        switch (matchedCommand) {
//            case MENU_ENTER:
//                return new Result(false, "You cannot navigate to other menus from here");
//
//            case MENU_EXIT:
//                return new Result(true, "now you are in main menu");
//
//            case SHOW_CURRENT_MENU:
//                return new Result(false, "now you are in game menu");
//
//            case GAME_NEW:
//                return handleGameNewCommand(command);
//
//            case GAME_MAP:
//                String mapNumberStr = command.split("\\s+")[2];
//                int mapNumber = Integer.parseInt(mapNumberStr);
//                return chooseGameMap(mapNumber);
//            case NEXT_TURN:
//                return handleNextTurn();
//        }
        return null;
    }

    private Result loadGame() {
        return null;
    }

    private Result exitGame() {
        return null;
    }

    private Result deleteGame() {
        return null;
    }

    public void startGame(Label messageLabel, List<TextField> players) throws IOException {
        GameClient client = GameClient.getInstance();

        try {
            client.connect("127.0.0.1");
        } catch (IOException e) {
            System.out.println("Could not connect to server!");
        }

        LobbyController.getInstance().init(repo);
        Main.getMain().setScreen(new LobbyView(LobbyController.getInstance()));
    }



    public void nextTurn(Label messageLabel) {

    }

    public void back(Label messageLabel) {
        messageLabel.setText("Loading SignUp Menu...");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
            }
        }, 2);
    }
}
