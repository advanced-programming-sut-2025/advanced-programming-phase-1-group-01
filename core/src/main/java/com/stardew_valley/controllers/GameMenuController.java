package com.stardew_valley.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Timer;
import com.stardew_valley.Main;
import com.stardew_valley.Main;
import com.stardew_valley.models.Game;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.building.Farm;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.commands.GameMenuCommands;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.models.initializer.VillageInitializer;
import com.stardew_valley.views.GameMenuView;
import com.stardew_valley.views.GameView;
import com.stardew_valley.views.MainMenuView;
import com.stardew_valley.views.SignUpMenuView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.stardew_valley.models.Game.PLAYERS_STARTING_POSITION;

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

    public void startGame(Label messageLabel, List<TextField> players) {
        Set<String> playerSet = new HashSet();
        playerSet.add(repo.getCurrentUser().getUsername());

        List<Player> playerList = null;
        for (TextField textField : players) {
            User user = repo.getUserByUsername(textField.getText());
            if (user == null) {
                messageLabel.setText("Invalid username: " + textField.getText());
                return;
            }

            if (!playerSet.add(textField.getText())) {
                messageLabel.setText("Duplicate username: " + textField.getText());
                return;
            }

            if (user.getGame() != null) {
                messageLabel.setText("Game already in another game: " + textField.getText());
                return;
            }

            playerList = new ArrayList<>();
            playerList.add(repo.getCurrentUser().getPlayer());

            for (String username : playerSet) {
                playerList.add(repo.getUserByUsername(username).getPlayer());
            }
        }

        //phony
        Game game = new Game(playerList);
        repo.addGame(game);
        repo.setCurrentGame(game);
        repo.getCurrentGame().setNpcVillage(VillageInitializer.initializeVillage(playerList));
        repo.getCurrentUser().getPlayer().setPosition(PLAYERS_STARTING_POSITION);

        Farm farm = FarmInitializer.initializeFarm();

        for (Player player : playerList) {
            player.setFarm(farm);
            player.setCurrentMap(farm);
        }
        game.getForagingManager().prepareNewDayForaging();

        Main.getMain().setScreen(new GameView(new GameController(repo)));
    }

    public void nextTurn(Label messageLabel) {
        messageLabel.setText("Sik baba!");
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
