package controllers;

import models.Result;
import models.data.Repository;
import models.enums.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController extends Controller {
    private final List<Command> commands;

    private final DateTimeController dateTimeController;
    private final EnergyController energyController;
    private final FarmingController farmingController;
    private final GameMenuController gameMenuController;
    private final LoginMenuController loginMenuController;
    private final MainMenuController mainMenuController;
    private final ProfileMenuController profileMenuController;
    private final RelationshipController relationshipController;
    private final ToolController toolController;
    private final WeatherController weatherController;

    public GameController(Repository repo) {
        super(repo);
        this.dateTimeController = new DateTimeController(repo);
        this.energyController = new EnergyController(repo);
        this.farmingController = new FarmingController(repo);
        this.gameMenuController = new GameMenuController(repo);
        this.loginMenuController = new LoginMenuController(repo);
        this.mainMenuController = new MainMenuController(repo);
        this.profileMenuController = new ProfileMenuController(repo);
        this.relationshipController = new RelationshipController(repo);
        this.toolController = new ToolController(repo);
        this.weatherController = new WeatherController(repo);
        commands = new ArrayList<>();
        initCommands();
    }

    private void initCommands() {
        commands.addAll(Arrays.stream(DateTimeCommands.values()).toList());
        commands.addAll(Arrays.stream(EnergyCommands.values()).toList());
        commands.addAll(Arrays.stream(FarmingCommands.values()).toList());
        commands.addAll(Arrays.stream(GameMenuCommands.values()).toList());
        commands.addAll(Arrays.stream(LoginMenuCommands.values()).toList());
        commands.addAll(Arrays.stream(MainMenuCommands.values()).toList());
        commands.addAll(Arrays.stream(ProfileMenuCommands.values()).toList());
        commands.addAll(Arrays.stream(RelationshipCommands.values()).toList());
        commands.addAll(Arrays.stream(ToolCommands.values()).toList());
        commands.addAll(Arrays.stream(WeatherCommands.values()).toList());
    }

    @Override
    public Result handleCommand(String commandLine) {
        Command matchedCommand = null;

        for (Command command : commands) {
            if (commandLine.matches(command.getRegex())) {
                matchedCommand = command;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        if (matchedCommand instanceof DateTimeCommands) {
            dateTimeController.handleCommand(commandLine);
        } else if (matchedCommand instanceof EnergyCommands) {
            energyController.handleCommand(commandLine);
        } else if (matchedCommand instanceof FarmingCommands) {
            farmingController.handleCommand(commandLine);
        } else if (matchedCommand instanceof GameMenuCommands) {
            gameMenuController.handleCommand(commandLine);
        } else if (matchedCommand instanceof LoginMenuCommands) {
            loginMenuController.handleCommand(commandLine);
        } else if (matchedCommand instanceof MainMenuCommands) {
            mainMenuController.handleCommand(commandLine);
        } else if (matchedCommand instanceof ProfileMenuCommands) {
            profileMenuController.handleCommand(commandLine);
        } else if (matchedCommand instanceof RelationshipCommands) {
            relationshipController.handleCommand(commandLine);
        } else if (matchedCommand instanceof ToolCommands) {
            toolController.handleCommand(commandLine);
        } else if (matchedCommand instanceof WeatherCommands) {
            weatherController.handleCommand(commandLine);
        }

        return null;
    }
}