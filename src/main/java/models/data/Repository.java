package models.data;

import models.Game;
import models.enums.commands.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private List<Game> games;
    private User currentUser;
    private Menu currentMenu;
    private Game currentGame;
    private static Map<String, User> users;
    private FileManager fileManager;

    public Repository() {
        games = new ArrayList<>();
        users = new HashMap<>();
        currentMenu = Menu.LOGIN;
        fileManager = new FileManager();
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

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
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


    //@
    public void nextTurn() {
        return;
    }
}