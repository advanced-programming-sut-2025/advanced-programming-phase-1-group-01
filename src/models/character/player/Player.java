package models.character.player;

import models.Position;
import models.character.Character;
import models.relations.RelationService;
import models.tool.Tool;

import java.util.List;

public class Player extends Character {
    private Position position;
    private int numOfCoins;
    private int numOfPlayedGames;
    private int highestEarnedBalance;
    private Inventory inventory;
    private Energy energy;
    private AbilityService abilityService;
    private List<Tool> tools;
    private RelationService relationService;
}
