package models.character;

import models.Ability;
import models.Inventory;
import models.Position;
import models.relations.RelationService;
import models.tool.Tool;

import java.util.List;

public class Player extends Character {
    private Position position;
    private int numOfCoins;
    private int numOfPlayedGames;
    private int highestEarnedBalance;
    private Inventory inventory;
    private double energy;
    private Ability ability;
    private List<Tool> tools;
    private RelationService relationService;
}
