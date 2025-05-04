package controllers;

import models.Result;
import models.data.Repository;

public class CraftingController extends Controller {
    CraftingController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }
}
