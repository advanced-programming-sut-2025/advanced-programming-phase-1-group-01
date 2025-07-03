package com.stardew_valley.controllers;

import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.Result;

public abstract class Controller {
    protected final Repository repo;

    public Controller(Repository repo) {
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
