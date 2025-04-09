package controllers;

import models.Result;
import models.data.Repository;

public class GreenhouseController extends Controller {
    public GreenhouseController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result greenhouseBuild() {
        return null;
    }


}
