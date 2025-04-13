package controllers;

import models.Result;
import models.data.Repository;

public class MapController extends Controller {
    MapController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result printMap() {
        return null;
    }

    private Result helpReadingMap() {
        return null;
    }
}
