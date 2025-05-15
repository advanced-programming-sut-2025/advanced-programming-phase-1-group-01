package controllers;

import models.Result;
import models.data.Repository;

public class AnimalController extends Controller {
    AnimalController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }
}
