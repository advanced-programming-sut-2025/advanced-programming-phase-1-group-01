package models.character.player;

import models.Game;
import models.MessageEntry;
import models.Position;
import models.animal.Animal;
import models.animal.AnimalHouse;
import models.animal.AnimalInfo;
import models.building.Farm;
import models.character.Character;
import models.data.User;
import models.enums.Color;
import models.enums.Direction;
import models.enums.Gender;
import models.relations.RelationshipService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Player extends Character {
    private final Game game;
    private final User user;
    private final Gender gender;
    private Position position;
    private Direction direction;
    private Farm farm;
    private int numOfCoins;
    private Inventory inventory;
    private Energy energy;
    private AbilityService abilityService;
    private RelationshipService relationshipService;
    private final Map<MessageEntry, Boolean> notifications;
    private static final int INITIAL_PLAYER_X = 0;
    private static final int INITIAL_PLAYER_Y = 0;
    //@ list unripe
    //@ list ripe and ready to get items

    public Player(Game game, User user) {
        this.game = game;
        this.user = user;
        position = new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y);
        direction = Direction.UP;
        numOfCoins = 0;
        inventory = new Inventory(this);
        energy = new Energy();
        abilityService = new AbilityService();
        relationshipService = new RelationshipService(this);
        gender = user.getGender();
        notifications = new LinkedHashMap<>();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getNumOfCoins() {
        return numOfCoins;
    }

    public void setNumOfCoins(int numOfCoins) {
        this.numOfCoins = numOfCoins;
    }

    public void consumeCoin(int amount) {
        numOfCoins -= amount;
    }

    public void addCoin(int amount) {
        numOfCoins += amount;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public AbilityService getAbilityService() {
        return abilityService;
    }

    public void setAbilityService(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    public RelationshipService getRelationService() {
        return relationshipService;
    }

    public void setRelationService(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Game getGame() {
        return game;
    }

    public User getUser() {
        return user;
    }

    public Gender getGender() {
        return gender;
    }

    public Map<MessageEntry, Boolean> getNotifications() {
        return notifications;
    }

    public void addNotification(Player sender, String message) {
        notifications.put(new MessageEntry(sender, message), false);
    }

    public void readNotification(MessageEntry notification) {
        notifications.put(notification, true);
    }

    public void readNotifications() {
        for (MessageEntry key : notifications.keySet()) {
            if (!notifications.get(key)) {
                readNotification(key);
            }
        }
    }

    public boolean isNearTo(Player p) {
        return Math.abs(p.getPosition().x() - position.x()) <= 1 && Math.abs(p.getPosition().y() - position.y()) <= 1;
    }

    public boolean isNearTo(Position position) {
        return Math.abs(this.position.x() - position.x()) <= 1 && Math.abs(this.position.y() - position.y()) <= 1;
    }


    public boolean isNearToSellBucket() {
        List<Position> neighbors = new ArrayList<>();
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

        for (int[] dir : directions) {
            int nx = position.x() + dir[0];
            int ny = position.y() + dir[1];
            Position neighbor = new Position(nx, ny);
            neighbors.add(neighbor);
        }

        for (Position p : neighbors) {
            if (farm.getTiles().get(p.x()).get(p.y()).getObject() == null) return true;
        }
        return false;
    }

    private AnimalInfo stringToAnimalInfo(String string) {
        return switch (string) {
            case "cow" -> AnimalInfo.COW;
            case "dinosaur" -> AnimalInfo.DINOSAUR;
            case "duck" -> AnimalInfo.DUCK;
            case "goat" -> AnimalInfo.GOAT;
            case "hen" -> AnimalInfo.HEN;
            case "pig" -> AnimalInfo.PIG;
            case "rabbit" -> AnimalInfo.RABBIT;
            case "sheep" -> AnimalInfo.SHEEP;
            default -> null;
        };
    }

    public String buyAnimal(String animalType, String animalName) {
        AnimalInfo animalInfo = stringToAnimalInfo(animalType);
        if (animalInfo == null) return "Invalid animal type";
        if (!farm.isAnimalNameUnique(animalName)) return "Animal name is not unique";
        AnimalHouse shelter = farm.findEmptyShelter(animalInfo);
        if (shelter == null) return "No empty shelter";
        //@ check if player has no enough money
        farm.addAnimalToShelter(new Animal(animalInfo, animalName, this, shelter));
        return "Successfully built the shelter";
    }

    public String petAnimal(String animalName) {
        Animal animal = farm.fineAnimalByName(animalName);
        if (animal == null) return "Animal not found";
        if (!isNearTo(animal.getPosition())) return "Animal is not near to you found";
        animal.petting();
        return "Successfully petted the animal";
    }

    public String getFormattedAnimals() {
        StringBuilder animals = new StringBuilder();
        int maxLength = farm.getAnimals().stream()
                .mapToInt(animal -> animal.getAnimalName().length())
                .max()
                .orElse(0);

        animals.append("Animal Type, Animal Name, Has Been Petted, Is Hungry, Friendship Level:\n");
        for (Animal animal : farm.getAnimals()) {
            String formattedAnimalType = Color.YELLOW_BOLD.colorize(String.format("%-" + 8 + "s", animal.getAnimalInfo()
                    .name().toLowerCase()));
            animals.append(formattedAnimalType);
            String formattedAnimalName = Color.GREEN.colorize(String.format("%-" + maxLength + "s", animal.getAnimalName()));
            animals.append(formattedAnimalName);
            animals.append(animal.hasBeenPetted());
            animals.append(animal.isHungry());
            animals.append(animal.getFriendshipLevel());
            animals.append("\n");
        }
        return animals.toString();
    }

    //@ get artisan by name, error

    //@ get Item

    //@ check if are not close
    //@ add to unripe list

    //@ conditions and get
}