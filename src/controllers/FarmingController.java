package controllers;

import models.Result;
import models.data.Repository;

public class FarmingController extends Controller {
    FarmingController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }
}
