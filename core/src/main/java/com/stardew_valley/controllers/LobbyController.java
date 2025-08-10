package com.stardew_valley.controllers;

import com.stardew_valley.models.LobbyData;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.network.GameClient;
import com.stardew_valley.network.Network;

import java.util.*;
import java.util.stream.Collectors;

public class LobbyController {

    private List<LobbyData> lobbies;
    private static LobbyController instance;
    private Repository repo;

    private LobbyController() {
    }

    public static synchronized LobbyController getInstance() {
        if (instance == null) {
            instance = new LobbyController();
        }
        return instance;
    }

    public synchronized void init(Repository repo) {
        if (this.repo == null) {
            this.repo = repo;
        }
    }

    public Repository getRepository() {
        return repo;
    }


    public List<LobbyData> getLobbies() {
        GameClient.getInstance().requestLobbyList();
        return lobbies;
    }

    public Result joinLobby(int id, String password) {
        LobbyData lobby = findLobbyById(id);
        if (lobby == null) {
            return new Result(false, "Lobby not found.");
        }
        if (lobby.isFull()) {
            return new Result(false, "Lobby is full.");
        }
        if (lobby.isPrivate() && !lobby.checkPassword(password)) {
            return new Result(false, "Wrong password.");
        }
        User currentPlayer = getCurrentUser();
        if (lobby.addUser(currentPlayer)) {
            GameClient.getInstance().joinLobby(id, password);
            return new Result(true, "Joined lobby successfully.");
        } else {
            return new Result(false, "You are already in the lobby.");
        }
    }

    public List<LobbyData> loadRecentLobbies() {
        return lobbies.stream()
            .filter(LobbyData::isVisible)
            .sorted(Comparator.comparingLong(LobbyData::getCreatedTime).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    public Result createLobby(String name, boolean isPrivate, String password, boolean isVisible) {
        int id = generateUniqueId();
        User admin = getCurrentUser();
        System.out.println(admin.getUsername());

        LobbyData newLobby = new LobbyData(name, isPrivate, isVisible, password, id, admin);
        lobbies.add(newLobby);
        newLobby.addUser(admin);
        GameClient client = GameClient.getInstance();

        client.createLobby(name, isPrivate, password, isVisible);

        return new Result(true, "Lobby created successfully with ID: " + id);
    }

    private int generateUniqueId() {
        Random random = new Random();
        int id;
        do {
            id = 100000 + random.nextInt(900000);
        } while (findLobbyById(id) != null);
        return id;
    }

    public LobbyData findLobbyById(int id) {

        for (LobbyData lobby : lobbies) {
            System.out.println(lobby.getId() + ": " + lobby.getName());
        }

        System.out.println(lobbies.size());

        System.out.println("Lobby " + id + " found.");
        return lobbies.stream()
            .filter(l -> l.getId() == id)
            .findFirst()
            .orElse(null);
    }

    private User getCurrentUser() {
        return repo.getCurrentUser();
    }


    public void updateLobbyListFromNetwork(Network.LobbyInfo[] lobbyInfos) {
        List<LobbyData> lobbyDataList = new ArrayList<>();

        for (Network.LobbyInfo info : lobbyInfos) {
            List<User> users = new ArrayList<>();
            for (String playerJson : info.playerNames) {
                users.add(Repository.fromUserInfoJson(playerJson));
            }

            User admin = users.isEmpty() ? null : users.get(0);

            LobbyData lobby = new LobbyData(
                info.name,
                info.isPrivate,
                info.isVisible,
                "",
                info.id,
                admin
            );

            for (int i = 1; i < users.size(); i++) {
                lobby.addUser(users.get(i));
            }

            lobbyDataList.add(lobby);
        }
        setLobbies(lobbyDataList);
    }

    private void setLobbies(List<LobbyData> lobbyDataList) {
        this.lobbies = lobbyDataList;
    }

}
