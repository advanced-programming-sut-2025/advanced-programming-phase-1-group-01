package models.character.player;

import models.Game;
import models.Position;
import models.building.Farm;
import models.character.Character;
import models.enums.Direction;
import models.relations.RelationService;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    private final Game game;
    private Position position;
    private Direction direction;
    private Farm farm;
    private int numOfCoins;
    private Inventory inventory;
    private Energy energy;
    private AbilityService abilityService;
    private List<Tool> tools;
    private Tool equippedTool;
    private RelationService relationService;
    private static final int INITIAL_PLAYER_X = 0;
    private static final int INITIAL_PLAYER_Y = 0;

    public Player(Game game) {
        this.game = game;
        position = new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y);
        direction = Direction.UP;
        numOfCoins = 0;
        inventory = new Inventory(this);
        energy = new Energy();
        abilityService = new AbilityService();
        tools = new ArrayList<>();
        equippedTool = null;
        relationService = new RelationService();
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

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public Tool getEquippedTool() {
        return equippedTool;
    }

    public void setEquippedTool(Tool equippedTool) {
        this.equippedTool = equippedTool;
    }

    public RelationService getRelationService() {
        return relationService;
    }

    public void setRelationService(RelationService relationService) {
        this.relationService = relationService;
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
}