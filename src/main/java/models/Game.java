package models;

import java.util.List;

import models.animal.Animal;
import models.building.Maps;
import models.character.NPC.NPC;
import models.character.NPC.NPCVillage;
import models.character.player.Player;
import models.dateTime.TimeManager;
import models.farming.FarmingManager;
import models.foraging.ForagingManager;
import models.initializer.FarmInitializer;
import models.initializer.VillageInitializer;
import models.shop.*;
import models.weather.WeatherManager;

public class Game {
    private final static Position PLAYERS_STARTING_POSITION = new Position(67, 6);
    private Player currentPlayer;
    private final List<Player> players;
    private final TimeManager timeManager;
    private final WeatherManager weatherManager;
    private final FarmingManager farmingManager;
    private final ForagingManager foragingManager;
    private NPCVillage npcVillage;
    private Maps currentMap;
    private int currentIndex = 1;

    private final Blacksmith blacksmith = new Blacksmith();
    private final JojaMart jojaMart = new JojaMart();
    private final PierreGeneralStore pierreGeneralStore = new PierreGeneralStore();
    private final CarpenterShop carpenterShop = new CarpenterShop();
    private final FishShop fishShop = new FishShop();
    private final MarnieRanch marnieRanch = new MarnieRanch();
    private final TheStardropSaloon theStarDropSaloon = new TheStardropSaloon();

    public Game(List<Player> players) {
        this.players = players;
        currentPlayer = players.get(0);
        currentMap = currentPlayer.getFarm();
        timeManager = new TimeManager(this);
        weatherManager = new WeatherManager(this);
        farmingManager = new FarmingManager(this);
        foragingManager = new ForagingManager(this);
    }


    public List<Player> players() {
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

    public NPCVillage getNPCVillage() {
        return npcVillage;
    }

    public void setNPCVillage(NPCVillage npcVillage) {
        this.npcVillage = npcVillage;
    }

    private void setNpcVillage(NPCVillage npcVillage) {
        this.npcVillage = npcVillage;
    }

    public Maps getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Maps currentMap) {
        this.currentMap = currentMap;
    }

    private void initializeGame(List<Player> players) {
        for (Player player : players) {
            player.setFarm(FarmInitializer.initializeFarm(3, 5));
            player.setPosition(PLAYERS_STARTING_POSITION);
        }
        setNpcVillage(VillageInitializer.initializeVillage(players));
    }

    private Maps getMapById(int id) {
        return switch (id) {
            case 0 -> players.get(0).getFarm();
            case 1 -> players.get(1).getFarm();
            case 2 -> players.get(2).getFarm();
            case 3 -> players.get(3).getFarm();
            default -> npcVillage;
        };
    }

    public String enterOthersRoom(int id) {
        Maps map = getMapById(id);
        if (map != null) {
            setCurrentMap(getMapById(id));
            currentPlayer.setCurrentMap(getMapById(id));
            currentPlayer.setPosition(new Position(PLAYERS_STARTING_POSITION.x() + 1, PLAYERS_STARTING_POSITION.y()));
            return "You are now there";
        } else return "invalid map ID";
    }

    public boolean isAnyoneHere(int x, int y) {
        for (Player player : players) {
            if (player.getPosition().x() == x && player.getPosition().y() == y) {
                for (Animal animal : player.getFarm().getAnimals()) {
                    if (animal.getPosition().x() == x && animal.getPosition().y() == y) {
                        return true;
                    }
                }
                return true;
            }
        }
        for (NPC npc : npcVillage.getNPCs()) {
            if (npc.getPosition().x() == x && npc.getPosition().y() == y) {
                return true;
            }
        }
        return false;
    }

    public String returnSymbol(int x, int y) {
        for (Player player : players) {
            if (player.getPosition().x() == x && player.getPosition().y() == y) {
                for (Animal animal : player.getFarm().getAnimals()) {
                    if (animal.getPosition().x() == x && animal.getPosition().y() == y) {
                        return animal.getAnimalInfo().getSymbol();
                    }
                }
                return player.getGender().getSymbol();
            }
        }
        for (NPC npc : npcVillage.getNPCs()) {
            if (npc.getPosition().x() == x && npc.getPosition().y() == y) {
                return npc.getType().getSymbol();
            }
        }
        return null;
    }

    public void nextTurn() {
        currentPlayer = players.get(currentIndex);
        currentIndex = (currentIndex + 1) % players.size();
    }
}