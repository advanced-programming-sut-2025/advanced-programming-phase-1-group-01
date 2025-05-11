package models;

import java.util.HashMap;
import java.util.Map;

import models.character.player.Player;
import models.dateTime.TimeManager;
import models.farming.FarmingManager;
import models.foraging.ForagingManager;
import models.shop.*;
import models.weather.WeatherManager;

public class Game {
    private Player currentPlayer;
    private final Map<String, Player> players;
    private final TimeManager timeManager;
    private final WeatherManager weatherManager;
    private final FarmingManager farmingManager;
    private final ForagingManager foragingManager;

    private final Blacksmith blacksmith = new Blacksmith();
    private final JojaMart jojaMart = new JojaMart();
    private final PierreGeneralStore pierreGeneralStore = new PierreGeneralStore();
    private final CarpenterShop carpenterShop = new CarpenterShop();
    private final FishShop fishShop = new FishShop();
    private final MarnieRanch marnieRanch = new MarnieRanch();
    private final TheStardropSaloon theStarDropSaloon = new TheStardropSaloon();

    public Game(Player player) {
        players = new HashMap<>();
        currentPlayer = player;
        timeManager = new TimeManager(this);
        weatherManager = new WeatherManager(this);
        farmingManager = new FarmingManager(this);
        foragingManager = new ForagingManager(this);
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

    public Blacksmith getBlacksmith() {
        return blacksmith;
    }

    public JojaMart getJojaMart() {
        return jojaMart;
    }

    public PierreGeneralStore getPierreGeneralStore() {
        return pierreGeneralStore;
    }

    public CarpenterShop getCarpenterShop() {
        return carpenterShop;
    }

    public FishShop getFishShop() {
        return fishShop;
    }

    public MarnieRanch getMarnieRanch() {
        return marnieRanch;
    }

    public TheStardropSaloon getTheStardropSaloon() {
        return theStarDropSaloon;
    }

    public ForagingManager getForagingManager() {
        return foragingManager;
    }
}