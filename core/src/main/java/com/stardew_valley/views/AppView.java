package com.stardew_valley.views;

import com.stardew_valley.controllers.*;
import com.stardew_valley.models.data.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppView {
    private final Map<com.stardew_valley.models.enums.commands.View, View> views;
    private final Repository repo;

    public AppView() {
        this.views = new HashMap<>();
        this.repo = new Repository();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void run() {
        initViews();
        while (true) {
            View view = views.get(repo.getCurrentView());
            if (view != null) {
                view.handleInput();
            }
        }
    }

    private void initViews() {
        views.put(com.stardew_valley.models.enums.commands.View.LOGIN_MENU, new LoginMenuView(new LoginMenuController(repo), this));
        views.put(com.stardew_valley.models.enums.commands.View.MAIN_MENU, new MainMenuView(new MainMenuController(repo), this));
        views.put(com.stardew_valley.models.enums.commands.View.PROFILE_MENU, new ProfileMenuView(new ProfileMenuController(repo), this));
        views.put(com.stardew_valley.models.enums.commands.View.GAME_MENU, new GameMenuView(new GameMenuController(repo), this));
        views.put(com.stardew_valley.models.enums.commands.View.GAME, new GameView(new GameController(repo), this));
    }
}
