package com.stardew_valley.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.stardew_valley.Main;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.data.FileManager;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.views.MainMenuView;
import com.stardew_valley.views.SignUpMenuView;

import java.util.List;


public class LoginMenuController extends Controller {

    public LoginMenuController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }


    public void login(List<String> data, Label messageLabel) {
        String username = data.get(0);
        String password = data.get(1);
        String stayLogin = data.get(2);

        if (username.isEmpty()) {
            messageLabel.setText("please fill username field");
            return;
        }

        User user = repo.getUserByUsername(username);
        if (user == null) {
            messageLabel.setText("User not found");
            return;
        }

        if (password.isEmpty()) {
            messageLabel.setText("please fill password field");
            return;
        }

        if (!user.getPassword().equals(password)) {
            messageLabel.setText("Wrong password");
            return;
        }

        if (stayLogin.equals("Yes")) {
            FileManager.saveToFile(user.getUsername(), user.getPassword(), user.getNickname(),
                user.getEmail(), user.getGender().toString(), user.getSecurityQuestion().toString(), user.getSecurityAnswer(), user.getAvatarPath());
        }

        repo.setCurrentUser(user);

        messageLabel.setText("Login successful!");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
            }
        }, 2);
    }

    public void loadGame(Label messageLabel) {
        User user = FileManager.loadUserFromFile(repo);

        if (user == null) {
            messageLabel.setText("you don't save your account!");
            return;
        }

        repo.setCurrentUser(user);
        messageLabel.setText("You are logged in!");

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
            }
        }, 2);
    }

    public void back(Label messageLabel) {
        messageLabel.setText("Loading SignUp Menu...");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(repo)));
            }
        }, 2);
    }
}
