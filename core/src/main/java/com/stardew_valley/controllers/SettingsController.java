package com.stardew_valley.controllers;

import com.stardew_valley.models.Result;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;

import java.util.List;

public class SettingsController extends Controller {
    public SettingsController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    public void nextTurn() {
        List<User> users = repo.getUsers().values().stream().toList();
        int currIndex = users.indexOf(repo.getCurrentUser());
        int nextIndex = (currIndex + 1) % users.size();
        repo.setCurrentUser(users.get(nextIndex));
        repo.getCurrentGame().setCurrentPlayer(users.get(nextIndex).getPlayer());
    }
}
