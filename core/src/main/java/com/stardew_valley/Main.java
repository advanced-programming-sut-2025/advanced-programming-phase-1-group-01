package com.stardew_valley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stardew_valley.controllers.*;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.building.Farm;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.SecurityQuestion;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.models.initializer.VillageInitializer;
import com.stardew_valley.views.*;

import java.util.ArrayList;
import java.util.List;

import static com.stardew_valley.models.Game.PLAYERS_STARTING_POSITION;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;

    public static Main getMain() {
        if (main == null) {
            main = new Main();
        }
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        main = this;
        user();
        setScreen(new GameView(new GameController(Repository.getRepo())));
        //setScreen(new SignUpMenuView(new SignUpMenuController(Repository.getRepo())));
        //setScreen(new GameMenuView(new GameMenuController(Repository.getRepo())));
        //setScreen(new TempLogin(new Repository()));
        //setScreen(new CookingView(new CookingController(Repository.getRepo())));
        //setScreen(new CraftingView(new CraftingController(Repository.getRepo())));
        //setScreen(new GameView(new GameController(Repository.getRepo())));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void user() {
        if (Repository.getRepo().getUserByUsername("1") == null) {
            String path = "avatars/avatar1.png";
            User user = new User("1", "a", "a", "a", Gender.MALE, SecurityQuestion.QUESTION1, "a", path);

            Repository.getRepo().addUser(user);
            Repository.getRepo().setCurrentUser(user);
        }

        if (Repository.getRepo().getUserByUsername("2") == null) {
            String path = "avatars/avatar1.png";
            User user = new User("2", "a", "a", "a", Gender.MALE, SecurityQuestion.QUESTION1, "a", path);

            Repository.getRepo().addUser(user);
        }

        if (Repository.getRepo().getUserByUsername("3") == null) {
            String path = "avatars/avatar1.png";
            User user = new User("3", "a", "a", "a", Gender.MALE, SecurityQuestion.QUESTION1, "a", path);

            Repository.getRepo().addUser(user);
        }

        if (Repository.getRepo().getUserByUsername("4") == null) {
            String path = "avatars/avatar1.png";
            User user = new User("4", "a", "a", "a", Gender.MALE, SecurityQuestion.QUESTION1, "a", path);

            Repository.getRepo().addUser(user);
        }

        List<Player> playerList = new ArrayList<>();
        playerList.add(Repository.getRepo().getUserByUsername("1").getPlayer());
        playerList.add(Repository.getRepo().getUserByUsername("2").getPlayer());
        playerList.add(Repository.getRepo().getUserByUsername("3").getPlayer());
        playerList.add(Repository.getRepo().getUserByUsername("4").getPlayer());

        com.stardew_valley.models.Game game = new com.stardew_valley.models.Game(playerList);
        Repository.getRepo().addGame(game);
        Repository.getRepo().setCurrentGame(game);
        Repository.getRepo().getCurrentGame().setNpcVillage(VillageInitializer.initializeVillage(playerList));
        Repository.getRepo().getCurrentUser().getPlayer().setPosition(PLAYERS_STARTING_POSITION);

        Farm farm = FarmInitializer.initializeFarm();

        for (Player player : playerList) {
            player.setFarm(farm);
            player.setCurrentMap(farm);
            if (player.getUser().getUsername().equals("4"))
                player.getGame().getTimeManager().prepareForNewDay();
        }
    }
}
