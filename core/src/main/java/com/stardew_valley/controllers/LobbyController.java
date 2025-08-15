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

    private List<LobbyData> lobbies = new ArrayList<>();
    private List<LobbyData> lobbiesForOnlinePlayers = new ArrayList<>();
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
        //GameClient.getInstance().requestLobbyList(false);
        //System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee 5");
        return lobbies;
    }

    public List<LobbyData> getLobbiesForOnlinePlayers() {
        GameClient.getInstance().requestLobbyList(true);
        //System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee 4");
        return lobbiesForOnlinePlayers;
    }

    public void setLobbyListForOnlinePlayers(List<LobbyData> lobbiesForOnlinePlayers) {
        this.lobbiesForOnlinePlayers = lobbiesForOnlinePlayers;
    }

    public Result joinLobby(int id, String password) {
        LobbyData lobby = findLobbyById(id);
        if (lobby == null) {
            System.out.println("Failed to join lobby 3 yse");
            return new Result(false, "Lobby not found.");
        }
        if (lobby.isFull()) {
            System.out.println("Failed to join lobby 4 yse");
            return new Result(false, "Lobby is full.");
        }
        if (lobby.isPrivate() && !lobby.checkPassword(password)) {
            System.out.println("Failed to join lobby 2 yse");
            return new Result(false, "Wrong password.");
        }
        User currentPlayer = getCurrentUser();
        if (lobby.addUser(currentPlayer)) {
            GameClient.getInstance().joinLobby(id, password);
            System.out.println("Joined lobby yse" + id);
            return new Result(true, "Joined lobby successfully.");
        } else {
            System.out.println("Failed to join lobby 1 yse");
            return new Result(false, "You are already in the lobby.");
        }
    }

    public List<LobbyData> loadRecentLobbies() {
        GameClient.getInstance().requestLobbyList(false);
        //System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee 3");
        return lobbies.stream()
            .filter(LobbyData::isVisible)
            .sorted(Comparator.comparingLong(LobbyData::getCreatedTime).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    public Result createLobby(int id, String name, boolean isPrivate, String password, boolean isVisible) {
        User admin = getCurrentUser();
        System.out.println(admin.getUsername());


        System.out.println("# # # # ## # # # # ## # # # # # ## # # # # # # ## ## # # # # # # # ##  ## *");
        LobbyData newLobby = new LobbyData(name, isPrivate, isVisible, password, id, admin);
        lobbies.add(newLobby);
        newLobby.addUser(admin);
        GameClient client = GameClient.getInstance();

        client.createLobby(name, isPrivate, password, isVisible, admin.getUsername(), id);

        return new Result(true, "Lobby created successfully with ID: " + id);
    }

    public Result deleteLobby(int id) {
        User currentUser = getCurrentUser();
        System.out.println(currentUser.getUsername());

        LobbyData lobbyToDelete = null;
        for (LobbyData lobby : lobbies) {
            if (lobby.getId() == id) {
                lobbyToDelete = lobby;
                break;
            }
        }

        if (lobbyToDelete == null) {
            return new Result(false, "Lobby not found with ID: " + id);
        }

        if (!lobbyToDelete.getAdmin().equals(currentUser)) {
            return new Result(false, "Only the admin can delete the lobby");
        }

        lobbies.remove(lobbyToDelete);

        GameClient client = GameClient.getInstance();
        client.deleteLobby(id);

        return new Result(true, "Lobby deleted successfully with ID: " + id);
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
        return lobbies.stream()
            .filter(l -> l.getId() == id)
            .findFirst()
            .orElse(null);
    }

    private User getCurrentUser() {
        return repo.getCurrentUser();
    }


    public void updateLobbyListFromNetwork(Network.LobbyInfo[] lobbyInfos, boolean isForOnlinePlayersList) {
        List<LobbyData> lobbyDataList = new ArrayList<>();

        for (Network.LobbyInfo info : lobbyInfos) {
            List<User> users = new ArrayList<>();
            for (String playerJson : info.playerNames) {
                System.out.println(Repository.fromUserInfoJson(playerJson).getUsername() + " hey ui");
                users.add(Repository.fromUserInfoJson(playerJson));
            }
            if (users.isEmpty()) {
                continue;
            }

            User admin = users.get(0);

            System.out.println(" & & & & & & &  & & & & 77 & & & 7 & 7&&&&& & && & & & & & & & & & ");
            LobbyData lobby = new LobbyData(
                info.name,
                info.isPrivate,
                info.isVisible,
                info.password,
                info.id,
                admin
            );

            for (int i = 1; i < users.size(); i++) {
                lobby.addUser(users.get(i));
            }

            lobbyDataList.add(lobby);
        }
        if (isForOnlinePlayersList) {
            setLobbyListForOnlinePlayers(lobbyDataList);
        } else {
            setLobbies(lobbyDataList);
        }
    }

    private void setLobbies(List<LobbyData> lobbyDataList) {
        this.lobbies = lobbyDataList;
    }

    public LobbyData searchLobbyById(String id) {
        for (LobbyData lobby : lobbies) {
            if (String.valueOf(lobby.getId()).equals(id)) {
                return lobby;
            }
        }
        return null;
    }


}
