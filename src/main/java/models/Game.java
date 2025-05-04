package models;

import java.util.HashMap;
import java.util.Map;

import models.character.player.Player;
import models.data.User;
import models.dateTime.TimeManager;
import models.farming.FarmingManager;
import models.weather.WeatherManager;

public class Game {
    private Player currentPlayer;
    private final Map<String, User> users;
    private final TimeManager timeManager;
    private final WeatherManager weatherManager;
    private final FarmingManager farmingManager;

    public Game() {
        users = new HashMap<>();
        timeManager = new TimeManager(this);
        weatherManager = new WeatherManager(this);
        farmingManager = new FarmingManager(this);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public Map<String, User> users() {
        return users;
    }

    public WeatherManager getWeatherManager() {
        return weatherManager;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public FarmingManager getFarmingManager() {
        return farmingManager;
    }
}