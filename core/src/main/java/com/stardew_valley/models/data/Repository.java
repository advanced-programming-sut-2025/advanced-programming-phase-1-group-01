package com.stardew_valley.models.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.Game;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.commands.View;
import com.stardew_valley.network.GameClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private static Repository repo;

    private List<Game> games;
    private User currentUser;
    private View currentView;
    private Game currentGame;
    private static Map<String, User> users;
    private float otherX = 0.0f;
    private float otherY = 0.0f;

    public Repository() {
        games = new ArrayList<>();
        users = new HashMap<>();
        currentView = View.LOGIN_MENU;
    }

    public static Repository getRepo() {
        if (repo == null) repo = new Repository();
        return repo;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public void addUser(User user) {
        GameClient.getInstance().sendAddRequest(user);
        users.put(user.getUsername(), user);
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentMenu(View currentView) {
        this.currentView = currentView;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public float getOtherX() {
        return otherX;
    }

    public float getOtherY() {
        return otherY;
    }

    public void setOtherX(float otherX) {
        this.otherX = otherX;
    }

    public void setOtherY(float otherY) {
        this.otherY = otherY;
    }


    public String toUserInfoJson() {
        User user = getCurrentUser();

        if (user == null) return "{}";

        JsonObject json = new JsonObject();
        json.addProperty("username", user.getUsername());
        json.addProperty("password", user.getPassword());
        json.addProperty("nickname", user.getNickname());
        json.addProperty("email", user.getEmail());
        json.addProperty("gender", user.getGender() != null ? user.getGender().toString() : "");

        return new Gson().toJson(json);
    }

    public static User fromUserInfoJson(String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

        String username = json.has("username") ? json.get("username").getAsString() : "";
        String nickname = json.has("nickname") ? json.get("nickname").getAsString() : "";
        String email = json.has("email") ? json.get("email").getAsString() : "";
        String genderStr = json.has("gender") ? json.get("gender").getAsString() : "";
        String avatarPath = json.has("avatarPath") ? json.get("avatarPath").getAsString() : "";

        Gender gender = null;
        if (!genderStr.isEmpty()) {
            try {
                gender = Gender.valueOf(genderStr);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid gender: " + genderStr);
            }
        }

        return new User(username, "", nickname, email, gender, null, "", avatarPath);
    }


}
