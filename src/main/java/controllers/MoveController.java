package controllers;

import models.Position;
import models.Result;
import models.data.Repository;

public class MoveController extends Controller {
    MoveController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    public Result walk() {
        return null;
    }

    private boolean isValidMove(Position pos) {
        return false;
    }

    private double calculateEnergyNeeded(Position pos) {
        return 0;
    } // use this formula: energy needed = distance in tiles / 20

}
