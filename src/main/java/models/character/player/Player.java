package models.character.player;

import models.Game;
import models.MessageEntry;
import models.Position;
import models.Size;
import models.building.Building;
import models.building.Farm;
import models.character.Character;
import models.cooking.CookingRecipe;
import models.cooking.CookingRecipes;
import models.crafting.CraftingRecipe;
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
    private Refrigerator refrigerator;
    private Energy energy;
    private AbilityService abilityService;
    private RelationshipService relationshipService;
    private final Map<MessageEntry, Boolean> notifications;
    private static final int INITIAL_PLAYER_X = 0;
    private static final int INITIAL_PLAYER_Y = 0;
    private final List<CraftingRecipe> craftingRecipes;
    private final List<CookingRecipe> cookingRecipes;
    private Farm partnerFarm ;

    public Player(Game game, User user) {
        this.game = game;
        this.user = user;
        position = new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y);
        direction = Direction.UP;
        numOfCoins = 0;
        inventory = new Inventory(this);
        refrigerator = new Refrigerator(this);
        energy = new Energy();
        abilityService = new AbilityService();
        relationshipService = new RelationshipService(this);
        gender = user.getGender();
        notifications = new LinkedHashMap<>();
        craftingRecipes = new ArrayList<>();
        cookingRecipes = new ArrayList<>();
        initializeCookingRecipes();
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
        if (relationshipService.getMarriage() != null) {
            relationshipService.getMarriage().getPartner(this).setNumOfCoins(numOfCoins);
        }
        this.numOfCoins = numOfCoins;
    }

    public void consume(int amount) {
        if (relationshipService.getMarriage() != null) {
            relationshipService.getMarriage().getPartner(this).consume(amount);
        }
        numOfCoins -= amount;
    }

    public void increase(int amount) {
        if (relationshipService.getMarriage() != null) {
            relationshipService.getMarriage().getPartner(this).increase(amount);
        }
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

    public boolean isPlayerNearBuilding(Building building) {
        Position buildingPos = building.getPosition();
        Size size = building.getSize();

        int x1 = buildingPos.x() - 1;
        int y1 = buildingPos.y() - 1;
        int x2 = buildingPos.x() + size.getWidth();
        int y2 = buildingPos.y() + size.getHeight();

        return this.position.x() >= x1 && this.position.x() <= x2 &&
                this.position.y() >= y1 && this.position.y() <= y2;
    }

    public List<CraftingRecipe> getCraftingRecipes() {
        return craftingRecipes;
    }

    public void addCraftingRecipe(CraftingRecipe recipe) {
        craftingRecipes.add(recipe);
    }

    public List<CookingRecipe> getCookingRecipes() {
        return cookingRecipes;
    }

    public void initializeCookingRecipes() {
        addCookingRecipe(CookingRecipes.FRIED_EGG.toRecipe());
        addCookingRecipe(CookingRecipes.BAKED_FISH.toRecipe());
        addCookingRecipe(CookingRecipes.SALAD.toRecipe());
    }

    public void addCookingRecipe(CookingRecipe recipe) {
        cookingRecipes.add(recipe);
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public void setLastHugDate(int lastHugDate) {
        relationshipService.setLastHugDate(lastHugDate);
    }

    public int getLastHugDate() {
        return relationshipService.getLastHugDate();
    }

    public void setPartnerFarm(Farm farm) {
        this.partnerFarm = farm;
    }

    public void updateOfMarriage(Player player) {
        numOfCoins += player.getNumOfCoins();
        player.setNumOfCoins(numOfCoins);
        setPartnerFarm(player.getFarm());
    }
}