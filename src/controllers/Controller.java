package controllers;

import models.data.Repository;
import models.Result;

public abstract class Controller {
    private final Repository repo;

    Controller(Repository repo) {
        this.repo = repo;
    }

    public Repository getRepo() {
        return repo;
    }

    public abstract Result handleCommand(String commandLine);

    protected Result menuExit() {
        return null;
    }

    private Result showCurrentMenu() {
        return null;
    }
}