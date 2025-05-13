package controllers;

import models.Result;
import models.data.Repository;
import models.enums.Direction;
import models.enums.commands.ToolCommands;

public class ToolController extends Controller {
    ToolController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        ToolCommands matchedCommand = null;

        for (ToolCommands command : ToolCommands.values()) {
            if (commandLine.matches(command.getRegex())) {
                matchedCommand = command;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case TOOLS_EQUIP:
                return equipTool();
            case TOOLS_SHOW_CURRENT:
                return showCurrentTool();
            case TOOLS_SHOW_AVAILABLE:
                return showAvailableTools();
            case TOOLS_UPGRADE:
                return upgradeTool();
            case TOOLS_USE:
                Direction direction = Direction.UP;
                return useTool(direction);
        }
        return new Result(false, "invalid command");
    }

    private Result equipTool() {
        return null;
    }

    private Result showCurrentTool() {
        return null;
    }

    private Result showAvailableTools() {
        return null;
    }

    private Result upgradeTool() {
        return null;
    }

    private Result useTool(Direction direction) {
        return null;
    }
}
