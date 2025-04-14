package controllers;

import models.Result;
import models.data.Repository;

public class EnergyController extends Controller {
    EnergyController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result showEnergy() {
        return null;
    }

    private Result cheatEnergySet() {
        return null;
    }

    private Result cheatEnergyUnlimited() {
        return null;
    }

}
