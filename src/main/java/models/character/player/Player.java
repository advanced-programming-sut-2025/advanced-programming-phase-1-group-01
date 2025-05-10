package models.character.player;

import models.Game;
import models.MessageEntry;
import models.Position;
import models.building.Farm;
import models.character.Character;
import models.data.User;
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
        return Math.abs(p.getPosition().getX() - position.getX()) <= 1 && Math.abs(p.getPosition().getY() - position.getY()) <= 1;
    }
}