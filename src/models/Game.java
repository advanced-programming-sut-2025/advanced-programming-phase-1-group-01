package models;

import models.dateTime.DateTime;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<String, User> users;
    private DateTime currentDateTime;

    public Game() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public Map<String, User> getUsers() {
        return users;
    }
}