package controllers;

import models.Result;
import models.data.Repository;
import models.enums.Direction;

public class ToolController extends Controller {
    ToolController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
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

    private Result useTool(Direction dir) {
        return null;
    }

    
}
