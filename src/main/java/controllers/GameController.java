package controllers;

import models.Result;
import models.data.Repository;
import models.enums.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController extends Controller {
    private final List<Command> commands = new ArrayList<>();

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

    GameController(Repository repo, DateTimeController dateTimeController, EnergyController energyController, FarmingController farmingController, GameMenuController gameMenuController, LoginMenuController loginMenuController, MainMenuController mainMenuController, ProfileMenuController profileMenuController, RelationshipController relationshipController, ToolController toolController, WeatherController weatherController) {
        super(repo);
        this.dateTimeController = dateTimeController;
        this.energyController = energyController;
        this.farmingController = farmingController;
        this.gameMenuController = gameMenuController;
        this.loginMenuController = loginMenuController;
        this.mainMenuController = mainMenuController;
        this.profileMenuController = profileMenuController;
        this.relationshipController = relationshipController;
        this.toolController = toolController;
        this.weatherController = weatherController;
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
        commands.addAll(Arrays.stream(WeatherCommands.values()).toList());
    }

    @Override
    public Result handleCommand(String commandLine) {
        Command matchedCommand = null;

        for (Command command : commands) {
            if (commandLine.matches(command.getRegex())) {
                matchedCommand = command;
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
        } else if (matchedCommand instanceof WeatherCommands) {
            weatherController.handleCommand(commandLine);
        }

        return null;
    }

    public static class GameControllerBuilder {
        private Repository repo;
        private DateTimeController dateTimeController;
        private EnergyController energyController;
        private FarmingController farmingController;
        private GameMenuController gameMenuController;
        private LoginMenuController loginMenuController;
        private MainMenuController mainMenuController;
        private ProfileMenuController profileMenuController;
        private RelationshipController relationshipController;
        private ToolController toolController;
        private WeatherController weatherController;

        public GameControllerBuilder setRepo(Repository repo) {
            this.repo = repo;
            return this;
        }

        public GameControllerBuilder setDateTimeController(DateTimeController dateTimeController) {
            this.dateTimeController = dateTimeController;
            return this;
        }

        public GameControllerBuilder setEnergyController(EnergyController energyController) {
            this.energyController = energyController;
            return this;
        }

        public GameControllerBuilder setFarmingController(FarmingController farmingController) {
            this.farmingController = farmingController;
            return this;
        }

        public GameControllerBuilder setGameMenuController(GameMenuController gameMenuController) {
            this.gameMenuController = gameMenuController;
            return this;
        }

        public GameControllerBuilder setLoginMenuController(LoginMenuController loginMenuController) {
            this.loginMenuController = loginMenuController;
            return this;
        }

        public GameControllerBuilder setMainMenuController(MainMenuController mainMenuController) {
            this.mainMenuController = mainMenuController;
            return this;
        }

        public GameControllerBuilder setProfileMenuController(ProfileMenuController profileMenuController) {
            this.profileMenuController = profileMenuController;
            return this;
        }

        public GameControllerBuilder setRelationshipController(RelationshipController relationshipController) {
            this.relationshipController = relationshipController;
            return this;
        }

        public GameControllerBuilder setToolController(ToolController toolController) {
            this.toolController = toolController;
            return this;
        }

        public GameControllerBuilder setWeatherController(WeatherController weatherController) {
            this.weatherController = weatherController;
            return this;
        }

        public GameController build() {
            return new GameController(repo, dateTimeController, energyController, farmingController, gameMenuController, loginMenuController, mainMenuController, profileMenuController, relationshipController, toolController, weatherController);
        }
    }
}