package com.stardew_valley.models.data;

import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.Game;
import com.stardew_valley.models.enums.commands.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private static Repository repo;

    private List<Game> games;
    private User currentUser;
    private View currentView;
    private Game currentGame;
    private static Map<String, User> users;

    public Repository() {
        games = new ArrayList<>();
        users = new HashMap<>();
        currentView = View.LOGIN_MENU;
    }

    public static Repository getRepo() {
        if (repo == null) repo = new Repository();
        return repo;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentMenu(View currentView) {
        this.currentView = currentView;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

}
