package models.data;

import models.Game;
import models.User;
import models.character.player.Player;
import models.enums.commands.Menu;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    private User currentUser;
    private Player currentPlayer;
    private Menu currentMenu;
    private Game currentGame;
    private static Map<String, User> users;
    private FileManager fileManager;

    public Repository() {
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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
}