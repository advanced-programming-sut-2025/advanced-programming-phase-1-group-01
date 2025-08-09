package com.stardew_valley.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.stardew_valley.controllers.GameMenuController;
import com.stardew_valley.controllers.LobbyController;
import com.stardew_valley.models.data.Repository;

import java.io.IOException;

public class GameClient {

    private static GameClient instance;
    private final Client pClient;
    private int playerId;

    public GameClient() {
        pClient = new Client(6553600, 6553600);
        Network.register(pClient);

        pClient.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                playerId = connection.getID();
                System.out.println("Connected with Id: " + playerId);

                Network.JsonMessage userInfoMsg = new Network.JsonMessage();
                userInfoMsg.type = "userInfo";
                userInfoMsg.json = Repository.getRepo().toUserInfoJson();
                pClient.sendTCP(userInfoMsg);
            }

            @Override
            public void received(com.esotericsoftware.kryonet.Connection connection, Object object) {
                if (object instanceof Network.CreateLobbyResponse resp) {
                    System.out.println("Create Lobby Response: " + resp.message);
                } else if (object instanceof Network.JoinLobbyResponse resp) {
                    System.out.println("Join Lobby Response: " + resp.message);
                } else if (object instanceof Network.LobbyListResponse resp) {
                    LobbyController.getInstance().updateLobbyListFromNetwork(resp.lobbies);
                }
            }

            @Override
            public void disconnected(com.esotericsoftware.kryonet.Connection connection) {
                System.out.println("Disconnected from the server");
            }
        });

        pClient.start();
    }

    public void connect(String ip) throws IOException {
        pClient.connect(5000, ip, Network.PORT);
    }

    public void createLobby(String name, boolean isPrivate, String password, boolean isVisible) {
        Network.CreateLobbyRequest req = new Network.CreateLobbyRequest();
        req.name = name;
        req.isPrivate = isPrivate;
        req.password = password;
        req.isVisible = isVisible;
        pClient.sendTCP(req);
    }

    public void joinLobby(int lobbyId, String password) {
        Network.JoinLobbyRequest req = new Network.JoinLobbyRequest();
        req.lobbyId = lobbyId;
        req.password = password;
        pClient.sendTCP(req);
    }

    public static synchronized GameClient getInstance() {
        if (instance == null) {
            instance = new GameClient();
        }
        return instance;
    }

    public void requestLobbyList() {
        Network.RequestLobbyList req = new Network.RequestLobbyList();
        pClient.sendTCP(req);
    }

}
