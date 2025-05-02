package views;

import controllers.LoginMenuController;
import models.data.Repository;
import models.enums.commands.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppView {

    private final Map<Menu,AppMenu> menus;
    private Repository repo;

    public AppView() {
        this.menus = new HashMap<>();
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
        initMenus();
        while (true) {
            AppMenu appMenu = menus.get(repo.getCurrentMenu());
            if (appMenu != null) {
                appMenu.handleInput();
            }
        }
    }

    private void initMenus() {
        menus.put(Menu.LOGIN, new LoginMenu(new LoginMenuController(repo), this));
        menus.put(Menu.MAIN, new MainMenu(new LoginMenuController(repo), this));
        menus.put(Menu.LOGIN, new LoginMenu(new LoginMenuController(repo), this));
        menus.put(Menu.LOGIN, new LoginMenu(new LoginMenuController(repo), this));

    }
}
