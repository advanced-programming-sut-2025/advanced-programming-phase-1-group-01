package controllers;

import models.Result;
import models.data.Repository;

public class CookingController extends Controller {
    CookingController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }
}
