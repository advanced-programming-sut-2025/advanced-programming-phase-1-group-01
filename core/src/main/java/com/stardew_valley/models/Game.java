package com.stardew_valley.models;

import java.util.HashMap;
import java.util.List;

import com.stardew_valley.models.animal.Animal;
import com.stardew_valley.models.building.Farm;
import com.stardew_valley.models.building.Maps;
import com.stardew_valley.models.character.NPC.NPC;
import com.stardew_valley.models.character.NPC.NPCVillage;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.TimeManager;
import com.stardew_valley.models.farming.FarmingManager;
import com.stardew_valley.models.foraging.ForagingManager;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.models.initializer.VillageInitializer;
import com.stardew_valley.models.shop.*;
import com.stardew_valley.models.weather.WeatherManager;
import com.stardew_valley.models.shop.DelayedPaymentSystem;

public class Game {
    public final static Position PLAYER1_STARTING_POSITION = new Position(80, 1280);
    public final static Position PLAYER2_FINISHED_POSITION = new Position(2480, 1280);
    public final static Position PLAYER3_FINISHED_POSITION = new Position(1280, 80);
    public final static Position PLAYER4_FINISHED_POSITION = new Position(1280, 2480);
    private Player currentPlayer;
    private final List<Player> players;
    private final HashMap<Player, Position> playerPositions;
    private final TimeManager timeManager;
    private final WeatherManager weatherManager;
    private final FarmingManager farmingManager;
    private final ForagingManager foragingManager;
    private NPCVillage npcVillage;
//    private Maps currentMap;
    private int currentIndex = 0;
    private DelayedPaymentSystem delayedPaymentSystem = new DelayedPaymentSystem();
    private final Farm farm;

    private final Blacksmith blacksmith = new Blacksmith(72,2);
    private final JojaMart jojaMart = new JojaMart(72,4);
    private final PierreGeneralStore pierreGeneralStore = new PierreGeneralStore(72,6);
    private final CarpenterShop carpenterShop = new CarpenterShop(72,8);
    private final FishShop fishShop = new FishShop(72,10);
    private final MarnieRanch marnieRanch = new MarnieRanch(72,12);
    private final TheStardropSaloon theStarDropSaloon = new TheStardropSaloon(72,14);

    public Game(List<Player> players) {
        this.players = players;
        currentPlayer = Repository.getRepo().getCurrentUser().getPlayer();
        System.out.println(currentPlayer.getUser().toString());
        System.out.println(Repository.getRepo().getCurrentUser().toString());
        for (Player player : players) {
            player.setGame(this);
        }
//        currentMap = currentPlayer.getFarm();
        timeManager = new TimeManager(this);
        weatherManager = new WeatherManager(this);
        farmingManager = new FarmingManager(this);
        foragingManager = new ForagingManager(this);
        farm = FarmInitializer.initializeFarm();
        playerPositions = new HashMap<>();
        initializePosition(players.size());
    }


    public List<Player> getPlayers() {
        return players;
    }

    public WeatherManager getWeatherManager() {
        return weatherManager;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public Player getCurrentPlayer() {
//        return Repository.getRepo().getCurrentUser().getPlayer();
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

    public void setNpcVillage(NPCVillage npcVillage) {
        this.npcVillage = npcVillage;
    }

//    public Maps getCurrentMap() {
//        return currentMap;
//    }

//    public void setCurrentMap(Maps currentMap) {
//        this.currentMap = currentMap;
//    }

    private void initializeGame(List<Player> players) {
        for (Player player : players) {
//            player.setFarm(FarmInitializer.initializeFarm(3, 5)); FIXME : Phony
            player.setPosition(PLAYER1_STARTING_POSITION);
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
            currentPlayer.setCurrentMap(getMapById(id));
            currentPlayer.setPosition(new Position(PLAYER1_STARTING_POSITION.x() + 1, PLAYER1_STARTING_POSITION.y()));
            return "You are now there";
        } else return "invalid map ID";
    }

    public boolean isAnyoneHere(int x, int y) {
        for (Player player : players) {
            if (player.getPosition().x() == x && player.getPosition().y() == y) {
                return true;
            }
            for (Animal animal : player.getFarm().getAnimals()) {
                if (animal.getPosition().x() == x && animal.getPosition().y() == y) {
                    return true;
                }
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
            for (Animal animal : player.getFarm().getAnimals()) {
                if (animal.getPosition().x() == x && animal.getPosition().y() == y) {
                    return animal.getAnimalInfo().getSymbol();
                }
            }
            if (player.getPosition().x() == x && player.getPosition().y() == y) {
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
        if (currentPlayer.getEnergy().hasPassedOut()) {
            System.out.println("You passed out from exhaustion! It's the next player's turn now.");
        }
        if (currentIndex == 0) {
            timeManager.getNow().advanceHour();
        }
    }

    public DelayedPaymentSystem getDelayedPaymentSystem() {
        return delayedPaymentSystem;
    }

    public Farm getFarm() {
        return farm;
    }

    private void initializePosition(int numOfPlayers) {

        for (int i = 0; i < numOfPlayers; i++) {
            Player player = players.get(i);
            Position pos;
            switch (i) {
                case 0:
                    pos = PLAYER1_STARTING_POSITION;
                    break;
                case 1:
                    pos = PLAYER2_FINISHED_POSITION;
                    break;
                case 2:
                    pos = PLAYER3_FINISHED_POSITION;
                    break;
                case 3:
                    pos = PLAYER4_FINISHED_POSITION;
                    break;
                default:
                    pos = PLAYER1_STARTING_POSITION;
            }
            playerPositions.put(player, pos);
            player.setPosition(pos);
        }
    }

    public void nextIndex() {
        currentIndex++;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public Player getPlayerByUsername(String username) {
        for (Player player : players) {
            if (player.getUser().getUsername().equals(username)) {
                return player;
            }
        }
        System.out.println("Player not found for username: " + username);
        return null;
    }


}
