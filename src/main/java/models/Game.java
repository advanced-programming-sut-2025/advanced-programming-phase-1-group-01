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
    private final Map<String, Player> players;
    private final TimeManager timeManager;
    private final WeatherManager weatherManager;
    private final FarmingManager farmingManager;

    public Game(Player player) {
        players = new HashMap<>();
        currentPlayer = player;
        timeManager = new TimeManager(this);
        weatherManager = new WeatherManager(this);
        farmingManager = new FarmingManager(this);
    }

    public void addPlayer(Player player) {
        players.put(player.getUser().getUsername(), player);
    }

    public void removePlayer(String username) {
        players.remove(username);
    }

    public Map<String, Player> players() {
        return players;
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