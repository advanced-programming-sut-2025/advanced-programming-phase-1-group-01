package views;

import controllers.GameMenuController;
import controllers.LoginMenuController;
import controllers.MainMenuController;
import controllers.ProfileMenuController;
import models.data.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppView {

    private final Map<models.enums.commands.View, View> views;
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
        views.put(models.enums.commands.View.LOGIN_MENU, new LoginMenu(new LoginMenuController(repo), this));
        views.put(models.enums.commands.View.MAIN_MENU, new MainMenu(new MainMenuController(repo), this));
        views.put(models.enums.commands.View.PROFILE_MENU, new ProfileMenu(new ProfileMenuController(repo), this));
        views.put(models.enums.commands.View.GAME_MENU, new GameMenu(new GameMenuController(repo), this));

    }
}
